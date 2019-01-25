package com.infobit.testtask.controllers;

import com.infobit.testtask.entity.DataEntity;
import com.infobit.testtask.entity.Value;
import com.infobit.testtask.service.DataService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class MainRestController {

    private final DataService dataService;

    @Autowired
    public MainRestController(DataService dataService) {
        this.dataService = dataService;
    }

    @ApiOperation(value = "Retrieves all values by Data key")
    @GetMapping("/data")
    public Set<Value> getAllValuesOfData(String key) {
        return dataService.getValuesOfDataEntity(key);
    }

    @ApiOperation(value = "Puts DataEntity",
            notes = "if DataEntity exists and has no sent value, adds new value to existing DataEntity")
    @PutMapping("/data")
    public String putDataEntity(@RequestBody DataEntity dataEntity) {
        dataService.putDataEntity(dataEntity);
        return null;
    }

}
