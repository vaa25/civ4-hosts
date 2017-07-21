package com.vaa25;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        System.getProperties().list(System.out);
        if (args.length!=1){
            System.out.println("try 'java -jar civ4-hosts.jar [add|remove]'");
            System.exit(0);
        }
        if ("add".equals(args[0])){
            new Main().add();
        } else if ("remove".equals(args[0])){
            new Main().remove();
        } else{
            System.out.println("Only 'add' or 'remove' arguments supported");
            System.exit(0);
        }

    }

    private void add() throws IOException {
        final Path path = new HostsAddress().get();
        final List<String> local = Files.readAllLines(path);
        final List<String> zulan = Files.readAllLines(Paths.get("civ4-hosts"));
        final List<String> merged = Strings.merge(local, zulan);
        Files.write(path, merged);
        System.out.println("Civ 4 hosts added");
    }

    private void remove() throws IOException {
        final Path path = new HostsAddress().get();
        final List<String> local = Files.readAllLines(path);
        final List<String> zulan = Files.readAllLines(Paths.get("civ4-hosts"));
        final List<String> merged = Strings.remove(local, zulan);
        Files.write(path, merged);
        System.out.println("Civ 4 hosts removed");
    }
}
