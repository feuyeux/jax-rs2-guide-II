package com.example.client.suite;

import org.junit.Test;

import com.example.client.common.BasicTest;
import com.example.client.Jaxrs2Client;
import com.example.client.common.PerformanceLog;
import com.example.client.PoolingClient;

public class TestPoolingClient extends BasicTest {

    @Test
    public void testTalk() {
        final Jaxrs2Client one = new PoolingClient();
        one.test();
    }

    //@Test
    public void testPerformance() throws InterruptedException {
        int n = 0;
        final int times = 1000;
        while (n < times) {
            final Jaxrs2Client one = new PoolingClient();
            one.test();
            Thread.currentThread();
            Thread.sleep(100);
            if (n++ % 10 == 0) {
                System.out.println(n + ": " + PerformanceLog.getMemory());
            }
        }
    }
}
