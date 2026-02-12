package com.github_repo_search.Github_Repo_Search.controller;

import com.github_repo_search.Github_Repo_Search.dto.RepoSearchDTO;
import com.github_repo_search.Github_Repo_Search.entity.RepositoryEntity;
import com.github_repo_search.Github_Repo_Search.repository.RepositoryRepo;
import com.github_repo_search.Github_Repo_Search.service.RepositoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/github")
public class RepositoryController {

    private final RepositoryService service;
    private final RepositoryRepo repo;
    RepositoryController(RepositoryService service,RepositoryRepo repo){
        this.service=service;
        this.repo=repo;
    }
    //This API Searches All The  Github Repositories Based on the Query,
    // Language And sorts based on the Number of Forks,Stars

    @PostMapping("/search")
    public ResponseEntity<?> searchRepo(@Valid @RequestBody RepoSearchDTO dto){

        List<RepositoryEntity> repos = service.searchRepository(dto);
        return ResponseEntity.ok(
                Map.of(
                        "status", "success",
                        "message", "Repositories Fetched And Saved Successfully",
                        "count", repos.size(),
                        "data", repos
                )
        );

    }


    // This API Returns The List of All the Repositories Stored in DataBase

    @GetMapping("/repositories")
    public List<RepositoryEntity> getAllRepositories(
            @RequestParam(required = false) String sortBy){

       return service.getAllRepositories(sortBy);
    }


}
