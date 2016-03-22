package com.example.client.suite;

import org.junit.Test;

import com.example.client.common.BasicTest;
import com.example.client.DefaultClient;
import com.example.client.Jaxrs2Client;
import com.example.client.common.PerformanceLog;

public class TestDefaultClient extends BasicTest {

    @Test
    public void testTalk() {
        final Jaxrs2Client one = new DefaultClient();
        one.test();
    }
    
    //@Test
    public void testPerformance() throws InterruptedException {
        int n = 0;
        final int times = 1000;
        while (n < times) {
            final Jaxrs2Client one = new DefaultClient();
            one.test();
            Thread.currentThread();
            Thread.sleep(100);
            if (n++ % 10 == 0) {
                System.out.println(n + ": " + PerformanceLog.getMemory());
            }
        }
    }
}
