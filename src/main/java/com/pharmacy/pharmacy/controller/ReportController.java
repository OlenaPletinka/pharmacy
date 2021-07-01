package com.pharmacy.pharmacy.controller;

import com.pharmacy.pharmacy.dto.ReportDto;
import com.pharmacy.pharmacy.entity.OrderHistory;
import com.pharmacy.pharmacy.services.OrderHistoryService;
import com.pharmacy.pharmacy.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ReportController {
  @Autowired
  private OrderHistoryService orderHistoryService;
  @Autowired
  private ReportService reportService;

  @RequestMapping(path = "/report", method = RequestMethod.GET)
  public String showReport(@RequestParam("start") String startDate, @RequestParam("to") String toDate, Model model) {
    LocalDate start = LocalDate.parse(startDate);
    LocalDate to = LocalDate.parse(toDate);

    UserRequestValidator.validateDate(start, to);

    model.addAttribute("startDate", start);
    model.addAttribute("toDate", to);

    List<OrderHistory> orderHistories = orderHistoryService.findAllOrdersBetweenDate(start, to);
    if (!orderHistories.isEmpty()) {
      Double receipts = orderHistoryService.calculateReceipts(orderHistories);
      model.addAttribute("receipts", receipts);
      List<ReportDto> reportDtos = reportService.generateReportDtos(orderHistories);
      model.addAttribute("reportDtos", reportDtos);
    }

    return "report";
  }
}
