package com.example.client.suite;

import com.example.client.Jaxrs2Client;
import com.example.client.PoolingClient;
import com.example.client.common.BasicTest;
import com.example.client.common.PerformanceLog;
import org.junit.Test;

public class TestPoolingClient extends BasicTest {

  @Test
  public void testTalk() {
    final Jaxrs2Client one = new PoolingClient();
    one.test();
  }

  // @Test
  public void testPerformance() throws InterruptedException {
    int n = 0;
    final int times = 1000;
    while (n < times) {
      final Jaxrs2Client one = new PoolingClient();
      one.test();
      Thread.sleep(100);
      if (n++ % 10 == 0) {
        System.out.println(n + ": " + PerformanceLog.getMemory());
      }
    }
  }
}
