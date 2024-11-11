package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Coin;

public interface CoinRepository extends JpaRepository<Coin, String> {
  List<Coin> findByChinese(String chinese);

  List<Coin> findByCode(String code);
}
