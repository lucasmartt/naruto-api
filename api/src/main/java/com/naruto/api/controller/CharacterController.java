package com.naruto.api.controller;

import com.naruto.api.characters.Character;
import com.naruto.api.characters.CharacterRepository;
import com.naruto.api.characters.CharactersGetDTO;
import com.naruto.api.characters.CharactersPostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private CharacterRepository repository;

    @PostMapping
    @Transactional
    public void register(@RequestBody CharactersPostDTO data) {
        repository.save(new Character(data));
    }

    @GetMapping
    public List<CharactersGetDTO> list() {
        return repository.findAll().stream().map(CharactersGetDTO::new);
    }

}
