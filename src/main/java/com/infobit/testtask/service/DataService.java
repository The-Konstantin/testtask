package com.infobit.testtask.service;

import com.infobit.testtask.entity.DataEntity;
import com.infobit.testtask.entity.Value;

import java.util.Set;


public interface DataService {

    Set<Value> getValuesOfDataEntity(String key);

    void putDataEntity(DataEntity dataEntity);

}
