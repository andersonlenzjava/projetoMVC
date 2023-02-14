package br.com.xavecoding.regescweb.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public ModelAndView hello() {
        ModelAndView mv = new ModelAndView("hello"); // nome do arquivo a ser reenderizado/xibido
        mv.addObject("nome", "Maria");
        return mv; // o spring vai rendereizar o arquivo que esta em templates com este nome
    }

    @GetMapping("/hello-model")
    public String hello(Model model) {
        model.addAttribute("nome", "Anderson Lenz");
        return "hello"; // o spring vai rendereizar o arquivo que esta em templates com este nome
    }

    @GetMapping("/hello-servllet")
    public String hello(HttpServletRequest request) {
        request.setAttribute("nome","Anderson");
        return "hello"; // o spring vai rendereizar o arquivo que esta em templates com este nome
    }

}
