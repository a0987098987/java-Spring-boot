package com.example.demo.service;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.Coin;
import com.example.demo.entity.Translate;
import com.example.demo.repository.CoinRepository;
import com.example.demo.repository.TranslateRepository;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;

@Service
public class CoinService extends BaseService{
	private Logger logger = LogManager.getLogger(CoinService.class);
	private final String API_URL = "https://api.coindesk.com/v1/bpi/currentprice.json";
	private RestTemplate restTemplate = new RestTemplate();

	@Autowired
	CoinRepository coinRepository;

	@Autowired
	TranslateRepository translateRepository;
	
	public Object demo5() throws Exception{
		try {
			String res = restTemplate.getForObject(API_URL, String.class);
			logger.info(toGsonPrint(res));
			Object rtn = gson.fromJson(res, Object.class);
			return rtn;
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}

	public Object download() throws Exception{
		try {
			String res = restTemplate.getForObject(API_URL, String.class);
			logger.info(toGsonPrint(res));
			try {
				JsonObject jObj = gson.fromJson(res, JsonObject.class);
				JsonElement updatedISO = jObj.getAsJsonObject("time").get("updatedISO");
				String updateTime = toDate(updatedISO);

				JsonObject bpi = jObj.getAsJsonObject("bpi");

				for (Entry<String, JsonElement> entry : bpi.entrySet()) {
					JsonObject bpiObj = bpi.getAsJsonObject(entry.getKey());
					logger.info(bpiObj);

					Coin vo = new Coin(bpiObj);
					String chinese = "";
					Optional<Translate> tranVo = translateRepository.findById(vo.getCode());
					if (tranVo.isPresent()) {
						chinese = tranVo.get().getChinese();
					}

					vo.setChinese(chinese);
					vo.setUpdated(updateTime);
					vo = coinRepository.save(vo);
				}
			} catch (Exception e) {
				logger.error(e);
				throw new Exception(e);
			}
			
			List<Coin> rtn = new ArrayList<Coin>();
			coinRepository.findAll().forEach(rtn::add);
			return rtn;
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}

	public Object get() throws Exception {
		return get(null);
	}
	public Object get(String code) throws Exception {
		try {
			List<Coin> rtn = new ArrayList<Coin>();

			if (code == null)
				coinRepository.findAll().forEach(rtn::add);
			else
				coinRepository.findByCode(code).forEach(rtn::add);

			if (rtn.size() == 0) {
				return responseEntityMsg("查無資料");
			}
			return rtn;
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}

	public Object getById(String code) throws Exception {
		try {
			Optional<Coin> optData = coinRepository.findById(code);
	
			if (optData.isPresent()) {
				return new ResponseEntity<>(optData.get(), HttpStatus.OK);
			} else {
				return responseEntityMsg("查無資料");
			}
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}

	public Object post(Coin coin) throws Exception {
		try {
			Coin vo = coinRepository
					.save(new Coin(coin.getCode(), coin.getChinese(), coin.getRate_float(), coin.getUpdated()));
			return new ResponseEntity<>(vo, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}

	public Object put(String code, Coin coin) throws Exception {
		try {
			Optional<Coin> optData = coinRepository.findById(code);
	
			if (optData.isPresent()) {
				Coin vo = optData.get();
				vo.setCode(			coin.getCode()			);
				vo.setChinese(		coin.getChinese()		);
				vo.setRate_float(	coin.getRate_float()	);
				vo.setUpdated(		coin.getUpdated()		);
				return new ResponseEntity<>(coinRepository.save(vo), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}


}
