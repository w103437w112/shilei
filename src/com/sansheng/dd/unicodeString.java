package com.sansheng.dd;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class unicodeString {


    public static String stringToUnicode(String string) {
        StringBuffer unicode = new StringBuffer();

        for (int i = 0; i < string.length(); i++) {

            // 取出每一个字符
            char c = string.charAt(i);

            // 转换为unicode
            unicode.append(String.format("\\u%04x", Integer.valueOf(c)));
        }

        return unicode.toString();
    }


    public static String unicodeToString(String unicode) {
        StringBuffer string = new StringBuffer();

        String[] hex = unicode.split("\\\\u");

        for (int i = 1; i < hex.length; i++) {

            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);

            // 追加成string
            string.append((char) data);
        }

        return string.toString();
    }

    public static void main(String[] args) {
        String un = stringToUnicode("上海吉盛实业发展有限公司");
        System.out.println( un);
        String name = unicodeToString(un);
        System.out.println( name);
    }

}
