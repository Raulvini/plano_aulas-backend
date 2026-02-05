package com.planoAulas.demo.controller;


import com.planoAulas.demo.AssitentIAService;
import com.planoAulas.demo.PlanoAulaCompleto;
import com.planoAulas.demo.PlanoRequestDTO;
import com.planoAulas.demo.PlanoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/IAplan")
@RequiredArgsConstructor
public class PlanoController {



    private final PlanoService planoService;

    private final AssitentIAService assitentIAService;


    @PostMapping("/gerar")
    public ResponseEntity<byte[]> criarPlano(@RequestBody PlanoRequestDTO plano) throws Exception {
        System.out.println("Recebi os dados: " + plano);

        var resIA = assitentIAService.gerarPlano(plano.tema(),plano.escola(), plano.professor(),plano.data(),plano.materia(),plano.materiais(),plano.metodologia(), plano.alunos(), plano.coordenador(),plano.turma());

//
        byte[] documentoWord = planoService.executarFluxoCompleto(resIA);

        // 3. Montamos a resposta de download
        HttpHeaders headers = new HttpHeaders();

        // Define que o conteúdo é um arquivo binário (Word)
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        // Define o nome do arquivo que o usuário verá ao baixar
        String nomeArquivo = "Plano_de_Aula_" + plano.tema().replace(" ", "_") + ".docx";
        headers.setContentDisposition(ContentDisposition.attachment().filename(nomeArquivo).build());

        return ResponseEntity.ok()
                .headers(headers)
                .body(documentoWord);
    }
}
