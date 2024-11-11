package com.example.demo.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.JsonObject;

public class BaseEntity {
	private Logger logger = LogManager.getLogger(BaseEntity.class);

	protected float toFloat(JsonObject jsonObject, String memberName) {
		float rtn = 0;
		if ( jsonObject.has(memberName)) {
			rtn = jsonObject.get(memberName).getAsFloat();
		}
		return rtn;
	}

	protected String toString(JsonObject jsonObject, String memberName) {
		String rtn = null;
		if ( jsonObject.has(memberName)) {
			rtn = jsonObject.get(memberName).getAsString();
		}
		return rtn;
	}

}
