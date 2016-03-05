package com.example;


import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "saying")
public class CacheComponent {
    @Cacheable
    public String hi(String v) {
        return v + "-" + System.nanoTime();
    }
}
