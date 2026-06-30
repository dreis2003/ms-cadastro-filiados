package br.com.ikonbrasil.cadastrofiliados.filiado.infraestrutura.rest.dto.entrada;

import br.com.ikonbrasil.cadastrofiliados.filiado.dominio.enumerador.Sexo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record CriarFiliadoRequest(
        @NotBlank @Size(max = 180)
        String nomeCompleto,

        @Size(max = 180)
        String nomeSocial,

        @NotNull @Past
        LocalDate dataNascimento,

        @Size(max = 14)
        String cpf,

        @Size(max = 30)
        String rg,

        @Email @Size(max = 150)
        String email,

        @Size(max = 30)
        String telefone,

        Sexo sexo,

        @Size(max = 3)
        String tipoSanguineo,

        LocalDate dataInicioTreinamento,

        @Size(max = 80)
        String nacionalidade,

        @Size(max = 120)
        String naturalidade,

        @Size(max = 120)
        String profissao,

        @Size(max = 180)
        String responsavelNome,

        @Size(max = 80)
        String responsavelParentesco,

        @Size(max = 14)
        String responsavelCpf,

        @Size(max = 30)
        String responsavelTelefone,

        @Email @Size(max = 150)
        String responsavelEmail,

        String dadosMedicos,

        Boolean parqPergunta1,
        Boolean parqPergunta2,
        Boolean parqPergunta3,
        Boolean parqPergunta4,
        Boolean parqPergunta5,
        Boolean parqPergunta6,
        Boolean parqPergunta7,

        @NotBlank @Size(max = 180)
        String assinaturaNome,

        @NotNull @AssertTrue
        Boolean declaracaoSaudeAceite,

        @Valid
        EnderecoRequest endereco,

        @Size(max = 80)
        String numeroInternacional,

        String fotoPerfilUrl,

        @NotNull
        UUID filialId
) {
}
