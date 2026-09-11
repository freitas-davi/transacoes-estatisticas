# Transações Estatísticas

API para gerenciamento e estatísticas de transações.

## Endpoints

### Criar Transação
**`POST /transacao`**

#### Request
```json
{
  "valor": 100.50,
  "dataHora": "2026-09-11T15:30:00-03:00"
}
```

#### Response
- **201 Created (no content)**: Transação criada com sucesso (sem corpo)
- **400 Bad Request (no content)**: JSON inválido ou formato incorreto
- **422 Unprocessable Entity (no content)**: Validação falhou


## Regras de Negócio

| Regra | Descrição |
|-------|-----------|
| **Valor** | Não pode ser negativo (valor < 0 retorna erro 422) |
| **Data/Hora** | Não pode ser no futuro (dataHora > agora retorna erro 422) |
| **Obrigatoriedade** | Valor e dataHora são campos obrigatórios (ausente retorna erro 422) |

