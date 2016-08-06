package com.dikzz.repository;

import com.dikzz.domain.Order;

import java.util.List;

/**
 * Created by dikzz on 7/25/16.
 */
public interface OrderingRepository {
    List<Order> getAll();

    void put();
}
