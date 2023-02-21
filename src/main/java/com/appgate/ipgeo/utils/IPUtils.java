package com.appgate.ipgeo.utils;

public class IPUtils {
    private static final long FACTOR_SEG_1 = 16777216;
    private static final long FACTOR_SEG_2 = 65536;
    private static final long FACTOR_SEG_3 = 256;
    private static final long FACTOR_SEG_4 = 1;

    public static long getDecimalIP(String ip) {
        String[] partsIp = ip.split("\\.");

        return Long.parseLong(partsIp[0]) * FACTOR_SEG_1 +
                Long.parseLong(partsIp[1]) * FACTOR_SEG_2 +
                Long.parseLong(partsIp[2]) * FACTOR_SEG_3 +
                Long.parseLong(partsIp[3]) * FACTOR_SEG_4;
    }
}
