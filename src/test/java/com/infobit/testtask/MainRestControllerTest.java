package com.infobit.testtask;

import com.infobit.testtask.entity.DataEntity;
import com.infobit.testtask.entity.Value;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class MainRestControllerTest extends TesttaskApplication {

    @Test
    public void getAllValuesOfDataWrongKeyTest() {
        given().when().param("key", "notmock").get("/data").then().statusCode(404);
    }

    // mock object already exists in db, but it if not change the test
    @Test
    public void getAllValuesOfDataTest() {
        given().when().param("key", "mock").get("/data").then().statusCode(200);
    }

    @Test
    public void putDataEntityTest() {
        DataEntity dataEntity = new DataEntity();
        Value value = new Value();
        value.setGeneration(1);
        value.setDescription("green");
        dataEntity.setKey("mock1");
        dataEntity.setValue(value);

        given()
                .contentType("application/json")
                .body(dataEntity)
                .when().put("/data").then()
                .statusCode(200);
    }
}
