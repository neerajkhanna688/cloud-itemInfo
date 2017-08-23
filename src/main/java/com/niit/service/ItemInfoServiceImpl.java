package com.niit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.niit.model.Item;
import com.niit.repository.ItemRepositoryCrudInterface;

@Service
public class ItemInfoServiceImpl implements ItemInfoService{

	@Autowired
	ItemRepositoryCrudInterface itemRepository;
	
	@Override
	@HystrixCommand(fallbackMethod="reliable")
	public Item getItemInfo(Integer id) throws Exception {
		return (Item)itemRepository.findOne(id);
	}

	  public Item reliable(Integer id) {
		  Item item = new Item();
		  item.setId(id);item.setBrand("234");
		  item.setModel("wer");
		  item.setWeight("22");
          return item;
	  }
	@Override
	@Transactional
	public Item saveItem(Item item) throws Exception {
		return itemRepository.save(item);
		
	}

	@Override
	public Iterable<Item> getItems() throws Exception {
		return itemRepository.findAll();
	}
	
	

}
