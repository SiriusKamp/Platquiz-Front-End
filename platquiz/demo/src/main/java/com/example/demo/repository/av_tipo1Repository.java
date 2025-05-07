package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Quiz;
import com.example.demo.model.av_tipo1;

public interface av_tipo1Repository extends JpaRepository<av_tipo1, Long> {

    Optional<av_tipo1> findByquiz(Quiz quiz);

}
