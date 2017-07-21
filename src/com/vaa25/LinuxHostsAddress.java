package com.vaa25;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public final class LinuxHostsAddress implements HostsAddress {
    private final Map<String, Path> hostsPaths;

    public LinuxHostsAddress() {
        hostsPaths = new HashMap<>();
        hostsPaths.put("Linux", Paths.get("/etc", "hosts"));
        hostsPaths.put("Windows 10", Paths.get("/etc", "hosts"));
    }

    @Override
    public Path get() {


        final String os = System.getProperty("os.name");
        if (hostsPaths.containsKey(os)) {
            return hostsPaths.get(os);
        } else {
            System.out.println("Unknown OS: " + os);
            System.exit(0);
            return null;
        }
    }
}
