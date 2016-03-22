package com.example.client.suite;

import org.junit.Test;

import com.example.client.ApacheClient;
import com.example.client.common.BasicTest;
import com.example.client.Jaxrs2Client;

public class TestApacheClient extends BasicTest {
    @Test
    public void testTalk() {
        final Jaxrs2Client one = new ApacheClient();
        one.test();
    }
}
