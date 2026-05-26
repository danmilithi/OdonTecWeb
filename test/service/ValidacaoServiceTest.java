package service;

import org.junit.Test;

public class ValidacaoServiceTest {

    @Test
    public void deveAceitarTextoObrigatorioPreenchido() {
        ValidacaoService.textoObrigatorio("Paciente", "Nome");
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveRejeitarTextoObrigatorioVazio() {
        ValidacaoService.textoObrigatorio("   ", "Nome");
    }

    @Test
    public void deveAceitarNumeroPositivo() {
        ValidacaoService.numeroPositivo(10.0, "Preco");
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveRejeitarNumeroNegativo() {
        ValidacaoService.numeroPositivo(-5.0, "Preco");
    }
}
