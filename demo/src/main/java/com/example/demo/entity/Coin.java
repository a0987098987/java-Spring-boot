package com.example.demo.entity;

import com.google.gson.JsonObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Coin extends BaseEntity {
	@Id
	@Column
	private String code;
	
	@Column
	private String chinese;

	@Column
	private float rate_float;

	
	@Column
	private String updated;

	public Coin() {
	}
	public Coin(JsonObject jObj) {
		this.code 			= toString(jObj, "code"		);
		this.chinese 		= toString(jObj, "chinese"	);
		this.rate_float 	= toFloat( jObj, "rate_float");
		this.updated 		= toString(jObj, "updated"	);
	}
	public Coin(String code, String chinese, float rate_float, String updated) {
		this.code 		= code;
		this.chinese 	= chinese;
		this.rate_float = rate_float;
		this.updated 	= updated;
	}

//	public String getInsertLog() {
//		return "INSERT INTO Coin VALUES('" + code + "', '" + chinese + "', '" + rate_float
//				+ "', '" + updated + "')";
//	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getChinese() {
		return chinese;
	}
	public void setChinese(String chinese) {
		this.chinese = chinese;
	}

	public float getRate_float() {
		return rate_float;
	}
	public void setRate_float(float rate_float) {
		this.rate_float = rate_float;
	}

	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}

}
