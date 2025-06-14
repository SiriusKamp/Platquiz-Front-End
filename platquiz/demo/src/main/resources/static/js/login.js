if (sessionStorage.getItem("usuario")) {
    window.location.href = "/";
}

document.getElementById("login-form").addEventListener("submit", async function (e) {
  e.preventDefault();

  const raInput = document.getElementById("RA");
  const senhaInput = document.getElementById("senha");

  // Remove mensagens antigas de erro
  removeMensagensErro();

  const ra = raInput.value.trim();
  const senha = senhaInput.value;

  try {
    // Busca o usuário pelo RA
    const response = await fetch(`/getusuarios/${ra}`);
    if (!response.ok) {
  if (response.status === 404) {
    mostrarErro(raInput, "Usuário não existente");
  } else {
    alert("Erro no servidor. Tente novamente mais tarde.");
    console.log("Erro ao buscar usuário:", response.status, response.statusText);
  }
  return;
}
    const usuario = await response.json();
    const usuariosalvo = {
        id : usuario.id,
        email : usuario.email,
        nome: usuario.nome,
        registroAcademico: usuario.registroAcademico,
        telefone: usuario.telefone,
        
    }
    console.log("Usuário encontrado:", usuario.nome);
    if (!usuario || !usuario.registroAcademico) {
      mostrarErro(raInput, "Usuário não existente");
      return;
    }

    if (usuario.senha !== senha) {
      mostrarErro(senhaInput, "Senha incorreta");
      return;
    }

    // Salva no sessionStorage para uso posterior
    sessionStorage.setItem("usuario", JSON.stringify(usuariosalvo));

    // Redireciona para a página principal
    window.location.href = "/";
  } catch (err) {
    console.error("Erro ao realizar login:", err);
    alert("Erro ao realizar login. Tente novamente mais tarde.");
  }
});

function mostrarErro(inputElement, mensagem) {
  const erro = document.createElement("div");
  erro.className = "mensagem-erro";
  erro.textContent = mensagem;
  inputElement.parentNode.appendChild(erro);
}

function removeMensagensErro() {
  const erros = document.querySelectorAll(".mensagem-erro");
  erros.forEach(el => el.remove());
}
