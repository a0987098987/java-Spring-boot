package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Translate;

public interface TranslateRepository extends JpaRepository<Translate, String> {

}
