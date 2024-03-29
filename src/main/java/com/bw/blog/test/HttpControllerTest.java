package com.bw.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 사용자가 요청 -> 응답(HTML 파일)
// @Controller

// 사용자가 요청 -> 응답(Data)
@RestController
public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest : ";

//	@GetMapping("/http/lombok")
//	public String lombokTest() {
//		Member m =  new Member(1, "sarr", "1234", "sarr@nate.com");
//		System.out.println(TAG + "getter : "+m.getId());
//		m.setId(5000);
//		System.out.println(TAG + "setter : "+m.getId());
//		
//		return "lombok test 완료";
//	}
	
	// builder 패턴 시
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m =  Member.builder().username("sarr").password("1234").email("sarr@nate.com").build(); // 빌더 패턴 사용 방법 / 장점: 생성자 순서를 안 지켜도 됨 
		
		System.out.println(TAG + "getter : "+m.getUsername());
		m.setUsername("bw");
		System.out.println(TAG + "setter : "+m.getUsername());
		
		return "lombok test 완료";
	}
	
	// 인터넷 브라우저 요청은 무조건 get 요청밖에 할 수 없다.
	// localhost:8080/http/get -> (select)
	@GetMapping("/http/get")
	public String getTest(Member m) { //id=1&username=sarr&password=1234&email=sarr@nate.com(쿼리스트링 방식) // MessageConverter (스프링부트)
		
		return "get 요청 : "+ m.getId() + ", "+m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
	}
	
	// localhost:8080/http/post -> (insert)
	@PostMapping("/http/post") // text/plain, application/json
	public String postTest(@RequestBody Member m) { // MessageConverter (스프링부트)
		return "post 요청 : " + m.getId() + ", "+m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
	}
	
	// localhost:8080/http/put -> (update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청 : " + m.getId() + ", "+m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
	}
	
	// localhost:8080/http/delete -> (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}
