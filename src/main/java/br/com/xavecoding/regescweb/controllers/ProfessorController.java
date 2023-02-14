package br.com.xavecoding.regescweb.controllers;

import br.com.xavecoding.regescweb.dto.RequisicaoNovoProfessor;
import br.com.xavecoding.regescweb.models.Professor;
import br.com.xavecoding.regescweb.models.StatusProfessor;
import br.com.xavecoding.regescweb.repositories.ProfessorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProfessorController {

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping("/professores")
    public ModelAndView index() {

        List<Professor> professores = professorRepository.findAll();
        ModelAndView mv = new ModelAndView("professores/index");
        mv.addObject("professores", professores);

        return mv;
    }

    // o POST da página faz este redirecionamento e chama o método GET dos professores
    @PostMapping("/professores")
    public String create(@Valid RequisicaoNovoProfessor formNovoProfessor, BindingResult bindingResult) {

        // se a validação tiver erros faz esta operação
        if(bindingResult.hasErrors()) {
            System.out.println("\n******************** TEM ERROS *****************\n");
            return "redirect:/professores/new";
        }
        else {
            Professor professor = formNovoProfessor.toProfessor();
            this.professorRepository.save(professor);

            return "redirect:/professores";
        }

    }

    //USO DE CONVENÇÕES AO FAZER A ENTRADA DE DADOS
    //usar os mesmos nomes dos atrubutos em todas as camadas, no model e no view

    @GetMapping("/professores/new")
    public ModelAndView nnew() {
        ModelAndView mv = new ModelAndView("professores/new");
        mv.addObject("statusProfessor", StatusProfessor.values());

        return mv;
    }

}
