package com.example.catchEx.controller;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.catchEx.domain.RoleType;
import com.example.catchEx.domain.User;
import com.example.catchEx.dto.ResponseDTO;
import com.example.catchEx.exception.JBlogException;
import com.example.catchEx.persistence.UserRepository;
import com.example.catchEx.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	/*
	 * @PostMapping("/user") public @ResponseBody String insertUser(@RequestBody
	 * User user) { user.setRole(RoleType.USER); userRepository.save(user); return
	 * user.getUsername() + "회원가입 성공";
	 * 
	 * }
	 */
	
	@GetMapping("/user/get/{id}")
	public @ResponseBody User getUser(@PathVariable int id) {
		//특정 id에 해당하는 User 객체 반환
		//검색된 회원이 없을 경우 예외 반환
		User findUser = userRepository.findById(id).orElseThrow(new Supplier<JBlogException>() {
			@Override
			public JBlogException get() {
				return new JBlogException(id + "번 회원이 없습니다.");
			}
			
		});
		return findUser;
		
	}
	
	@PutMapping("/user")
	public @ResponseBody String updateUser(@RequestBody User user) {
		User findUser = userRepository.findById(user.getId()).orElseThrow(() -> {
			return new JBlogException(user.getId() + "번 회원이 없습니다.");
		});
		findUser.setUsername(user.getUsername());
		findUser.setPassword(user.getPassword());
		findUser.setEmail(user.getEmail());
		
		userRepository.save(findUser);
		return "회원 수정 성공";
	}
	
	@DeleteMapping("/user/{id}")
	public @ResponseBody String deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);;
		return "회원 삭제 성공";
	}
	
	@GetMapping("/user/list")
	public @ResponseBody List<User> getUserList(){
		return userRepository.findAll();
		
	}
	
	@GetMapping("/user/page")
	public @ResponseBody Page<User> getUserListPaging(
		@PageableDefault(page = 0, size = 2, direction = Sort.Direction.DESC, sort = {"id", "username"})
		Pageable pageable) {
		//첫 번째 페이지에 해당하는 2개의 데이터 조회
		//id 내림차순 정렬
		return userRepository.findAll(pageable);

		}
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/auth/insertUser")
	public String insertUser() {
		return "user/insertUser";
	}
	
	@PostMapping("/auth/insertUser")
	public @ResponseBody ResponseDTO<?> insertUser(@RequestBody User user){
		User findUser = userService.getUser(user.getUsername());
		
		if(findUser.getUsername() == null) {
			userService.insertUser(user);
			return new ResponseDTO<>(HttpStatus.OK.value(),
					user.getUsername() + " 가입 성공.");
		} else {
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(),
					user.getUsername() + "님은 이미 회원입니다.");
		}
	}
	
	


}