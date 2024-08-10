package api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class InputTest {

    @Test
    void testComplexStructure() {
        Input input = Input.builder()
                .body("data").setting("validator", "dataValidator")
                .property("id").setting("regex", "[0-9]{4}").setting("validator", "idValidator")
                .property("details").setting("validator", "detailsValidator").down("name").down("deepName")
                .up("date").setting("validator", "futureDateValidator")
                .build();

        String expected = input.json();

        assertEquals(expected, "{\"body\": {\"properties\": [{\"name\": \"data\", \"settings\": [{\"key\": \"validator\", \"value\": \"dataValidator\"}]}, {\"name\": \"id\", \"settings\": [{\"key\": \"regex\", \"value\": \"[0-9]{4}\"}, {\"key\": \"validator\", \"value\": \"idValidator\"}]}, {\"name\": \"details\", \"settings\": [{\"key\": \"validator\", \"value\": \"detailsValidator\"}], \"properties\": [{\"name\": \"name\", \"properties\": [{\"name\": \"deepName\"}]}, {\"name\": \"date\", \"settings\": [{\"key\": \"validator\", \"value\": \"futureDateValidator\"}]}]}]}}");
    }

}