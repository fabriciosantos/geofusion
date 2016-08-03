package br.com.fabricio.service;

import org.junit.Test;

public class SurveyServiceTestIT {

	String path = "rest/survey";
	
    @Test
    public void create() {
    	String json = "{\"id\":\"\",\"question1\":\"reposta1\",\"question2\":\"reposta2\",\"question3\":\"reposta3\",\"user\": "
    			+ "{\"id\":\"\",\"name\":\"joao\",\"email\":\"teste@gmail.com\",\"compositeKey\":\"9876227678893232314\"}}";
    	TestUtil.restCreate(json, path +"/9876227678893232314"  );
    }
    
    @Test
    public void createNotFound() {
       	String json = "{\"id\":\"\",\"question1\":\"reposta1\",\"question2\":\"reposta2\",\"question3\":\"reposta3\","
    			+ "\"user\":{\"id\":\"\",\"name\":\"joao\",\"email\":\"carta@test.com\",\"compositeKey\":\"\"}}";
    	TestUtil.restCreateNotFound(json, path + "/");
    }
    
    @Test
    public void createBadRequest(){
    	String json = "{\"id\":\"\",\"question1\":\"reposta1\",\"question2\":\"reposta2\",\"question3\":\"reposta3\","
    			+ "\"user\":{\"id\":\"\",\"name\":\"joao\",\"email\":\"mail@mail.com\",\"compositeKey\":\"9876227672672627\"}}";
    	TestUtil.restCreateBadRequest(json, path + "/9876227672672627");
    }
    
    @Test
    public void verify() {
    	TestUtil.restVerify(path, "1234");
    }

    @Test
    public void verifyBadRequest() {
    	TestUtil.restVerifyBadRequest(path, "9876227678893232314");
    }
    
    @Test
    public void verifyNotFound() {
    	TestUtil.restVerifyNotFound(path, "");
    }
}
