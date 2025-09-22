# 📱 EmoTrack

EmoTrack é um aplicativo Android simples para **controle de sentimentos diários**, desenvolvido como projeto acadêmico da disciplina de Programação Mobile.

O app permite registrar como você está se sentindo em um determinado dia, associar fatores externos, marcar eventos importantes e adicionar observações.  

---

## 🚀 Funcionalidades

Atualmente, o EmoTrack oferece as seguintes funcionalidades:

*   **Tela Principal (`MainActivity`):**
    *   Interface inicial do aplicativo, provavelmente com opções para visualizar sentimentos ou adicionar novos.
*   **Cadastro de Sentimentos (`CadastroSentimentoActivity`):**
    *   Permite ao usuário registrar um novo sentimento.
    *   Seleção de um **nome/título** para o sentimento.
    *   Escolha de um **fator** associado (ex: Trabalho, Estudos, Família, Amigos, Saúde, Lazer, Outro).
    *   Opção para marcar o sentimento como **"Marcante"** ou "Comum".
    *   Campo para adicionar **observações** textuais detalhadas sobre o sentimento.
*   **Listagem de Sentimentos (`ListaSentimentos` e `SentimentoAdapter`):**
    *   Exibe uma lista com todos os sentimentos previamente cadastrados.
    *   Cada item da lista mostra os detalhes do sentimento (nome, fator, se é marcante, observações).
    *   Permite interagir com os itens da lista (atualmente, um clique exibe um Toast com o nome do sentimento).
*   **Visualização de Informações Adicionais (`SobreActivity`):**
    *   Tela destinada a exibir informações sobre o aplicativo ou seus desenvolvedores.

## 🏗️ Estrutura do Projeto (Principais Classes)

*   **`Sentimento.java`**: Classe modelo (POJO) que representa um sentimento, contendo seus atributos (nome, fator, marcante, observações).
*   **`SentimentoAdapter.java`**: Adaptador para a `RecyclerView`, responsável por vincular os dados da lista de sentimentos à interface de cada item.
*   **`MainActivity.java`**: Ponto de entrada principal do aplicativo.
*   **`CadastroSentimentoActivity.java`**: Activity responsável pela interface e lógica de cadastro de novos sentimentos.
*   **`ListaSentimentos.java`**: Activity que exibe a lista de sentimentos usando uma `RecyclerView` e o `SentimentoAdapter`.
*   **`SobreActivity.java`**: Activity para a tela "Sobre".

---

## 🛠️ Tecnologias

- Android Studio Narwhal 2025.1.1
- Linguagem: **Java**
- Target SDK: **35 (Android 15.0)**
- Minimum SDK: **24 (Android 7.0)**

#### 📦 Versão 1.2
