package com.bw.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bw.blog.model.User;

// DAO
// 자동으로 bean 등록
// @ Repository 생략 가능
public interface UserRepository extends JpaRepository<User, Integer> {

}
