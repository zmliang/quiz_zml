package com.self.java.quiz.dao;

public interface IBaseRedisDao<K, V> {

    public void set(final K key, final V value, final long expireTime);

    public void incr(final K key, final long expireTime);

    public V get(final K key);

    public Integer getInteger(final K key);

    public void del(final K key);

    public boolean exists(final K key);

}
