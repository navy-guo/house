package com.navy.util;

public class JsonResult {
	/**
	 * 失败[0], 成功[1]
	 */
	private Integer status; // 是否成功
	private String message; // 返回消息
	private Object result; // 返回结果
	
	/**
	 * 成功状态
	 */
	public final static Integer STATUS_SUCCESS = 1;
	/**
	 * 失败状态
	 */
	public final static Integer STATUS_FAIL = 0;

	
	/**
	 * 构造函数
	 */
	public JsonResult(Integer status) {
		this.status = status;
		this.message = "SUCCESS";
	}

	public JsonResult(Integer status, String message) {
		this.status = status;
		this.message = message;
	}

	public JsonResult(Integer status, Object result) {
		this.status = status;
		this.result = result;
	}

	public JsonResult(Integer status, String message, Object result) {
		this.status = status;
		if(message == null){
			this.message = "SUCCESS";
		} else {
			this.message = message;
		}
		this.result = result;
	}

	public Integer getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public Object getResult() {
		return result;
	}
}
