
package com.recetasapp.repository;

import com.recetasapp.entity.Receta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Long> {

    Page<Receta> findByTituloContainingIgnoreCaseOrIngredientesContainingIgnoreCase(
        String titulo, String ingredientes, Pageable pageable
    );
}
