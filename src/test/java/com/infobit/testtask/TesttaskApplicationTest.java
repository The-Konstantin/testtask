package com.infobit.testtask;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TesttaskApplicationTest {
	private final String URL = "localhost:8080/data";

	@BeforeClass
	public static void setup() {
		String port = System.getProperty("server.port");
		if (port == null) {
			RestAssured.port = 8080;
		} else{
			RestAssured.port = Integer.valueOf(port);
		}

		String basePath = System.getProperty("server.base");
		if(basePath==null){
			basePath = "";
		}
		RestAssured.basePath = basePath;

		String baseHost = System.getProperty("server.host");
		if(baseHost==null){
			baseHost = "http://localhost";
		}
		RestAssured.baseURI = baseHost;

	}

	@Test
	public void makeSureThatServerIsUp() {
		given().when().get().then().statusCode(200);
	}

}

