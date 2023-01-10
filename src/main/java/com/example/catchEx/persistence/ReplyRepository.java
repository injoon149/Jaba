package com.example.catchEx.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.catchEx.domain.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{

}
