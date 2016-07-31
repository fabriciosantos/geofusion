package br.com.fabricio.service;

import org.junit.Test;
import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.given;

import javax.ws.rs.core.Response;

public class SurveyServiceTestIT {

	String path = "rest/survey";
	
    @Test
    public void create() {
    	String json = "{\"question1\":\"reposta1\",\"question2\":\"reposta2\",\"question3\":\"reposta3\",\"user\":{\"id\":\"1\"}";
    	TestUtil.restCreate(json, path);
    }
    
    @Test
    public void createException(){
    	String json = "{\"question1\":\"reposta1\",\"question2\":\"reposta2\",\"question3\":\"reposta3\",\"user\":{\"id\":\"1\"}";
    	TestUtil.restCreateBadRequest(json, path);
    }

}
