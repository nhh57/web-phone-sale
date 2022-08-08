package com.mockproject.service.impl;

import com.mockproject.entity.model.ProductTypes;
import com.mockproject.entity.model.UnitTypes;
import com.mockproject.repository.UnitTypesRepo;
import com.mockproject.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Service
public class UnitServiceImpl implements UnitService {
    @Autowired
    private UnitTypesRepo typesRepo;
    @Override
    public List<UnitTypes> getALl() {
        return typesRepo.findByIsDeleted(Boolean.FALSE);
    }
}
