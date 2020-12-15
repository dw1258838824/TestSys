package com.testsys.common;

import java.time.LocalTime;
import java.util.Random;

public class RandomUtils {

    /**
     * 生成一个准考证号
     * @return
     */
    public static String createExamCode(){
        //随机6位数字+当前时间时分秒毫秒
        return new Random().nextInt(999999) + "" + LocalTime.now().toString()
                .replaceAll("T", "")
                .replaceAll("-", "")
                .replaceAll(":", "")
                .replaceAll("\\.", "");
    }

}
