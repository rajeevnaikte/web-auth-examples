package com.rajeevn.jwtidp.repository.impl;

import com.rajeevn.jwtidp.repository.IdpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public class SqlRepository implements IdpRepository
{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SqlRepository(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Set<String> getIdentifiers()
    {
        jdbcTemplate.setMaxRows(1);
        return jdbcTemplate.queryForMap("select * from " + DATA_STORE_NAME).keySet();
    }

    @Override
    public Map<String, Object> getRecord(Map<String, Object> identity)
    {
        jdbcTemplate.setMaxRows(2);
        List<Object> searchFields = new ArrayList<>(identity.size());
        StringBuilder query = new StringBuilder();
        query.append("select * from ");
        query.append(DATA_STORE_NAME);
        query.append(" where ");
        for (Map.Entry<String, Object> identifier : identity.entrySet())
        {
            query.append(identifier.getKey());
            query.append("=?");
            searchFields.add(identifier.getValue());
            query.append(" and ");
        }
        query.delete(query.length() - 5, query.length());

        return jdbcTemplate.queryForMap(query.toString(), searchFields.toArray());
    }
}
