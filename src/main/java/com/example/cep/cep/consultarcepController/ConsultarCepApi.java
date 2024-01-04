package com.example.cep.cep.consultarcepController;

import com.example.cep.cep.CepResultDTO.CepResultDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("consulta-cep")
public class ConsultarCepApi {
    @GetMapping("{cep}")
    public CepResultDTO consultaCep(@PathVariable("cep") String cep) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CepResultDTO> resultDTOResponseEntity =
                restTemplate.getForEntity(String.format("https://viacep.com.br/ws/%s/json", cep), CepResultDTO.class);
        return resultDTOResponseEntity.getBody();

    }
}
