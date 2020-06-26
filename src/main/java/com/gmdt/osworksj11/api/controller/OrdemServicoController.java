/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmdt.osworksj11.api.controller;

import com.gmdt.osworksj11.api.modeldto.OrdemServicoDTO;
import com.gmdt.osworksj11.api.modeldto.OrdemServicoInput;
import com.gmdt.osworksj11.domain.model.OrdemServico;
import com.gmdt.osworksj11.domain.repository.OrdemServicoRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/ordens-servico")
public class OrdemServicoController {
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServicoDTO criar(@Valid @RequestBody OrdemServicoInput ordemServicoInput) {
        
        OrdemServico ordemServico = toEntity(ordemServicoInput);
        return toDTO(ordemServico);
        
    }
    
    @GetMapping
    public List<OrdemServicoDTO> listar() {
        
        return toCollectionDTO(ordemServicoRepository.findAll());
        
    }
    
    private OrdemServicoDTO toDTO(OrdemServico ordemServico) {
        
        return modelMapper.map(ordemServico, OrdemServicoDTO.class);
        
    }
    
    private List<OrdemServicoDTO> toCollectionDTO(List<OrdemServico> ordensServico) {
        
        return ordensServico.stream().map(ordemServico -> toDTO(ordemServico))
                .collect(Collectors.toList());
        
    }
    
    private OrdemServico toEntity(OrdemServicoInput ordemServicoInput) {
        
        return modelMapper.map(ordemServicoInput, OrdemServico.class);
        
    }
}
