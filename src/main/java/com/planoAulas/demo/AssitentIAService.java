package com.planoAulas.demo;


import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AssitentIAService {



        @SystemMessage("""
                ""\"
                    Você é um Especialista em Design Instrucional e Assistente Pedagógico de Alta Precisão.
                    Sua tarefa é gerar um plano de aula estruturado em formato JSON.
                
                    ### REGRAS DE PROCESSAMENTO:
                    1. MAPEAMENTO: Utilize os dados de entrada fornecidos. Se {{escola}} ou {professor}} forem vazios, retorne null no JSON.
                    2. DIA: Calcule o dia da semana correspondente à data {{data}} e preencha o campo 'DIA'.
                    3. HABILIDADES: Use a ferramenta 'buscarHabilidadesNoPdf'. Se falhar, redija habilidades originais padrão BNCC/DCRC.
                    4. ÁREA: Deduza a 'areaConhecimento' com base na matéria {{materia}}.
                    5. COERÊNCIA: Gere 'objetivoEspecifico', 'objetoConhecimento', 'atividadeSala' e 'atividadeCasa' adequados para a turma {{turma}}.
                    6. UNIDADE: GERE A UNIDADE TEMATICA COM BASE NO {{tema}} , {{materia}} E {{turma}}.
                    7. PROFESSOR : {{professor}}.
                    8. COODENADOR : {{coodenador}}.
                    9. ALUNOS: {{alunos}}.
                    10. DATA: {{data}} DEIXE NO FROMATO DE DIA/MES/ANO.
                    11. ESCOLA: {{escola}}.
                    ### FORMATO DE SAÍDA:
                    Retorne estritamente um JSON que mapeie para a classe PlanoAulaCompleto:
        """)
        PlanoAulaCompleto gerarPlano(
                @UserMessage @V("tema") String tema,
                @V("escola") String escola,
                @V("professor") String professor,
                @V("data") String data,
                @V("materia") String materia,
                @V("materiais") List<String> materiais,
                @V("metodologia") List<String> matetodologia,
                @V("alunos") Integer alunos,
                @V("coodenador") String coodenador,
                @V("turma") String turma

        );

}



