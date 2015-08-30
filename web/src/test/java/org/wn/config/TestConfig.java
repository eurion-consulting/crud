package org.wn.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wn.repository.UserRepository;
import org.wn.service.UserService;
import org.wn.service.UserServiceImpl;

@Configuration
public class TestConfig {

	@Bean(name="UserServiceMock")
    public UserService getUserService() {
        return Mockito.mock(UserServiceImpl.class);
    }
	
	@Bean(name="UserRepositoryMock")
    public UserRepository getUserRepository() {
        return Mockito.mock(UserRepository.class);
    }
}
