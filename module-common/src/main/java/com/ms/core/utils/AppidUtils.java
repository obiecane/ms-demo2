package com.ms.core.utils;


import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author xzg
 * @version 1.0
 * @date 2019/9/3 14:24
 * @copyright 江西金磊科技发展有限公司 All rights reserved. Notice
 * 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */
@Slf4j
public class AppidUtils {


    public static String generate(String productName, Date year, Integer recordNumber) {
        String appid = "";
        appid = appid + categoryCode(productName);
        appid = appid + yearCode(year);
        appid = appid + versionCode(productName);
        appid = appid + recordNumberCode(recordNumber.toString());
        appid = appid + (String.valueOf((int) ((Math.random() * 9 + 1) * 100000)));
        return appid;
    }

    private static String categoryCode(String productName) {
        productName = productName.toLowerCase();
        if (productName.contains("jeecms")) {
            return "CMS";
        } else if (productName.contains("jspgou")) {
            return "GOU";
        }

        if (productName.length() > 2) {
            return productName.substring(0, 3).toUpperCase();
        } else if (productName.length() == 2) {
            return "0" + productName.substring(0, 2).toUpperCase();
        }
        productName = "00" + productName;
        return productName.substring(productName.length() - 3).toUpperCase();
    }

    private static String yearCode(Date year) {
        int code = year.getYear();
        code -= 119;
        char startCode = 'A';
        startCode += code;
        return String.valueOf(startCode);
    }

    private static String versionCode(String productName) {
        productName = "000" + productName;
        String substring = productName.substring(productName.length() - 4);

        try {
            Integer.valueOf(substring);
        } catch (NumberFormatException e) {
            substring = "0001";
        }
        return substring;
    }

    private static String recordNumberCode(String recordNumber) {
        recordNumber = "0000000" + recordNumber;
        recordNumber = recordNumber.substring(recordNumber.length() - 8);
        return recordNumber;
    }

}
