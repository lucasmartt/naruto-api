package com.naruto.api.service;

import com.naruto.api.characters.*;
import com.naruto.api.characters.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public void postNewCharacter(CharactersPostDTO data) {
        Long lastId = repository.findMostRecentId();
        repository.save(new Character(data, lastId));
    }

    public Page<CharactersGetDTO> getAllLocalCharacters(Pageable pageable) {
        return repository.findAll(pageable).map(CharactersGetDTO::new);
    }

    public String getAllCharacters(String page, String size) {
        String params = "?page=" + page + "&limit=" + size;
        return fetchCharacters(params);
    }

    public CharactersGetDTO getCharacterByIdDB(Long id) {
        return repository.findById(id).map(CharactersGetDTO::new).orElse(null);
    }

    public CharactersGetDTO getCharacterById(Long id) {
        CharactersGetDTO itemInDatabase = getCharacterByIdDB(id);
        if (itemInDatabase != null) {
            return itemInDatabase;
        } else {
            CharactersPostDTO data = fetchCharacterById(id);
            Character savedCharacter = repository.save(new Character(data));
            return new CharactersGetDTO(savedCharacter);
        }
    }

    public void deleteCharacterById(Long id) {
        repository.deleteById(id);
    }
}
