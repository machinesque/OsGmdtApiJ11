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
import com.gmdt.osworksj11.domain.service.GestaoOrdemServicoService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    
    @Autowired
    private GestaoOrdemServicoService gestaoOrdemServico;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServicoDTO criar(@Valid @RequestBody OrdemServicoInput ordemServicoInput) {
        
        OrdemServico ordemServico = toEntity(ordemServicoInput);
        return toDTO(gestaoOrdemServico.criar(ordemServico));
        
    }
    
    @GetMapping
    public List<OrdemServicoDTO> listar() {
        
        return toCollectionDTO(ordemServicoRepository.findAll());
        
    }

    @GetMapping("{id}")
    public ResponseEntity<OrdemServicoDTO> buscar(@PathVariable Long id) {
        
        Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(id);
        
        if (ordemServico.isPresent()) {
            OrdemServicoDTO ordemServicoDto = toDTO(ordemServico.get());
            return ResponseEntity.ok(ordemServicoDto);
        }
        
        return ResponseEntity.notFound().build();
        
    }
    
    @PutMapping("/{id}/finalizacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizar(@PathVariable Long id) {
        
        gestaoOrdemServico.finalizar(id);
        
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
