package com.naruto.api.controller;

import com.naruto.api.dto.CharactersPutDTO;
import com.naruto.api.dto.CharactersGetDTO;
import com.naruto.api.dto.CharactersPostDTO;
import com.naruto.api.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> postNewCharacter(@RequestBody CharactersPostDTO data) {
        return characterService.postNewCharacter(data);
    }

    @GetMapping(path = "/local", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<CharactersGetDTO>> getAllLocalCharacters(Pageable pageable) {
        return characterService.getAllLocalCharacters(pageable);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllCharacters(
            @RequestParam(required = false, defaultValue = "1") String page,
            @RequestParam(required = false, defaultValue = "20") String size
    ) {
        return characterService.getAllCharacters(page, size);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<CharactersGetDTO> getCharacterById(@PathVariable Long id) {
        return characterService.getCharacterById(id);
    }

    @PutMapping
    public ResponseEntity<CharactersGetDTO> updateCharacterInfo(@RequestBody CharactersPutDTO data) {
        return characterService.updateCharacterInfo(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deleteCharacterById(@PathVariable Long id) {
        return characterService.deleteCharacterById(id);
    }
}
