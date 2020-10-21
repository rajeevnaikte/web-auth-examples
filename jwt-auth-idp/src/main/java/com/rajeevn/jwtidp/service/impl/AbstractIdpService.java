package com.rajeevn.jwtidp.service.impl;

import com.rajeevn.jwtidp.exception.BadRequest;
import com.rajeevn.jwtidp.exception.InternalServerError;
import com.rajeevn.jwtidp.repository.IdpRepository;
import com.rajeevn.jwtidp.service.IdpService;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class AbstractIdpService<R> implements IdpService<R>
{
    protected final IdpRepository idpRepository;

    protected Set<String> identifiers;

    public AbstractIdpService(IdpRepository idpRepository)
    {
        this.idpRepository = idpRepository;
    }

    @Override
    public Map<String, Object> findIdentityData(Map<String, Object> identity)
    {
        if (identity == null || identity.size() == 0)
        {
            throw new BadRequest();
        }

        if (identifiers == null)
        {
            identifiers = idpRepository.getIdentifiers();
            identifiers.remove("ID");
            identifiers.remove("CREATED_AT");
            identifiers.remove("MODIFIED_AT");
        }

        identity = verifyAndFilterIdentityStructure(identity);

        try
        {
            return idpRepository.getRecord(identity);
        }
        catch (ClassCastException e)
        {
            throw new InternalServerError(e);
        }
    }

    private Map<String, Object> verifyAndFilterIdentityStructure(Map<String, Object> identity)
    {
        Map<String, Object> allowedIdentifiersOnly = new HashMap<>();
        for (Map.Entry<String, Object> identifier : identity.entrySet())
        {
            if (!identifiers.contains(identifier.getKey().toUpperCase()))
            {
                throw new BadRequest();
            }
            allowedIdentifiersOnly.put(identifier.getKey(), identifier.getValue());
        }
        return allowedIdentifiersOnly;
    }
}
