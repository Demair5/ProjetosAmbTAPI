package com.projeto.projeto.controllers;

import com.projeto.projeto.entities.ProjetosAmb;
import com.projeto.projeto.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjetoController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<ProjetosAmb> getAllProjects() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/category/{category}")
    public List<ProjetosAmb> getProjectsByCategory(@PathVariable String category) {
        return usuarioRepository.findByCategory(category);
    }

    @GetMapping("/status/{status}")
    public List<ProjetosAmb> getProjectsByStatus(@PathVariable String status) {
        return usuarioRepository.findByStatus(status);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetosAmb> getProjectById(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .map(project -> ResponseEntity.ok().body(project))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ProjetosAmb createProject(@RequestBody ProjetosAmb project) {
        return usuarioRepository.save(project);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjetosAmb> updateProject(@PathVariable Long id, @RequestBody ProjetosAmb projectDetails) {
        return usuarioRepository.findById(id)
                .map(project -> {
                    project.setName(projectDetails.getNome());
                    project.setCategory(projectDetails.getCategory());
                    project.setStatus(projectDetails.getStatus());
                    project.setDescription(projectDetails.getDescription());
                    ProjetosAmb updatedProject = usuarioRepository.save(project);
                    return ResponseEntity.ok().body(updatedProject);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .map(project -> {
                    usuarioRepository.delete(project);
                    return ResponseEntity.ok().<Void>build();
                }).orElse(ResponseEntity.notFound().build());
    }
}