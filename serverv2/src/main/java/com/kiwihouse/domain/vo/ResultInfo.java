package com.kiwihouse.domain.vo;

import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName: ResultInfo 
 * @Description: TODO
 * @author mlh
 * @date 2017年5月8日 下午4:43:49 
 * @Company 广州积雨云科技有限公司
 */
public class ResultInfo  {

	private static final long serialVersionUID = -7879597014511213651L;

	/**
	 * 状态
	 */
	private String status;
     /**
	 * 数据
	 */
	private Object data;


	/**
	 * 消息
	 */
	private String message;
	/**
	 * 消息编码
	 */
	private String  code;
	


	public ResultInfo() {

	}

	public ResultInfo(String status, Object data,String message,String code) {
		super();
		this.status = status;
		this.data = data;
		this.message = message;
		this.code = code;
	}

	/**
	 * 描述：产生一个成功不带参数的结果
	 * @author wufs@miugo.net
	 * @return
	 */
	public static ResultInfo genSuccessResultInfo() {
		return new ResultInfo("SUCCESS", null,null,"10000");
	}

	/**
	 *
	 * 描述：产生一个成功带参数的结果
	 * @author wufs@miugo.net
	 * @return
	 */
	public static ResultInfo genSuccessResultInfo(Object data,String message,String code) {
		return new ResultInfo("SUCCESS", data,message,code);
	}
	
	/**
	 *
	 * 描述：产生一个成功带参数的结果
	 * @author wufs@miugo.net
	 * @return
	 */
	public static ResultInfo genSuccessResultInfo(Object data,String message) {
		return new ResultInfo("SUCCESS", data,message,"10000");
	}
	/**
	 *
	 * 描述：产生一个失败不带参数的结果
	 * @author wufs@miugo.net
	 * @return
	 */
	public static ResultInfo genErrorResultInfo() {
		return new ResultInfo("ERROR", null,null,null);
	}
	/**
	 *
	 * 描述：产生一个失败带参数的结果
	 * @author wufs@miugo.net
	 * @return
	 */
	public static ResultInfo genErrorResultInfo(Object data,String message,String code) {
		return new ResultInfo("ERROR", data,message,code);
	}

	/**
	 * 获取 data
	 *
	 * @return 返回 data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * 设置 data
	 *
	 * @param对data进行赋值
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * 获取 status
	 *
	 * @return 返回 status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 设置 status
	 *
	 * @param对status进行赋值
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 获取 message
	 * @return 返回 message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * 设置 message
	 * @param对message进行赋值
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);

	}
}
