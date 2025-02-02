# 🚀 Consulta de CEP com API Externa - Java Spring Boot

Este projeto é uma aplicação em **Java** com **Spring Boot** que realiza a consulta de um CEP utilizando uma API externa, como o [ViaCEP](https://viacep.com.br/). Ele retorna informações sobre o endereço associado ao CEP informado.

## 📌 Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Web
- OpenFeign
- Lombok
- Maven

## 📥 Como Rodar o Projeto

### Pré-requisitos

Antes de começar, certifique-se de ter instalado:
- [JDK 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Git](https://git-scm.com/)

### Passos para execução

1. Clone este repositório:
   ```sh
   git clone https://github.com/seu-usuario/seu-repositorio.git
   cd seu-repositorio
   ```

2. Execute o projeto com o Maven:
   ```sh
   mvn spring-boot:run
   ```

O servidor será iniciado em `http://localhost:8080`.

## 📡 Como Funciona?

O projeto expõe um endpoint REST que recebe um CEP e consulta os dados da API externa para retornar as informações associadas.

### Exemplo de Requisição:
```sh
GET http://localhost:8080/cep/01001000
```

### Exemplo de Resposta:
```json
{
  "cep": "01001-000",
  "logradouro": "Praça da Sé",
  "bairro": "Sé",
  "cidade": "São Paulo",
  "estado": "SP"
}
```

## 🔧 Implementação da Integração com API Externa

A comunicação com a API externa é feita utilizando **FeignClient**, que simplifica chamadas HTTP em aplicações Spring Boot.

### Exemplo de FeignClient:

```java
@FeignClient(name = "viacep", url = "https://viacep.com.br/ws/")
public interface ViaCepClient {
    @GetMapping("{cep}/json/")
    EnderecoDTO buscarCep(@PathVariable String cep);
}
```

### Controlador REST:

```java
@RestController
@RequestMapping("/cep")
public class CepController {

    @Autowired
    private ViaCepClient viaCepClient;

    @GetMapping("/{cep}")
    public ResponseEntity<EnderecoDTO> consultarCep(@PathVariable String cep) {
        return ResponseEntity.ok(viaCepClient.buscarCep(cep));
    }
}
```

### DTO para Resposta:

```java
@Data
public class EnderecoDTO {
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
}
```

## 🛠 Possíveis Melhorias

- Implementação de cache para evitar múltiplas consultas ao mesmo CEP.
- Tratamento de erros mais robusto para falhas na API externa.
- Testes unitários e de integração para garantir a confiabilidade.

## 📄 Licença

Este projeto está sob a licença MIT - veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

Feito com ❤️ por [Breno Augusto](https://github.com/brenoaugustoalves).

