package com.sansheng.dd;

import java.io.*;
import java.sql.SQLOutput;
import java.util.*;
import java.security.*;
import java.math.BigInteger;

class ddsign
{
    //java md5算法
    public static String md5(String plainText)
    {
        byte[] secretBytes = null;
        try {
            MessageDigest md  = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes("utf-8"));
            secretBytes = md.digest();
        }
        catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException("no such algorithm！");
        }


        String md5code = new BigInteger(1, secretBytes).toString(16);

        int length = md5code.length();
        for (int i = 0; i < 32 - length; i++) {
            md5code = "0" + md5code;
        }
        System.out.println(md5code);
        return md5code;
    }

    public static String genSign2(HashMap<String, String> params, String signKey)
    {
        params.put("sign_key", signKey);
        String result = "";
        try {
            List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(params.entrySet());
            Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
                public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                    return (o1.getKey()).toString().compareTo(o2.getKey());
                }
            });
            // 构造签名键值对的格式
            for (Map.Entry<String, String> item : infoIds) {
                if (item.getKey() != null || item.getKey() != "") {
                    String key = item.getKey();
                    String val = item.getValue();
                    if (result == "") {
                        result += key + "=" + val;
                    } else {
                        result += "&" + key + "=" + val;
                    }
                }
            }
            //System.out.println(result);
        } catch(Exception e) {
            throw new RuntimeException("error");
        }
        System.out.println(result);
        return md5(result);
    }

    public static String genSign(String[] paramNames,String[] paramValues ,String signKey){
        HashMap<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < paramNames.length; i++) {
            map.put(paramNames[i], paramValues[i]);
        }
        String sign = genSign2(map, signKey);
        return sign;
    }


    public static void main (String[] args) throws java.lang.Exception
    {

        String[] nms=new String[]{"client_id","access_token","timestamp","company_id","data"};
        String[] values=new String[]{"253f35c96cfc23fe6c9c84081f4f3d8a","b4858b1f744638ec38149ec6899af97a7a5e1b1d","1618310704566","1125909966923849","{\"phone\":\"15166232651\",\"realname\":\"\\u6234\\u4e50\\u6625\",\"employee_number\":\"10100009\",\"cost_center_id\":\"\\u798f\\u5efa\\u4e09\\u76db\\u623f\\u5730\\u4ea7\\u5f00\\u53d1\\u6709\\u9650\\u516c\\u53f8\",\"budget_center_id\":\"1125910149862558\",\"regulation_id\":\"1125910135264990\"}"};
        String signKey = "f624F03Ceb23C7465431";
        String sign = genSign(nms,values, signKey);
        String xxx="\\\"";
        System.out.println(sign);
    }
}