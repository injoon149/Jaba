package com.example.catchEx.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "comment")
public class Reply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_idx")
	private int id;
	
	@Column(name = "comment_status", nullable = false)
	private boolean commentStatus;
	
	@Column(name = "content", nullable = false, length = 400)
	private String content;
	
	@UpdateTimestamp
	@Column(name = "updated_at")
	private Timestamp updated_at;
	
	
	@CreationTimestamp   //현재 시간이 기본값으로 등록되도록 설정
	@Column(name = "created_at")
	private Timestamp createDate;
	
	@Column(name = "status")
	private boolean status;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_idx")
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "post_idx")
	private Post post;
	

}
