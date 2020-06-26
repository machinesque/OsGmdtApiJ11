/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmdt.osworksj11.api.controller;

import com.gmdt.osworksj11.domain.model.Cliente;
import com.gmdt.osworksj11.domain.repository.ClienteRepository;
import com.gmdt.osworksj11.domain.service.CrudClienteService;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private  ClienteRepository clienteRepository;
    
    @Autowired
    private CrudClienteService crudCliente;
    
    @GetMapping()
    public List<Cliente> listar() {

        return clienteRepository.findAll();
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long id) {
        
        Optional<Cliente> cliente = clienteRepository.findById(id);
        
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }
        
        return ResponseEntity.notFound().build();
        
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) 
    public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
        
        return crudCliente.salvar(cliente);
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long id, @RequestBody Cliente cliente) {
        
        if (clienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        cliente.setId(id);
        cliente = crudCliente.salvar(cliente);
        
        return ResponseEntity.ok(cliente);
        
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        
        if (clienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        crudCliente.excluir(id);
        
        return ResponseEntity.noContent().build();
        
    }
    
}
