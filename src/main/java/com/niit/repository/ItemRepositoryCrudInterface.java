package com.niit.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.niit.model.Item;

@Repository
public interface ItemRepositoryCrudInterface extends CrudRepository<Item, Integer> {

}
