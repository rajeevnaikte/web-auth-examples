package com.rajeevn.jwtidp.repository;

import java.util.Map;
import java.util.Set;

public interface IdpRepository
{
    String DATA_STORE_NAME = "idp";
    Set<String> getIdentifiers();
    Map<String, Object> getRecord(Map<String, Object> identity);
}
