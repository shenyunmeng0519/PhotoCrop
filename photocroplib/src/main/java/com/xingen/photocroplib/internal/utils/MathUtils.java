package com.xingen.photocroplib.internal.utils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * @author HeXinGen
 * date 2018/12/17.
 */
public class MathUtils {

    /**
     * 生成随机数纯数字
     * @param length  // 随机数的长度
     * @return
     */
    public static String getRandom(int length) {
        Random random = new Random();
        String rr = "";
        Set set = new HashSet();
        while (set.size() < length) {
            set.add(random.nextInt(10));
        }
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            rr = rr + iterator.next();
        }
        return rr;
    }
}
