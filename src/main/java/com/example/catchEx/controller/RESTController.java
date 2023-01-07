
  package com.example.catchEx.controller;
  
  import org.springframework.web.bind.annotation.*; import
  com.example.catchEx.domain.User; import
  org.springframework.web.bind.annotation.RestController;
  
  
  @RestController public class RESTController {
  
  //GET: SELECT
  
  @GetMapping("/jblog") public User httpGet() { User findUser = User.builder()
  .id(1) .username("gurum") .password("222") .email("gurum@gmail.com")
  .build(); return findUser; }
  
  @PostMapping("/jblog") public String httpPost(@RequestBody User user) {
  return "Post 요청 처리 입력값: " + user.toString(); }
  
  @PutMapping("/jblog") public String httpPut(@RequestBody User user) { return
  "PUT 요청 처리 입력값: " + user.toString(); }
  
  @DeleteMapping("/jblog") public String httpDelete(@RequestParam int id) {
  return "DELETE 요청 처리 입력값: " + id; }
 
  }
 