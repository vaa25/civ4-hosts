package com.vaa25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by vaa25 on 21.07.2017.
 */
public class WindowsHostsAddress implements HostsAddress {

    @Override
    public Path get() {
        final ProcessBuilder builder = new ProcessBuilder("reg", "query",
                "HKEY_LOCAL_MACHINE\\SYSTEM\\CurrentControlSet\\services\\Tcpip\\Parameters"
        );
        final String systemRoot = System.getenv("SystemRoot");
        try {
            final Process reg = builder.start();
            final String hosts = getHostsFromRegistry(systemRoot, reg);
            reg.waitFor();
            return Paths.get(hosts);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private String getHostsFromRegistry(String systemRoot, Process reg) throws IOException {
        try (BufferedReader output = new BufferedReader(new InputStreamReader(reg.getInputStream()))) {

            final String dataBasePath = output
                    .lines()
                    .filter(key -> key.contains("DataBasePath"))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException(
                            "No registry key 'HKEY_LOCAL_MACHINE\\SYSTEM\\CurrentControlSet\\services\\Tcpip\\Parameters\\DataBasePath'"));
            return dataBasePath.split("\\s+")[3].replace("%SystemRoot%", systemRoot) + "\\hosts";
        }
    }
}
