package com.example.catchEx.domain;

import java.sql.Timestamp;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor    //기본 생성자 생성
@AllArgsConstructor //모든 멤버변수 초기화하는 생성자 생성
@Builder
@Entity
@Table(name = "user")
public class User {
	
	
	//기본키에 대응하는 식별자 변수
	@Id	
	//1부터 시작하여 자동으로 1씩 증가하도록 증가 전략 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_idx")
	private int id;                          //회원 일련번호
	
	@Column(name = "user_nickname", nullable = false, length = 45, unique = true)
	@NotNull(message = "닉네임은 필수 입력 값입니다.")
	@Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.")
	private String username;                 //회원 닉네임
	
	@Column(name = "user_email", nullable = false, length = 45, unique = true)
	@NotNull(message = "아이디, 비밀번호를 확인해 주세요.")
	private String email;
	
	@Column(name = "user_phone", nullable = false, length = 45)
	private String user_phone;
	
	@Column(name = "user_pwd", nullable = false, length = 45)
    @NotNull(message = "아이디, 비밀번호를 확인해 주세요.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
	private String user_pwd;
	
	
	@Column(name = "user_sex", nullable = false)
	@NotNull(message = "성별은 필수 입력 값입니다.")
	private boolean user_sex;
	
	@Column(name = "other_phone", nullable = true)
	private String other_phone;
	
	@Column(name = "isMatch", nullable = true)
	private boolean isMatch;
	
	@Nullable
	@UpdateTimestamp
	@Column(name = "updated_at", nullable = true)
	private Timestamp updated_at;
	
	
	@Nullable
	@CreationTimestamp   //현재 시간이 기본값으로 등록되도록 설정
	@Column(name = "created_at", nullable = true)
	private Timestamp createDate;
	
	@Column(name = "status", nullable = true)
	private boolean status;
}
