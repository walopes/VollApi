package med.voll.api.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import med.voll.api.domain.consulta.AgendaDeConsultas;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosDetalhamentoConsulta;
import med.voll.api.domain.medico.Especialidade;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class ConsultaControllerTest {

        @Autowired
        private MockMvc mvc;

        @Autowired
        private JacksonTester<DadosAgendamentoConsulta> dadosAgendamentoConsultaJson;

        @Autowired
        private JacksonTester<DadosDetalhamentoConsulta> dadosDetalhamentoConsultaJson;

        @MockBean // Tell spring to mock this class
        private AgendaDeConsultas agendaDeConsultas;

        @Test
        @DisplayName("Deveria devolver código HTTP 400 quando informações estão inválidas")
        @WithMockUser
        void testAgendarCenario1() throws Exception {
                var response = mvc.perform(post("/consultas"))
                                .andReturn().getResponse();

                assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        @DisplayName("Deveria devolver código HTTP 200 quando informações estão válidas")
        void testAgendarCenario2() throws Exception {
                var data = LocalDateTime.now().plusHours(1);
                var especialidade = Especialidade.CARDIOLOGIA;
                var dadosDetalhamento = new DadosDetalhamentoConsulta(null, 2l, 5l, data);
                when(agendaDeConsultas.agendar(any())).thenReturn(dadosDetalhamento);

                var response = mvc.perform(post("/consultas").contentType(MediaType.APPLICATION_JSON)
                                .content(dadosAgendamentoConsultaJson.write(
                                                new DadosAgendamentoConsulta(2l, 5l, data, especialidade)).getJson()))
                                .andReturn().getResponse();

                assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

                var jsonEsperado = dadosDetalhamentoConsultaJson.write(
                                new DadosDetalhamentoConsulta(null, 2l, 5l, data)).getJson();

                assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
        }

        // TODO Do the unitary test

        // @Test
        // void testCancelar() {

        // }
}
