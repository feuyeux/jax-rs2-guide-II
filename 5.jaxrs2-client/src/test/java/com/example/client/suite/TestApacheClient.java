package com.example.client.suite;

import com.example.client.ApacheClient;
import com.example.client.Jaxrs2Client;
import com.example.client.common.BasicTest;
import org.junit.Test;

public class TestApacheClient extends BasicTest {
    @Test
    public void testTalk() {
        final Jaxrs2Client one = new ApacheClient();
        one.test();
    }
}
