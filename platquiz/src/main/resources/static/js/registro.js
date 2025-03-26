document
  .getElementById("registro-form")
  .addEventListener("submit", function (event) {
    event.preventDefault(); // Impede o envio do formulário até a validação

    const nome = document.getElementById("nome").value.trim();
    const email = document.getElementById("email").value.trim();
    const contato = document.getElementById("contato").value.trim();
    const senha = document.getElementById("senha").value.trim();
    const senha2 = document.getElementById("senha2").value.trim();
    const ra = document.getElementById("ra").value.trim();
    const avisoSenha = document.getElementById("senha-aviso");

    // Verifica se todos os campos estão preenchidos
    if (!nome || !email || !contato || !senha || !senha2 || !ra) {
      alert("Por favor, preencha todos os campos!");
      return;
    }

    // Verifica o tamanho da senha
    if (senha.length < 5) {
      alert("A senha deve ter no mínimo 5 caracteres!");
      return;
    }

    // Verifica se as senhas coincidem
    if (senha !== senha2) {
      avisoSenha.style.display = "block";
      return;
    } else {
      avisoSenha.style.display = "none";
    }

    // Se tudo ok, simula o envio (pode substituir pelo envio real para o backend)
    console.log({
      nome: nome,
      email: email,
      contato: contato,
      senha: senha,
      ra: ra,
    });

    alert("Registro realizado com sucesso!");
    // Limpa o formulário
    document.getElementById("registro-form").reset();
  });
