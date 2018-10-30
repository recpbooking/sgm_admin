package com.recp.recpbooking.controller;

import com.google.gson.Gson;
import com.recp.recpbooking.common.AppConstant;
import com.recp.recpbooking.dto.DataGridDto;
import com.recp.recpbooking.dto.ItemDto;
import com.recp.recpbooking.service.ItemService;
import com.recp.recpbooking.service.impl.ItemServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
    @RequestMapping("/itemController")
public class ItemController {
    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ItemController.class);
    @Autowired
    private ItemService itemService;

    @RequestMapping("/addNew")
    public ModelAndView addNew(){
        ModelAndView modelAndView=new ModelAndView("admin/item");
        try {
            ItemDto itemDto = new ItemDto();
            modelAndView.addObject(AppConstant.ITEM_DTO, itemDto);
            modelAndView.addObject(AppConstant.ITEM_CATEGORY_LIST, itemService.getItemCategoryList());
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }
        return modelAndView;
    }

    @RequestMapping("/saveItem")
    public ModelAndView saveItem(@ModelAttribute("itemDto") ItemDto itemDto){
        ModelAndView modelAndView=new ModelAndView("admin/item");
        try {
            itemService.insert(itemDto);
            modelAndView.addObject(AppConstant.SUCCESS_MESSAGE,"Saved succsessfully");
        } catch (Exception e) {
           LOGGER.error(e.getMessage());
            modelAndView.addObject(AppConstant.SUCCESS_MESSAGE,"Saved Failed");
        }finally {
            modelAndView.addObject(AppConstant.ITEM_CATEGORY_LIST, itemService.getItemCategoryList());
        }

        return modelAndView;
    }

    @RequestMapping("/itemview")
    public ModelAndView listview() {
        ModelAndView modelAndView = new ModelAndView("admin/itemlist");
        ItemDto itemDto = new ItemDto();
        modelAndView.addObject(AppConstant.ITEM_DTO, itemDto);
        return modelAndView;
    }

    @RequestMapping("/itemlist")
    public Object list(){
        Gson gson=new Gson();
        String json;
        DataGridDto itemList = itemService.getItemList();
        json=gson.toJson(itemList);
        return json;

    }

    @RequestMapping("/viewEdit")
    public ModelAndView viewEdit(@RequestParam(value = "shortCode", required = false) String shortCode){
        ModelAndView modelAndView=new ModelAndView("admin/item");
        ItemDto itemDto;
        try {
            itemDto=itemService.getItem(shortCode);
            modelAndView.addObject(AppConstant.ITEM_DTO, itemDto);
            modelAndView.addObject(AppConstant.ITEM_CATEGORY_LIST, itemService.getItemCategoryList());
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }
        return modelAndView;
    }


}
