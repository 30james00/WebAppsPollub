package org.example.repositories;

import org.example.entities.Zadanie;
import org.springframework.data.repository.CrudRepository;

public interface ZadanieRepository extends CrudRepository<Zadanie, Long> {
}
