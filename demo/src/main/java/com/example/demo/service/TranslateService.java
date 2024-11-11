package com.example.demo.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Translate;
import com.example.demo.repository.TranslateRepository;

@Service
public class TranslateService {
	private Logger logger = LogManager.getLogger(TranslateService.class);

	@Autowired
	TranslateRepository translateRepository;
	
	public void init() throws Exception{
		try {
			List<Translate> optData = translateRepository.findAll();
			if (optData.size() == 0) {
				logger.info("optData.size() == 0");
				translateRepository.save(new Translate("USD", "美金"));
				translateRepository.save(new Translate("GBP", "英鎊"));
				translateRepository.save(new Translate("EUR", "歐元"));
			}
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}

}
