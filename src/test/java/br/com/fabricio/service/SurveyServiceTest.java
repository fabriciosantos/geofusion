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
import org.mockito.Matchers;

import br.com.fabricio.model.Survey;
import br.com.fabricio.model.User;
import br.com.fabricio.repository.SurveyRepository;
import br.com.fabricio.util.StatusException;

public class SurveyServiceTest {

	private static SurveyRepository surveyRepository;
	private static SurveyService surveyService;
	private Survey survey;
	
	@BeforeClass
	public static void mockClasses(){
		surveyRepository = mock(SurveyRepository.class);
	    ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
	    Validator validator = validatorFactory.getValidator();
	    surveyService = new DefaultSurveyService(surveyRepository, validator);
	}

	@Before
	public void init() {
		User user = new User();
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
		String compositeKey = "1231231231313";
		when(surveyRepository.create(compositeKey, survey)).thenReturn(survey);
        UriInfo uriInfo = mock(UriInfo.class);
        when(uriInfo.getRequestUriBuilder()).thenReturn(new UriBuilderImpl(new URI("/geofusion/rest/Survey/")));
        Response response = surveyService.create(uriInfo, compositeKey, survey);
        
        verify(surveyRepository).create(compositeKey, survey);
        assertEquals(201, response.getStatus());
	}
	
	@Test
	public void createAlreadyExist() throws Exception{
		String compositeKey = "1231231231313";
		when(surveyRepository.create(compositeKey, survey)).thenReturn(null);
        UriInfo uriInfo = mock(UriInfo.class);
        when(uriInfo.getRequestUriBuilder()).thenReturn(new UriBuilderImpl(new URI("/geofusion/rest/Survey/")));
        Response response = surveyService.create(uriInfo, compositeKey, survey);
        
        verify(surveyRepository).create(compositeKey, survey);
        assertEquals(500, response.getStatus());
	}
	
	@Test
	public void findOne() throws Exception{
		String compositeKey = "1231231231313";
		when(surveyRepository.findOne(compositeKey)).thenReturn(survey);
        Response response = surveyService.findOne(compositeKey);
        
        verify(surveyRepository).findOne(compositeKey);
        assertEquals(200, response.getStatus());
	}
	
	@Test
	public void findOneAlreadyExist() throws Exception{
		String compositeKey = "1231231231";
		when(surveyRepository.findOne(compositeKey)).thenReturn(null);
        Response response = surveyService.findOne(compositeKey);
        
        verify(surveyRepository).findOne(compositeKey);
        assertEquals(404, response.getStatus());
	}
}
