package com.nilsgaebel.compass.sports;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

/**
 * Endpoint-level test. The 400 case needs no network and always runs fast,
 * which makes it a reliable gate for the CI pipeline.
 */
@QuarkusTest
class TeamResourceTest {

    @Test
    void searchWithoutName_returnsBadRequest() {
        given()
                .when().get("/teams/search")
                .then()
                .statusCode(400);
    }

    @Test
    void searchWithBlankName_returnsBadRequest() {
        given()
                .queryParam("name", "   ")
                .when().get("/teams/search")
                .then()
                .statusCode(400);
    }

    @Test
    void leagueTableWithoutSeason_returnsBadRequest() {
        given()
                .when().get("/leagues/4328/table")
                .then()
                .statusCode(400);
    }

    @Test
    void healthEndpoint_isUp() {
        given()
                .when().get("/q/health")
                .then()
                .statusCode(200)
                .body("status", is("UP"));
    }
}
