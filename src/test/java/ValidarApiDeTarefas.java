import dto.ContactsRequest;
import dto.ContactsResponse;
import io.restassured.module.jsv.JsonSchemaValidator;
import listners.TestListener;
import org.testng.Assert;
import org.testng.IMethodInstance;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static report.ExtentTestManager.startTest;

@Listeners(TestListener.class)
public class ValidarApiDeTarefas {

    String id = null;
    @Test(dataProvider = "getContactInformation", priority = 1)
    public void validaPost(String name, String lastname, String email, String age,
                           String phone, String addres, String state, String city) {

        startTest("ValidaPost", "efetua a criação de um contato");

        ContactsRequest request = ContactsRequest.builder()
                .name(name)
                .lastname(lastname)
                .email(email)
                .age(age)
                .phone(phone)
                .address(addres)
                .state(state)
                .city(city)
                .build();

        ContactsResponse response =
                given().
                        contentType("application/json").
                        body(request).
                        when().
                        log().all().
                        post("https://api-de-tarefas.herokuapp.com/contacts").
                        then().
                        log().all().
                        statusCode(201).
                        extract().
                        body().as(ContactsResponse.class);

        id = response.getData().getId();

        Assert.assertNotNull(response.getData().getId());
        Assert.assertEquals(response.getData().getType(), "contacts");
    }

    @Test(priority = 2)
    public void validarBuscaPorId() {

        startTest("validarBuscaPorId", "validarBuscaPorId");

        given()
                .header("content-type", "application/json")
                .pathParams("id", id)
                .when()
                .log().all()
                .get("https://api-de-tarefas.herokuapp.com/contacts/{id}")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(
                        new File("src/test/resources/GetWithId.json")
                ))
                .log().all();
    }

    @Test
    public void validarBuscaGeral() {

        startTest("validarBuscaGeral", "validarBuscaGeral");

        given()
                .when()
                .log().all()
                .get("https://api-de-tarefas.herokuapp.com/contacts/")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(
                        new File("src/test/resources/GetAllSchema.json")
                ))
                .log().all();
    }

    @Test(dataProvider = "getContactInformationUpdate", priority = 3)
    public void validaAlteracaoPut(String name, String lastname, String email, String age,
                           String phone, String addres, String state, String city) {

        startTest("validaAlteracaoPut", "validaAlteracaoPut");


        ContactsRequest request = ContactsRequest.builder()
                .name(name)
                .lastname(lastname)
                .email(email)
                .age(age)
                .phone(phone)
                .address(addres)
                .state(state)
                .city(city)
                .build();

        ContactsResponse response =
                given().
                        contentType("application/json").
                        body(request).
                        pathParams("id", id).
                        when().
                        log().all().
                        put("https://api-de-tarefas.herokuapp.com/contacts/{id}").
                        then().
                        log().all().
                        statusCode(200).
                        extract().
                        body().as(ContactsResponse.class);

        id = response.getData().getId();
        Assert.assertNotNull(response.getData().getId());
        Assert.assertEquals(response.getData().getType(), "contacts");
    }


    @Test(priority = 4)
    public void validaRemocaoContato(){
        startTest("validaRemocaoContato", "validaRemocaoContato");

        given().
                pathParams("id",id).
                when().
                log().all().
                delete("https://api-de-tarefas.herokuapp.com/contacts/{id}").
                then().
                log().all().
                statusCode(204);



    }


    @DataProvider(name="getContactInformation")
    public Object[][] getData(){
        return new Object[][]{
                {"Aline", "Zanin", "a@kk.com", "45", "51123456789", "rua x nro 1", "RS", "POA" }
        };
    }

    @DataProvider(name="getContactInformationUpdate")
    public Object[][] getDataUpdate(){
        return new Object[][]{
                {"Maria", "Paula", "m@li.com", "45", "51123456789", "rua x nro 1", "RS", "POA" }
        };
    }
















}

