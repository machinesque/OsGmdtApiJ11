/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmdt.osworksj11.domain.repository;

import com.gmdt.osworksj11.domain.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author luiz
 */
@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    
    
    
}
