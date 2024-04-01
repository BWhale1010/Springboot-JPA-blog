package com.bw.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//ORM -> Java(또는 다른 언어) Object -> 테이블로 매핑해주는 기술

@Data
@NoArgsConstructor // bean 생성자
@AllArgsConstructor // 전체 생성자
@Builder // 빌더 패턴
@Entity // User 클래스가 MySQL에 테이블이 생성이 된다.
// @DynamicInsert // insert시에 null 필드는 제외하고 쿼리 작성 
public class User {

	@Id //Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라감 -> 오라클은 시퀀스 MySQL은 autoincrement
	private int id; // 시퀀스, auto_increment
	
	@Column(nullable = false, length = 30, unique = true)
	private String username; // 아이디
	 
	@Column(nullable = false, length = 100) // 12345 => 해쉬(비밀번호 암호화)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	//@ColumnDefault("'user'") // "int" / " 'String' " (문자는 ' '로 감쌈)
	// private String role;
	@Enumerated(EnumType.STRING) // enum 타입이 String 임을 알려줌
	private RoleType role; // Enum을 쓰는게 좋다. / ADMIN, USER
	
	@CreationTimestamp // 시간이 자동 입력
	private Timestamp createDate;	
	
}
