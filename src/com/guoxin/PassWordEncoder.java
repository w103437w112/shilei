package com.guoxin;


import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class PassWordEncoder {

    public static String aesEncrypt(String str, String key) throws Exception {
        if (str == null || key == null)
            return null;
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "AES"));
        byte[] bytes = cipher.doFinal(str.getBytes("utf-8"));        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String aesDecrypt(String str, String key) throws Exception {
        if (str == null || key == null)
            return null;
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "AES"));
        byte[] bytes = Base64.getDecoder().decode(str);
        bytes = cipher.doFinal(bytes);
        return new String(bytes, "utf-8");
    }

    public static void main(String[] args) throws Exception {
        System.out.println(PassWordEncoder.aesEncrypt("shilei","bc0eb9b55d5b468a"));
    }
}