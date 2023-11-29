package com.desafio.user.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.desafio.user.model.Usuario;
import com.desafio.user.repository.UsuarioRepository;

@Controller
public class UsuarioController {

@Autowired
private UsuarioRepository repository;


 @GetMapping("/usuario")
    public ModelAndView usuario(){

        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("usuario");


        modelAndView.addObject("allUsuario", repository.findAll());

        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam(value = "nome") final String nome) {

        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("usuario");

        modelAndView.addObject("allUsuario", repository.findByNameContainingIgnoreCase(nome));

        return modelAndView;
    }


    @GetMapping("/new-usuario")
    public ModelAndView newUsuario(){

        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("new-usuario");

        modelAndView.addObject("usuario", new Usuario());
        return modelAndView;
    }

    @PostMapping("/usuario")
    public String createJedi(@Valid @ModelAttribute Usuario usuario, BindingResult result, RedirectAttributes redirectAttributes){

        if(result.hasErrors()){
            return "new-usuario";
        }

        repository.save(usuario);

        redirectAttributes.addFlashAttribute("message", "Usuario cadatrado com sucesso.");

        return "redirect:usuario";

    }

    @GetMapping("/usuario/{id}/delete")
    public String deleteUsuario(@PathVariable("id") final Long id, RedirectAttributes redirectAttributes) {

        final Optional<Usuario> usuario = repository.findById(id);

        repository.delete(usuario.get());

        redirectAttributes.addFlashAttribute("message", "Usuario removido com sucesso.");

        return "redirect:/jedi" ;
    }

    @GetMapping("/usuario/{id}/update")
    public String updateJedi(@PathVariable("id") final Long id, Model model) {

        final Optional<Usuario> usuario = repository.findById(id);

        model.addAttribute("jedi", usuario.get());

        return "edit-usuario";
    }

}
