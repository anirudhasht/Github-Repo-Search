package com.github_repo_search.Github_Repo_Search.githubClient;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class GitHubClient {

    @Value("${github.token}")
    private String token;

    private final RestTemplate restTemplate = new RestTemplate();

    public String searchRepositories(String url) {

        HttpHeaders headers = new HttpHeaders();


        headers.set("Authorization", "Bearer " + token);


        headers.set("Accept", "application/vnd.github+json");

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        try {

            ResponseEntity<String> response =
                    restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            return response.getBody();

        } catch (HttpClientErrorException.Forbidden e) {


            throw new RuntimeException("Rate Limit Exceeded!!");

        } catch (Exception e) {

            throw new RuntimeException("GitHub API call failed!!!");
        }
    }
}
