package com.DesafioTinnova.DesafioTinnova.service;

import com.DesafioTinnova.DesafioTinnova.model.Veiculo;
import com.DesafioTinnova.DesafioTinnova.repository.VeiculoRepository;
import com.DesafioTinnova.DesafioTinnova.service.exception.VeiculoMissingValueException;
import com.DesafioTinnova.DesafioTinnova.service.exception.VeiculoNotFoundException;
import com.DesafioTinnova.DesafioTinnova.service.exception.VeiculosEmptyException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;


    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public Veiculo save(Veiculo veiculo) {
        if (verifyFields(veiculo)) {
            return this.veiculoRepository.save(veiculo);
        }
        return null;
    }

    private boolean verifyFields(Veiculo veiculo) {
        if (veiculo.getVeiculo() == null || veiculo.getMarca() == null
                || veiculo.getAno() == null || veiculo.getDescricao() == null
        ) {
            throw new VeiculoMissingValueException();
        }
        veiculo.setCreated();
        return true;
    }

    public List<Veiculo> findAll() {
        List<Veiculo> lista = this.veiculoRepository.findAll();
        if (lista.isEmpty()) {
            throw (new VeiculosEmptyException());
        } else return lista;
    }

    public Veiculo findById(Long id) {
        return veiculoRepository.findById(id)
                .orElseThrow(() -> new VeiculoNotFoundException(id));
    }

    public Veiculo update(Long id, Veiculo veiculoUpdate) {
        Veiculo veiculo = this.findById(id);
        veiculoUpdate.setId(id);
        veiculoUpdate.setUpdated();
        veiculoUpdate.setCreated(veiculo.getCreated());
        return veiculoRepository.save(veiculoUpdate);
    }

    public Veiculo patch(Long id, Veiculo veiculoNew) {
        Veiculo veiculoOld = this.findById(id);
        replaceFields(veiculoNew, veiculoOld);
        return veiculoRepository.save(veiculoNew);
    }

    private void replaceFields(Veiculo veiculoNew, Veiculo veiculoOld) {
        veiculoNew.setId(veiculoOld.getId());

        if (veiculoNew.getVeiculo() == null) {
            veiculoNew.setVeiculo(veiculoOld.getVeiculo());
        }

        if (veiculoNew.getMarca() == null) {
            veiculoNew.setMarca(veiculoOld.getMarca());
        }

        if (veiculoNew.getAno() == null) {
            veiculoNew.setAno(veiculoOld.getAno());
        }

        if (veiculoNew.getDescricao() == null) {
            veiculoNew.setDescricao(veiculoOld.getDescricao());
        }
        veiculoNew.setCreated(veiculoOld.getCreated());
        veiculoNew.setUpdated();
    }

    public String delete(Long id) {
        if (veiculoRepository.findById(id).isPresent()) {
            veiculoRepository.deleteById(id);
        } else throw new VeiculoNotFoundException(id);
        return "veiculo de id " + id + " excluido";
    }
}
