package org.pf9.pangu.framework.auth.security;

import org.apache.commons.logging.Log;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * Created by qiulin on 2017/6/1.
 */
public class MD5PasswordEncoder implements PasswordEncoder {
    private static final String HEX_NUMS_STR = "0123456789ABCDEF";
    private final Log logger;

    public MD5PasswordEncoder() {
        logger = null;
    }

    /**
     * 将16进制字符串转换成字节数组
     *
     * @param hex
     * @return
     */
    public static byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] hexChars = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (HEX_NUMS_STR.indexOf(hexChars[pos]) << 4
                    | HEX_NUMS_STR.indexOf(hexChars[pos + 1]));
        }
        return result;
    }


    /**
     * 将指定byte数组转换成16进制字符串
     *
     * @param b
     * @return
     */
    public static String byteToHexString(byte[] b) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            hexString.append(hex.toLowerCase());
        }
        return hexString.toString();
    }

    /**
     * 验证口令是否合法
     *
     * @param password
     * @param passwordInDb
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    @Override
    public boolean matches(CharSequence password, String passwordInDb) {
        if (password != null && password.length() != 0 && null != passwordInDb && passwordInDb.length() != 0) {
            if (encode(password).equals(passwordInDb)) {
                return true;
            } else {
                this.logger.warn("Encoded password does not look like MD5");
                return false;
            }
        } else {
            this.logger.warn("Empty encoded password");
            return false;
        }
    }


    /**
     * 获得加密后的16进制形式口令
     *
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    @Override
    public String encode(CharSequence password) {
        MessageDigest md = null;
        try {
            //创建消息摘要
            md = MessageDigest.getInstance("MD5");
            //将口令的数据传给消息摘要对象
            md.update(password.toString().getBytes("UTF-8"));
            //获得消息摘要的字节数组
            byte[] digest = md.digest();

            return byteToHexString(digest);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

}
