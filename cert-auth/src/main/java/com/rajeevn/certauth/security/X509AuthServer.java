package com.rajeevn.certauth.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class X509AuthServer extends WebSecurityConfigurerAdapter
{
    private static final Logger LOGGER = LoggerFactory.getLogger(X509AuthServer.class);

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests().anyRequest().permitAll()
                .and()
                .x509()
                .subjectPrincipalRegex("CN=(.*?)$")
                .userDetailsService(username ->
                {
                    LOGGER.info(username);
                    if (username.equals("client"))
                    {
                        return new User(username, "",
                                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"));
                    }
                    throw new UsernameNotFoundException(username);
                });
    }
}
