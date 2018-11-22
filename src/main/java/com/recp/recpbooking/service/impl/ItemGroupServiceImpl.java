package com.recp.recpbooking.service.impl;

import com.recp.recpbooking.dto.DataGridDto;
import com.recp.recpbooking.dto.ItemDto;
import com.recp.recpbooking.dto.ItemGroupDto;
import com.recp.recpbooking.service.AbstractBaseService;
import com.recp.recpbooking.service.ItemGroupService;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "session")
public class ItemGroupServiceImpl extends AbstractBaseService<ItemGroupServiceImpl> implements ItemGroupService {
    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ItemGroupServiceImpl.class);
    private int draw = 1;
    @Override
    public void insert(ItemGroupDto itemGroupDto) throws Exception {
        try {
            String URL = REST_BASE_URL.concat("/api/item").concat("/group");
            ResponseEntity<ItemGroupDto> getResponse = restTemplate.postForEntity(URL, itemGroupDto, ItemGroupDto.class);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }


}
