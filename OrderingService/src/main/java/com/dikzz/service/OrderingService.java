package com.dikzz.service;

import com.dikzz.domain.Order;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * Created by dikzz on 7/25/16.
 */
public interface OrderingService {
    List<Order> getAll() throws IOException;
}
