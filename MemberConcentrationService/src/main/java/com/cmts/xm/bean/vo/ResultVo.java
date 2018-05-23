package com.cmts.xm.bean.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 返回结果vo 父类
 * @author wdl
 *
 */
public class ResultVo {
	/**
	 * 错误编码
	 */
	private String resultcode;
	/**
	 * 错误信息
	 */
	private String resultmsg;
	/**
	 * 返回自定义参数
	 */
	private Map<String,Object> resultMap =new HashMap<String,Object>();
	
	public String getResultcode() {
		return resultcode;
	}
	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}
	public String getResultmsg() {
		return resultmsg;
	}
	public void setResultmsg(String resultmsg) {
		this.resultmsg = resultmsg;
	}
	public Map<String, Object> getResultMap() {
		return resultMap;
	}
	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	
	public String toChangeResultJson(){
		JSONObject resultObject = new JSONObject();
		resultObject.put("resultcode",resultcode);
		resultObject.put("resultmsg",resultmsg);
		for(String key:resultMap.keySet()){
			Object result= resultMap.get(key);
			if(ArrayList.class.isAssignableFrom(result.getClass())){
				JSONArray array = new JSONArray();
				ArrayList tempList = (ArrayList)result;
				for(Object object:tempList){
					array.add(object);
				}
				resultObject.put(key,array);
			}else{
				resultObject.put(key,resultMap.get(key));
			}
		}
		
		return resultObject.toString();
	}
	
	public static void main(String[] args) {
		ResultVo vo = new ResultVo();
		vo.setResultcode("1");
		vo.setResultmsg("2");
		List<ResultVo> list = new ArrayList<ResultVo>();
		ResultVo a = new ResultVo();
		a.setResultcode("2");
		a.setResultmsg("3");
		ResultVo b = new ResultVo();
		b.setResultcode("4");
		b.setResultmsg("5");
		list.add(a);
		list.add(b);
		vo.getResultMap().put("aa", list);
		System.out.println(vo.toChangeResultJson());
	}
	@Override
	public String toString() {
		return "ResultVo [resultcode=" + resultcode + ", resultmsg="
				+ resultmsg + ", resultMap=" + resultMap + "]";
	}
	

}
