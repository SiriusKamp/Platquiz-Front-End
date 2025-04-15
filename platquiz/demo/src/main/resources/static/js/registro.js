document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("registro-form");
  const senha = document.getElementById("senha");
  const senha2 = document.getElementById("senha2");
  const aviso = document.getElementById("senha-aviso");

  // Exibe um alerta se houver erro na URL
  const urlParams = new URLSearchParams(window.location.search);
  if (urlParams.has("erro") && urlParams.get("erro") === "email-existente") {
    alert("Usuário já cadastrado!");
  } else if (
    urlParams.has("erro") &&
    urlParams.get("erro") === "ra-existente"
  ) {
    alert("RA já cadastrado!");
  }

  form.addEventListener("submit", async (e) => {
    e.preventDefault();

    // Valida se as senhas coincidem
    if (senha.value !== senha2.value) {
      aviso.style.display = "block";
      return;
    }
    aviso.style.display = "none";

    // Cria o objeto com os dados do formulário
    const formData = {
      nome: form.nome.value,
      email: form.email.value,
      telefone: form.contato.value,
      registroAcademico: form.ra.value,
      senha: form.senha.value,
    };
    console.log(JSON.stringify(formData));

    try {
      const response = await fetch("/registro", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(formData),
      });

      // Redirecionamento e tratamento de resposta
      if (response.redirected) {
        window.location.href = response.url; // Redireciona para o destino do backend
      } else {
        alert("Erro: Não foi possível cadastrar.");
      }
    } catch (error) {
      console.error("Erro ao enviar dados:", error);
      alert("Erro no sistema. Tente novamente mais tarde.");
    }
  });
});
