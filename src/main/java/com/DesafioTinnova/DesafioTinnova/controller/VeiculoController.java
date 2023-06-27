package com.DesafioTinnova.DesafioTinnova.controller;

import com.DesafioTinnova.DesafioTinnova.model.Veiculo;
import com.DesafioTinnova.DesafioTinnova.service.VeiculoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @PostMapping
    public ResponseEntity<Veiculo> createVeiculo(@RequestBody Veiculo veiculo){
        Veiculo veiculoSalvo =  veiculoService.save(veiculo);
        return new ResponseEntity<>(veiculoSalvo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Veiculo>> findAll() {
        List<Veiculo> veiculos = veiculoService.findAll();
        return new ResponseEntity<>(veiculos, HttpStatus.OK) ;
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        Optional<Veiculo> veiculo = Optional.ofNullable(this.veiculoService.findById(id));
        return new ResponseEntity<>(veiculo,HttpStatus.OK);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") Long id, @RequestBody Veiculo veiculo) {
        Optional<Veiculo> veiculoNew = Optional.ofNullable(this.veiculoService.update(id, veiculo));
        return new ResponseEntity<>(veiculoNew, HttpStatus.OK);
    }

    @PatchMapping(value="/{id}")
    public ResponseEntity<?> patch(@PathVariable("id") Long id, @RequestBody Veiculo veiculo) {
        Optional<Veiculo> veiculoNew = Optional.ofNullable(this.veiculoService.patch(id, veiculo));
        return new ResponseEntity<>(veiculoNew, HttpStatus.OK);
    }

    @DeleteMapping(value="{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(veiculoService.delete(id), HttpStatus.OK);
    }



}
