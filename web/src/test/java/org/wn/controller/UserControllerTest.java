package org.wn.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.wn.util.TestUtil.DEFAULT_USER_EMAIL;
import static org.wn.util.TestUtil.DEFAULT_USER_FIRST_NAME;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.wn.config.SpringConfig;
import org.wn.config.TestConfig;
import org.wn.exception.UserAlreadyExistsException;
import org.wn.exception.UserNotFoundException;
import org.wn.model.User;
import org.wn.service.UserService;
import org.wn.util.TestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, SpringConfig.class})
@WebAppConfiguration
public class UserControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	private UserService userServiceMock;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private User user;
	
	@Before
	public void setUp(){
				
		//userServiceMock = Mockito.mock(UserService.class);
		Mockito.reset(userServiceMock);

		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		this.user = TestUtil.buildUser();
	}
	
	@Test 
	public void retrieveAll() throws Exception{

		User secondUser = TestUtil.buildUser("Tomas", "Metz", "tomas@germany.de");
		when(userServiceMock.list(1)).thenReturn(Arrays.asList(user, secondUser));
		
		mockMvc.perform(get("/users/"))
			.andDo(print())
	        .andExpect(status().isOk())
	        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
	        .andExpect(jsonPath("$", hasSize(2)))
	        .andExpect(jsonPath("$[0].name", is(DEFAULT_USER_FIRST_NAME)))
	        .andExpect(jsonPath("$[0].email", is(DEFAULT_USER_EMAIL)))
	        .andExpect(jsonPath("$[1].name", is("Tomas")))
	        .andExpect(jsonPath("$[1].lastName", is("Metz")))
	        .andExpect(jsonPath("$[1].email", is("tomas@germany.de")));
		
		verify(userServiceMock, times(1)).list(1);
        verifyNoMoreInteractions(userServiceMock);
	}
	
	@Test 
	public void retrieveByIdFailure() throws Exception {
	   
		when(userServiceMock.find(1L)).thenThrow(new UserNotFoundException("User could not be retrieved"));
	 
        mockMvc.perform(get("/users/{id}", 1))
    		.andDo(print())
            .andExpect(status().isNotFound());
       
        verify(userServiceMock, times(1)).find(1L);
        verifyNoMoreInteractions(userServiceMock);
    }
	
	
	@Test 
    public void retrieveById() throws Exception {
        
        when(userServiceMock.find(1L)).thenReturn(user);
 
        mockMvc.perform(get("/users/{id}", 1))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.name", is(DEFAULT_USER_FIRST_NAME)))
            .andExpect(jsonPath("$.email", is(DEFAULT_USER_EMAIL)));
 
        verify(userServiceMock, times(1)).find(1L);
        verifyNoMoreInteractions(userServiceMock);
    }
	
	@Test
	public void createConflictFailure() throws Exception {

		ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
		doThrow(UserAlreadyExistsException.class).when(userServiceMock).add(userCaptor.capture());
        byte[] content = TestUtil.serializeObjectToJSON(user);
		
		RequestBuilder request = MockMvcRequestBuilders
			.post("/users")
			.accept(MediaType.APPLICATION_JSON)
	        .contentType(MediaType.APPLICATION_JSON)
	        .content(content);
		
        mockMvc.perform(request)
            .andDo(print())
            .andExpect(status().isConflict())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
 
        verify(userServiceMock, times(1)).add(userCaptor.getValue());
       
    }
	
	@Test 
	public void create() throws Exception{
			
		byte[] content = TestUtil.serializeObjectToJSON(user);
		when(userServiceMock.add(user)).thenReturn(user);
		
		RequestBuilder request = MockMvcRequestBuilders
			.post("/users")
			.accept(MediaType.APPLICATION_JSON)
	        .contentType(MediaType.APPLICATION_JSON)
	        .content(content);
		
		mockMvc.perform(request)
	        .andDo(print())
	        .andExpect(status().isOk())
	        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	        .andExpect(jsonPath("$.name", is(DEFAULT_USER_FIRST_NAME)))
	        .andExpect(jsonPath("$.email", is(DEFAULT_USER_EMAIL)));
		
		ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
		verify(userServiceMock, times(1)).add(userCaptor.capture());
		verifyNoMoreInteractions(userServiceMock);
		
		User dtoArgument = userCaptor.getValue();
		assertEquals(dtoArgument.getEmail(), DEFAULT_USER_EMAIL);
		assertEquals(dtoArgument.getName(), DEFAULT_USER_FIRST_NAME);
		
	}

	@Test 
	public void deleteUser() throws Exception{
		
		doNothing().when(userServiceMock).remove(anyLong());
		mockMvc.perform(delete("/users/{id}", 2))
			.andDo(print())
			.andExpect(status().isOk());
		
	}
	
	@Test
	public void deleteFailure() throws Exception {
		
		doThrow(UserNotFoundException.class).when(userServiceMock).remove(3L);
		mockMvc.perform(delete("/users/{id}", 3))
			.andDo(print())
			.andExpect(status().isNotFound());
		
	}
	
	@Test
	public void testUserCreationFailure() throws Exception{

		byte[] content = TestUtil.serializeObjectToJSON(user);
		when(userServiceMock.add(user)).thenReturn(user);
		
		RequestBuilder request = MockMvcRequestBuilders
			.post("/users")
			.accept(MediaType.APPLICATION_JSON)
	        .contentType(MediaType.APPLICATION_JSON)
	        .content(content);
		
		mockMvc.perform(request)
	        .andDo(print())
	        .andExpect(status().isOk())
	        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	        .andExpect(jsonPath("$.name", is(DEFAULT_USER_FIRST_NAME)))
	        .andExpect(jsonPath("$.email", is(DEFAULT_USER_EMAIL)));
		
		ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
		verify(userServiceMock, times(1)).add(userCaptor.capture());
		verifyNoMoreInteractions(userServiceMock);
		
		User dtoArgument = userCaptor.getValue();
		assertEquals(dtoArgument.getEmail(), DEFAULT_USER_EMAIL);
		assertEquals(dtoArgument.getName(), DEFAULT_USER_FIRST_NAME);
		
	}
	
	@Test
	public void invalidPathVariable() throws Exception{
		
		when(userServiceMock.find(1L)).thenReturn(user);
		 
        mockMvc.perform(get("/users/{id}", "invalid"))
            .andExpect(status().isInternalServerError())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.message", notNullValue()));
            
         verifyZeroInteractions(userServiceMock);
	}
	
	@Test
	public void invalidRequestParameter() throws Exception{
		
		mockMvc.perform(get("/users?page=invalid"))
			.andDo(print())
	        .andExpect(status().isInternalServerError())
	        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
	        .andExpect(jsonPath("$.message", notNullValue()));
	        
		verifyZeroInteractions(userServiceMock);
	}
		
}
