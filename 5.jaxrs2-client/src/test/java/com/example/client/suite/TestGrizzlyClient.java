package com.example.client.suite;

import com.example.client.GrizzlyClient;
import com.example.client.Jaxrs2Client;
import com.example.client.common.BasicTest;
import org.junit.Test;

public class TestGrizzlyClient extends BasicTest {

  @Test
  public void testTalk() {
    final Jaxrs2Client one = new GrizzlyClient();
    one.test();
  }
}
