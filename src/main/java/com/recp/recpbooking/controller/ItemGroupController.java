package com.recp.recpbooking.controller;

import com.google.gson.Gson;
import com.recp.recpbooking.common.AppConstant;
import com.recp.recpbooking.dto.DataGridDto;
import com.recp.recpbooking.dto.ItemDto;
import com.recp.recpbooking.dto.ItemGroupDto;
import com.recp.recpbooking.service.ItemGroupService;
import com.recp.recpbooking.service.ItemService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
    @RequestMapping("/itemGroupController")
public class ItemGroupController {
    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ItemGroupController.class);
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemGroupService itemGroupService;

    @RequestMapping("/addNew")
    public ModelAndView addNew(){
        ModelAndView modelAndView=new ModelAndView("admin/itemgroup");
        try {
            ItemGroupDto itemDto = new ItemGroupDto();
            modelAndView.addObject(AppConstant.ITEMGROUP_DTO, itemDto);
            modelAndView.addObject(AppConstant.ITEM_LIST, itemService.itemList());
            modelAndView.addObject(AppConstant.ITEM_CATEGORY_LIST, itemService.getItemCategoryList());
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }
        return modelAndView;
    }

    @RequestMapping("/saveItem")
    public ModelAndView saveItem(@ModelAttribute("itemGroupDto") ItemGroupDto itemDto){
        ModelAndView modelAndView=new ModelAndView("admin/itemgroup");
        try {
            itemGroupService.insert(itemDto);
            modelAndView.addObject(AppConstant.SUCCESS_MESSAGE,"Saved succsessfully");
        } catch (Exception e) {
           LOGGER.error(e.getMessage());
            modelAndView.addObject(AppConstant.SUCCESS_MESSAGE,"Saved Failed");
        }finally {

            modelAndView.addObject(AppConstant.ITEMGROUP_DTO, new ItemGroupDto());
            modelAndView.addObject(AppConstant.ITEM_LIST, itemService.itemList());
            modelAndView.addObject(AppConstant.ITEM_CATEGORY_LIST, itemService.getItemCategoryList());
        }

        return modelAndView;
    }

    @RequestMapping("/itemgroupview")
    public ModelAndView listview() {
        ModelAndView modelAndView = new ModelAndView("admin/itemgrouplist");
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
