package com.bw.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bw.blog.dto.ResponseDto;
import com.bw.blog.model.RoleType;
import com.bw.blog.model.User;
import com.bw.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController : save 호출됨");
		// 실제로 DB에 insert를 하고 아래에서 return이 됨
		user.setRole(RoleType.USER);
		int result = userService.회원가입(user);
		
		return new ResponseDto<Integer>(HttpStatus.OK, 1);
	}
}
