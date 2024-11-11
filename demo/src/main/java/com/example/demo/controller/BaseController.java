package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {
    
	protected ResponseEntity<?> responseEntityMsg(String str){
		ArrayList<String> list = new ArrayList<>();
		list.add(str);
		return ResponseEntity.ok(list);
	}

}
