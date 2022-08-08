package com.mockproject.service.impl;

import com.mockproject.entity.model.ProductTypes;
import com.mockproject.repository.ProductTypesRepo;
import com.mockproject.service.ProductTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Service
public class ProductTypesServiceImpl implements ProductTypesService {
    @Autowired
    private ProductTypesRepo typesRepo;
    @Override
    public List<ProductTypes> getALl() {
        return typesRepo.findByIsDeleted(Boolean.FALSE);
    }
}
