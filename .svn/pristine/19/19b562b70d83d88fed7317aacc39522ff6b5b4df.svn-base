package com.ruoyi.common.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.PictureData;

/**
 * Map通用处理方法
 * 
 * @author ruoyi
 */
public class MapDataUtil
{
    public static Map<String, Object> convertDataMap(HttpServletRequest request)
    {
        Map<String, String[]> properties = request.getParameterMap();
        Map<String, Object> returnMap = new HashMap<String, Object>();
        Iterator<?> entries = properties.entrySet().iterator();
        Map.Entry<?, ?> entry;
        String name = "";
        String value = "";
        while (entries.hasNext())
        {
            entry = (Entry<?, ?>) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if (null == valueObj)
            {
                value = "";
            }
            else if (valueObj instanceof String[])
            {
                String[] values = (String[]) valueObj;
                for (int i = 0; i < values.length; i++)
                {
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
            }
            else
            {
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return returnMap;
    }
    

	/**
	 * 从map中查询想要的map项，根据key模糊匹配
	 */
	public static Map<String, PictureData> parseMapForFilter(Map<String, PictureData> map,String filters) {
	    if (map == null) {
	        return null;
	    } else {
	        map = map.entrySet().stream()
	                .filter((e) -> checkKey(e.getKey(),filters))
	                .collect(Collectors.toMap(
	                        (e) -> (String) e.getKey(),
	                        (e) -> e.getValue()
	                ));
	    }
	    return map;
	}
	 
	/**
	 * 通过indexof匹配想要查询的字符
	 */
	private static boolean checkKey(String key,String filters) {
	    if (key.indexOf(filters)>-1){
	        return true;
	    }else {
	        return false;
	    }
	}

}
