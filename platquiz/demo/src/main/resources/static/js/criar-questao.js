
const verify = () => {
const usuario = JSON.parse(sessionStorage.getItem("usuario"));
  if (!usuario) {
    window.location.href = "login";
  }
};

function adicionarResposta() {
  const container = document.getElementById("respostas-container");

  const card = document.createElement("div");
  card.classList.add("resposta-card");

  const tipo = parseInt(document.body.getAttribute("data-tipo") || "1");

  // Dropdown para tipo da resposta
  const select = document.createElement("select");
  const option1 = new Option("Texto", "texto");
  const option2 = new Option("Imagem", "imagem");
  select.append(option1, option2);

  // Campo de resposta (inicialmente como texto)
  let inputResposta = document.createElement("input");
  inputResposta.type = "text";
  inputResposta.placeholder = "Digite a resposta";
  inputResposta.classList.add("resposta-input");

  // Tipo de input (checkbox ou número, dependendo do tipo da pergunta)
  const tipoInput = document.createElement("input");
  tipoInput.type = (tipo === 1 || tipo === 3) ? "checkbox" : "number";
  tipoInput.placeholder = (tipo === 1 || tipo === 3) ? "" : "% da resposta";
  tipoInput.style.marginTop = "5px";

  // Botão de remover resposta
  const remover = document.createElement("button");
  remover.className = "remove-btn";
  remover.textContent = "Remover";
  remover.onclick = () => card.remove();

  // Função para trocar entre input de texto e input de imagem
  select.addEventListener("change", function () {
    const currentInput = card.querySelector(".resposta-input");
    if (currentInput) currentInput.remove();

    if (select.value === "texto") {
      inputResposta = document.createElement("input");
      inputResposta.type = "text";
      inputResposta.placeholder = "Digite a resposta";
    } else if (select.value === "imagem") {
      inputResposta = document.createElement("input");
      inputResposta.type = "file";
      inputResposta.accept = "image/*";
    }

    inputResposta.classList.add("resposta-input");
    card.insertBefore(inputResposta, tipoInput); // Coloca o input antes do checkbox/porcentagem
  });

  // Monta o card
  card.appendChild(select);
  card.appendChild(inputResposta);
  card.appendChild(tipoInput);
  card.appendChild(remover);

  container.appendChild(card);
}

window.onload = async () => {
  verify();
};
