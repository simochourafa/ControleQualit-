package com.group.azura.maraissa.controleQualite.util;

import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public class PaginationUtil {
    public static int checkPaginationPageSize(Integer pagesize) {

        return ((pagesize) >= 1 ? (pagesize) : 1);

    }

    public static Sort getSortOrders(String[] sortBy) {
        List<Sort.Order> orders = new ArrayList<>();
        if (sortBy != null) {
            for (String sortOrder : sortBy) {
                orders.add(new Sort.Order(Sort.Direction.fromString(sortOrder.split(";")[1]), sortOrder.split(";")[0]));
            }
        }
        return orders.isEmpty() ? Sort.unsorted() : Sort.by(orders);
    }
}
