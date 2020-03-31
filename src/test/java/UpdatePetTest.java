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

public class UpdatePetTest {


    @Test
    public void testUpdatePet() {
        Pet pet = Pet.builder()
                .id( 100L )
                .category( new PetCategory( 1L, "Category 1 updated" ) )
                .name( "Cat updated" )
                .status( "pending" )
                .tags( Arrays.asList( new PetTag( 1L, "Tag 1 updated" ), new PetTag( 2L, "Tag 2 updated" ) ) )
                .photoUrls( Arrays.asList( URLs.CAT_IMAGE_URL, URLs.CAT_IMAGE_URL ) )
                .build();

        Pet requestPet = RestAssured.expect()
                .defaultParser( Parser.JSON )
                .given()
                .contentType( ContentType.JSON )
                .accept( ContentType.JSON )
                .body( pet )
                .when()
                .put( URLs.UPDATE_PET_ENDPOINT )
                .as( Pet.class );

        Assert.assertEquals( requestPet.getCategory().getName(), "Category 1 updated" );
        Assert.assertEquals( requestPet.getName(), "Cat updated" );
        Assert.assertEquals( requestPet.getStatus(), "pending" );
        Assert.assertEquals( requestPet.getTags().get( 0 ).getName(), "Tag 1 updated" );
        Assert.assertEquals( requestPet.getTags().get( 1 ).getName(), "Tag 2 updated" );
    }

}
