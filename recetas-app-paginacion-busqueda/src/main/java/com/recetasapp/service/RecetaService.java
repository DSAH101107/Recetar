
package com.recetasapp.service;

import com.recetasapp.entity.Receta;
import com.recetasapp.repository.RecetaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecetaService {

    private final RecetaRepository repo;

    public RecetaService(RecetaRepository repo) {
        this.repo = repo;
    }

    public Page<Receta> listar(String filtro, Pageable pageable) {
        if (filtro == null || filtro.isBlank()) {
            return repo.findAll(pageable);
        }
        return repo.findByTituloContainingIgnoreCaseOrIngredientesContainingIgnoreCase(
                filtro, filtro, pageable);
    }

    public Optional<Receta> obtener(Long id) { return repo.findById(id); }

    public Receta guardar(Receta receta) { return repo.save(receta); }

    public void eliminar(Long id) { repo.deleteById(id); }
}
