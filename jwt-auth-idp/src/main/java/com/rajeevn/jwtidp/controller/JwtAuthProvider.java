package com.rajeevn.jwtidp.controller;

import com.rajeevn.jwtidp.exception.BadRequest;
import com.rajeevn.jwtidp.model.JwtResponse;
import com.rajeevn.jwtidp.service.IdpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class JwtAuthProvider
{
    private final IdpService<JwtResponse> idpService;

    @Autowired
    public JwtAuthProvider(IdpService<JwtResponse> idpService)
    {
        this.idpService = idpService;
    }

    @PostMapping("/get/token")
    public JwtResponse getToken(@RequestBody Map<String, Object> identity)
    {
        Map<String, Object> identityRecord = idpService.findIdentityData(identity);

        if (identityRecord == null)
        {
            throw new BadRequest();
        }

        return idpService.buildResponse(identityRecord);
    }
}
