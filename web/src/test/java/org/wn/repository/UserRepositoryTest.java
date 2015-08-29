package org.wn.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.wn.util.TestUtil.DEFAULT_USER_EMAIL;
import static org.wn.util.TestUtil.DEFAULT_USER_FIRST_NAME;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.wn.config.SpringConfig;
import org.wn.config.TestConfig;
import org.wn.model.User;
import org.wn.util.ResponseUtil;
import org.wn.util.TestUtil;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, SpringConfig.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DbUnitTestExecutionListener.class })
@WebAppConfiguration
public class UserRepositoryTest {

	@Autowired
	UserRepository repository;
	
	@Test
	@DatabaseSetup("single-user-set.xml")
	public void saveAndRetrieveUser() {

	    User user = TestUtil.buildUser();

	    User added = repository.save(user);
	    
	    assertThat(added, notNullValue());
	    assertThat(added.getName(), is(DEFAULT_USER_FIRST_NAME));
	    
	    assertThat(repository.findAll(), hasSize(2));
	    assertThat(repository.findByEmail(DEFAULT_USER_EMAIL), notNullValue());
	    
	  }
	
	@Test
	@DatabaseSetup("large-users-set.xml")
	public void retrieveUser() {
		User user = repository.findByEmail("daniel.velev@gmail.com");
		assertThat(user, notNullValue());
	}
	
	@Test
	@DatabaseSetup("large-users-set.xml")
	public void retrieveUsers() {
		assertThat(repository.findAll(), hasSize(greaterThan(0)));
	}
	
	@Test
	@DatabaseSetup("large-users-set.xml")
	public void testPagingListing() {
		
		Integer pageIndex = 2;
		Pageable pageSpecification = new PageRequest(pageIndex, ResponseUtil.NUMBER_OF_PERSONS_PER_PAGE); 
		Page<User> page = repository.findAll(pageSpecification);
		
		assertThat(page, notNullValue());
		assertThat(page.getNumberOfElements(), lessThanOrEqualTo(ResponseUtil.NUMBER_OF_PERSONS_PER_PAGE));
		assertThat(page.getTotalElements(), is(Long.valueOf(repository.findAll().size())));
		
	}
	
	@Test
	@DatabaseSetup("single-user-set.xml")
	public void testDelete() {
		
		List<User> users = repository.findAll();
		assertThat(repository.findAll(), hasSize(1));
				
		User single = users.get(0);
		repository.delete(single.getId());
		
		assertThat(repository.findAll(), hasSize(0));
		
	}
	
	
}
