package com.pharmacy.pharmacy.repository;

import com.pharmacy.pharmacy.entity.OrderHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderHistoryRepository extends CrudRepository<OrderHistory, Integer> {
  List<OrderHistory> findByDateBetween(LocalDate startDate, LocalDate toDate);
}
