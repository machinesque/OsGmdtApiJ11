/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmdt.osworksj11.api.controller;

import com.gmdt.osworksj11.api.modeldto.ComentarioDTO;
import com.gmdt.osworksj11.api.modeldto.ComentarioInput;
import com.gmdt.osworksj11.domain.exception.EntidadeNaoEncontradaException;
import com.gmdt.osworksj11.domain.model.Comentario;
import com.gmdt.osworksj11.domain.model.OrdemServico;
import com.gmdt.osworksj11.domain.repository.ComentarioRepository;
import com.gmdt.osworksj11.domain.repository.OrdemServicoRepository;
import com.gmdt.osworksj11.domain.service.GestaoOrdemServicoService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author luiz
 */
@RestController
@RequestMapping("/ordens-servico/{ordemServicoId}/comentarios")
public class ComentarioController {
    
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;
    
    @Autowired
    private GestaoOrdemServicoService gestaoOrdemServico;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @GetMapping
    public List<ComentarioDTO> listar(@PathVariable Long ordemServicoId) {
        
        OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId).orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de Serviço não encontrada."));
        
        return toCollectionDTO(ordemServico.getComentarios());
        
    }
    
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ComentarioDTO adicionar(@PathVariable Long ordemServicoId, @Valid @RequestBody ComentarioInput comentarioInput) {
        
        Comentario comentario = gestaoOrdemServico.adicionarComentario(ordemServicoId, comentarioInput.getDescricao());
        
        return toDTO(comentario);
        
    }
    
    private ComentarioDTO toDTO(Comentario comentario) {
        
        return modelMapper.map(comentario, ComentarioDTO.class);
        
    }
    
    private List<ComentarioDTO> toCollectionDTO(List<Comentario> comentarios) {
        
        return comentarios.stream().map(comentario -> toDTO(comentario)).collect(Collectors.toList());
        
    }
    
}
