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

    public static void restCreateFail(String json, String path) {
        RestAssured.urlEncodingEnabled = false;
        given().
        content(json).with().contentType(ContentType.JSON).then().
        expect().
        statusCode(500).
        when().
        post("/geofusion/{path}", path);
    }

    public static void restFindAll(String path) {
        RestAssured.urlEncodingEnabled = false;
        expect().
        statusCode(200).
        contentType(ContentType.JSON).
        body("size()", greaterThan(0)).
        when().
        get("/geofusion/{path}", path);
    }

    public static void restFindAllEmpty(String path) {
        RestAssured.urlEncodingEnabled = false;
        expect().
        statusCode(200).
        contentType(ContentType.JSON).
        body(equalTo("[]")).
        when().
        get("/geofusion/{path}", path);
    }

    public static void restFindAllNotFound(String path) {
        RestAssured.urlEncodingEnabled = false;
        expect().
        statusCode(404).
        when().
        get("/geofusion/{path}", path);
    }

    public static void restFindOne(String path, long id) {
        RestAssured.urlEncodingEnabled = false;
        expect().
        statusCode(200).
        contentType(ContentType.JSON).
        when().
        get("/geofusion/{path}/{id}", path, id);
    }

    public static void restFindOneNotFound(String path, long id) {
        RestAssured.urlEncodingEnabled = false;
        expect().
        statusCode(404).
        when().
        get("/geofusion/{path}/{id}", path, id);
    }

    public static void restFindOneBadRequest(String path, long id) {
        RestAssured.urlEncodingEnabled = false;
        expect().
        statusCode(400).
        when().
        get("/geofusion/{path}/{id}", path, id);
    }

    public static void restUpdateNotFound(String json, String path, long id) {
        RestAssured.urlEncodingEnabled = false;
        given().
        content(json).with().contentType(ContentType.JSON).then().
        expect().
        statusCode(404).
        when().
        put("/geofusion/{path}/{id}", path, id);
    }

    public static void restUpdateValidationFail(String json, String path, long id) {
        RestAssured.urlEncodingEnabled = false;
        given().
        content(json).with().contentType(ContentType.JSON).then().
        expect().
        statusCode(400).
        when().
        put("/geofusion/{path}/{id}", path, id);
    }

    public static void restUpdate(String json, String path, long id) {
        RestAssured.urlEncodingEnabled = false;
        given().
        content(json).with().contentType(ContentType.JSON).then().
        expect().
        statusCode(204).
        when().
        put("/geofusion/{path}/{id}", path, id);
    }

    public static void restUpdateFailBadRequest(String json, String path, long id) {
        RestAssured.urlEncodingEnabled = false;
        given().
        content(json).with().contentType(ContentType.JSON).then().
        expect().
        statusCode(400).
        when().
        put("/geofusion/{path}/{id}", path, id);
    }

    public static void restDelete(String path, long id){
        RestAssured.urlEncodingEnabled = false;
        expect().
        statusCode(204).
        when().
        delete("/geofusion/{path}/{id}", path, id);
    }

    public static void restDeleteFail(String path, String id){
        RestAssured.urlEncodingEnabled = false;
        expect().
        statusCode(500).
        when().
        delete("/geofusion/{path}/{id}", path, id);
    }

    public static void restDeleteNotFound(String path, long id){
        RestAssured.urlEncodingEnabled = false;
        expect().
        statusCode(404).
        when().
        delete("/geofusion/{path}/{id}", path, id);
    }

    public static void restDeleteBadRequest(String path, String id){
        RestAssured.urlEncodingEnabled = false;
        expect().
        statusCode(400).
        when().
        delete("/geofusion/{path}/{id}", path, id);
    }

    public static String getEntityId(String json, String path){
        Response response = given().content(json).with().contentType(ContentType.JSON).when().post("/geofusion/{path}", path);
        String location = response.getHeader("Location");
        String[] paths = location.split("/");
        String id = paths[paths.length-1];
        return id;
    }
}
