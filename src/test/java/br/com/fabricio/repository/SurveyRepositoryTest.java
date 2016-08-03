package br.com.fabricio.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Matchers;

import br.com.fabricio.model.Survey;
import br.com.fabricio.model.User;

public class SurveyRepositoryTest {
	
	public static EntityManager entityManager;
	public static DefaultSurveyRepository defaultSurveyRepository;
	private User user;
	private Survey survey;
		
	@BeforeClass
	public static void mockClasses(){
		entityManager = mock(EntityManager.class);
		defaultSurveyRepository  = new DefaultSurveyRepository();
		defaultSurveyRepository.entityManager = entityManager;
	}
	
    @Before
    public void init() {
    	user = new User();
    	user.setId(1);
    	user.setName("Joao");
    	user.setEmail("email@email.com");
    	user.setCompositeKey("9812365342");
    	
    	survey = new Survey();
    	survey.setId(1);
    	survey.setQuestion1("question1");
    	survey.setQuestion2("question2");
    	survey.setQuestion3("question3");
    	survey.setUser(user);
    }

    @Test
    public void create() throws Exception {
    	List<User> listUser = new ArrayList<>();
    	listUser.add(user);
    	
    	List<Survey> listSurvey = null;
    	
    	Query queryUser = mock(Query.class);
    	when(entityManager.createQuery("SELECT u FROM User u WHERE u.compositeKey = :compositeKey ")).thenReturn(queryUser);
    	when(queryUser.getResultList()).thenReturn(listUser);
    	
    	Query querySurvey = mock(Query.class);
    	when(entityManager.createQuery("SELECT s FROM Survey s INNER JOIN s.user u WHERE u.compositeKey = :compositeKey")).thenReturn(querySurvey);
    	when(querySurvey.getResultList()).thenReturn(listSurvey);
    	    		
    	Survey surveyReturn = defaultSurveyRepository.create(user.getCompositeKey(), survey);
    	assertEquals(survey, surveyReturn);
    }

    @Test(expected=Exception.class)
    public void createAlreadyExist() throws Exception {
    	List<User> listUser = new ArrayList<>();
    	listUser.add(user);
    	
    	List<Survey> listSurvey = new ArrayList<>();
    	listSurvey.add(survey);
    	
    	Query queryUser = mock(Query.class);
    	when(entityManager.createQuery("SELECT u FROM User u WHERE u.compositeKey = :compositeKey ")).thenReturn(queryUser);
    	when(queryUser.getResultList()).thenReturn(listUser);
    	
    	Query querySurvey = mock(Query.class);
    	when(entityManager.createQuery("SELECT s FROM Survey s INNER JOIN s.user u WHERE u.compositeKey = :compositeKey")).thenReturn(querySurvey);
    	when(querySurvey.getResultList()).thenReturn(listSurvey);
    	
    	Survey surveyReturn = defaultSurveyRepository.create(Matchers.anyString(), survey);
    	assertEquals(survey, surveyReturn);
    }
    
    @Test
    public void verify() throws Exception {
    	List<User> listUser = new ArrayList<>();
    	listUser.add(user);
    	
    	List<Survey> listSurvey = null;
    	
    	Query queryUser = mock(Query.class);
    	when(entityManager.createQuery("SELECT u FROM User u WHERE u.compositeKey = :compositeKey ")).thenReturn(queryUser);
    	when(queryUser.getResultList()).thenReturn(listUser);
    	
    	Query querySurvey = mock(Query.class);
    	when(entityManager.createQuery("SELECT s FROM Survey s INNER JOIN s.user u WHERE u.compositeKey = :compositeKey")).thenReturn(querySurvey);
    	when(querySurvey.getResultList()).thenReturn(listSurvey);
    	    		
    	boolean verify = defaultSurveyRepository.verify(user.getCompositeKey());
    	assertEquals(false, verify);
    }

    @Test
    public void verifyAlreadyExist() throws Exception {
    	List<User> listUser = new ArrayList<>();
    	listUser.add(user);
    	
    	List<Survey> listSurvey = new ArrayList<>();
    	listSurvey.add(survey);
    	
    	Query queryUser = mock(Query.class);
    	when(entityManager.createQuery("SELECT u FROM User u WHERE u.compositeKey = :compositeKey ")).thenReturn(queryUser);
    	when(queryUser.getResultList()).thenReturn(listUser);
    	
    	Query querySurvey = mock(Query.class);
    	when(entityManager.createQuery("SELECT s FROM Survey s INNER JOIN s.user u WHERE u.compositeKey = :compositeKey")).thenReturn(querySurvey);
    	when(querySurvey.getResultList()).thenReturn(listSurvey);
    	
    	boolean verify = defaultSurveyRepository.verify(Matchers.anyString());
    	assertEquals(true, verify);
    }    
    
    @Test(expected=Exception.class)
    public void verifyUserNotFound() throws Exception {
    	List<User> listUser = null;
    	
    	List<Survey> listSurvey = null;
    	
    	Query queryUser = mock(Query.class);
    	when(entityManager.createQuery("SELECT u FROM User u WHERE u.compositeKey = :compositeKey ")).thenReturn(queryUser);
    	when(queryUser.getResultList()).thenReturn(listUser);
    	
    	Query querySurvey = mock(Query.class);
    	when(entityManager.createQuery("SELECT s FROM Survey s INNER JOIN s.user u WHERE u.compositeKey = :compositeKey")).thenReturn(querySurvey);
    	when(querySurvey.getResultList()).thenReturn(listSurvey);
    	
    	boolean verify = defaultSurveyRepository.verify(Matchers.anyString());
    	assertEquals(true, verify);
    }  
}
