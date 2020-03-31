package apiHelpers.payloads;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Pet {

    private Long id;
    private PetCategory category;
    private String name;
    private List<String> photoUrls;
    private List<PetTag> tags;
    private String status;
}
