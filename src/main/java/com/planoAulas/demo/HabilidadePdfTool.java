package com.planoAulas.demo;
import dev.langchain4j.agent.tool.Tool;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class HabilidadePdfTool {

    // Carrega o PDF uma única vez para ganhar performance
    private String conteudoPdfCache;

    @Tool("Busca a lista de habilidades oficiais no documento PDF da BNCC/DCRC")
    public String buscarHabilidadesNoPdf() {
        if (conteudoPdfCache != null) return conteudoPdfCache;

        try (InputStream is = getClass().getResourceAsStream("templates/habilidades.pdf")) {
            Loader.loadPDF(is.readAllBytes());
            try (PDDocument document = Loader.loadPDF(is.readAllBytes())) {
                PDFTextStripper stripper = new PDFTextStripper();
                this.conteudoPdfCache = stripper.getText(document);
                return conteudoPdfCache;
            }
        } catch (IOException e) {
            return "Erro ao ler o documento de habilidades.";
        }
    }
}
