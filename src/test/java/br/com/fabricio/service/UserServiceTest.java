package br.com.fabricio.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URI;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.apache.cxf.jaxrs.impl.UriBuilderImpl;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.fabricio.model.User;
import br.com.fabricio.repository.UserRepository;
import br.com.fabricio.util.StatusException;

public class UserServiceTest {

	private static UserRepository userRepository;
	private static UserService userService;
	private User user;
	
	@BeforeClass
	public static void mockClasses(){
		userRepository = mock(UserRepository.class);
	    ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
	    Validator validator = validatorFactory.getValidator();
	    userService = new DefaultUserService(userRepository, validator);
	}

	@Before
	public void init() {
	user = new User();
	user.setId(1);
	user.setName("joão");
	user.setEmail("fabricioashua@hotmail.com");
	user.setCompositeKey("187635265362");
	
	}
		
	@Test
	public void create() throws Exception{
		when(userRepository.create(user)).thenReturn(user);
        UriInfo uriInfo = mock(UriInfo.class);
        when(uriInfo.getRequestUriBuilder()).thenReturn(new UriBuilderImpl(new URI("")));
        Response response = userService.create(uriInfo, user);
        
        verify(userRepository).create(user);
        assertEquals(201, response.getStatus());
	}
	
	@Test
	public void createAlreadyExist() throws Exception{
		when(userRepository.create(user)).thenThrow(new Exception());
        UriInfo uriInfo = mock(UriInfo.class);
        when(uriInfo.getRequestUriBuilder()).thenReturn(new UriBuilderImpl(new URI("/geofusion/rest/user/")));
        Response response = userService.create(uriInfo, user);
        
        verify(userRepository).create(user);
        assertEquals(500, response.getStatus());
	}
	
	@Test
	public void findOne() throws Exception{
		when(userRepository.findOne(user.getId())).thenReturn(user);
        Response response = userService.findOne(user.getId());
        
        verify(userRepository).findOne(user.getId());
        assertEquals(200, response.getStatus());
	}
	
	@Test
	public void findOneAlreadyExist() throws Exception{
		User user1 = new User();
		user1.setId(2);
		user1.setName("joão");
		user1.setEmail("teseemail@test.com");
		user1.setCompositeKey("187635265362");
				
		when(userRepository.findOne(user1.getId())).thenThrow((new StatusException(Status.NOT_FOUND.getStatusCode(), "Service : Usuario não encontrado")));
        Response response = userService.findOne(user1.getId());
        
        verify(userRepository).findOne(user1.getId());
        assertEquals(404, response.getStatus());
	}
	
	@Test
	public void update() throws Exception{
		when(userRepository.update(user)).thenReturn(user);
        Response response = userService.update(user);
        
        verify(userRepository).update(user);
        assertEquals(204, response.getStatus());
	}
	
	@Test
	public void updateNotFound() throws Exception{
		when(userRepository.update(user)).thenThrow((new StatusException(Status.NOT_FOUND.getStatusCode(), "Service : Usuario não encontrado")));
        Response response = userService.update(user);
        
        verify(userRepository).update(user);
        assertEquals(404, response.getStatus());
	}
	
}
