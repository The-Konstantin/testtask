package com.infobit.testtask.DAO;

import com.infobit.testtask.entity.DataEntity;
import com.infobit.testtask.entity.Value;

import java.util.Set;

public interface DataEntityDAO {
    Set<Value> getValuesByKey(String key);
    void putDataEntity(DataEntity dataEntity);
}
