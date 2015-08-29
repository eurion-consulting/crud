package org.wn.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wn.service.UserService;

@Configuration
public class TestConfig {

	@Bean
    public UserService userService() {
        return Mockito.mock(UserService.class);
    }
	
}
