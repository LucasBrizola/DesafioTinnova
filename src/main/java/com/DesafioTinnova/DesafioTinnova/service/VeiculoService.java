package com.DesafioTinnova.DesafioTinnova.service;

import com.DesafioTinnova.DesafioTinnova.model.Veiculo;
import com.DesafioTinnova.DesafioTinnova.repository.VeiculoRepository;
import com.DesafioTinnova.DesafioTinnova.service.exception.DateWrongException;
import com.DesafioTinnova.DesafioTinnova.service.exception.VeiculoMissingValueException;
import com.DesafioTinnova.DesafioTinnova.service.exception.VeiculoNotFoundException;
import com.DesafioTinnova.DesafioTinnova.service.exception.VeiculosEmptyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;


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
        if(veiculo.getAno() < LocalDateTime.now().getYear() - 100 || veiculo.getAno() > LocalDateTime.now().getYear()){
            throw new DateWrongException();
        }
        veiculo.setCreated();
        return true;
    }

    public Map<String, Object> findAll(int page, int size) {
        List<Veiculo> lista;
        Pageable paging = PageRequest.of(page, size);

        Page<Veiculo> pageVeiculos = veiculoRepository.findAll(paging);
        lista = pageVeiculos.getContent();

        if (lista.isEmpty()) {
            throw (new VeiculosEmptyException());
        }

        Map<String, Object> response = new HashMap<>();
        response.put("veiculos", lista);
        response.put("veiculos não vendidos na base", contaVendidos());
        response.put("veiculos veiculos por fabricante", contaMarca());
        response.put("veiculos por década", contarVeiculosPorDecada());
        response.put("página atual", pageVeiculos.getNumber());
        response.put("itens totais", pageVeiculos.getTotalElements());
        response.put("páginas totais", pageVeiculos.getTotalPages());

        return response;
    }

    private long contaVendidos() {
        List<Veiculo> veiculos = veiculoRepository.findAll();
        return veiculos.stream().filter(Veiculo::isVendido).count();
    }

    private long contaMarca() {
        List<Veiculo> veiculos = veiculoRepository.findAll();
        return veiculos.stream().filter(veiculo -> "ferrari".equals(veiculo.getMarca())).count();
    }

    public Map<String, String> contarVeiculosPorDecada() {
        List<Veiculo> veiculos = veiculoRepository.findAll();
        List<String> listaDecadas = new ArrayList<>();
        String decada;

        for (Veiculo veiculo : veiculos) {
            Integer ano = veiculo.getAno();
            if (ano < 2000) {
                decada = Integer.toString(ano - 1900);

            }
            else{
                decada = Integer.toString(ano - 2000);
            }
            decada = String.valueOf(decada.charAt(0)).concat("0");
            listaDecadas.add(decada);
        }

        HashMap<String, String> map = new HashMap<>();

        for (String valor : listaDecadas) {
            long count = listaDecadas.stream().filter(valor::equals).count();
            map.put("Década de " + valor + ": ", count + " veículos");
        }

        return map;
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
