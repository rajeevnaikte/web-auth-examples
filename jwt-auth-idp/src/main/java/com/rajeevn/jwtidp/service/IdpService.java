package com.rajeevn.jwtidp.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface IdpService<ResponseType>
{
    /**
     * Find a record for given identity and return the datapoint value, if exists.
     * @param identity
     * @return
     */
    Map<String, Object> findIdentityData(Map<String, Object> identity);

    /**
     * Build response body for successful authenticated data.
     * @param identityRecord
     * @return
     */
    ResponseType buildResponse(Map<String, Object> identityRecord);
}
