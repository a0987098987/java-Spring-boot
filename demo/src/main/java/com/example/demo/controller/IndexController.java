package com.example.demo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.TranslateService;


@Controller
public class IndexController {
 
	private Logger logger = LogManager.getLogger(IndexController.class);

	@Autowired
	TranslateService translateService;

	@GetMapping("/")
	public String index(){
		try {
			translateService.init();
		} catch (Exception e) {
			logger.error(e);
		}
		return "index";
	}
}
