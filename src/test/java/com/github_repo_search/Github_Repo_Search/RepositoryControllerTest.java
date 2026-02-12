
package com.github_repo_search.Github_Repo_Search;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github_repo_search.Github_Repo_Search.controller.RepositoryController;
import com.github_repo_search.Github_Repo_Search.dto.RepoSearchDTO;
import com.github_repo_search.Github_Repo_Search.entity.RepositoryEntity;
import com.github_repo_search.Github_Repo_Search.repository.RepositoryRepo;
import com.github_repo_search.Github_Repo_Search.service.RepositoryService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RepositoryController.class)
class RepositoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RepositoryService service;


    @MockBean
    private RepositoryRepo repositoryRepo;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnRepositoriesSuccessfully() throws Exception {

        RepositoryEntity repo = new RepositoryEntity();
        repo.setName("DSA");
        repo.setOwner("Anirudh");

        Mockito.when(service.searchRepository(Mockito.any()))
                .thenReturn(List.of(repo));

        RepoSearchDTO dto = new RepoSearchDTO();
        dto.setRepoName("DSA");
        dto.setLanguage("Java");
        dto.setSortBy("stars");

        mockMvc.perform(post("/api/github/search").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.data[0].name").value("DSA"));
    }
}