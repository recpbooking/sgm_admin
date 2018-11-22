package com.recp.recpbooking.service;

import com.recp.recpbooking.dto.DataGridDto;
import com.recp.recpbooking.dto.ItemCategoryDto;
import com.recp.recpbooking.dto.ItemDto;

import java.util.List;

public interface ItemService {

    public void insert(ItemDto itemDto) throws Exception;

    public DataGridDto getItemList() ;

    public List<ItemCategoryDto> getItemCategoryList();

    public ItemDto getItem(String shortCode);

    public List<ItemDto> itemList();
}
