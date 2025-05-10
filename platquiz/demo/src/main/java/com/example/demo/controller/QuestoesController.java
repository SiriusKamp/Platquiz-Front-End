package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Questoes;
import com.example.demo.model.Quiz;
import com.example.demo.repository.QuestoesRepository;
import com.example.demo.repository.QuizRepository;
import com.example.demo.repository.RespostasRepository;
import com.example.demo.repository.av_tipo1Repository;
import com.example.demo.repository.av_tipo2Repository;
import com.example.demo.repository.av_tipo3Repository;
import com.example.demo.repository.av_tipo4Repository;

@Controller
@RequestMapping("/criarquestoes")
public class QuestoesController {

    @Autowired
    private QuestoesRepository questoesRepository;

    @Autowired
    private QuizRepository quizRepository;

    @GetMapping("/{quizid}/{index}/{tipo}")
    public String exibirFormularioQuestoes(@PathVariable Long quizid,
            @PathVariable int index, @PathVariable int tipo,
            Model model
    ) {

        Optional<Quiz> quizOpt = quizRepository.findById(quizid);
        if (quizOpt.isEmpty()) {
            return "redirect:/criar-questionario";
        }

        Quiz quiz = quizOpt.get();
        model.addAttribute("quizid", quiz.getId());
        model.addAttribute("index", index);
        model.addAttribute("tipo", tipo);
        model.addAttribute("BackGroundColor", quiz.getCor_fundo());
        model.addAttribute("NumeroQuestoes", quiz.getN_perguntas());

        return "criar-questao"; // Nome da página HTML para criar a questão
    }

    @PostMapping("/salvar")
    public String salvarQuestaoERedirecionar(@ModelAttribute Questoes questao,
            @RequestParam Long quizid,
            @RequestParam int index) {

        Optional<Quiz> quizOpt = quizRepository.findById(quizid);
        if (quizOpt.isEmpty()) {
            return "redirect:/criar-questionario";
        }

        questao.setQuiz(quizOpt.get());
        questoesRepository.save(questao);

        if (index >= quizOpt.get().getN_perguntas()) {
            return "redirect:/resumoQuiz/" + quizid;
        }

        return "redirect:/criarquestoes/" + quizid + "/" + (index + 1);
    }
}
