package com.sdv.kit.blogserver.repository;

import com.sdv.kit.blogserver.model.Post;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "user")
    @Override
    List<Post> findAll();

    @Query("select p from Post p join fetch p.user u where u.username = ?1")
    List<Post> findAllByUser(String username);
}
