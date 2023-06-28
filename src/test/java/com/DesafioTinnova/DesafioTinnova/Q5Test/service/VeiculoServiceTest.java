package com.DesafioTinnova.DesafioTinnova.Q5Test.service;


import com.DesafioTinnova.DesafioTinnova.model.Marca;
import com.DesafioTinnova.DesafioTinnova.model.Veiculo;
import com.DesafioTinnova.DesafioTinnova.repository.MarcaRepository;
import com.DesafioTinnova.DesafioTinnova.repository.VeiculoRepository;
import com.DesafioTinnova.DesafioTinnova.service.VeiculoService;
import com.DesafioTinnova.DesafioTinnova.service.exception.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@SpringBootTest
public class VeiculoServiceTest {
    @InjectMocks
    private VeiculoService veiculoService;

    @Mock
    private static VeiculoRepository veiculoRepository;

    @Mock
    private static MarcaRepository marcaRepository;

    long DAY_IN_MS = 1000 * 60 * 60 * 24;
    Veiculo veiculo1 = new Veiculo.VeiculoBuilder("f50","ferrari",2020,"carro criado em homenagem ao 50 cent.").setCreated(new Date(System.currentTimeMillis() - (10 * DAY_IN_MS))).build();
    Veiculo veiculo2 = new Veiculo.VeiculoBuilder("aventador","lamborghini",2018,"carro que atinge velocidades altíssimas.").setCreated(new Date(System.currentTimeMillis())).build();
    Marca marca1 = new Marca();
    Marca marca2 = new Marca();

    public void configMarca(){
        marca1.setName("ferrari");
        marca2.setName("lamborghini");
        when(marcaRepository.findAll()).thenReturn(Arrays.asList(marca1,marca2));
    }

    //save veiculo tests
    @Test
    @DisplayName("deve salvar um veiculo")
    public void deveSalvarVeiculo() {
        configMarca();
        when(veiculoRepository.save(Mockito.any(Veiculo.class))).thenReturn(veiculo1);
        when(marcaRepository.findAll()).thenReturn(Arrays.asList(marca1,marca2));
        Veiculo veiculoSalvo = veiculoService.save(veiculo1);

        assertEquals("f50", veiculoSalvo.getVeiculo());
        assertEquals("ferrari", veiculoSalvo.getMarca());
        assertEquals(2020, veiculoSalvo.getAno());
    }

    @Test
    @DisplayName("VeiculoMissingValueException test")
    public void deveLancarVeiculoMissingValueException() {
        configMarca();
        when(veiculoRepository.save(Mockito.any(Veiculo.class))).thenThrow(VeiculoMissingValueException.class);

        assertThrows(VeiculoMissingValueException.class, () -> veiculoService.save(veiculo1));
    }

    @Test
    @DisplayName("DateWrongException test")
    public void deveLancarDateWrongException() {
        configMarca();
        when(veiculoRepository.save(Mockito.any(Veiculo.class))).thenThrow(DateWrongException.class);

        assertThrows(DateWrongException.class, () -> veiculoService.save(veiculo1));
    }

    @Test
    @DisplayName("MarcaNotExistException test")
    public void deveLancarMarcaNotExistException() {
        configMarca();
        when(veiculoRepository.save(Mockito.any(Veiculo.class))).thenThrow(MarcaNotExistException.class);

        assertThrows(MarcaNotExistException.class, () -> veiculoService.save(veiculo1));
    }

    //findAll veiculo tests
    @Test
    @DisplayName("Veiculo FindAll test")
    public void deveEncontrarTodosVeiculos() {
        List<Veiculo> veiculos = Arrays.asList(veiculo1, veiculo2);

        when(veiculoRepository.findAll(PageRequest.of(0, 3))).thenReturn(new PageImpl<>(veiculos));
        assertEquals(veiculo1, veiculos.get(0));
        assertEquals(veiculo2, veiculos.get(1));
        assertTrue(veiculos.size() == 2);
    }

    @Test
    @DisplayName("VeiculosEmptyException test")
    public void deveLancarVeiculosEmptyException() {
        configMarca();
        List<Veiculo> veiculos = Arrays.asList(veiculo1, veiculo2);

        when(veiculoRepository.findAll(PageRequest.of(0, 3))).thenReturn(new PageImpl<>(veiculos));
        when(veiculoRepository.findAll()).thenThrow(VeiculosEmptyException.class);

        assertThrows(VeiculosEmptyException.class, () -> veiculoService.findAll(0,3));
    }

    //findById veiculos tests
    @Test
    @DisplayName("Veiculo FindById test")
    public void deveFindByIdVeiculo() {
        when(veiculoRepository.findById(veiculo1.getId())).thenReturn(Optional.ofNullable(veiculo1));

        Veiculo veiculoNew = veiculoService.findById(veiculo1.getId());
        assertEquals(veiculo1.getId(), veiculoNew.getId());
        assertEquals(veiculo1.getVeiculo(), veiculoNew.getVeiculo());
    }

    @Test
    @DisplayName("VeiculoNotFoundException test")
    public void deveLancarVeiculoNotFoundException() {
        when(veiculoRepository.findById(0L)).thenThrow(VeiculoNotFoundException.class);

        assertThrows(VeiculoNotFoundException.class, () -> veiculoService.findById(0L));
    }

    //update Veiculo tests
    @Test
    @DisplayName("Veiculo update test")
    public void deveAtualizarVeiculo() {
        when(veiculoRepository.save(veiculo2)).thenReturn(veiculo2);
        when(veiculoRepository.findById(veiculo1.getId())).thenReturn(Optional.ofNullable(veiculo1));
        Veiculo veiculoNew = veiculoService.update(veiculo1.getId(), veiculo2);
        assertEquals(veiculo2.getId(), veiculoNew.getId());
        assertEquals(veiculo2.getVeiculo(), veiculoNew.getVeiculo());
    }

    @Test
    @DisplayName("Veiculo campo update test")
    public void deveAtualizarCampoVeiculo() {
        when(veiculoRepository.save(veiculo2)).thenReturn(veiculo2);
        when(veiculoRepository.findById(veiculo1.getId())).thenReturn(Optional.ofNullable(veiculo1));
        Veiculo veiculoNew = veiculoService.patch(veiculo1.getId(), veiculo2);
        assertEquals(veiculo2.getId(), veiculoNew.getId());
        assertEquals(veiculo2.getVeiculo(), veiculoNew.getVeiculo());
    }

    @Test
    @DisplayName("Cliente deleteById test")
    public void deveDeletarPorIdVeiculo() {
        when(veiculoRepository.findById(veiculo1.getId())).thenReturn(Optional.ofNullable(veiculo1));
        doAnswer((invocation) -> {
            when(veiculoRepository.findById(veiculo1.getId())).thenReturn(null);
            return null;
        }).when(veiculoRepository).deleteById(veiculo1.getId());
        veiculoService.delete(veiculo1.getId());
        assertNull(veiculoRepository.findById(veiculo1.getId()));
    }

}
