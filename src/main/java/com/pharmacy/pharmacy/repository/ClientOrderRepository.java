package com.pharmacy.pharmacy.repository;

import com.pharmacy.pharmacy.entity.ClientOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientOrderRepository extends CrudRepository<ClientOrder, Integer> {
  List<ClientOrder> findAll();
}
