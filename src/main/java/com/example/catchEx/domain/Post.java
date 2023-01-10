package com.example.catchEx.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "post")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_idx", nullable = false)
	private int id;
	
	@Column(name = "title", nullable = false, length = 100)
	private String title;
	
	//@Column(name = "user_idx", nullable = false)
	//private int useridx;
	
	@Lob
	@Column(name = "content", nullable = false)
	private String content;
	
	@Nullable
	@UpdateTimestamp
	@Column(name = "updated_at", nullable = true)
	private Timestamp updated_at;
	
	
	@Nullable
	@CreationTimestamp   //현재 시간이 기본값으로 등록되도록 설정
	@Column(name = "created_at", nullable = true)
	private Timestamp created_at;
	
	@Column(name = "status", nullable = true)
	private boolean status;
	
	//private int cnt;
	
	
	@ManyToOne(fetch = FetchType.EAGER) 
	@JoinColumn(name = "user_idx")
	private User user; 
	
	//양방향 매핑 - 해당 포스트에 속한 댓글 목록도 같이 출력되도록 하기 위함
	@OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
	@OrderBy("id desc")
	private List<Reply> replyList;	

}
