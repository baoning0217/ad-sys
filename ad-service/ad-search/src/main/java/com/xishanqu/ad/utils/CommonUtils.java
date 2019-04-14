package com.xishanqu.ad.utils;

import java.util.Map;
import java.util.function.Supplier;

/**
 * @Function:
 * @Author: BaoNing
 * @Date: 2019-04-13
 */
public class CommonUtils {

    public static <K, V> V getorCreate(K key, Map<K,V> map, Supplier<V> factory){
        return map.computeIfAbsent(key, k -> factory.get());
    }

}
