package com.pharmacy.pharmacy.repository;

import com.pharmacy.pharmacy.entity.ClientOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientOrderRepository extends CrudRepository<ClientOrder, Integer> {
  List<ClientOrder> findAll();
}
