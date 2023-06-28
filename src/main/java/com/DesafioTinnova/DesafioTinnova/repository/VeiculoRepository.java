package com.DesafioTinnova.DesafioTinnova.repository;

import com.DesafioTinnova.DesafioTinnova.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    @Override
    Optional<Veiculo> findById(Long id);

    List<Veiculo> findByCreatedBetween(Date primeiraData, Date segundaData);
}
