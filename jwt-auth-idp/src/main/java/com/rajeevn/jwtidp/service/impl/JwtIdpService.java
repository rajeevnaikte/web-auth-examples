package com.rajeevn.jwtidp.service.impl;

import com.rajeevn.jwtidp.exception.ImproperSetupException;
import com.rajeevn.jwtidp.model.JwtResponse;
import com.rajeevn.jwtidp.repository.IdpRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.util.HashMap;
import java.util.Map;

@Service
public class JwtIdpService extends AbstractIdpService<JwtResponse>
{
    @Autowired
    public JwtIdpService(IdpRepository idpRepository)
    {
        super(idpRepository);
    }

    @Override
    public JwtResponse buildResponse(Map<String, Object> identityRecord)
    {
        String secret = (String) identityRecord.get("secret");
        if (secret == null)
        {
            throw new ImproperSetupException();
        }

        identityRecord = new HashMap<>(identityRecord);
        identityRecord.remove("secret");

        String jws = Jwts.builder()
                .addClaims(identityRecord)
                //.setIssuedAt()
                //.setExpiration()
                .signWith(SignatureAlgorithm.HS256, Base64Utils.decode(secret.getBytes()))
                .compact();

        return new JwtResponse(jws);
    }
}
