package service;

public final class ValidacaoService {
    private ValidacaoService() {
    }

    public static void textoObrigatorio(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(campo + " e obrigatorio.");
        }
    }

    public static void numeroPositivo(double valor, String campo) {
        if (valor <= 0) {
            throw new IllegalArgumentException(campo + " deve ser maior que zero.");
        }
    }

    public static void numeroNaoNegativo(int valor, String campo) {
        if (valor < 0) {
            throw new IllegalArgumentException(campo + " nao pode ser negativo.");
        }
    }
}
