/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmdt.osworksj11.api.modeldto;

import com.gmdt.osworksj11.domain.model.EnumStatusOrdemServico;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 *
 * @author luiz
 */
public class OrdemServicoDTO {
    
    private Long id;
    private ClienteResumoDTO cliente;
    private String descricao;
    private BigDecimal preco;
    private EnumStatusOrdemServico status;
    private OffsetDateTime dataAbertura;
    private OffsetDateTime dataFinalizacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClienteResumoDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteResumoDTO cliente) {
        this.cliente = cliente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public EnumStatusOrdemServico getStatus() {
        return status;
    }

    public void setStatus(EnumStatusOrdemServico status) {
        this.status = status;
    }

    public OffsetDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(OffsetDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public OffsetDateTime getDataFinalizacao() {
        return dataFinalizacao;
    }

    public void setDataFinalizacao(OffsetDateTime dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }
        
}
