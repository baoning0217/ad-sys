package com.xishanqu.ad.index;

/**
 * @Function: 索引
 * @Author: BaoNing
 * @Date: 2019-04-13
 */
public interface IndexAware<K, V> {

    V get(K key);

    void add(K key, V value);

    void update(K key, V value);

    void delete(K key, V value);

}
