package com.bw.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bw.blog.model.Board;
import com.bw.blog.model.User;
import java.util.List;



public interface BoardRepository extends JpaRepository<Board, Integer> {

	

}
