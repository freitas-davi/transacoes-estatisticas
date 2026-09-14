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


---

### Deletar Todas as Transações
**`DELETE /transacao`**

#### Request
Sem parâmetros.

#### Response
- **200 OK**: Lista de transações limpa com sucesso (sem corpo)

---

### Obter Estatísticas
**`GET /estatistica`**

#### Request
Sem parâmetros. Retorna estatísticas das transações dos últimos 60 segundos.

#### Response
**200 OK**
```json
{
  "count": 10,
  "sum": 1500.75,
  "avg": 150.075,
  "min": 50.25,
  "max": 300.50
}
```

**Campos:**
- `count`: Quantidade de transações
- `sum`: Somatório dos valores
- `avg`: Média dos valores
- `min`: Valor mínimo
- `max`: Valor máximo

---

## Regras de Negócio

| Regra | Descrição |
|-------|-----------|
| **Valor** | Não pode ser negativo (valor < 0 retorna erro 422) |
| **Data/Hora** | Não pode ser no futuro (dataHora > agora retorna erro 422) |
| **Obrigatoriedade** | Valor e dataHora são campos obrigatórios (ausente retorna erro 422) |
| **Janela de Tempo** | Estatísticas consideram apenas transações dos últimos 60 segundos |

---

## Decisões Técnicas

### Estrutura de Dados: ConcurrentLinkedQueue

A escolha foi por utilizar `ConcurrentLinkedQueue` ao invés de `ConcurrentHashMap`:

- **ConcurrentHashMap**: Exigiria uma chave única (como UUID) para cada transação, ocupando mais memória
- **ConcurrentLinkedQueue**: Mantém a ordem de inserção e otimiza a operação de leitura sequencial necessária para calcular estatísticas
- **Thread-safe**: Ambas são seguras para ambientes multi-thread, mas Queue é mais eficiente para este caso de uso

