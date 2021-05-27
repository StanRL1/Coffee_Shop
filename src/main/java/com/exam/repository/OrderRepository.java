package com.exam.repository;

import com.exam.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("select Sum(o.category.neededTime) from Order o")
    int findTimeForAllOrders();

}
