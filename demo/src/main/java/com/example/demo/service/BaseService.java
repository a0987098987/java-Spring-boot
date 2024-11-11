package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Service
public class BaseService {
	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	protected ResponseEntity<?> responseEntityMsg(String str){
		ArrayList<String> list = new ArrayList<>();
		list.add(str);
		return ResponseEntity.ok(list);
	}

	protected String toDate(JsonElement jEle){
		String rtn = null;
		if (!jEle.isJsonNull()) {
			rtn = jEle.getAsString();
			LocalDateTime date = LocalDateTime.parse(rtn, DateTimeFormatter.ISO_DATE_TIME);
			rtn = date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
		}
		return rtn;
	}


	protected String toGsonPrint(String json){
		JsonObject jObj = gson.fromJson(json, JsonObject.class);
		String rtn = gson.toJson(jObj);
		return rtn;
	}


}
