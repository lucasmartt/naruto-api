package com.naruto.api.controller;

import com.naruto.api.characters.CharactersPostDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    @PostMapping
    public void register(@RequestBody CharactersPostDTO json) {
        System.out.println(json);
    }

}
