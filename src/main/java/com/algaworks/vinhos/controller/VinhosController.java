package com.algaworks.vinhos.controller;

import com.algaworks.vinhos.model.TipoVinho;
import com.algaworks.vinhos.model.Vinho;
import com.algaworks.vinhos.repository.VinhosRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Marlon on 12/02/2017.
 */
@Controller
public class VinhosController {

    @Autowired
    private VinhosRepository repository;

    @GetMapping("/vinhos/novo")
    public ModelAndView novo(Vinho vinho) {
        ModelAndView mv = new ModelAndView("vinhos/cadastro-vinho");
        mv.addObject("tipos", TipoVinho.values());
        return mv;
    }

    @PostMapping("/vinhos/novo")
    public ModelAndView salvar(@Valid Vinho vinho, BindingResult result) {
        if (result.hasErrors()) {
            return novo(vinho);
        }

        repository.save(vinho);
        return new ModelAndView("redirect:/vinhos/novo");
    }
}
