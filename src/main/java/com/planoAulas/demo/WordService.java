package com.planoAulas.demo;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.policy.ListRenderPolicy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WordService {

    public byte[] gerarArquivoFinal(PlanoAulaCompleto dados) throws Exception {
        ClassPathResource resource = new ClassPathResource("templates/template.docx");

        var metodologia = dados.metodologia().stream()
                .map(item -> "• " + item)
                .collect(Collectors.joining("\n"));
        var materiais = dados.materiais().stream()
                .map(item -> "• " + item)
                .collect(Collectors.joining("\n"));


        try (InputStream is = resource.getInputStream();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            // Remova o .bind(NumberingRenderPolicy) pois agora são Strings comuns
            Map<String, Object> model = new HashMap<>();

            model.put("ESCOLA", dados.escola());
            model.put("DIA", dados.dia());
            model.put("PROFESSOR", dados.professor());
            model.put("ALUNOS", dados.alunos());
            model.put("TURMA", dados.turma());
            model.put("MATERIA", dados.materia());
            model.put("HABILIDADE", dados.habilidadeDCRC());
            model.put("OBJETIVO", dados.objetivoEspecifico());
            model.put("OBJETO", dados.objetoConhecimento());
            model.put("SALA", dados.atividadeSala());
            model.put("CASA", dados.atividadeCasa());
            model.put("AVALIACAO", dados.avaliacaoFormatada());

            // Inserindo as Strings formatadas manualmente
            model.put("METODOLOGIA", metodologia);
            model.put("MATERIAL", materiais);

            model.put("DATA", dados.data());
            model.put("COORDENADOR", dados.coodernador());
            model.put("AREA", dados.area());
            model.put("UNIDADE",dados.unidade());

            // Compile simples, sem a necessidade de políticas especiais para as listas
            XWPFTemplate template = XWPFTemplate.compile(is).render(model);

            template.write(out);
            template.close();

            return out.toByteArray();
        }
    }
}