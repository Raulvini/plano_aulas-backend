package com.planoAulas.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PlanoService {



    @Autowired
    private WordService wordService;

    public byte[] executarFluxoCompleto(PlanoAulaCompleto resultado) throws Exception {




        // 4. Gera o byte[] do arquivo preenchido
        return wordService.gerarArquivoFinal(resultado);
    }



}
