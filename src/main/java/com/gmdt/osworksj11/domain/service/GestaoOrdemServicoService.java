/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmdt.osworksj11.domain.service;

import com.gmdt.osworksj11.domain.exception.EntidadeNaoEncontradaException;
import com.gmdt.osworksj11.domain.exception.NegocioException;
import com.gmdt.osworksj11.domain.model.Cliente;
import com.gmdt.osworksj11.domain.model.Comentario;
import com.gmdt.osworksj11.domain.model.EnumStatusOrdemServico;
import com.gmdt.osworksj11.domain.model.OrdemServico;
import com.gmdt.osworksj11.domain.repository.ClienteRepository;
import com.gmdt.osworksj11.domain.repository.ComentarioRepository;
import com.gmdt.osworksj11.domain.repository.OrdemServicoRepository;
import java.time.OffsetDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author luiz
 */
@Service
public class GestaoOrdemServicoService {
    
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private ComentarioRepository comentarioRepository;
    
    public OrdemServico criar(OrdemServico ordemServico) {
        
        Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
                .orElseThrow(() -> new NegocioException("Cliente não encontrado!"));
        
        ordemServico.setCliente(cliente);
        ordemServico.setStatus(EnumStatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(OffsetDateTime.now());
        
        return ordemServicoRepository.save(ordemServico);
        
    }
    
    public void finalizar(Long id) {
        
        OrdemServico ordemServico = buscarPorId(id);
        
        ordemServico.finalizar();
        
        ordemServicoRepository.save(ordemServico);
        
    }
    
    public Comentario adicionarComentario(Long ordemServicoId, String descricao) {
        
        OrdemServico ordemServico = buscarPorId(ordemServicoId);
        
        Comentario comentario = new Comentario();
        comentario.setDataEnvio(OffsetDateTime.now());
        comentario.setDescricao(descricao);
        comentario.setOrdemServico(ordemServico);
        
        return comentarioRepository.save(comentario);
        
    }
    
    private OrdemServico buscarPorId(Long id) {
        return ordemServicoRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de Serviço não encontrada."));
    }
    
}
