package br.com.fabricio.service;

import org.junit.Test;
import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.given;

import javax.ws.rs.core.Response;

public class UserServiceTestIT {

	String path = "rest/user";
	
    @Test
    public void create() {
    	String json = "{\"name\":\"joao\",\"email\":\"carta@test.com\",\"compositeKey\":\"3625272573843\"}";
    	TestUtil.restCreate(json, path);
    }
    
    @Test
    public void createException(){
    	String json = "{\"name\":\"joao\",\"email\":\"carta@test.com\",\"compositeKey\":\"3625272573843\"}";
        TestUtil.restCreateBadRequest(json, path);
    }

}
