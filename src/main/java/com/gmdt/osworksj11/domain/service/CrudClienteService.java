/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmdt.osworksj11.domain.service;

import com.gmdt.osworksj11.domain.exception.NegocioException;
import com.gmdt.osworksj11.domain.model.Cliente;
import com.gmdt.osworksj11.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author luiz
 */
@Service
public class CrudClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente salvar(Cliente cliente) {

        Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());

        if (clienteExistente != null && !clienteExistente.equals(cliente)) {
            throw new NegocioException("Já existe um cliente cadastrado com esse e-mail!");
        }

        return clienteRepository.save(cliente);

    }

    public void excluir(Long id) {

        clienteRepository.deleteById(id);

    }

}
