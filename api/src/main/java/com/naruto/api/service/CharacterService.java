package com.naruto.api.service;

import com.naruto.api.characters.*;
import com.naruto.api.characters.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CharacterService {

    private final CharacterRepository repository;
    private final WebClient.Builder webClientBuilder;

    @Autowired
    public CharacterService(CharacterRepository repository, WebClient.Builder webClientBuilder) {
        this.repository = repository;
        this.webClientBuilder = webClientBuilder;
    }

    public CharactersPostDTO fetchCharacterById(Long id) {
        WebClient webClient = webClientBuilder.build();
        Mono<CharactersPostDTO> monoDTO = webClient.get()
                .uri("https://narutodb.xyz/api/character/" + id)
                .retrieve()
                .bodyToMono(CharactersPostDTO.class);

        return monoDTO.block();
    }

    public String fetchCharacters(String params) {
        WebClient webClient = webClientBuilder.build();
        Mono<String> monoDTO = webClient.get()
                .uri("https://narutodb.xyz/api/character" + params)
                .retrieve()
                .bodyToMono(String.class);

        return monoDTO.block();
    }

    public ResponseEntity postNewCharacter(CharactersPostDTO data) {
        try {
        Long lastId = repository.findMostRecentId();
        repository.save(new Character(data, lastId));
        return ResponseEntity.ok().body("Character created.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid character.");
        }
    }

    public ResponseEntity<Page<CharactersGetDTO>> getAllLocalCharacters(Pageable pageable) {
        try {
            Page<CharactersGetDTO> response = repository.findAll(pageable).map(CharactersGetDTO::new);
            if (response != null) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<String> getAllCharacters(String page, String size) {
        try {
            String params = "?page=" + page + "&limit=" + size;
            String response = fetchCharacters(params);
            if (response != null) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    public CharactersGetDTO getCharacterByIdDB(Long id) {
        return repository.findById(id).map(CharactersGetDTO::new).orElse(null);
    }

    public ResponseEntity<CharactersGetDTO> getCharacterById(Long id) {
        CharactersGetDTO response;
        try {
            CharactersGetDTO itemInDatabase = getCharacterByIdDB(id);

            if (itemInDatabase != null) {
                response = itemInDatabase;
            } else {
                CharactersPostDTO data = fetchCharacterById(id);
                Character savedCharacter = repository.save(new Character(data));
                response = new CharactersGetDTO(savedCharacter);

            }

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    public ResponseEntity deleteCharacterById(Long id) {
        try {
            repository.deleteById(id);
            return ResponseEntity.ok().body("Character deleted.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request.");
        }

    }
}
