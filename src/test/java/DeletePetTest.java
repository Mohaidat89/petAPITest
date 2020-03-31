import apiHelpers.URLs;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.testng.annotations.Test;

public class DeletePetTest {


    @Test
    public void testDeletePet() {


        RestAssured.expect()
                .defaultParser( Parser.JSON )
                .when()
                .delete( URLs.DELETE_PET_ENDPOINT+"/11")
                .then()
                .statusCode( 200 );
    }


}
