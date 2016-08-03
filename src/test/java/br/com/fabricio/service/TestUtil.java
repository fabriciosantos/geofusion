package br.com.fabricio.service;

import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public final class TestUtil {

    private TestUtil(){
        throw new AssertionError();
    }

    public static void restCreate(String json, String path) {
        RestAssured.urlEncodingEnabled = false;
        given().
        content(json).with().contentType(ContentType.JSON).then().
        expect().
        statusCode(201).
        header("Location", containsString("/geofusion/"+path)).
        when().
        post("/geofusion/{path}", path);
    }

    public static void restCreateNotFound(String json, String path) {
        RestAssured.urlEncodingEnabled = false;
        given().
        content(json).with().contentType(ContentType.JSON).then().
        expect().
        statusCode(404).
        when().
        post("/geofusion/{path}", path);
    }

    public static void restCreateBadRequest(String json, String path) {
        RestAssured.urlEncodingEnabled = false;
        given().
        content(json).with().contentType(ContentType.JSON).then().
        expect().
        statusCode(400).
        when().
        post("/geofusion/{path}", path);
    }    

   public static void restVerify(String path, String key) {
        RestAssured.urlEncodingEnabled = false;
        expect().
        statusCode(200).
        when().
        get("/geofusion/{path}/{key}", path, key);
    }

    public static void restVerifyNotFound(String path, String key) {
        RestAssured.urlEncodingEnabled = false;
        expect().
        statusCode(404).
        when().
        get("/geofusion/{path}/{key}", path, key);
    }

    public static void restVerifyBadRequest(String path, String key) {
        RestAssured.urlEncodingEnabled = false;
        expect().
        statusCode(400).
        when().
        get("/geofusion/{path}/{key}", path, key);
    } 
}
