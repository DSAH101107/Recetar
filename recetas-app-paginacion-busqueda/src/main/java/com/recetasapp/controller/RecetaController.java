
package com.recetasapp.controller;

import com.recetasapp.entity.Receta;
import com.recetasapp.service.RecetaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RecetaController {

    private final RecetaService service;

    public RecetaController(RecetaService service) {
        this.service = service;
    }

    @GetMapping({"/", "/recetas"})
    public String index(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String filtro
    ) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Receta> pagina = service.listar(filtro, pageable);

        model.addAttribute("pagina", pagina);
        model.addAttribute("filtro", filtro);

        return "index";
    }

    @GetMapping("/recetas/crear")
    public String crearForm(Model model) {
        model.addAttribute("receta", new Receta());
        return "crear";
    }

    @PostMapping("/recetas/crear")
    public String crearSubmit(@ModelAttribute Receta receta) {
        service.guardar(receta);
        return "redirect:/recetas";
    }

    @GetMapping("/recetas/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        Receta r = service.obtener(id).orElse(null);
        if (r == null) return "redirect:/recetas";
        model.addAttribute("receta", r);
        return "editar";
    }

    @PostMapping("/recetas/editar/{id}")
    public String editarSubmit(@PathVariable Long id, @ModelAttribute Receta receta) {
        receta.setId(id);
        service.guardar(receta);
        return "redirect:/recetas";
    }

    @PostMapping("/recetas/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/recetas";
    }
}
