package cn.com.alo7.inf.service;

import java.util.List;

public interface IRedisService {
	boolean set(String key, String value);  
    
    String get(String key);  
      
    boolean expire(String key,long expire);  
      
    <T> boolean setList(String key ,List<T> list);  
      
    <T> List<T> getList(String key,Class<T> clz);  
    
    <T> boolean setObject(String key, T t);
    
    <T> T getObject(String key,Class<T> clz);
      
    long lpush(String key,Object obj);  
      
    long rpush(String key,Object obj);  
      
    String lpop(String key); 
    
    void delete(String key);
}
