package com.vaa25;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("try 'java -jar civ4-hosts.jar [add|remove]'");
            System.exit(0);
        }
        if ("add".equals(args[0])) {
            new Main().add();
        } else if ("remove".equals(args[0])) {
            new Main().remove();
        } else {
            System.out.println("Only 'add' or 'remove' arguments supported");
            System.exit(0);
        }

    }

    private void validateHosts(Path path) {
        if (Files.isRegularFile(path)) {
            System.out.println("'hosts' found in " + path.getParent());
        } else {
            System.out.println("'hosts' not found in " + path.getParent());
            System.exit(0);
        }
    }

    private void add() throws IOException {
        final Path path = new HostsPath().get();
        validateHosts(path);
        final List<String> local = Files.readAllLines(path);
        final List<String> zulan = Files.readAllLines(zulanPath());
        final List<String> merged = Strings.merge(local, zulan);
        Files.write(path, merged);
        System.out.println("Civ 4 hosts added");
    }

    private void remove() throws IOException {
        final Path path = new HostsPath().get();
        validateHosts(path);
        final List<String> local = Files.readAllLines(path);
        final List<String> zulan = Files.readAllLines(zulanPath());
        final List<String> merged = Strings.remove(local, zulan);
        Files.write(path, merged);
        System.out.println("Civ 4 hosts removed");
    }

    private Path zulanPath() {
        return Paths.get(System.getProperty("java.class.path")).getParent().resolve("civ4-hosts");
    }
}
