# 🎵  API Sound

Uma aplicação Java Spring Boot para buscar e armazenar informações sobre **diversos artistas** e suas músicas mais famosas. A aplicação integra Inteligência Artificial - Gemini com persistência de dados em banco de dados PostgreSQL.

## 🚀 Funcionalidades

- 🔍 Consulta de informações sobre artistas via API Gemini (Google AI).
- 🎤 Armazenamento de dados de artistas e suas músicas.
- 📦 Persistência com Spring Data JPA + Hibernate.
- 🌐 API REST construída com Spring Web.
- 🧪 Suporte a Hot Reload com Spring DevTools.

---

## 🧠 Integração com IA (Gemini)

A aplicação utiliza a API do **Google Gemini** para obter informações contextuais e musicais sobre artistas. A IA retorna dados como:

- Biografia resumida
- Músicas mais famosas
- Outros dados relevantes do artista

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia      | Descrição                                           |
|-----------------|-----------------------------------------------------|
| Java 23        | Linguagem principal da aplicação                    |
| Spring Boot     | Framework para construção da aplicação REST         |
| Spring Data JPA | Comunicação com o banco via repositórios            |
| Hibernate       | ORM para persistência de entidades                  |
| Jackson         | Serialização e desserialização JSON                 |
| PostgreSQL      | Banco de dados relacional utilizado                 |
| Gemini API      | API da Google AI para obter dados inteligentes      |

---

## 💽 Estrutura do Projeto

```bash
src
├── main
│   ├── java
│   │   └── com.exemplo.musicinfo
│   │       ├── integracao (Gemini)
│   │       ├── model
│   │       ├── principal
│   │       ├── repository
│   │       └── service
│   └── resources
│       ├── application.properties
│       └── ...
```
---
## ⚙️  Como Executar

1. Clone o projeto:
2. Configure o banco PostgreSQL
3. Atualize o `application.properties`
4. Adicione sua chave Gemini
5. Execute a aplicação

---
## 🧪 Testes

-  Pode-se utilizar ferramentas como Postman ou Insomnia para testar os endpoints.

---
## 🤖 Como funciona a consulta com a IA?

Ao cadastrar um artista, a aplicação:

1. Envia um prompt com o nome do artista para o modelo Gemini.

2. A IA retorna dados textuais sobre o artista e suas músicas.

3. Esses dados são armazenados no banco de dados via JPA/Hibernate.

---
## 📝 Licença

Este projeto está licenciado sob a licença MIT. Veja o arquivo [LICENSE](https://mit-license.org/) para mais detalhes.

---
## 📫 Contato

Caso tenha dúvidas, entre em contato:

- Desenvolvido por Daniela Mota em realização ao Challenge: Utilizando IA em projetos do curso ONE - Oracle Next Education

📧 Email: danielamedeiromota@hotmail.com

🔗 [LinkedIn](https://www.linkedin.com/in/danielammota/)
