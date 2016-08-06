package com.dikzz.service;

import com.dikzz.configuration.ZooKeeperRegistrar;
import com.dikzz.domain.Order;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.List;

/**
 * Created by dikzz on 7/25/16.
 */
@RestController
@RequestMapping(path = "ordering")
public class OrderingServiceImpl implements OrderingService {

    public static final Logger logger = LoggerFactory.getLogger(OrderingServiceImpl.class);

    private ZooKeeperRegistrar zooKeeperRegistrar;

    @Autowired
    public OrderingServiceImpl(ZooKeeperRegistrar zooKeeperRegistrar) {
        this.zooKeeperRegistrar = zooKeeperRegistrar;
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getAll() throws IOException {
        logger.info("Service path is " + zooKeeperRegistrar.getServicePath());
        return Collections.emptyList();
    }
}
