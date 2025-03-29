package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/")
    public String home() {
        System.out.println("home page accessed");
        return "home.html";
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
        Usuario novoUsuario = new Usuario(usuario.getNome(), usuario.getEmail(), usuario.getContato(), usuario.getRegistroAcademico(), usuario.getSenha(), true);
        System.out.println("Novo usuário: " + novoUsuario.getNome() + ", " + novoUsuario.getEmail() + ", " + novoUsuario.getContato() + ", " + novoUsuario.getRegistroAcademico() + ", " + novoUsuario.getSenha() + ", " + novoUsuario.isAtivo());
        usuarioRepository.save(novoUsuario);

        // Redireciona para a página de login
        return "redirect:/login";
    }
}
