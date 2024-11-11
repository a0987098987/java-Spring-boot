package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.JsonObject;

@Entity
@Table
public class Translate extends BaseEntity {
	@Id
	@Column
	private String code;

	@Column
	private String chinese;

	public Translate() {
	}
	public Translate(JsonObject jObj) {
		this.code 			= toString(jObj, "code"		);
		this.chinese 		= toString(jObj, "chinese"	);
	}
	public Translate(String code, String chinese) {
		this.code = code;
		this.chinese = chinese;
	}

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

}
