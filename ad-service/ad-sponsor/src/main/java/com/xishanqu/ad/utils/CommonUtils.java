package com.xishanqu.ad.utils;

import com.xishanqu.ad.exception.AdException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.time.DateUtils;

import java.util.Date;

/**
 * @Function:
 * @Author: BaoNing
 * @Date: 2019-04-13
 */
public class CommonUtils {

    /**
     * 获取字符串md5
     * @param value
     * @return
     */
    public static String md5(String value){
        return DigestUtils.md5Hex(value).toUpperCase();
    }

    /**
     * 将字符串转化时间格式
     * @param dateString
     * @return
     */
    public static String[] parsePatterns = {"yyyy-MM-dd","yyyy/MM/dd","yyyy.MM.dd"};
    public static Date parseStringDate(String dateString) throws AdException {
        try {
            return DateUtils.parseDate(dateString,parsePatterns);
        }catch (Exception ex){
            throw new AdException(ex.getMessage());
        }
    }


}
