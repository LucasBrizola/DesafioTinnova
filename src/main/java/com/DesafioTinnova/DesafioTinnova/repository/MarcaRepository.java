package com.DesafioTinnova.DesafioTinnova.repository;

import com.DesafioTinnova.DesafioTinnova.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {
}
