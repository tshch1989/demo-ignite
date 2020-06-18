package server;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCluster;
import org.apache.ignite.Ignition;

import java.io.File;

public class Starter {
    public static void main(String[] args) {
        File cfg = new File("src\\main\\resources\\example-ignite.xml");
        Ignite ignite = Ignition.start(cfg.getPath());
        IgniteCluster cluster = ignite.cluster();
        cluster.active(true);
    }
}
