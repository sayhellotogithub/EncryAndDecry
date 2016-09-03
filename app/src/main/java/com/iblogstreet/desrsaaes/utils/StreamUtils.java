package com.iblogstreet.desrsaaes.utils;

import java.io.Closeable;
import java.io.IOException;

/*
 *  @项目名：  DesRsaAes 
 *  @包名：    com.iblogstreet.desrsaaes.utils
 *  @文件名:   StreamUtils
 *  @创建者:   Army
 *  @创建时间:  2016/9/2 21:29
 *  @描述：    TODO
 */
public class StreamUtils {
    private static final String TAG = "StreamUtils";
    /**
     * DESC :用于关闭流 . <br/>
     *
     * @param iStream
     */
    public static void closeStream(Closeable iStream) {
        if (iStream != null)
            try {
                iStream.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }
}
