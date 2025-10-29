# 📱 EmoTrack

EmoTrack é um aplicativo Android desenvolvido em **Java** no **Android Studio Narwhal 2025.1.1**, voltado para o registro e acompanhamento de sentimentos. Desenvolvido como projeto acadêmico da disciplina de Programação Mobile.
O app permite registrar como você está se sentindo em um determinado dia, associar fatores externos, marcar eventos importantes e adicionar observações.  

---

## 🚀 Funcionalidades

*   **Tela Principal (`MainActivity`):**
    *   Exibe os sentimentos cadastrados em uma **RecyclerView**.
    *   Inclui um **menu de opções** na barra superior:
        *   `Adicionar` → abre a tela de cadastro de sentimentos, aguardando retorno.
        *   `Sobre` → abre a tela com informações sobre a autoria do app.
    *   Implementa um **menu de ação contextual** (ao manter pressionado um item da lista):
        *   `Editar` → abre a tela de cadastro já preenchida, permitindo alterações.
        *   `Excluir` → remove o item selecionado da lista e atualiza a RecyclerView.

*   **Cadastro de Sentimentos (`CadastroSentimentoActivity`):**
    *   Permite ao usuário registrar ou editar um sentimento.
    *   Campos disponíveis:
        *   **Nome/Título** do sentimento.
        *   **Fator associado** (ex: Trabalho, Estudos, Família, Amigos, Saúde, Lazer, Outro).
        *   **Marcante** (checkbox).
        *   **Observações** detalhadas.
    *   Inclui um **menu de opções**:
        *   `Salvar` → valida os dados e retorna à listagem com resultado **RESULT_OK**.
        *   `Limpar` → apaga os campos preenchidos e exibe um **Toast** de confirmação.
    *   Possui botão **Up** na barra de navegação para retornar à lista sem salvar.

*   **Listagem de Sentimentos (`ListaSentimentos` e `SentimentoAdapter`):**
    *   Mostra todos os sentimentos cadastrados com detalhes (nome, fator, marcante, observações).
    *   Interação direta com cada item (edição e exclusão via menu contextual).

*   **Visualização de Informações Adicionais (`SobreActivity`):**
    *   Tela destinada a exibir informações sobre o aplicativo e sua autoria.
    *   Inclui botão **Up** para retornar à lista.


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

#### 📦 Versão 0.5
