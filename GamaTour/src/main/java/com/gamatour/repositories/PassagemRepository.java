package com.gamatour.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gamatour.models.Passagem;

public interface PassagemRepository extends JpaRepository<Passagem, Long> {

}
