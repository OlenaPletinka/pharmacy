package com.pharmacy.pharmacy.controller;

import com.pharmacy.pharmacy.entity.ClientOrder;
import com.pharmacy.pharmacy.exception.BasketIsEmptyException;
import com.pharmacy.pharmacy.services.CheckoutService;
import com.pharmacy.pharmacy.services.ClientOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class CheckoutController {
  @Autowired
  private ClientOrderService clientOrderService;
  @Autowired
  private CheckoutService checkoutService;

  @RequestMapping(path = "/checkout", method = RequestMethod.GET)
  public String checkout(Model model) {
    List<ClientOrder> orderList = clientOrderService.showAllItems();

    if (orderList.isEmpty()){
      throw new BasketIsEmptyException("Ваш кошик порожній! Продовжіть, будь ласка, покупки.");
    }

    model.addAttribute("orderList", orderList);
    Double sum = checkoutService.calculateTotalSum(orderList);
    model.addAttribute("sum", sum);

    return "basket";
  }

  @RequestMapping(path = "/dispatch", method = RequestMethod.GET)
  public String dispatch(Model model) {
    checkoutService.dispatch();

    return "welcome";
  }
}

