package com.example.catchEx.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor    //기본 생성자 생성
@AllArgsConstructor //모든 멤버변수 초기화하는 생성자 생성
@Builder
@Entity
@Table(name = "USERS")
public class User {
	
	//기본키에 대응하는 식별자 변수
	@Id
	
	//1부터 시작하여 자동으로 1씩 증가하도록 증가 전략 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int id;                          //회원 일련번호
	
	@Column(nullable = false, length = 50, unique = true)
	private String username;                 //회원 아이디
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(nullable = false, length = 100)
	private String email;
	
	@Enumerated(EnumType.STRING)
	private RoleType role;
	
	@CreationTimestamp   //현재 시간이 기본값으로 등록되도록 설정
	private Timestamp createDate;

}
