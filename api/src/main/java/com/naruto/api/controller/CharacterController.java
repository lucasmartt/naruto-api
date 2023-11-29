package com.naruto.api.controller;

import com.naruto.api.characters.*;
import com.naruto.api.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @PostMapping
    @Transactional
    public void postNewCharacter(@RequestBody CharactersPostDTO data) {
        characterService.postNewCharacter(data);
    }

    @GetMapping(path = "/local", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<CharactersGetDTO> getAllLocalCharacters(Pageable pageable) {
        return characterService.getAllLocalCharacters(pageable);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllCharacters(
            @RequestParam(required = false, defaultValue = "1") String page,
            @RequestParam(required = false, defaultValue = "20") String size
    ) {
        return characterService.getAllCharacters(page, size);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public CharactersGetDTO getCharacterById(@PathVariable Long id) {
        return characterService.getCharacterById(id);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteCharacterById(@PathVariable Long id) {
        characterService.deleteCharacterById(id);;
    }
}
