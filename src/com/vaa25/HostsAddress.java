package com.vaa25;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public final class HostsAddress {
    private final Map<String, Path> hostsPaths;

    public HostsAddress() {
        hostsPaths = new HashMap<>();
        hostsPaths.put("Linux", Paths.get("/etc", "hosts"));
    }

    public Path get(){
        final String os = System.getProperty("os.name");
        if (hostsPaths.containsKey(os)){
            return hostsPaths.get(os);
        } else {
            System.out.println("Unknown OS: " + os);
            System.exit(0);
            return null;
        }
    }
}
