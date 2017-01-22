package me.islim.zktest;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ResilientConfigUpdater extends ConfigUpdater {

    public ResilientConfigUpdater(String hosts) throws IOException, InterruptedException {
        super(hosts);
    }

    public static void main(String[] args) throws Exception {
        while (true) {
            try {
                ResilientConfigUpdater configUpdater =
                        new ResilientConfigUpdater("localhost");
                configUpdater.run();
            } catch (KeeperException.SessionExpiredException e) {
                // start a new session
            } catch (KeeperException e) {
                // already retried, so exit
                e.printStackTrace();
                break;
            }
        }
    }
}
