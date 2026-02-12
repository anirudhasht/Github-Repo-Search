package com.github_repo_search.Github_Repo_Search.service;

import com.github_repo_search.Github_Repo_Search.dto.RepoSearchDTO;
import com.github_repo_search.Github_Repo_Search.entity.RepositoryEntity;
import com.github_repo_search.Github_Repo_Search.githubClient.GitHubClient;
import com.github_repo_search.Github_Repo_Search.repository.RepositoryRepo;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.List;
import java.time.OffsetDateTime;


@Service
public class RepositoryService {
    private final RepositoryRepo repo;
    private final GitHubClient client;

    public RepositoryService(RepositoryRepo repo,GitHubClient client){
        this.repo = repo;
        this.client=client;
    }

    public List<RepositoryEntity> searchRepository(RepoSearchDTO dto){
        String query=dto.getRepoName();

        if(dto.getLanguage()!=null){
            query+= "+language:"+dto.getLanguage();
        }
        String url=
                "https://api.github.com/search/repositories?q=" +
                        query +"&sort=" + dto.getSortBy() +
                "&order=desc";

        String responseRepos=client.searchRepositories(url);

        List<RepositoryEntity> allRepos = new ArrayList<>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(responseRepos);
            JsonNode items = root.get("items");

            for(JsonNode item : items){

                RepositoryEntity currRepo = new RepositoryEntity();

                currRepo.setName(item.get("name").asText());
                currRepo.setOwner(item.get("owner").get("login").asText());
                currRepo.setStars(item.get("stargazers_count").asInt());
                if(item.get("description") != null && !item.get("description").isNull()){
                    currRepo.setDescription(item.get("description").asText());
                }

                if(item.get("language") != null && !item.get("language").isNull()){
                    currRepo.setLanguage(item.get("language").asText());
                }
                String updatedAt = item.get("updated_at").asText();

                currRepo.setLastUpdated(
                        OffsetDateTime.parse(updatedAt).toLocalDateTime()
                );
                currRepo.setForks(item.get("forks_count").asInt());
                String repoUrl = item.get("html_url").asText();
                if(repo.existsByRepoUrl(repoUrl)){
                    continue;
                }
                currRepo.setRepoUrl(item.get("html_url").asText());

                allRepos.add(currRepo);
            }


            repo.saveAll(allRepos);

        } catch (Exception e){
            throw new RuntimeException("Error Occured While Parsing!!!!");
        }

        return allRepos;


    }
    public List<RepositoryEntity> getAllRepositories(String sortBy){

        if("stars".equals(sortBy)){
            return repo.findAll(Sort.by(Sort.Direction.DESC,"stars"));
        }

        if("forks".equals(sortBy)){
            return repo.findAll(Sort.by(Sort.Direction.DESC,"forks"));
        }

        return repo.findAll();
    }
}
