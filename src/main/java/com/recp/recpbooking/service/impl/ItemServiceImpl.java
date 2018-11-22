package com.recp.recpbooking.service.impl;

import com.recp.recpbooking.dto.DataGridDto;
import com.recp.recpbooking.dto.ItemCategoryDto;
import com.recp.recpbooking.dto.ItemDto;
import com.recp.recpbooking.service.AbstractBaseService;
import com.recp.recpbooking.service.ItemService;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "session")
public class ItemServiceImpl extends AbstractBaseService<ItemServiceImpl> implements ItemService {
    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ItemServiceImpl.class);
    private int draw = 1;


    @Override
    public void insert(ItemDto itemDto) {
        try {
            String URL = REST_BASE_URL.concat("/api/item").concat("/save");
            ResponseEntity<ItemDto> getResponse = restTemplate.postForEntity(URL, itemDto, ItemDto.class);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public DataGridDto getItemList() {
        List<Object> list = null;
        DataGridDto dataGridDto=new DataGridDto();
        try {
            String URL = REST_BASE_URL.concat("/api/item").concat("/itemList");
            ResponseEntity<ItemDto[]> response = restTemplate.getForEntity(URL, ItemDto[].class);
            list = Arrays.asList(response.getBody());

            dataGridDto.setData(list);
            dataGridDto.setDraw(draw++);
            dataGridDto.setRecordsTotal(list.size());
            dataGridDto.setRecordsFiltered(list.size());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return dataGridDto;
    }

    @Override
    public List<ItemCategoryDto> getItemCategoryList() {
        List<ItemCategoryDto>list = new ArrayList<>();
        try{
            String URL = REST_BASE_URL.concat("/api/item").concat("/itemCategoryList");
            ResponseEntity<ItemCategoryDto[]> response = restTemplate.getForEntity(URL, ItemCategoryDto[].class);
            list = Arrays.asList(response.getBody());

        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }
        return list;
    }

    @Override
    public ItemDto getItem(String shortCode) {
        ItemDto itemDto=new ItemDto();
        try {
            String URL = REST_BASE_URL.concat("/api/item").concat("/byshortcode/").concat(shortCode);
            ResponseEntity<ItemDto> response = restTemplate.getForEntity(URL, ItemDto.class);
            itemDto=response.getBody();
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }
        return itemDto;
    }

    @Override
    public List<ItemDto> itemList() {
        List<ItemDto> list = null;
        DataGridDto dataGridDto=new DataGridDto();
        try {
            String URL = REST_BASE_URL.concat("/api/item").concat("/itemList");
            ResponseEntity<ItemDto[]> response = restTemplate.getForEntity(URL, ItemDto[].class);
            list = Arrays.asList(response.getBody());


        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return list;
    }
}
