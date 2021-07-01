package com.pharmacy.pharmacy.services;

import com.pharmacy.pharmacy.entity.OrderHistory;
import com.pharmacy.pharmacy.repository.OrderHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderHistoryService {
  @Autowired
  private OrderHistoryRepository orderHistoryRepository;

  public void save(OrderHistory orderHistory) {
    orderHistoryRepository.save(orderHistory);
  }

  public List<OrderHistory> findAllOrdersBetweenDate(LocalDate startDate, LocalDate toDate) {
    return orderHistoryRepository.findByDateBetween(startDate, toDate);
  }

  public Double calculateReceipts(List<OrderHistory> orderHistories) {
    return orderHistories.stream().map(orderHistory -> orderHistory.getSum()).reduce((aDouble, aDouble2) -> aDouble+aDouble2).orElseThrow(IllegalArgumentException::new);
  }
}
