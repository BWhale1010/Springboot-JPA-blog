package com.bw.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter
//@Setter
// @RequiredArgsConstructor // final 붙은 것들에 constructor 만들어줌

@Data // getter + setter
//@AllArgsConstructor // 생성자,  생성자 직접 작성 시 사용
@NoArgsConstructor
public class Member {
//	private final int id; // final -> 불변성 유지
	private  int id; 
	private  String username;
	private  String password;
	private  String email;
	
	@Builder // 빌더 패턴
	public Member(int id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	



	
}
