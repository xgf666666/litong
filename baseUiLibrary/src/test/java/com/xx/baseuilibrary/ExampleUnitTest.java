package com.xx.baseuilibrary;

import com.blankj.utilcode.util.EncryptUtils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        String encryptMD5ToString = EncryptUtils.encryptMD5ToString(EncryptUtils.encryptMD5ToString("123456"+"mcjp").toLowerCase()).toLowerCase();
        System.out.println(encryptMD5ToString);
    }
}