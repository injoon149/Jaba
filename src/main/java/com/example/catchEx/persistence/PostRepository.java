package com.example.catchEx.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.catchEx.domain.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

}
