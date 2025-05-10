package com.example.demo.controller;

import java.util.Optional;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.DTOs.AvaliacaoRequest;
import com.example.demo.DTOs.CampoDTO;
import com.example.demo.DTOs.Faixa;
import com.example.demo.DTOs.FormDTO;
import com.example.demo.DTOs.QuizDTO;
import com.example.demo.model.Campos;
import com.example.demo.model.Faixas;
import com.example.demo.model.Formularios;
import com.example.demo.model.Questoes;
import com.example.demo.model.Quiz;
import com.example.demo.model.Usuario;
import com.example.demo.model.av_tipo1;
import com.example.demo.model.av_tipo2;
import com.example.demo.model.av_tipo3;
import com.example.demo.model.av_tipo4;
import com.example.demo.repository.CamposRepository;
import com.example.demo.repository.FaixasRepository;
import com.example.demo.repository.FormulariosRepository;
import com.example.demo.repository.QuestoesRepository;
import com.example.demo.repository.QuizRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.repository.av_tipo1Repository;
import com.example.demo.repository.av_tipo2Repository;
import com.example.demo.repository.av_tipo3Repository;
import com.example.demo.repository.av_tipo4Repository;

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
    @Autowired
    private av_tipo1Repository av_tipo1Repository;
    @Autowired
    private av_tipo2Repository av_tipo2Repository;
    @Autowired
    private av_tipo3Repository av_tipo3Repository;
    @Autowired
    private av_tipo4Repository av_tipo4Repository;
    @Autowired
    private FaixasRepository faixasRepository;
    @Autowired
    private QuestoesRepository questoesRepository;

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
    public String CriarQuiz(@RequestBody QuizDTO dados) {

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

        return "redirect:/criar-questionario2/" + ultimoQuiz.getId();
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
        model.addAttribute("BackGroundColor", quiz.get().getCor_fundo());

        return "CriarFormulario"; // sem .html se estiver em templates (Thymeleaf)
    }

    @PostMapping("/api/formularios")
    @ResponseBody
    public Formularios salvarFormulario(@RequestBody FormDTO formdata) {
        if (!formdata.hasCampos) {
            return new Formularios("NoForm"); // ou uma página de erro personalizada 
        }
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

        return "redirect:/TipoAv/" + formulario.getQuiz().getId(); // ou uma página de erro personalizada
    }

    @GetMapping("/TipoAv/{quizid}")
    public String TipoAv(@PathVariable Long quizid, Model model) {
        Optional<Quiz> quiz = quizRepository.findById(Long.valueOf(quizid));

        if (quiz.isEmpty()) {
            return "redirect:/criar-questionario"; // ou uma página de erro personalizada
        }
        model.addAttribute("quizid", quiz.get().getId());
        model.addAttribute("BackGroundColor", quiz.get().getCor_fundo());
        if (av_tipo1Repository.findByquiz(quiz.get()).isPresent()
                || av_tipo2Repository.findByquiz(quiz.get()).isPresent()
                || av_tipo3Repository.findByquiz(quiz.get()).isPresent()
                || av_tipo4Repository.findByquiz(quiz.get()).isPresent()) {
            return "redirect:/criarquestoes/" + quiz.get().getId();
        }
        return "TipoAv"; // sem .html se estiver em templates (Thymeleaf)
    }

    @PostMapping("/avaliacoes")
    public ResponseEntity<String> criarAvaliacao(@RequestBody AvaliacaoRequest request) {
        try {
            int tipo = request.getTipo();
            Optional<Quiz> quizOptional = quizRepository.findById(request.getQuizId());
            if (quizOptional.isEmpty()) {
                return ResponseEntity.badRequest().body("Quiz não encontrado.");
            }

            Quiz quiz = quizOptional.get();

            if (tipo == 1) {
                av_tipo1 avaliacao = new av_tipo1();
                avaliacao.setAc_necessario(request.getValor());
                avaliacao.setQuiz(quiz);
                av_tipo1Repository.save(avaliacao);

            } else if (tipo == 2) {
                av_tipo2 avaliacao = new av_tipo2();
                avaliacao.setPorcent_necessario(request.getValor());
                avaliacao.setQuiz(quiz);
                av_tipo2Repository.save(avaliacao);

            } else if (tipo == 3) {
                av_tipo3 av3 = new av_tipo3();
                av3.setQuiz(quiz);
                av3 = av_tipo3Repository.save(av3);

                for (Faixa faixaReq : request.getFaixas()) {
                    Faixas faixa = new Faixas();
                    faixa.setDe(faixaReq.getDe());
                    faixa.setAte(faixaReq.getAte());
                    faixa.setMsg(faixaReq.getMensagem());
                    faixa.setAv_tipo3(av3);
                    faixasRepository.save(faixa);
                }

            } else if (tipo == 4) {
                av_tipo4 av4 = new av_tipo4();
                av4.setQuiz(quiz);
                av4 = av_tipo4Repository.save(av4);

                for (Faixa faixaReq : request.getFaixas()) {
                    Faixas faixa = new Faixas();
                    faixa.setDe(faixaReq.getDe());
                    faixa.setAte(faixaReq.getAte());
                    faixa.setMsg(faixaReq.getMensagem());
                    faixa.setAv_tipo4(av4);
                    faixasRepository.save(faixa);
                }

            } else {
                return ResponseEntity.badRequest().body("Tipo inválido.");
            }

            return ResponseEntity.ok("Avaliação salva com sucesso.");

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao salvar avaliação: " + e.getMessage());
        }
    }

    @GetMapping("/criarquestoes/{quizid}")
    public String CriarQuestoes(@PathVariable Long quizid, Model model) {
        Optional<Quiz> quiz = quizRepository.findById(Long.valueOf(quizid));
        if (quiz.isEmpty()) {
            return "redirect:/criar-questionario"; // ou uma página de erro personalizada
        }
        int tipo = 0;
        if (av_tipo1Repository.findByquiz(quiz.get()).isPresent()) {
            tipo = 1;
        } else if (av_tipo2Repository.findByquiz(quiz.get()).isPresent()) {
            tipo = 2;
        } else if (av_tipo3Repository.findByquiz(quiz.get()).isPresent()) {
            tipo = 3;
        } else if (av_tipo4Repository.findByquiz(quiz.get()).isPresent()) {
            tipo = 4;
        }

        if (tipo != 0) {

            List<Questoes> questoes = questoesRepository.findByQuiz(quiz.get());
            if (questoes.isEmpty()) {
                return "redirect:/criarquestoes/" + quiz.get().getId() + "/1/" + tipo; // ou uma página de erro personalizad
            } else {
                return "redirect:/criarquestoes/" + quiz.get().getId() + "/" + (questoes.size() + 1) + "/" + tipo; // ou uma página de erro personalizada
            }
        } else {
            return "redirect:/TipoAv/" + quiz.get().getId(); // ou uma página de erro personalizada
        }
    }

}
