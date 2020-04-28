package top.xujie.tools.utils;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

/**
 * @author xujie
 */
public class CacheMapUtil {
    public static void main(String[] args) throws InterruptedException {
        Cache<Integer, Integer> requestTimes = Caffeine.newBuilder().expireAfterAccess(10, TimeUnit.SECONDS).build();

        for (int i = 0; i < 60; i++) {
            Thread.sleep(1000);
            requestTimes.put(i, i);
            System.out.println(requestTimes.getIfPresent(i));
        }

    }

}
