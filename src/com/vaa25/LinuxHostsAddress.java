package com.vaa25;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class LinuxHostsAddress implements HostsAddress {

    @Override
    public Path get() {
        return Paths.get("/etc", "hosts");
    }
}
