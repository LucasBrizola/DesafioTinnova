package com.DesafioTinnova.DesafioTinnova.controller;

import com.DesafioTinnova.DesafioTinnova.model.Veiculo;
import com.DesafioTinnova.DesafioTinnova.service.VeiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "criar um veículo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "veículo criado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Veiculo.class))}),
            @ApiResponse(responseCode = "400", description = "body com formato inválido")})
    @PostMapping
    public ResponseEntity<Veiculo> createVeiculo(@Parameter(description = "veiculo que será cadastrado") @RequestBody Veiculo veiculo) {
        Veiculo veiculoSalvo = veiculoService.save(veiculo);
        return new ResponseEntity<>(veiculoSalvo, HttpStatus.CREATED);
    }

    @Operation(summary = "buscar todos os veículos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "veiculo encontrado por id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Veiculo.class))}),
            @ApiResponse(responseCode = "500", description = "sem veiculos cadastrados")})
    @GetMapping
    public ResponseEntity<List<Veiculo>> findAll() {
        List<Veiculo> veiculos = veiculoService.findAll();
        return new ResponseEntity<>(veiculos, HttpStatus.OK);
    }

    @Operation(summary = "buscar veículo por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "lista de veiculos",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Veiculo.class)))}),
            @ApiResponse(responseCode = "500", description = "veiculo nao encontrado")})
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@Parameter(description = "id do veiculo que será buscado") @PathVariable("id") Long id) {
        Optional<Veiculo> veiculo = Optional.ofNullable(this.veiculoService.findById(id));
        return new ResponseEntity<>(veiculo, HttpStatus.OK);
    }

    @Operation(summary = "atualizar veículo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "veículo atualizado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Veiculo.class))}),
            @ApiResponse(responseCode = "400", description = "body inválido"),
            @ApiResponse(responseCode = "500", description = "veiculo nao encontrado")})
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> editar(@Parameter(description = "id do veiculo que será atualizado") @PathVariable("id") Long id, @Parameter(description = "veiculo que será atualizado") @RequestBody Veiculo veiculo) {
        Optional<Veiculo> veiculoNew = Optional.ofNullable(this.veiculoService.update(id, veiculo));
        return new ResponseEntity<>(veiculoNew, HttpStatus.OK);
    }

    @Operation(summary = "atualizar uma parte do veículo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "veículo atualizado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Veiculo.class))}),
            @ApiResponse(responseCode = "400", description = "body inválido"),
            @ApiResponse(responseCode = "500", description = "veiculo nao encontrado")})
    @PatchMapping(value = "/{id}")
    public ResponseEntity<?> patch(@Parameter(description = "id veiculo que será atualizado") @PathVariable("id") Long id, @Parameter(description = "campo do veiculo que será atualizado") @RequestBody Veiculo veiculo) {
        Optional<Veiculo> veiculoNew = Optional.ofNullable(this.veiculoService.patch(id, veiculo));
        return new ResponseEntity<>(veiculoNew, HttpStatus.OK);
    }

    @Operation(summary = "atualizar uma parte do veículo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "veículo deletado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "500", description = "veiculo nao encontrado")})
    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> deleteById(@Parameter(description = "id do veiculo que será excluído") @PathVariable("id") Long id) {
        return new ResponseEntity<>(veiculoService.delete(id), HttpStatus.OK);
    }


}
