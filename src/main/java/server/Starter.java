package server;

import org.apache.ignite.Ignition;

import java.io.File;

public class Starter {
    public static void main(String[] args) {
        File cfg = new File("src\\main\\resources\\example-ignite.xml");
        Ignition.start(cfg.getPath());
    }
}
