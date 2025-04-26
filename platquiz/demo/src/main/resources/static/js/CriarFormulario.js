let campoIndex = 0;

const colorPicker = document.getElementById("cor-fundo");
const card = document.getElementById("card-body");

colorPicker.addEventListener("input", (e) => {
  card.style.backgroundColor = e.target.value;
});

document.getElementById("adicionar-campo").addEventListener("click", () => {
  campoIndex++;

  const container = document.getElementById("campos-container");

  const div = document.createElement("div");
  div.className = "campo-form";
  div.innerHTML = `
    <h2>Campo ${campoIndex}</h2>
    <label>Texto do Label:</label>
    <input type="text" class="form-control mb-2" name="labelCampo${campoIndex}" placeholder="Digite o texto do campo">
    
    <label>Tipo de Campo:</label>
    <select class="form-select mb-3 tipo-campo" name="tipoCampo${campoIndex}">
      <option value="text">Texto</option>
      <option value="number">Número</option>
      <option value="email">Email</option>
      <option value="date">Data</option>
      <option value="checkbox">Caixa de seleção</option>
      <option value="radio">Seleção única</option>
      <option value="select">Select</option>
    </select>

    <div class="opcoes-container mt-2" data-index="${campoIndex}"></div>
    <hr/>
  `;

  container.appendChild(div);
});

document
  .getElementById("campos-container")
  .addEventListener("change", (event) => {
    if (event.target.classList.contains("tipo-campo")) {
      const select = event.target;
      const tipoSelecionado = select.value;
      const campoDiv = select.closest(".campo-form");
      const opcoesContainer = campoDiv.querySelector(".opcoes-container");
      const campoId = opcoesContainer.getAttribute("data-index");

      opcoesContainer.innerHTML = "";

      if (["select", "checkbox", "radio"].includes(tipoSelecionado)) {
        const inputQtd = document.createElement("input");
        inputQtd.type = "number";
        inputQtd.className = "form-control mb-2";
        inputQtd.placeholder = "Quantidade de opções";
        inputQtd.min = 1;

        const labelQtd = document.createElement("label");
        labelQtd.className = "form-label";
        labelQtd.textContent = `Quantidade de opções do ${tipoSelecionado}:`;

        opcoesContainer.appendChild(labelQtd);
        opcoesContainer.appendChild(inputQtd);

        inputQtd.addEventListener("input", () => {
          const qtd = parseInt(inputQtd.value);
          opcoesContainer
            .querySelectorAll(".option-text")
            .forEach((el) => el.remove());

          if (!isNaN(qtd) && qtd > 0) {
            const labelDefinir = document.createElement("label");
            labelDefinir.textContent = `Defina as opções do ${tipoSelecionado}:`;
            labelDefinir.className = "form-label mt-2 option-text";
            opcoesContainer.appendChild(labelDefinir);

            for (let i = 0; i < qtd; i++) {
              const inputOpcao = document.createElement("input");
              inputOpcao.type = "text";
              inputOpcao.className = "form-control mb-1 option-text";
              inputOpcao.placeholder = `Opção ${i + 1}`;
              inputOpcao.name = `opcao_${campoId}_${i}`;
              opcoesContainer.appendChild(inputOpcao);
            }
          }
        });
      }
    }
  });

// Clique para salvar o formulário — apenas gerar JSON para console
document
  .getElementById("salvar-formulario")
  .addEventListener("click", async () => {
    const campos = document.querySelectorAll(".campo-form");
    const dados = [];

    campos.forEach((campo, index) => {
      const labelInput = campo.querySelector(
        `input[name="labelCampo${index + 1}"]`
      );
      const tipoSelect = campo.querySelector(
        `select[name="tipoCampo${index + 1}"]`
      );
      if (!labelInput || !tipoSelect) return;

      const tipo = tipoSelect.value;
      const label = labelInput.value || `Campo ${index + 1}`;
      const opcoesContainer = campo.querySelector(".opcoes-container");

      let inputHtml = "";

      if (tipo === "select") {
        const select = document.createElement("select");
        select.className = "form-select";
        const opcoes = opcoesContainer.querySelectorAll("input");

        opcoes.forEach((input) => {
          const opt = document.createElement("option");
          opt.textContent = input.value;
          select.appendChild(opt);
        });

        inputHtml = select.outerHTML;
      } else if (tipo === "radio" || tipo === "checkbox") {
        const opcoes = opcoesContainer.querySelectorAll("input");

        opcoes.forEach((input) => {
          const inputElem = document.createElement("input");
          inputElem.type = tipo;
          inputElem.name = `campo_${index}`;
          inputElem.value = input.value;
          const labelElem = document.createElement("label");
          labelElem.textContent = input.value;
          labelElem.className = "ms-1 me-3";

          inputHtml += `<div>${inputElem.outerHTML}${labelElem.outerHTML}</div>`;
        });
      } else {
        const input = document.createElement("input");
        input.type = tipo;
        input.name = `campo_${index}`;
        input.className = "form-control";
        inputHtml = input.outerHTML;
      }

      dados.push({
        label: label,
        inputHtml: inputHtml,
      });
    });

    const cor = colorPicker.value;
    const quiz_id = parseInt(document.getElementById("quiz-id").value);

    console.log(quiz_id);
    try {
      const resFormulario = await fetch("/api/formularios", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          quiz_id: quiz_id,
          cor: cor,
        }),
      });

      const formularioCriado = await resFormulario.json();
      console.log(formularioCriado);

      if (!formularioCriado.id) {
        alert("Erro ao criar o formulário");
        return;
      }

      const formulario_id = formularioCriado.id;

      for (let campo of dados) {
        await fetch("/api/campos", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({
            formularioId: formulario_id,
            label: campo.label,
            campocodigo: campo.inputHtml,
          }),
        });
      }

      alert("Formulário salvo com sucesso!");
    } catch (error) {
      console.error("Erro ao salvar formulário:", error);
      alert("Erro ao salvar o formulário. Veja o console para mais detalhes.");
    }
  });
