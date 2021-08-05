package com.pharmacy.pharmacy.repository;

import com.pharmacy.pharmacy.entity.ClientOrderItems;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientOrderItemsRepository extends CrudRepository<ClientOrderItems, Integer> {
}
