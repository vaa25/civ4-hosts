package com.vaa25;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vaa25 on 21.07.2017.
 */
public class HostsPath {

    private final Map<String, HostsAddress> hostsAddresses;

    public HostsPath() {
        hostsAddresses = new HashMap<>();
        hostsAddresses.put("Linux", new LinuxHostsAddress());
        hostsAddresses.put("Windows 10", new WindowsHostsAddress());
        hostsAddresses.put("Windows 8.1", new WindowsHostsAddress());
        hostsAddresses.put("Windows 8", new WindowsHostsAddress());
        hostsAddresses.put("Windows Vista", new WindowsHostsAddress());
        hostsAddresses.put("Windows 7", new WindowsHostsAddress());
        hostsAddresses.put("Windows XP", new WindowsHostsAddress());
        hostsAddresses.put("Windows 2003", new WindowsHostsAddress());
        hostsAddresses.put("Windows 2000", new WindowsHostsAddress());
        hostsAddresses.put("Windows NT", new WindowsHostsAddress());
    }

    public Path get() {
        final String os = System.getProperty("os.name");
        if (hostsAddresses.containsKey(os)) {
            return hostsAddresses.get(os).get();
        } else {
            System.out.println("Unknown OS: " + os);
            System.exit(0);
            return null;
        }
    }
}
