package com.naruto.api.characters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    @Query(value = "SELECT MAX(id) FROM Character")
    Long findMostRecentId();
}
