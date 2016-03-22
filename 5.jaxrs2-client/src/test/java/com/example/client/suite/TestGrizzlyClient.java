package com.example.client.suite;

import org.junit.Test;

import com.example.client.common.BasicTest;
import com.example.client.GrizzlyClient;
import com.example.client.Jaxrs2Client;

public class TestGrizzlyClient extends BasicTest {

    @Test
    public void testTalk() {
        final Jaxrs2Client one = new GrizzlyClient();
        one.test();
    }
}
