package com.ruoyi.common.utils;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Map通用处理方法
 * 
 * @author ruoyi
 */
public class ListDataUtil
{
    public static Object ListFindValue(List<?> list,String keyStr,String keyValue,String valueStr)
    {
    	if(null!=list && list.size()>0) {
    		String json =  JSONObject.toJSONString(list);
    		JSONArray array = JSONArray.parseArray(json);
    		for(int i=0;i<array.size();i++ ) {
    			if((array.getJSONObject(i).getString(keyStr)).equals(keyValue)) {
    				return array.getJSONObject(i).get(valueStr);
    			}
    		}
    	}
        return "";
    }

}
