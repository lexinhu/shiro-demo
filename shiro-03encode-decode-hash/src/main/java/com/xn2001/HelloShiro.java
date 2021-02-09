package com.xn2001;

import com.xn2001.tools.DigestsUtil;
import org.junit.Test;

import java.util.Map;

/**
 * @author 乐心湖
 * @date 2021/2/8 19:13
 **/
public class HelloShiro {
    @Test
    public void DigestsUtilTest() {
        Map<String, String> admin = DigestsUtil.encryptPassword("123");
        admin.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}
