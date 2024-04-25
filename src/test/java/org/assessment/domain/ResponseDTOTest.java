package org.assessment.domain;

import org.assessment.domain.response.ResponseDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ResponseDTOTest {
    @Test
    public void testResponseDTOBuilders() {
        ResponseDTO.Data<String, Integer> data = ResponseDTO.Data.<String, Integer>builder()
                .ent("Entity")
                .ext(123)
                .build();

        ResponseDTO<String, Integer> responseDTO = ResponseDTO.<String, Integer>builder()
                .msg("Success")
                .code("200")
                .data(data)
                .build();

        assertEquals("Success", responseDTO.getMsg());
        assertEquals("200", responseDTO.getCode());
        assertNotNull(responseDTO.getData());
        assertEquals("Entity", responseDTO.getData().getEnt());
        assertEquals(Integer.valueOf(123), responseDTO.getData().getExt());
    }

    @Test
    public void testResponseDTOGettersAndSetters() {
        ResponseDTO<String, Integer> responseDTO = new ResponseDTO<>();
        ResponseDTO.Data<String, Integer> data = new ResponseDTO.Data<>();

        // Set values using setters
        responseDTO.setMsg("Operation successful");
        responseDTO.setCode("200");
        responseDTO.setData(data);

        // Check values with getters
        assertEquals("Operation successful", responseDTO.getMsg());
        assertEquals("200", responseDTO.getCode());
        assertEquals(data, responseDTO.getData());
    }

    @Test
    public void testDataGettersAndSetters() {
        ResponseDTO.Data<String, Integer> data = new ResponseDTO.Data<>();

        // Set values using setters
        data.setEnt("Entity data");
        data.setExt(42);

        // Check values with getters
        assertEquals("Entity data", data.getEnt());
        assertEquals(Integer.valueOf(42), data.getExt());
    }

    @Test
    public void testResponseDTONoArgsConstructor() {
        ResponseDTO<Object, Object> responseDTO = new ResponseDTO<>();

        assertNull(responseDTO.getMsg());
        assertNull(responseDTO.getCode());
        assertNull(responseDTO.getData());
    }

    @Test
    public void testDataNoArgsConstructor() {
        ResponseDTO.Data<Object, Object> data = new ResponseDTO.Data<>();

        assertNull(data.getEnt());
        assertNull(data.getExt());
    }
}
