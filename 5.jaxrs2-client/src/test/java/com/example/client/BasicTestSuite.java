package com.example.client;

import com.example.client.suite.TestApacheClient;
import com.example.client.suite.TestDefaultClient;
import com.example.client.suite.TestGrizzlyClient;
import com.example.client.suite.TestPoolingClient;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestDefaultClient.class,
        TestApacheClient.class,
        TestGrizzlyClient.class,
        TestPoolingClient.class })
public class BasicTestSuite {
}
