package com.pharmacy.pharmacy.services;

import com.pharmacy.pharmacy.dto.ReportDto;
import com.pharmacy.pharmacy.entity.ClientOrderItems;
import com.pharmacy.pharmacy.entity.Medicines;
import com.pharmacy.pharmacy.entity.OrderHistory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class ReportService {
  public List<ReportDto> generateReportDtos(List<OrderHistory> orderHistories) {
    List<ClientOrderItems> items = orderHistories.stream().map(orderHistory -> orderHistory.getItems()).flatMap(List::stream).collect(Collectors.toList());
    Map<Medicines, List<ClientOrderItems>> map = items.stream().collect(groupingBy(clientOrderItems -> clientOrderItems.getMedicine()));

    List<ReportDto> reportDtos = new ArrayList<>();
    for (Map.Entry<Medicines, List<ClientOrderItems>> entry : map.entrySet()) {
      Integer quantity = 0;
      Double totalCost = 0.0;
      for (ClientOrderItems order : entry.getValue()) {
        quantity += order.getQuantity();
        totalCost += order.getCost();
      }
      reportDtos.add(new ReportDto(entry.getKey(), quantity, totalCost));
    }

    return reportDtos;
  }
}
