import apiHelpers.URLs;
import apiHelpers.payloads.Pet;
import apiHelpers.payloads.PetCategory;
import apiHelpers.payloads.PetTag;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

public class AddNewPetTest {

    @Test
    public void testCreatePet() {
        Pet pet = Pet.builder()
                .category( new PetCategory( 1L, "Category 1" ) )
                .name( "Cat" )
                .status( "available" )
                .tags( Arrays.asList( new PetTag( 1L, "Tag 1" ), new PetTag( 2L, "Tag 2" ) ) )
                .photoUrls( Arrays.asList( URLs.CAT_IMAGE_URL, URLs.CAT_IMAGE_URL ) )
                .build();

        Pet requestPet = RestAssured.expect()
                .defaultParser( Parser.JSON )
                .given()
                .contentType( ContentType.JSON )
                .accept( ContentType.JSON )
                .body( pet )
                .when()
                .post( URLs.CREATE_PET_ENDPOINT )
                .then()
                .statusCode( 200 )
                .extract()
                .as( Pet.class );

        // Validation
        Assert.assertEquals( requestPet.getCategory().getName(), "Category 1" );
        Assert.assertEquals( requestPet.getName(), "Cat" );
        Assert.assertEquals( requestPet.getStatus(), "available" );
        Assert.assertEquals( requestPet.getTags().get( 0 ).getName(), "Tag 1" );
    }




}
