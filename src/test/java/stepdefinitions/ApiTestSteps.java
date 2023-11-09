package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ApiTestSteps {
    private Response response;

    @Given("the webpage is accessible")
    public void theWebpageIsAccessible() {
        RestAssured.baseURI = "https://www.addteq.com/products/mscgen";
    }

    @When("it returns a 200 status code")
    public void itReturnsA200StatusCode() {
        response = RestAssured.get("/");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(200, statusCode);
        System.out.println(response.asString());

    }

    @Then("it has a valid title")
    public void itHasAValidTitle() {
        String htmlContent = response.asString();
        Document document = Jsoup.parse(htmlContent);
        String pageTitle = document.title();

        String expectedTitle = "MSCGen | Confluence Charts | Confluence Table Filter | Addteq Inc.";

        Assert.assertEquals(expectedTitle, pageTitle);
    }

    @Then("it has an image")
    public void itHasAnImage() {
       // String imageUrl = xmlPath.get("image");
       // Assert.assertNotNull(imageUrl);
    }

    @Then("it has a description of the page")
    public void itHasADescriptionOfThePage() {
        //String description = xmlPath.get("description");
       // Assert.assertNotNull(description);
    }

    @Then("it has Author details")
    public void itHasAuthorDetails() {
        String htmlContent = response.asString();
        Document document = Jsoup.parse(htmlContent);
        String expectedAuthorDetails = "Addteq: The Top DevOps Tools & Software Team Products";

        Element metaTag = document.select("meta[property=og:site_name]").first();

        if (metaTag != null) {
            String authorDetails = metaTag.attr("content");
            Assert.assertEquals(expectedAuthorDetails, authorDetails);
        } else {
            Assert.fail("Author details not found in <meta> tag with property 'og:site_name'");
        }
    }

    @Then("it has Published date")
    public void itHasPublishedDate() {
        String publishDate = response.then().extract().path("publishDate");
        Assert.assertNotNull(publishDate);
    }
}
