// Simulação de dados vindos do backend (array de questionários do usuário)
const questionarios = [
  { id: 1, titulo: "Pesquisa de Satisfação" },
  { id: 2, titulo: "Formulário de Inscrição" },
  { id: 3, titulo: "Avaliação de Curso" },
];

// Função para carregar questionários na tela
function carregarQuestionarios() {
  const container = document.getElementById("cards-container");
  const contador = document.getElementById("questionario-count");

  // Atualiza o número total de questionários
  contador.textContent = questionarios.length;

  // Cria um card para cada questionário
  questionarios.forEach((q) => {
    const card = document.createElement("div");
    card.classList.add("card", "questionario-card");
    card.innerText = q.titulo;
    card.onclick = () =>
      (window.location.href = `questionario.html?id=${q.id}`);
    container.appendChild(card);
  });
}

// Função de busca (filtro) de questionários
document.getElementById("search").addEventListener("input", function () {
  const filtro = this.value.toLowerCase();
  const cards = document.querySelectorAll(".questionario-card");

  cards.forEach((card) => {
    const titulo = card.innerText.toLowerCase();
    card.style.display = titulo.includes(filtro) ? "flex" : "none";
  });
});

// Executa ao carregar a página
window.onload = carregarQuestionarios;
