package com.farjuce.appreservas.logica;

import com.farjuce.appreservas.bd.business.Business;
import com.farjuce.appreservas.bd.business.BusinessRepository;
import com.farjuce.appreservas.controller.dto.BusinessDTO;
import org.springframework.stereotype.Service;

@Service
public class BusinessLogic {

    private BusinessRepository repository;

    public BusinessLogic(BusinessRepository repository) {
        this.repository = repository;
    }

    public void addBusiness(BusinessDTO businessDTO) {

        Business business = new Business();

        business.setName(businessDTO.getName());
        business.setAddress(businessDTO.getAddress());
        business.setType(businessDTO.getType());
        business.setOpening_time(businessDTO.getOpening_time());
        business.setClosure_time(businessDTO.getClosure_time());

        repository.save(business);
    }


}
