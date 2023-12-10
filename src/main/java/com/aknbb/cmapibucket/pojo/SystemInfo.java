package com.aknbb.cmapibucket.pojo;

import com.aknbb.cmapibucket.Util;
import com.aknbb.cmapibucket.initializer.DataInitializer;

public class SystemInfo {
    private ApplicationInfo application;
    private MonitoringInfo monitoring;
    private BuildInfo build;

    public SystemInfo(String version, String timeStamp, String debugLevel) {
        this.application = new ApplicationInfo(debugLevel);
        this.monitoring = new MonitoringInfo();
        this.build = new BuildInfo(version, timeStamp);
    }

    public ApplicationInfo getApplication() {
        return application;
    }

    public MonitoringInfo getMonitoring() {
        return monitoring;
    }

    public BuildInfo getBuild() {
        return build;
    }

    static class ApplicationInfo {
        String dataPath = DataInitializer.getDataDirPath().toString();
        String debugLevel;

        ApplicationInfo(String debugLevel) {
            this.debugLevel = debugLevel;
        }

        public String getdataPath() {
            return dataPath;
        }

        public String getDebugLevel() {
            return debugLevel;
        }
    }

    static class MonitoringInfo {
        String heapMemoryUsage = Util.getMemoryUsage();
        String applicationCpuUsage = Util.getApplicationCpuUsage();
        String systemCpuUsage = Util.getSystemCpuUsage();
        String upTime = Util.getUptime();

        public String getHeapMemoryUsage() {
            return heapMemoryUsage;
        }

        public String getApplicationCpuUsage() {
            return applicationCpuUsage;
        }

        public String getSystemCpuUsage() {
            return systemCpuUsage;
        }

        public String getUpTime() {
            return upTime;
        }
    }

    static class BuildInfo {
        String version;

        String timeStamp;

        BuildInfo(String version, String timeStamp) {
            this.version = version;
            this.timeStamp = timeStamp;
        }

        public String getVersion() {
            return version;
        }

        public String getTimeStamp() {
            return timeStamp;
        }

    }
}
