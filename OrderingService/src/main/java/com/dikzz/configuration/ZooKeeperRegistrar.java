package com.dikzz.configuration;

import org.apache.zookeeper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Created by dikzz on 8/6/16.
 */
public class ZooKeeperRegistrar implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(ZooKeeperRegistrar.class);
    private static final String SERVICES = "/services";

    private String zookeeperHost;
    private String zooKeeperTimeout;
    private String serviceName;
    private String servicePath;


    @Override
    public void afterPropertiesSet() throws Exception {
        int sessionTimeout = Integer.parseInt(zooKeeperTimeout);
        InetAddress zookeeperAddress = InetAddress.getByName(zookeeperHost);
        if (zookeeperAddress.isReachable(sessionTimeout)) {
            ZooKeeper zooKeeper = new ZooKeeper(zookeeperHost, sessionTimeout, this::processEvent);
            createServicePath(zooKeeper);

            servicePath = zooKeeper.create(getPath(serviceName, serviceName), getData(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        } else {
            logger.warn("Cannot connect to zookeeper instance " + zookeeperHost);
        }
    }

    private byte[] getData() throws UnknownHostException {
        return InetAddress.getLocalHost().toString().getBytes();
    }

    private String getPath(String... services) {
        String additionalPath = Arrays.stream(services).filter(Objects::nonNull).reduce((left, right) -> left + "/" + right).orElse("");
        return SERVICES + (additionalPath.isEmpty() ? additionalPath : "/" + additionalPath);
    }

    private void createServicePath(ZooKeeper zooKeeper) throws KeeperException, InterruptedException {
        if (Objects.isNull(zooKeeper.exists(getPath(), false))) {
            zooKeeper.create(getPath(), null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        if (Objects.isNull(zooKeeper.exists(getPath(serviceName), false))) {
            zooKeeper.create(getPath(serviceName), null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
    }

    private void processEvent(WatchedEvent event) {
        logger.info("ZooKeeper event has occurred " + event.getPath() + " Type " + event.getType());
    }

    public String getZookeeperHost() {
        return zookeeperHost;
    }

    public String getZooKeeperTimeout() {
        return zooKeeperTimeout;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getServicePath() {
        return servicePath;
    }

    @Value("${serviceName}")
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Value("${zookeeper.timeout}")
    public void setZooKeeperTimeout(String zooKeeperTimeout) {
        this.zooKeeperTimeout = zooKeeperTimeout;
    }

    @Value("${zookeeper.host}")
    public void setZookeeperHost(String zookeeperHost) {
        this.zookeeperHost = zookeeperHost;
    }
}
