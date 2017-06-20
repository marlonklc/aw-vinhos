package com.algaworks.vinhos.controller;

import java.util.Optional;
import com.algaworks.vinhos.filter.VinhoFilter;
import com.algaworks.vinhos.model.TipoVinho;
import com.algaworks.vinhos.model.Vinho;
import com.algaworks.vinhos.repository.VinhosRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by Marlon on 12/02/2017.
 */
@Controller
@RequestMapping("/vinhos")
public class VinhosController {

    @Autowired
    private VinhosRepository repository;

    @GetMapping("/novo")
    public ModelAndView novo(Vinho vinho) {
        ModelAndView mv = new ModelAndView("vinhos/cadastro-vinho");
        mv.addObject("tipos", TipoVinho.values());
        return mv;
    }

    @PostMapping("/novo")
    public ModelAndView salvar(@Valid Vinho vinho, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return novo(vinho);
        }

        repository.save(vinho);
        attributes.addFlashAttribute("mensagem", "Vinho salvo com sucesso!");
        return new ModelAndView("redirect:/vinhos/novo");
    }

    @GetMapping
    public ModelAndView pesquisar(VinhoFilter vinhoFilter) {
    	ModelAndView mv = new ModelAndView("vinhos/pesquisa-vinho");
    	mv.addObject("vinhos", repository.findByNomeContainingIgnoreCase(
    	        Optional.ofNullable(vinhoFilter.getNome()).orElse("%")));

    	return mv;
    }
}
