package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

@Controller
public class usuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public String login() {
        System.out.println("Login page accessed");
        return "login.html";
    }

    @GetMapping("/registro")
    public String register() {
        System.out.println("register page accessed");
        return "registro.html";
    }

 

    @PostMapping("/registro")
    public String registrarUsuario(@RequestBody Usuario usuario) {
        // Verifica se o e-mail já está cadastrado
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
        if (usuarioExistente.isPresent()) {
            return "redirect:/registro?erro=email-existente";
        }
        Optional<Usuario> raexistente = usuarioRepository.findByregistroAcademico(usuario.getRegistroAcademico());
        if (raexistente.isPresent()) {
            return "redirect:/registro?erro=ra-existente";
        }
        // Cria o usuário e salva no banco
        Usuario novoUsuario = new Usuario(usuario.getNome(), usuario.getEmail(), usuario.gettelefone(), usuario.getRegistroAcademico(), usuario.getSenha(), true);
        System.out.println("Novo usuário: " + novoUsuario.getNome() + ", " + novoUsuario.getEmail() + ", " + novoUsuario.gettelefone() + ", " + novoUsuario.getRegistroAcademico() + ", " + novoUsuario.getSenha() + ", " + novoUsuario.isAtivo());
        usuarioRepository.save(novoUsuario);

        // Redireciona para a página de login
        return "redirect:/login";
    }
@GetMapping( path = "/getusuarios/{ra}", produces = "application/json")
@ResponseBody
public Usuario getUsuarioPorRa(@PathVariable String ra) {
    Optional<Usuario> raexistente = usuarioRepository.findByregistroAcademico(ra);

    if (raexistente.isPresent()) {
        return raexistente.get();
    } else {
        // Retorna 404 caso o usuário não seja encontrado
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
    }
}

    
}
