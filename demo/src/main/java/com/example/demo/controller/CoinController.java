package com.example.demo.controller;

import java.util.Optional;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Coin;
import com.example.demo.repository.CoinRepository;
import com.example.demo.service.CoinService;

@RestController
@RequestMapping("/api")
@SuppressWarnings("null")
public class CoinController extends BaseController {
	
	private Logger logger = LogManager.getLogger(CoinController.class);

	@Autowired
	CoinRepository coinRepository;

	@Autowired
	CoinService coinService;

	/**
	 * 5. 測試呼叫 coindesk API,並顯示其內容。
	 */
	@GetMapping("/demo5")
	public Object demo5(){
		try {
			return coinService.demo5();
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 6. 測試呼叫資料轉換的 API,並顯示其內容。
	 */
	@GetMapping("/demo6")
	public Object demo6(){
		try {
			return coinService.get();
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 3. 呼叫 coindesk 的 API,並進行資料轉換,組成新 API。
	 */
	
	@GetMapping("/download")
	public Object download(){
		try {
			return coinService.download();
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/get")
	public Object get(@RequestParam(required = false) String code) {
		try {
			return coinService.get();
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/get/{code}")
	public Object getById(@PathVariable("code") String code) {
		try {
			return coinService.getById(code);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/post")
	public Object post(@RequestBody Coin coin) {
		try {
			return coinService.post(coin);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/put/{code}")
	public Object put(@PathVariable("code") String code, @RequestBody Coin coin) {
		try {
			return coinService.put(code, coin);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/del")
	public Object delete() {
		try {
			coinRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/del/{code}")
	public Object deleteById(@PathVariable("code") String code) {
		try {
			coinRepository.deleteById(code);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return ResponseEntity.created(null).header("MyResponseHeader", "MyValue").body("Delete!");
		}
	}

}