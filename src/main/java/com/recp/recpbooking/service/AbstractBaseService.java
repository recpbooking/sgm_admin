package com.recp.recpbooking.service;


import com.recp.recpbooking.common.RestTemplateBuilders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public abstract class AbstractBaseService<T> {

    @Value("${system.rest.base.url}")
    protected String REST_BASE_URL;

    protected RestTemplate restTemplate;


    public AbstractBaseService() {
        restTemplate = RestTemplateBuilders.securityRestTemplateBuilder();
    }

}
