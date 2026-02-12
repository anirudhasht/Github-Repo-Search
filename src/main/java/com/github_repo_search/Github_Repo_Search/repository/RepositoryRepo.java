package com.github_repo_search.Github_Repo_Search.repository;

import com.github_repo_search.Github_Repo_Search.entity.RepositoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryRepo extends JpaRepository<RepositoryEntity,Long> {
            boolean existsByRepoUrl(String repoUrl);


}
