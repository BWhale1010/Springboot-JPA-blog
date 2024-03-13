package com.bw.blog.test;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bw.blog.model.RoleType;
import com.bw.blog.model.User;
import com.bw.blog.repository.UserRepository;

import jakarta.transaction.Transactional;

// html 파일이 아니라 데이터를 리턴해주는 controller = RestController
@RestController
public class DummyControllerTest {

	@Autowired // 의존성 주입 (DI)
	private UserRepository userRepository;
	
	// save 함수는 id를 전달하지 않으면 insert 해주고
	// save 함수는 id를 전달하면 id에 대한 데이터가 있으면 update, id에 대한 데이터가 없으면 insert를 함 -> 대신 Transaction 사용이 유리
	//http://localhost:8000/blog/dummy/user/{id}
	// email, password
	@Transactional // 함수 종료 시에 자동 commit이 됨 // 더티 체킹
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) { // json 데이터를 요청 -> Java Object(MessageConvertor의 Jackson 라이브러리가 변환)
		System.out.println("id : " + id);
		System.out.println("password : " + requestUser.getPassword());
		System.out.println("email : " + requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());

		// userRepository.save(user); 
		
		return null;
	}
	
	// http://localhost:8000/blog/dummy/users
	@GetMapping("/dummy/users")
	public List<User> list(){
		
		return userRepository.findAll();
	}
	
	// http://localhost:8000/blog/dummy/user
	// 한 페이지 당 2건의 데이터를 리턴 받기
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable Pageable){
		Page<User> pagingUser = userRepository.findAll(Pageable);
		
		List<User> users = pagingUser.getContent();
		return users;
	}

	// {id} 주소로 파라미터를 전달
	// http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// user/n을 찾다가 데이터베이스에서 못 찾으면 user가 null이 됨

		// Optional로 User 객체를 감싸서 가져올테니 null인지 아닌지 판단 후 return
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
			}
		});
		
		// 람다식
//		User user = userRepository.findById(id).orElseThrow(()->{
//			return new IllegalArgumentException("해당 사용자는 없습니다.");
//		});

		// 요청 : 웹브라우저
		// user 객체 = 자바 오브젝트 
		// 변환 (웹브라우저가 이해할 수 있는 데이터) -> json (Gson 라이브러리)
		// 스프링부트 = MessageConvertor가 응답 시 자동 작동
		// 만약 자바 오브젝트를 리턴하게 되면 MessageConvertor가 Jackson이라는 라이브러리를 호출해서 
		// user 오브젝트를 json으로 변환해서 브라우저에 던져줌
		return user;
	}

	// http://localhost:8000/blog/dummy/join(요청)
	// http의 body에 username, password, email 데이터를 가지고 요청
	@PostMapping("/dummy/join")
//	public String join(String username, String password, String email) { // key = value (약속된 규칙)
//		System.out.println("username : " + username);
//		System.out.println("password : " + password);
//		System.out.println("email : " + email);

	public String join(User user) {
		System.out.println("id : " + user.getId());
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		System.out.println("role : " + user.getRole());
		System.out.println("createDate : " + user.getCreateDate());

		user.setRole(RoleType.USER);
		userRepository.save(user);

		return "회원가입이 완료되었습니다.";
	}
}
