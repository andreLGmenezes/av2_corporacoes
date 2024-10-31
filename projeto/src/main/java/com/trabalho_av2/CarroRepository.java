package com.trabalho_av2;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarroRepository extends JpaRepository<Carro, UUID> {}
