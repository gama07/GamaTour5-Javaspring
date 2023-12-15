package com.gamatour.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gamatour.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
