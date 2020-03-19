import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;

public class StartIgnite {
    public static void main(String[] args) {
//        IgniteConfiguration cfg = new IgniteConfiguration();
//        cfg.setClientMode(false);
        Ignite ignite = Ignition.start();
    }
}
