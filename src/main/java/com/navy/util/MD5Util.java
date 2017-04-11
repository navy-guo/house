package com.navy.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5进行加密的组件
 * @author Wangsf
 *
 */
public final class MD5Util {

    /**
     * 私有構造方法
     */
    private MD5Util() {

    }
    /**
     * 獲取字符串MD5碼，系統默認編碼格式
     * 
     * @param source
     * @return
     */
    public static String getMD5(String source) {
        return MD5Util.getMD5(source.getBytes());
    }

    /**
     * 獲取字符串MD5碼，指定編碼格式
     * 
     * @param source
     * @param charset
     * @return
     */
    public static String getMD5(String source, String charset) {
        String output = null;
        try {
            output = MD5Util.getMD5(source.getBytes(charset));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return output;
    }

    /**
     * 獲取字節數組MD5碼
     * 
     * @param in
     * @return
     */
    public static String getMD5(byte[] in) {
        String result = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            StringBuilder sbu = new StringBuilder();
            byte[] out = md5.digest(in);
            for (byte b : out) {
                int o = b & 0xFF;
                if (o < 16) {
                    sbu.append("0");
                }
                sbu.append(Integer.toHexString(o));
            }
            result = sbu.toString().toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

}

