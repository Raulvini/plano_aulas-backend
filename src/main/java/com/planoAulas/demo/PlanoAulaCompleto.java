package com.planoAulas.demo;

import com.deepoove.poi.data.Numberings;

import java.util.List;

public record PlanoAulaCompleto(

        String escola,
        String dia,
        String area,
        String turma,
        String professor,
        String materia,
        String coodernador,
        List<String> metodologia,
        List<String> materiais,
        String habilidadeDCRC,
        String objetivoEspecifico,   // Texto gerado
        String objetoConhecimento,   // Texto gerado
        String atividadeSala,        // Texto gerado
        String atividadeCasa,        // Texto gerado
        String avaliacaoFormatada ,
        String alunos,
        String unidade,
        String data// A linha de avaliação com os (X) nas opções escolhidas
) {

    public Object getObjetivosFormatados() {
        return Numberings.create(metodologia.toArray(new String[0]));
    }

    public Object getmateriaFormatados() {
        return Numberings.create(materiais.toArray(new String[0]));
    }
}
