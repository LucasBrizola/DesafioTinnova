package com.DesafioTinnova.DesafioTinnova.service;

import com.DesafioTinnova.DesafioTinnova.model.Marca;
import com.DesafioTinnova.DesafioTinnova.model.Veiculo;
import com.DesafioTinnova.DesafioTinnova.repository.MarcaRepository;
import com.DesafioTinnova.DesafioTinnova.repository.VeiculoRepository;
import com.DesafioTinnova.DesafioTinnova.service.exception.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;


@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    private final MarcaRepository marcaRepository;


    public VeiculoService(VeiculoRepository veiculoRepository, MarcaRepository marcaRepository) {
        this.veiculoRepository = veiculoRepository;
        this.marcaRepository = marcaRepository;
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
        if (veiculo.getAno() < LocalDateTime.now().getYear() - 100 || veiculo.getAno() > LocalDateTime.now().getYear()) {
            throw new DateWrongException();
        }
        veiculo.setMarca(veiculo.getMarca().toLowerCase());
        List<Marca> marcas = marcaRepository.findAll();
        boolean flag = false;
        for (Marca marca : marcas) {
            if (marca.getName().equals(veiculo.getMarca())) {
                flag = true;
                break;
            }
        }
        if(flag){
            veiculo.setCreated();
            return flag;
        }else throw new MarcaNotExistException(veiculo.getMarca());
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
        response.put("veiculos não vendidos na base", contaNaoVendidos());
        response.put("veiculos por fabricante", contaMarcas());
        response.put("veiculos por década", contarVeiculosPorDecada());
        response.put("veiculos cadastrados esta semana", exibirVeiculosUltimaSemana());
        response.put("página atual", pageVeiculos.getNumber()+1);
        response.put("itens totais", pageVeiculos.getTotalElements());
        response.put("páginas totais", pageVeiculos.getTotalPages());

        return response;
    }

    private long contaNaoVendidos() {
        List<Veiculo> veiculos = veiculoRepository.findAll();
        return veiculos.stream().filter(veiculo -> !veiculo.isVendido()).count();
    }

    private Map<String, Long> contaMarcas() {
        List<Marca> marcas = marcaRepository.findAll();
        List<Veiculo> veiculos = veiculoRepository.findAll();
        HashMap<String, Long> map = new HashMap<>();

        for (Marca marca : marcas) {
            long count = veiculos.stream().filter(veiculo -> marca.getName().equals(veiculo.getMarca())).count();
            map.put("veiculos " + marca.getName() + ": ", count);
        }
        return map;
    }

    public Map<String, String> contarVeiculosPorDecada() {
        List<Veiculo> veiculos = veiculoRepository.findAll();
        List<String> listaDecadas = new ArrayList<>();
        String decada;

        for (Veiculo veiculo : veiculos) {
            Integer ano = veiculo.getAno();
            if (ano < 2000) {
                decada = Integer.toString(ano - 1900);

            } else {
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

    public List<Veiculo> exibirVeiculosUltimaSemana() {
        long DAY_IN_MS = 1000 * 60 * 60 * 24;
        return veiculoRepository.findByCreatedBetween(new Date(System.currentTimeMillis() - (7 * DAY_IN_MS)), new Date(System.currentTimeMillis()));
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
