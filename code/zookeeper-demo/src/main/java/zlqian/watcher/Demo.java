package zlqian.watcher;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author qianzhaoliang
 * @since 2019/07/25
 */
public class Demo implements Watcher {
    private static final String host = "127.0.0.1:2181";


    public static void main(String[] args) throws IOException , KeeperException, InterruptedException {
        ZooKeeper client = new ZooKeeper(host, 5000, new Demo());
        Stat stat = new Stat();
        client.exists("/a/a", true);
        client.create("/a/a", "data".getBytes(),  ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        client.getData("/a/a", true, stat);
        client.getChildren("/a/a", new Demo());
        client.delete("/a/a", stat.getVersion());
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("receive watched event: " + watchedEvent);
        System.out.println("watchedEvent Type : " + watchedEvent.getType());
    }
}
