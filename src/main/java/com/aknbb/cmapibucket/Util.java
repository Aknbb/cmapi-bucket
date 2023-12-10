package com.aknbb.cmapibucket;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.RuntimeMXBean;
import java.util.concurrent.TimeUnit;

public class Util {
    private static final double byteToMBConstant = 1024 * 1024;
    private static final OperatingSystemMXBean operatingSystemMXBean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    private static final RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();

    public static String getMemoryUsage() {
        MemoryMXBean memBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heap = memBean.getHeapMemoryUsage();
        return byteToHumanReadable(heap.getUsed());
    }

    public static String getUptime() {
        long upTime = runtimeMXBean.getUptime();
        return String.format("%d min, %d sec",
                TimeUnit.MILLISECONDS.toMinutes(upTime),
                TimeUnit.MILLISECONDS.toSeconds(upTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(upTime))
        );
    }

    public static String getApplicationCpuUsage() {
        return String.format("%.2f %%", operatingSystemMXBean.getProcessCpuLoad() * 100.0);
    }

    public static String getSystemCpuUsage() {
        return String.format("%.2f %%", operatingSystemMXBean.getSystemCpuLoad() * 100.0);
    }

    private static String byteToHumanReadable(long bytes) {
        return String.format("%.1f MB", bytes / byteToMBConstant);
    }
}
