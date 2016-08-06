package com.dikzz.service;

import com.dikzz.domain.Order;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by dikzz on 7/25/16.
 */
public interface OrderingService {
    List<Order> getAll() throws IOException;
}
