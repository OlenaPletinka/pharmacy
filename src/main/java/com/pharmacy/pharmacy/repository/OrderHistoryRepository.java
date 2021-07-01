package com.pharmacy.pharmacy.repository;

import com.pharmacy.pharmacy.entity.OrderHistory;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrderHistoryRepository extends CrudRepository<OrderHistory, Integer> {
  List<OrderHistory> findByDateBetween(LocalDate startDate, LocalDate toDate);
}
