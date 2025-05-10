function adicionarResposta() {
  const container = document.getElementById("respostas-container");

  const card = document.createElement("div");
  card.classList.add("resposta-card");

  const tipo = parseInt(document.body.getAttribute("data-tipo") || "1");

  const select = document.createElement("select");
  const option1 = new Option("Texto", "texto");
  const option2 = new Option("Imagem", "imagem");
  select.append(option1, option2);

  const inputResposta = document.createElement("input");
  inputResposta.type = "text";
  inputResposta.placeholder = "Digite a resposta";

  const tipoInput = document.createElement(
    tipo === 1 || tipo === 3 ? "input" : "input"
  );
  tipoInput.type = tipo === 1 || tipo === 3 ? "checkbox" : "number";
  tipoInput.placeholder = tipo === 1 || tipo === 3 ? "" : "% da resposta";
  tipoInput.style.marginTop = "5px";

  const remover = document.createElement("button");
  remover.className = "remove-btn";
  remover.textContent = "Remover";
  remover.onclick = () => card.remove();

  card.appendChild(select);
  card.appendChild(inputResposta);
  card.appendChild(tipoInput);
  card.appendChild(remover);

  container.appendChild(card);
}
