package com.jmreisswitz.aegromini.ports.repository;

import com.jmreisswitz.aegromini.domain.Farm;

import java.util.List;
import java.util.Optional;

public interface FarmRepository {
    Farm save(Farm farm);

    List<Farm> listAll();

    Optional<Farm> findOneById(String id);

    void delete(String id);
}
