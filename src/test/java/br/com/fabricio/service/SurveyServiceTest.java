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

import br.com.fabricio.model.Survey;
import br.com.fabricio.model.User;
import br.com.fabricio.repository.SurveyRepository;
import br.com.fabricio.repository.UserRepository;
import br.com.fabricio.util.StatusException;

public class SurveyServiceTest {

	private static SurveyRepository surveyRepository;
	private static SurveyService surveyService;
	private static UserRepository userRepository;
	private User user;
	private Survey survey;
	
	@BeforeClass
	public static void mockClasses(){
		surveyRepository = mock(SurveyRepository.class);
		userRepository = mock(UserRepository.class);
	    ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
	    Validator validator = validatorFactory.getValidator();
	    surveyService = new DefaultSurveyService(surveyRepository, userRepository, validator);
	}

	@Before
	public void init() {
		user = new User();
		user.setId(1);
		user.setEmail("Email@test.com.br");
		user.setName("joaõ");
		user.setCompositeKey("123123434343");
		
		survey = new Survey();
		survey.setId(1);
		survey.setQuestion1("question1");
		survey.setQuestion2("question2");
		survey.setQuestion3("question3");
		survey.setUser(user);
		
	}
		
	@Test
	public void create() throws Exception{
		when(surveyRepository.create(user.getCompositeKey(), survey)).thenReturn(survey);
        UriInfo uriInfo = mock(UriInfo.class);
        when(uriInfo.getRequestUriBuilder()).thenReturn(new UriBuilderImpl(new URI("")));
        when(userRepository.update(user)).thenReturn(user);
        Response response = surveyService.create(uriInfo, user.getCompositeKey(), survey);
        
        verify(surveyRepository).create(user.getCompositeKey(), survey);
        assertEquals(201, response.getStatus());
	}
	
	@Test
	public void createAlreadyExist() throws Exception{
		when(surveyRepository.create(user.getCompositeKey(), survey)).thenReturn(survey);
        UriInfo uriInfo = mock(UriInfo.class);
        when(uriInfo.getRequestUriBuilder()).thenReturn(new UriBuilderImpl(new URI("")));
        when(userRepository.update(user)).thenThrow(new StatusException(Status.NOT_FOUND.getStatusCode(), "Pessoa não encontrado."));
        Response response = surveyService.create(uriInfo, user.getCompositeKey(), survey);
      
        assertEquals(404, response.getStatus());
	}
	
	@Test
	public void verifySurvey() throws Exception{
		String compositeKey = "1231231231313";
		when(surveyRepository.verify(compositeKey)).thenReturn(false);
        Response response = surveyService.verify(compositeKey);
        
        verify(surveyRepository).verify(compositeKey);
        assertEquals(200, response.getStatus());
	}
	
	@Test
	public void verifyAlreadyExist() throws Exception{
		String compositeKey = "1231231231";
		when(surveyRepository.verify(compositeKey)).thenReturn(null);
        Response response = surveyService.verify(compositeKey);
        
        verify(surveyRepository).verify(compositeKey);
        assertEquals(500, response.getStatus());
	}
}
