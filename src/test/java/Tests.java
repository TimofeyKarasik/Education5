
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import edu.innotech.Starter;


@SpringBootTest(classes = {Starter.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class Tests {
    @Test
    void accountProductErrorTest(){

        String requestBody = "{\n" +
                "\t\"instanceId\":1,\n" +
                "\t\"registryTypeCode\":\"03.012.002_47533_ComSoLd2\",\n" +
                "\t\"accountType\":\"Клиентский\",\n" +
                "\t\"currencyCode\":\"800\",\n" +
                "\t\"branchCode\":\"0022\",\n" +
                "\t\"priorityCode\":\"00\",\n" +
                "\t\"mdmCode\":\"15\"\n" +
                "}";

        Response response = RestAssured.given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/corporate-settlement-account/create")
                .then()
                .extract().response();

        Assertions.assertEquals(404, response.statusCode());
    }

    @Test
    void accountValueErrorTest(){

        String requestBody = "{\n" +
                "\t\"instanceId\":null,\n" +
                "\t\"registryTypeCode\":\"03.012.002_47533_ComSoLd2\",\n" +
                "\t\"accountType\":\"Клиентский\",\n" +
                "\t\"currencyCode\":\"800\",\n" +
                "\t\"branchCode\":\"0022\",\n" +
                "\t\"priorityCode\":\"00\",\n" +
                "\t\"mdmCode\":\"15\"\n" +
                "}";

        Response response = RestAssured.given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/corporate-settlement-account/create")
                .then()
                .extract().response();

        Assertions.assertEquals(400, response.statusCode());
    }


    @Test
    void instanceProductErrorTest(){
        String requestBody2 = "{\n" +
                "\t\"instanceId\":null,\n" +
                "\t\"productType\":\"НСО\",\n" +
                "\t\"productCode\":\"03.012.002_Error\",\n" +
                "\t\"registerType\":\"03.012.002_47533_ComSoLd\",\n" +
                "\t\"mdmCode\":\"15\",\n" +
                "\t\"contractNumber\":\"400001\",\n" +
                "\t\"contractDate\":\"2024-01-01\",\n" +
                "\t\"priority\":1,\n" +
                "\t\"interestRatePenalty\":15.3,\n" +
                "\t\"minimalBalance\":2.2,\n" +
                "\t\"thresholdAmount\":9.2,\n" +
                "\t\"accountingDetails\":\"Details\",\n" +
                "\t\"rateType\":\"прогрессивная\",\n" +
                "\t\"taxPercentageRate\":3.4,\n" +
                "\t\"technicalOverdraftLimitAmount\":4.1,\n" +
                "\t\"contractId\":0,\n" +
                "\t\"branchCode\":\"0022\",\n" +
                "\t\"isoCurrencyCode\":\"800\",\n" +
                "\t\"urgencyCode\":\"00\",\n" +
                "\t\"referenceCode\":25,\n" +
                "\t\"additionalPropertiesVIP\":{\n" +
                "\t\t\t\"data\":[\n" +
                "\t\t\t\t\t{\"key\":\"RailwayRegionOwn\",\"value\":\"ABCD\"},\n" +
                "\t\t\t\t\t{\"key\":\"counter\",\"value\":\"123456\"}\n" +
                "\t\t\t]\n" +
                "\t},\n" +
                "\t\"instanceArrangement\":[\n" +
                "\t\t{\"generalAgreementId\":\"351\",\n" +
                "\t\t \"supplementaryAgreementId\":\"27\",\n" +
                "\t\t \"arrangementType\":\"СМО\",\n" +
                "\t\t \"number\":\"624\",\n" +
                "\t\t \"openingDate\":\"2024-01-01\",\n" +
                "\t\t \"closingDate\":null,\n" +
                "\t\t \"cancelDate\":null,\n" +
                "\t\t \"validityDuration\":56,\n" +
                "\t\t \"cancellationReason\":null,\n" +
                "\t\t \"status\":\"открыт\",\n" +
                "\t\t \"interestCalculationDate\":\"2025-01-01\",\n" +
                "\t\t \"interestRate\":1.8,\n" +
                "\t\t \"coefficient\":1.8,\n" +
                "\t\t \"coefficientAction\":\"+\",\n" +
                "\t\t \"minimumInterestRate\":3.2,\n" +
                "\t\t \"minimumInterestRateCoefficient\":3.2,\n" +
                "\t\t \"minimumInterestRateCoefficientAction\":\"-\",\n" +
                "\t\t \"maximalInterestRate\":12.9,\n" +
                "\t\t \"maximalInterestRateCoefficient\":12.9,\n" +
                "\t\t \"maximalInterestRateCoefficientAction\":\"-\"\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";

        Response response2 = RestAssured.given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody2)
                .when()
                .post("/corporate-settlement-instance/create")
                .then()
                .extract().response();

        Assertions.assertEquals(404, response2.statusCode());
    }


}
