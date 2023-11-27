package com.naruto.api.controller;

import com.naruto.api.characters.Character;
import com.naruto.api.characters.CharacterRepository;
import com.naruto.api.characters.CharactersGetDTO;
import com.naruto.api.characters.CharactersPostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


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
    public Page<CharactersGetDTO> list(Pageable pageable) {
        return repository.findAll(pageable).map(CharactersGetDTO::new);
    }

    @GetMapping("/{id}")
    public CharactersGetDTO getById(@PathVariable Long id) {
        return repository.findById(id).map(CharactersGetDTO::new).orElse(null);
    }

}
