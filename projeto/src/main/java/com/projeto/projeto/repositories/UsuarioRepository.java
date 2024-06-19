package com.projeto.projeto.repositories;

import com.projeto.projeto.entities.ProjetosAmb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<ProjetosAmb, Long> {
    List<ProjetosAmb> findByCategory(String category);
    List<ProjetosAmb> findByStatus(String status);
}