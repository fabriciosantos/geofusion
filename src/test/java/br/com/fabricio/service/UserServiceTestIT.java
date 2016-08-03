package br.com.fabricio.service;

import org.junit.Test;

public class UserServiceTestIT {

	String path = "rest/user";
	
    @Test
    public void create() {
    	String json = "{\"id\":\"\",\"name\":\"joao\",\"email\":\"carta@test.com\",\"compositeKey\":\"3625272573843\"}";
    	TestUtil.restCreate(json, path);
    }
    
    @Test
    public void createException(){
    	String json = "{\"id\":\"\",\"name\":\"joao\",\"email\":\"carta@test.com\",\"compositeKey\":\"3625272573843\"}";
        TestUtil.restCreateBadRequest(json, path);
    }

}
