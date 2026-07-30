# 7News

Sistema de publicação e notificação de notícias construído com arquitetura de microsserviços.

## Sobre o projeto

O 7News demonstra na prática como microsserviços independentes se comunicam de forma assíncrona. Quando uma notícia é criada, o sistema a publica em uma fila de mensagens. Um segundo serviço escuta essa fila e envia automaticamente um e-mail com o conteúdo da notícia — incluindo imagem — para o destinatário configurado.

Se o serviço de e-mail cair, os pedidos continuam sendo processados normalmente. As mensagens ficam guardadas na fila e são processadas quando o serviço voltar, sem perda de dados.

## Arquitetura

```
POST /news
     │
     ▼
[notice-service]  ──→  [RabbitMQ: news.sent]  ──→  [mail-service]
 Publisher                    Fila                   Consumer + Email
```

Dois serviços Spring Boot independentes, orquestrados via Docker Compose.

## Tecnologias

- **Java 21** + **Spring Boot**
- **RabbitMQ**
- **Spring AMQP**
- **Spring Mail**
- **Docker** + **Docker Compose**

## Como rodar

**Pré-requisitos:** Docker e Docker Compose instalados.

1. Clone o repositório:
```bash
git clone https://github.com/seu-usuario/7news.git
cd 7news
```

2. Crie o arquivo `.env` na raiz com as variáveis necessárias:
```env
RABBITMQ_USER=seu_usuario
RABBITMQ_PASSWORD=sua_senha
MAIL_USER=seu@email.com
MAIL_PASSWORD=sua_senha_de_app
MAIL_SENDER=destinatario@email.com
```

3. Suba os serviços:
```bash
docker compose up --build
```

4. Publique uma notícia via POST:
```bash
curl -X POST http://localhost:8080/news \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Título da notícia",
    "content": "Conteúdo da notícia",
    "imageUrl": "https://url-da-imagem.jpg"
  }'
```

O e-mail será entregue automaticamente após a publicação.

## Fluxo completo

1. Cliente faz um `POST /news` com título, conteúdo e URL de imagem
2. O `notice-service` publica o evento na fila `news.sent` do RabbitMQ
3. O `mail-service` consome o evento e monta um e-mail em HTML
4. O e-mail é enviado com título, conteúdo e imagem renderizada