package org.drools.hackfest2022;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Map;

import org.drools.hackfest2022.model.Item;
import org.drools.hackfest2022.model.ItemCategory;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@QuarkusTest
public class BasicTest {

    static {
      RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void testExpensiveGrocery() {
        Item item1 = new Item("barcode", "orange", ItemCategory.GROCERY, 45, 1);
        Item item2 = new Item("barcode", "orange", ItemCategory.GROCERY, 45, 1);

        String response = given()
          .body(Map.of("items", Arrays.asList(item1, item2)))
          .contentType(ContentType.JSON)
          .accept(ContentType.JSON)
          .when().post("/alert")
          .then()
          .statusCode(200)
          .extract().asString();

        System.out.println(response);
        assertTrue(response.contains("Was item weighted correctly at the self-service station?"));
        assertTrue(response.contains("Please check you have not scanned this item more than once?"));
    }

    @Test
    public void testWagyu() {
        Item item1 = new Item("barcode", "wagyu", null, 45, 1);
        Item item2 = new Item("barcode", "wagyu", null, 45, 1);

        String response = given()
          .body(Map.of("items", Arrays.asList(item1, item2)))
          .contentType(ContentType.JSON)
          .accept(ContentType.JSON)
          .when().post("/alert")
          .then()
          .statusCode(200)
          .extract().asString();

        System.out.println(response);

        assertTrue(response.contains("Please check you have not scanned this item more than once?"));
    }

}