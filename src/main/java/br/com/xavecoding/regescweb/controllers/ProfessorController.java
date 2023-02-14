package br.com.xavecoding.regescweb.controllers;

import br.com.xavecoding.regescweb.dto.RequisicaoFormProfessor;
import br.com.xavecoding.regescweb.models.Professor;
import br.com.xavecoding.regescweb.models.StatusProfessor;
import br.com.xavecoding.regescweb.repositories.ProfessorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/professores")
public class ProfessorController {

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping("")
    public ModelAndView index() {

        List<Professor> professores = professorRepository.findAll();
        ModelAndView mv = new ModelAndView("professores/index");
        mv.addObject("professores", professores);

        return mv;
    }

    //USO DE CONVENÇÕES AO FAZER A ENTRADA DE DADOS
    //usar os mesmos nomes dos atrubutos em todas as camadas, no model e no view

    // passagem do parametro requisicao para ele ter ele carregado
    @GetMapping("/new")
    public ModelAndView nnew(RequisicaoFormProfessor formNovoProfessor) {
        ModelAndView mv = new ModelAndView("professores/new");
        mv.addObject("listaStatusProfessor", StatusProfessor.values());

        return mv;
    }

    // o POST da página faz este redirecionamento e chama o método GET dos professores
    @PostMapping("")
    public ModelAndView create(@Valid RequisicaoFormProfessor formNovoProfessor, BindingResult bindingResult) {

        // se a validação tiver erros faz esta operação
        if (bindingResult.hasErrors()) {
            System.out.println("\n******************** TEM ERROS *****************\n");

            ModelAndView mv = new ModelAndView("professores/new");

            // atributo que é carregado novamente ao entrar na clase de erros
            mv.addObject("listaStatusProfessor", StatusProfessor.values());
            return mv;
        }
        else {
            Professor professor = formNovoProfessor.toProfessor();
            this.professorRepository.save(professor);

//          após criar os professores chava o seu detalhamento através da string montada abaixo
            return new ModelAndView("redirect:/professores/" + professor.getId());
        }

    }

    @GetMapping("/{id}")
    public ModelAndView show(@PathVariable Long id) {
       Optional<Professor> optionalProfessor = this.professorRepository.findById(id);

       if(optionalProfessor.isPresent()) {
           Professor professor =optionalProfessor.get();

           ModelAndView mv = new ModelAndView("professores/show");
           mv.addObject("professor", professor);

           return mv;
       }
       else {
            System.out.println("$$$$$$$$$$$ NÃO FOI ENCONTRADO O PROFESSOR DE ID " + id + " $$$$$$$$$$");
           //poderia fazer uma mensagem de erro, mas redireciona para a listagem
           return new ModelAndView("redirect:/professores");
       }
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Long id, RequisicaoFormProfessor formNovoProfessor) {
        Optional<Professor> optionalProfessor = this.professorRepository.findById(id);

        if(optionalProfessor.isPresent()) {
            Professor professor = optionalProfessor.get();
            formNovoProfessor.fromProfessor(professor);

//            modifica o objeto reqnovoprof que é passado para view
            ModelAndView mv = new ModelAndView("professores/edit");
            mv.addObject("professorId",professor.getId());
//            passagem deste atributo junto com a requisição
            mv.addObject("listaStatusProfessor", StatusProfessor.values());

            return mv;
        }
            else {
            System.out.println("$$$$$$$$$$$ NÃO FOI ENCONTRADO O PROFESSOR DE ID " + id + " $$$$$$$$$$");
            //poderia fazer uma mensagem de erro, mas redireciona para a listagem
            return new ModelAndView("redirect:/professores");
        }
    }

    @PostMapping("/{id}")
    public ModelAndView update(@PathVariable Long id, @Valid RequisicaoFormProfessor formNovoProfessor,
                               BindingResult bindingResult) {
        // se a validação tiver erros faz esta operação
        if (bindingResult.hasErrors()) {
            System.out.println("\n******************** TEM ERROS *****************\n");

            ModelAndView mv = new ModelAndView("professores/edit");
            // passagem do id do objeto para o caso de entrar no form erro
            mv.addObject("professorId", id);

            // atributo que é carregado novamente ao entrar na clase de erros
            mv.addObject("listaStatusProfessor", StatusProfessor.values());
            return mv;

        }
        else {
            Optional<Professor> optionalProfessor = this.professorRepository.findById(id);

            if(optionalProfessor.isPresent()) {
                Professor professor = formNovoProfessor.toProfessor(optionalProfessor.get());
                this.professorRepository.save(professor);

                // redireciona para os detalhes do recurso atualizado (da mesma forma quando é criado)
                return new ModelAndView("redirect:/professores/" + professor.getId());

            }  else {
                System.out.println("##### NÃO FOI ENCONTRADO O PROFESSOR DE ID " + id + " ####");
                //poderia fazer uma mensagem de erro, mas redireciona para a listagem
                return new ModelAndView("redirect:/professores");
            }
        }
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        // tratamento do erro para evitar que o usuario saiba que fez algo errado
        try {
            this.professorRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            System.out.println(e);
            return "redirect:/professores";
        }
        return "redirect:/professores";
    }




    // para o objeto ser encontrado pela view new
//    @ModelAttribute(value = "requisicaoNovoProfessor")
//    public RequisicaoNovoProfessor getRequisicaoNovoProfessor()
//    {
//        return new RequisicaoNovoProfessor();
//    }

}
