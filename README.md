# DIO Desafio de Projeto: Criando um Sudoku em Java

Esse é um desafio de projeto do bootcamp **GFT Start 7**, com o intuito de criar um jogo Sudoku usando Java. Nesse desafio, foram implementados pontos adicionais para focar no aprendizado e desenvolvimento em **orientação a objetos**.

---

## Sobre o Projeto

Decidi usar a biblioteca **Swing** para a interface gráfica, pois é uma biblioteca mais complexa e flexível para design de UI em Java, principalmente usando a classe **Graphics** para desenhar o tabuleiro e os números.

O projeto conta com:

- Criação de um tabuleiro 9x9 para o Sudoku.
- Interface gráfica feita com Swing, incluindo:
  - Um painel de jogo (`BoardPanel`) que desenha o tabuleiro, linhas e números.
  - Um painel de teclado numérico para selecionar números e apagar.
  - Menu para verificar erros, resetar o jogo e sair.
  - Indicação visual dos números fixos (em preto), números inseridos corretos (em verde) e incorretos (em vermelho).
- Geração inicial dos números fixos do Sudoku (puzzles) para que cada jogo comece com uma configuração diferente.
- Validação das regras do Sudoku para detectar erros na posição dos números.
- Mensagens de feedback ao usuário para facilitar o entendimento do jogo.
- Área explicativa "Como Jogar", para ajudar jogadores iniciantes.

---

## Como Jogar

O Sudoku é um jogo de lógica onde o objetivo é preencher um tabuleiro 9x9 com números de 1 a 9, sem repetir números na mesma linha, coluna ou nas nove regiões 3x3.

### Regras básicas:

- Cada linha deve conter os números de 1 a 9, sem repetições.
- Cada coluna deve conter os números de 1 a 9, sem repetições.
- Cada bloco 3x3 deve conter os números de 1 a 9, sem repetições.

### Na interface do jogo:

- Os números fixos (inicialmente no tabuleiro) aparecem em preto e não podem ser alterados.
- Os números que você inserir aparecem em verde se estiverem corretos, ou em vermelho se estiverem em conflito com as regras.
- Para inserir um número, selecione o número no painel lateral e clique na célula desejada.
- Para apagar, selecione o botão "Apagar" e clique na célula.
- Use o menu "Jogo" para:
  - Verificar se há erros no tabuleiro.
  - Resetar o jogo para o estado inicial.
  - Sair do jogo.

---

## Como Rodar o Projeto

1. **Pré-requisitos**:
   - Java JDK 11 ou superior instalado.
   - IDE (IntelliJ, Eclipse, VSCode, etc) ou usar linha de comando.

2. **Passos para rodar**:
   - Clone ou baixe o projeto para seu computador.
   - Importe o projeto em sua IDE favorita (como projeto Java simples ou Maven/Gradle, conforme configurado).
   - Compile e execute a classe `MainFrame` (ou classe principal que inicializa o JFrame).
   - A janela do Sudoku Swing aparecerá, e você poderá jogar normalmente.

---

## Considerações Finais

Esse projeto foi uma ótima oportunidade para reforçar conceitos de orientação a objetos, manipulação gráfica com Swing e lógica de programação. Além disso, trouxe desafios interessantes na validação do jogo e na interação visual com o usuário.

Se quiser ajudar a melhorar, sugestões são sempre bem-vindas!

---
