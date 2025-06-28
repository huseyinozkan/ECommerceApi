package com.example.e_commerce_rest_api.utils.helper;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimitHelper {
    private static final long EXPIRATION_TIME = 180 * 1000; // 3 Dakika (ms cinsinden)
    private final Map<String, Long> requestCache = new ConcurrentHashMap<>();

    public boolean isAllowed(String key) {
        long currentTime = Instant.now().toEpochMilli();

        synchronized (requestCache) {
            if (requestCache.containsKey(key)) {
                long lastRequestTime = requestCache.get(key);
                if ((currentTime - lastRequestTime) < EXPIRATION_TIME) {
                    return false; // 1 dakika dolmadı, yeni istek kabul edilmiyor
                }
            }
            requestCache.put(key, currentTime);
            return true; // Yeni istek kabul edildi ve cache güncellendi
        }
    }
}
