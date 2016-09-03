
package com.iblogstreet.desrsaaes.utils;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * ClassName:MD5Utils <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date: 2016年7月6日 下午10:26:08 <br/>
 * 
 * @author Army
 * @version
 */
public class MD5Utils {
    private static FileInputStream fis;
    private static MessageDigest digest;

    /**
     * DESC :MD5加密实现 . <br/>
     * 
     * @param msg 字符串
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String Md5Digest(String msg) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("md5");
        StringBuilder sBuilder = new StringBuilder();
        byte[] result = digest.digest(msg.getBytes());
        for (byte b : result) {
            int k = b & 0xff;// 可以转换成整数，通过Integer.toHexString(k)可以转成16进制
            String hex = Integer.toHexString(k);
            if (Integer.toHexString(k).length() == 1)
                sBuilder.append("0");
            sBuilder.append(hex);
        }
        return sBuilder.toString();
    }

    /**
     * DESC : MD5加密实现 . <br/>
     * 
     * @param file 要加密的文件
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String Md5DigestFile(File file) {

        StringBuilder sBuilder = new StringBuilder();
        try {
            digest = MessageDigest.getInstance("md5");
            fis = new FileInputStream(file);
            int len = -1;
            byte[] buffer = new byte[1024];
            while ((len = fis.read(buffer)) != -1) {
                digest.update(buffer, 0, len);
            }

            for (byte b : digest.digest()) {
                int number = b & 0xff;
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    sBuilder.append("0");
                }
                sBuilder.append(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            StreamUtils.closeStream(fis);
        }

        return sBuilder.toString();
    }

    /**
     * DESC : MD5加密实现 . <br/>
     * 
     * @param path 要加密的文件的路径
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String Md5DigestPath(String path) {

        StringBuilder sBuilder = new StringBuilder();
        try {
            digest = MessageDigest.getInstance("md5");
            fis = new FileInputStream(new File(path));
            int len = -1;
            byte[] buffer = new byte[1024];
            while ((len = fis.read(buffer)) != -1) {
                digest.update(buffer, 0, len);
            }

            for (byte b : digest.digest()) {
                int number = b & 0xff;
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    sBuilder.append("0");
                }
                sBuilder.append(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            StreamUtils.closeStream(fis);
        }

        return sBuilder.toString();
    }
}
