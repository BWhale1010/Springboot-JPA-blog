package com.bw.blog.model;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량 데이터
	@Column(columnDefinition = "longtext") // lob 디폴트가 tinytext로 변경되어 추가 설정
	private String content; // 섬머노트 라이브러리 사용 -> <html>태그가 섞여서 디자인 됨

	@ColumnDefault("0")
	private int count; // 조회수
	
	@ManyToOne(fetch = FetchType.EAGER) // Many = Board, User = One, (fetch = FetchType.EAGER)(동기)는 user정보가 1개니까 조인하여 데이터를 바로 가져옴
	@JoinColumn(name = "userId")
	private User user; // DB는 오브젝트를 저장할 수 없다. (FK사용), 자바는 오브젝트를 저장할 수 있다.(ORM 사용)
	 
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // Reply 테이블에 있는  board 컬럼과 join하여 데이터를 가져옴, fetch 타입이 LAZY이면 데이터 필요 시에 조회(비동기)
	// mappedBy는 Reply 테이블에 있는 필드 이름 작성, mappedBy 연관관계의 주인이 아님(FK가 아님), DB에 컬럼 생성  X
	// @JoinColumn(name = "replyId") -> 이건(FK) 필요 없음, 키 생성 X, 테이블 생성 시 1정규화 위반, join만 해서 데이터만 가져올 것
	private List<Reply> reply;
	
	@CreationTimestamp // 데이터가 insert, update 시 시간을 자동 입력
	private Timestamp createDate;
}
