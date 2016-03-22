package com.example.client.common;

import java.lang.management.ManagementFactory;

import com.sun.management.OperatingSystemMXBean;

@SuppressWarnings("restriction")
public class PerformanceLog {

    public static String getMemory() {
        Runtime runtime = Runtime.getRuntime();
        long jvmTotal = runtime.totalMemory() / 1024 / 1024;
        long jvmFree = runtime.freeMemory() / 1024 / 1024;
        final OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        final long total = osmxb.getTotalSwapSpaceSize() / 1024 / 1024;
        final long free = osmxb.getFreePhysicalMemorySize() / 1024 / 1024;
        return "Total:" + total + "Mb\tFree:" + free + "Mb\tJVM_Total:" + jvmTotal + "Mb\tJVM_Free:" + jvmFree + "Mb";
    }
}
