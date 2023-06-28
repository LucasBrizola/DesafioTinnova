package com.DesafioTinnova.DesafioTinnova.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "clientes")
public class Veiculo {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotBlank
    private String veiculo;
    @Column(nullable = false)
    @NotBlank
    private String marca;
    @Column(nullable = false)
    @NotBlank
    private Integer ano;
    private String descricao;
    private boolean vendido;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Date created;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Date updated;

    public Veiculo() {
    }

    private Veiculo(VeiculoBuilder builder) {
        this.veiculo = builder.veiculo;
        this.marca = builder.marca;
        this.ano = builder.ano;
        this.descricao = builder.descricao;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isVendido() {
        return vendido;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setCreated() {
        this.created = new Date(System.currentTimeMillis());
    }

    public void setUpdated() {
        this.updated = new Date(System.currentTimeMillis());
    }

    public static class VeiculoBuilder {
        private Long id;
        private String veiculo;
        private String marca;
        private Integer ano;
        private String descricao;
        private boolean vendido;
        private Date created;
        private Date updated;

        public VeiculoBuilder(String veiculo, String marca, Integer ano, String descricao) {
            this.veiculo = veiculo;
            this.marca = marca;
            this.ano = ano;
            this.descricao = descricao;
        }

        public VeiculoBuilder setVendido(boolean vendido) {
            this.vendido = vendido;
            return this;
        }

        public VeiculoBuilder setCreated(Date created) {
            this.created = created;
            return this;
        }

        public VeiculoBuilder setUpdated(Date updated) {
            this.updated = updated;
            return this;
        }

        public Veiculo build() {
            return new Veiculo(this);
        }
    }
}
