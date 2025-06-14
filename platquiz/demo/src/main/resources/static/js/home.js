const logout = () => {
  sessionStorage.removeItem("usuario");
  verify();
};
const questionarios = [];

const usuario = JSON.parse(sessionStorage.getItem("usuario"));

const verify = () => {
  if (!usuario) {
    window.location.href = "login";
  }
};

// Função para carregar os quizes do usuário
const carregarQuizes = async () => {
  if (!usuario) return;

  try {
   const response = await fetch(`/getquestionarios/${usuario.id}`);
    if (!response.ok) {
      throw new Error("Erro ao buscar questionários");
    }

    const quizes = await response.json();

    quizes.forEach((quiz) => {
      questionarios.push({
        id: quiz.id,
        nome: quiz.nome,
        materias: quiz.materias,
      });
    });

    carregarQuestionarios(); // Só carrega os cards depois de ter os dados

  } catch (error) {
    console.error("Erro ao buscar questionários:", error);
  }
};

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
    card.innerText = q.nome; // corrigido de q.nome para q.titulo
    card.onclick = () =>
      (window.location.href = `/criar-questionario/${q.id}`);
    container.appendChild(card);
  });
}

// Função de busca (filtro) de questionários
document.getElementById("search").addEventListener("input", function () {
  const filtro = this.value.toLowerCase();
  const cards = document.querySelectorAll(".questionario-card");

  cards.forEach((card) => {
    const nome = card.innerText.toLowerCase();
    card.style.display = nome.includes(filtro) ? "flex" : "none";
  });
});

// Executa ao carregar a página
window.onload = async () => {
  verify();
  await carregarQuizes();
};
