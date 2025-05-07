package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Quiz;
import com.example.demo.model.av_tipo2;

public interface av_tipo2Repository extends JpaRepository<av_tipo2, Long> {

    Optional<av_tipo2> findByquiz(Quiz quiz);
}
