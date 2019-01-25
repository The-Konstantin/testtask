package com.infobit.testtask.service.impl;

import com.infobit.testtask.DAO.DataEntityDAO;
import com.infobit.testtask.entity.DataEntity;
import com.infobit.testtask.entity.Value;
import com.infobit.testtask.exceptions.ThereIsNoSuchDataException;
import com.infobit.testtask.exceptions.ValidationFailedException;
import com.infobit.testtask.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DataServiceImpl implements DataService {

    private final DataEntityDAO dataEntityDAO;

    @Autowired
    public DataServiceImpl(DataEntityDAO dataEntityDAO) {
        this.dataEntityDAO = dataEntityDAO;
    }

    @Override
    public Set<Value> getValuesOfDataEntity(String key) {
        Set<Value> values = dataEntityDAO.getValuesByKey(key);
        if (key == null ||
                values == null ||
                values.isEmpty()) {
            throw new ThereIsNoSuchDataException();
        }
        return values;
    }

    @Override
    public void putDataEntity(DataEntity dataEntity) {
        if (validateData(dataEntity)) {
            dataEntityDAO.putDataEntity(dataEntity);
        } else {
            throw new ValidationFailedException();
        }
    }

    private boolean validateData(DataEntity dataEntity) {
        return dataEntity != null &&
                (dataEntity.getKey() != null) &&
                checkKeyValid(dataEntity.getKey()) &&
                dataEntity.getValue() != null &&
                checkGenerationValid(dataEntity.getValue().getGeneration());
    }

    private boolean checkKeyValid(String key){
        Pattern p = Pattern.compile("^[a-zA-Z]+[\\w\\d_]*$");
        Matcher m = p.matcher(key);
        return m.matches();
    }

    private boolean checkGenerationValid(long generation){
        return generation >= 1 && generation <= 5;
    }
}
