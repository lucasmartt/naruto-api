package com.naruto.api.controller;

import com.naruto.api.characters.Character;
import com.naruto.api.characters.CharacterRepository;
import com.naruto.api.characters.CharactersPostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
