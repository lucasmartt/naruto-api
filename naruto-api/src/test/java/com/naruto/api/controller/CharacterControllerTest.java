package com.naruto.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.naruto.api.dto.CharactersGetDTO;
import com.naruto.api.dto.CharactersPostDTO;
import com.naruto.api.dto.CharactersPutDTO;
import com.naruto.api.service.CharacterService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = CharacterController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class CharacterControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private CharactersPostDTO charactersPostDTO;
    private CharactersGetDTO charactersGetDTO;
    private CharactersPutDTO charactersPutDTO;

    @MockBean
    private CharacterService characterService;

    @BeforeEach
    public void init() {
        charactersPostDTO = CharactersPostDTO.builder().id(2000L).name("Super Test Ninja").build();
        charactersGetDTO = CharactersGetDTO.builder().id(2000L).name("Super Test Ninja").build();
        charactersPutDTO = CharactersPutDTO.builder().id(2000L).name("Super Test Ninja Update").build();
    }

    @Test
    public void CharacterService_PostNewCharacter_ReturnsOkResponse() throws Exception {
        given(characterService.postNewCharacter(ArgumentMatchers.any())).willReturn(ResponseEntity.ok().build());

        ResultActions response = mockMvc.perform(post("/characters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(charactersPostDTO)));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    };

    @Test
    public void CharacterService_GetCharacterById_ReturnsCharactersGetDTO() throws Exception {
        given(characterService.getCharacterById(2000L)).willReturn(ResponseEntity.ok(charactersGetDTO));

        ResultActions response = mockMvc.perform(get("/characters/2000"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(charactersGetDTO.getId().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(charactersGetDTO.getName())));
    };

    @Test
    public void CharacterService_UpdateCharacterInfo_ReturnsCharactersPutDTO() throws Exception {
            given(characterService.updateCharacterInfo(ArgumentMatchers.any())).willReturn(ResponseEntity.ok(charactersGetDTO));

            ResultActions response = mockMvc.perform(put("/characters")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(charactersPutDTO)));

            response.andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(charactersGetDTO.getId().intValue())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(charactersGetDTO.getName())));
    };

    @Test
    public void CharacterService_DeleteCharacterById_ReturnsCharactersPutDTO() throws Exception {
        given(characterService.deleteCharacterById(2000L)).willReturn(ResponseEntity.ok().build());

        ResultActions response = mockMvc.perform(delete("/characters/2000"));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    };
}
