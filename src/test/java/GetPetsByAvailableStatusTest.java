import apiHelpers.URLs;
import apiHelpers.payloads.Pet;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GetPetsByAvailableStatusTest {


    @Test
    public void testRetrievePet() {
        List<Pet> pets = RestAssured.expect()
                .defaultParser( Parser.JSON )
                .when()
                .get( URLs.GT_PETS_BY_AVAILABLE_STATUS_ENDPOINT )
                .jsonPath()
                .getList( ".", Pet.class );

        for (Pet pet : pets) {
            Assert.assertEquals( pet.getStatus(), "available" );
        }
    }
}
