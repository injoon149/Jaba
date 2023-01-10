package com.example.catchEx.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.catchEx.domain.Post;
import com.example.catchEx.domain.User;
import com.example.catchEx.dto.ResponseDTO;
import com.example.catchEx.service.PostService;

@Controller
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/post")
	public @ResponseBody ResponseDTO<?> insertPost(@RequestBody Post post, 
	//		@RequestHeader("session-id") String sessionId,
			HttpSession session) {
		// Post 객체를 영속화하기 전 연관된 User 엔티티 설정
		System.out.println(session);
		Object principal = session.getAttribute("principal");
		
		System.out.println(principal);
//		post.setUser(principal);
		
		//post.setCnt(0);

		postService.insertPost(post);

		return new ResponseDTO<>(HttpStatus.OK.value(), "새로운 포스트를 등록했습니다.");
	}

	
	 //@PostMapping("/put") public @ResponseBody ResponseDTO<?>
	 //insertPost_1(@RequestBody Post post, HttpSession session){
	 
	 
	 //postService.insertPost(post); return new ResponseDTO<>(HttpStatus.OK.value(),
	 //"새로운 포스트를 등록했습니다."); }
	 

	/*@GetMapping({ "", "/" })
	public @ResponseBody ResponseDTO<?> getPostList(Model model,
			@PageableDefault(size = 3, sort = "id", direction = Direction.DESC) Pageable pageable) {
		model.addAttribute("postList", postService.getPostList(pageable));
		return new ResponseDTO<>(HttpStatus.OK.value(), "포스트 목록 출력");
	} */

	/*
	 * @GetMapping("/post/insertPost") public String insertPost() { return
	 * "post/insertPost"; }
	 */

	/*@GetMapping("/post/{id}")
	public String getPost(@PathVariable int id, Model model) {
		model.addAttribute("post", postService.getPost(id));
		return "게시글 조회 완료";
	} */

	@PutMapping("/post")
	public @ResponseBody ResponseDTO<?> updatePost(@RequestBody Post post) {
		postService.updatePost(post);
		return new ResponseDTO<>(HttpStatus.OK.value(), post.getId() + "번 포스트를 수정했습니다.");
	}

	@DeleteMapping("/post/{id}")
	public @ResponseBody ResponseDTO<?> deletePost(@PathVariable int id) {
		postService.deletePost(id);
		return new ResponseDTO<>(HttpStatus.OK.value(), id + "번 포스트를 삭제했습니다.");
	}

}
