package org.drools.hackfest2022;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.hamcrest.CoreMatchers.*;

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
    public void testItemSNLoyaltyID() {
        Item item1 = new Item("barcode", "fish", ItemCategory.GROCERY, 1.99, 1, "sn123");

        given()
          .body(Map.of("items", Arrays.asList(item1), "loyaltyID", "loyalty123"))
          .contentType(ContentType.JSON)
          .accept(ContentType.JSON)
          .when().post("/bill")
          .then()
          .statusCode(200)
          .body("logs", hasItem(containsString("Item serial no.: sn123 associated with Loyalty card ID: loyalty123")));
    }

    @Test
    public void testExpensiveApple() {
        Item item1 = new Item("barcode", "apple", ItemCategory.GROCERY, 1.99, 1);

        String response = given()
          .body(Map.of("items", Arrays.asList(item1)))
          .contentType(ContentType.JSON)
          .accept(ContentType.JSON)
          .when().post("/alert")
          .then()
          .statusCode(200)
          .extract().asString();

        System.out.println(response);
        assertTrue(response.contains("Was item weighted correctly at the self-service station?"));
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