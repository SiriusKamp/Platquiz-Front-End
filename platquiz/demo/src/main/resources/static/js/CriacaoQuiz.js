document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("quiz-form");
  const materiasContainer = document.getElementById("materias-container");
  const colorPicker = document.getElementById("cor-fundo");

  const urlParams = new URLSearchParams(window.location.search);
  if (
    urlParams.has("erro") &&
    urlParams.get("erro") === "Professor-Nao-encontrado"
  ) {
    alert("Professor NÃO encontrado!");
  } else if (
    urlParams.has("erro") &&
    urlParams.get("erro") === "Quiz-existente"
  ) {
    alert("Não pode ter quizes de nomes iguais!");
  }

  function criarNovoSelect() {
    const novoSelect = document.createElement("select");
    novoSelect.classList.add("materia-select");

    const opcoes = `
              <option value="">Selecione</option>
              <option value="Filosofia">Filosofia</option>
              <option value="Biologia">Biologia</option>
              <option value="História">História</option>
              <option value="Química">Química</option>
              <option value="Matemática">Matemática</option>
              <option value="Geografia">Geografia</option>
              <option value="Artes">Artes</option>
              <option value="Fisica">Fisica</option>
              <option value="Enfermagem">Enfermagem</option>
              <option value="Medcina">Medcina</option>
              <option value="Tecnologias">Tecnologias</option>
              <option value="Administração">Administração</option>
              <option value="BioMedicina">BioMedicina</option>
              <option value="Marketing">Marketing</option>
              <option value="Educação Fisica">Educação Fisica</option>
              <option value="Psicologia">Psicologia</option>
              <option value="Sociologia">Sociologia</option>
              <option value="Engenharia">Engenharia civil</option>
              <option value="Engenharia De Produção">
                Engenharia De Produção
              </option>
              <option value="Arquitetura">Arquitetura</option>
              <option value="Fisioterapia">Fisioterapia</option>
              <option value="Nutrição">Nutrição</option>
              <option value="Outros">Outros</option>
      `;

    novoSelect.innerHTML = opcoes;
    materiasContainer.appendChild(novoSelect);

    novoSelect.addEventListener("change", () => {
      const selects = materiasContainer.querySelectorAll("select");
      const ultimoSelect = selects[selects.length - 1];
      if (novoSelect === ultimoSelect && novoSelect.value !== "") {
        criarNovoSelect();
      }
    });
  }

  // Inicia com o primeiro select
  criarNovoSelect();

  colorPicker.addEventListener("input", (e) => {
    document.body.style.backgroundColor = e.target.value;
  });

  form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const nome = form.nome.value;
    const n_perguntas = form.numeroQuestoes.value;
    const r_escrita = document.getElementById("respostaEscrita").checked;
    const cor_fundo = colorPicker.value;

    const materias = [...materiasContainer.querySelectorAll("select")]
      .map((select) => select.value)
      .filter((val) => val !== "");

    const quizData = {
      nome,
      n_perguntas,
      r_escrita,
      cor_fundo,
      materias,
    };

    try {
      const response = await fetch("/criar-questionario", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(quizData),
      });

      if (response.redirected) {
        // redireciona automaticamente
        window.location.href = response.url;
      } else {
        alert("Erro: Não foi possível cadastrar.");
      }
    } catch (error) {
      console.error("Erro ao enviar dados:", error);
      alert("Erro no sistema. Tente novamente mais tarde.");
    }
  });
});
