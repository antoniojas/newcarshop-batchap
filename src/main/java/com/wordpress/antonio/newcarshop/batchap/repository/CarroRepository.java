package com.wordpress.antonio.newcarshop.batchap.repository;

import com.wordpress.antonio.newcarshop.batchap.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {
}
