package com.planoAulas.demo;

import java.time.LocalDate;
import java.util.List;

public record PlanoRequestDTO(
        String tema,
        String escola,
        String professor,
        String data,
        String materia,
        Integer alunos,
        String coordenador,
        List<String> metodologia,
        List<String> materiais,
        String turma
        // Opcional, para a @Tool usar se necessário
) {}
