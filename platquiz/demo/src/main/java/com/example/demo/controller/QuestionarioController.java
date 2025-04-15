package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.DTOs.CampoDTO;
import com.example.demo.DTOs.FormDTO;
import com.example.demo.DTOs.QuizDTO;
import com.example.demo.model.Campos;
import com.example.demo.model.Formularios;
import com.example.demo.model.Quiz;
import com.example.demo.model.Usuario;
import com.example.demo.repository.CamposRepository;
import com.example.demo.repository.FormulariosRepository;
import com.example.demo.repository.QuizRepository;
import com.example.demo.repository.UsuarioRepository;

@Controller
public class QuestionarioController {
    @Autowired
    private QuizRepository quizRepository;
@Autowired
private UsuarioRepository usuarioRepository;
@Autowired
private FormulariosRepository formulariosRepository;
@Autowired
private CamposRepository camposRepository;

@GetMapping("/")
public String home() {
    System.out.println("home page accessed");
    return "home.html";
}

    @GetMapping("criar-questionario")
    public String CriacaoQuiz() {
        return "CriacaoQuiz.html";
    }

    @PostMapping("/criar-questionario")
    public String CriarQuiz(@RequestBody QuizDTO dados){

        
        Usuario professor = usuarioRepository.findById(1L).orElse(null);
        if (professor == null) {
            return "redirect:/criar-questionario?erro=Professor-Nao-encontrado";
        }
        Quiz quiz = new Quiz();
        quiz.setNome(dados.nome);
        quiz.setN_perguntas(dados.n_perguntas);
        quiz.setR_escrita(dados.r_escrita);
        quiz.setCor_fundo(dados.cor_fundo);
        quiz.setCor_feedback(dados.cor_fundo);
        quiz.setMaterias(String.join(",", dados.materias));
        quiz.setProfessor(professor);

        quizRepository.save(quiz);
        
        Quiz ultimoQuiz = quizRepository.findTopByOrderByIdDesc();
        
        return "redirect:/criar-questionario2/"+ultimoQuiz.getId();
    }
    

    @GetMapping("criar-questionario2/{quizid}")
public String carregarFormulario(@PathVariable Integer quizid, Model model) { 
    Optional<Quiz> quiz = quizRepository.findById(Long.valueOf(quizid));
    
    if (quiz.isEmpty()) {
        return "redirect:/criar-questionario"; // ou uma página de erro personalizada
    }

    Optional<Formularios> formOptional = formulariosRepository.findByquiz(quiz.get());

        //rota para edição de questões futuras
    if (formOptional.isPresent()) {
        model.addAttribute("formulario", formOptional.get());
    }

    model.addAttribute("quizId", quiz.get().getId());
    
    return "CriarFormulario"; // sem .html se estiver em templates (Thymeleaf)
    }


    @PostMapping("/api/formularios")
@ResponseBody
public Formularios salvarFormulario(@RequestBody FormDTO formdata) {
    Optional<Quiz> quiz = quizRepository.findById(Long.valueOf(formdata.quiz_id));
    if (quiz.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz não encontrado");
    }

    Formularios form = new Formularios(quiz.get(), formdata.cor);
     formulariosRepository.save(form); // Aqui já temos o ID
    Optional<Formularios> formSalvo = formulariosRepository.findByquiz(quiz.get());
    return formSalvo.get(); // Retorna o formulário criado com ID
}
    
    @PostMapping("/api/campos")
    public String SalvarCampos(@RequestBody CampoDTO campodto) {
        Optional<Formularios> form = formulariosRepository.findById(Long.valueOf(campodto.formularioId));
        
        if (form.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Formulário não encontrado");
        }

        Formularios formulario = form.get();

        Campos campo = new Campos(formulario, campodto.label, campodto.campocodigo);
        camposRepository.save(campo);
        
        return "redirect:/";
    }
    
}
    
    
