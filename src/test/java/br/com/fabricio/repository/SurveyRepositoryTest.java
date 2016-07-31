package br.com.fabricio.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
		
	@BeforeClass
	public static void mockClasses(){
		entityManager = mock(EntityManager.class);
		defaultSurveyRepository  = new DefaultSurveyRepository();
		defaultSurveyRepository.entityManager = entityManager;
	}
	
    @Before
    public void init() {
    	User user = new User();
    	user.setId(1);
    	user.setName("Joao");
    	user.setEmail("email@email.com");
    	user.setCompositeKey("9812365342");
    	
    	Survey survey = new Survey();
    	survey.setId(1);
    	survey.setQuestion1("question1");
    	survey.setQuestion2("question2");
    	survey.setQuestion3("question3");
    	survey.setUser(user);
    }

    @Test
    public void create() throws Exception {
    	Survey survey = null;
    	
    	Query query = mock(Query.class);
    	when(entityManager.createQuery(Matchers.anyString())).thenReturn(query);
    	when(query.getSingleResult()).thenReturn(survey);
    	
    	Survey surveyReturn = defaultSurveyRepository.create(Matchers.anyString(), survey);
    	assertEquals(survey, surveyReturn);
    }

    @Test(expected=Exception.class)
    public void createAlreadyExist() throws Exception {
    	Survey survey = new Survey();
    	
    	Query query = mock(Query.class);
    	when(entityManager.createQuery(Matchers.anyString())).thenReturn(query);
    	when(query.getSingleResult()).thenReturn(survey);
    	
    	Survey surveyReturn = defaultSurveyRepository.create(Matchers.anyString(), survey);
    	assertEquals(survey, surveyReturn);
    }
    
    @Test
    public void findOne() throws Exception {
    	Survey survey = null;
    	
    	Query query = mock(Query.class);
    	when(entityManager.createQuery(Matchers.anyString())).thenReturn(query);
    	when(query.getSingleResult()).thenReturn(survey);
    	
    	Survey surveyReturn = defaultSurveyRepository.findOne(Matchers.anyString());
    	assertEquals(survey, surveyReturn);
    }

    @Test(expected=Exception.class)
    public void findOneAlreadyExist() throws Exception {
    	Survey survey = new Survey();
    	
    	Query query = mock(Query.class);
    	when(entityManager.createQuery(Matchers.anyString())).thenReturn(query);
    	when(query.getSingleResult()).thenReturn(survey);
    	
    	Survey surveyReturn = defaultSurveyRepository.findOne(Matchers.anyString());
    	assertEquals(survey, surveyReturn);
    }    
}
