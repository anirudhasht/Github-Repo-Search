package com.github_repo_search.Github_Repo_Search.dto;

import jakarta.validation.constraints.NotBlank;

public class RepoSearchDTO {


    @NotBlank
    private String repoName;

    private String language;
    private String sortBy;

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }
//Getters And Setters


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
}
