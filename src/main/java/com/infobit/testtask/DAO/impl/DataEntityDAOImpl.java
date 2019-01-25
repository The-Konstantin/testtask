package com.infobit.testtask.DAO.impl;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.infobit.testtask.DAO.DataEntityDAO;
import com.infobit.testtask.entity.DataEntity;
import com.infobit.testtask.entity.Value;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Repository
public class DataEntityDAOImpl implements DataEntityDAO {

    private Multimap<String, Value> dataEntityDB = Multimaps.synchronizedMultimap(HashMultimap.<String, Value>create());

    {
        Value value1 = new Value();
        value1.setDescription("blue");
        value1.setGeneration(4L);
        Value value2 = new Value();
        value2.setDescription("black");
        value2.setGeneration(2L);
        dataEntityDB.put("mock", value1);
        dataEntityDB.put("mock", value2);
        dataEntityDB.put(null, value2);
    }

    @Override
    public Set<Value> getValuesByKey(String key) {
        Set<Value> values;
        Collection<Value> valuesCollection = dataEntityDB.get(key);
        // it should return Set, as it says in documentation, but it returns Collection
        // https://google.github.io/guava/releases/27.0-jre/api/docs/com/google/common/collect/HashMultimap.html
        if (valuesCollection instanceof Set)
            values = (Set<Value>) valuesCollection;
        else
            values = new HashSet<>(valuesCollection);
        return values;
    }

    @Override
    public void putDataEntity(DataEntity dataEntity) {
        dataEntityDB.put(dataEntity.getKey(), dataEntity.getValue());
    }

}
