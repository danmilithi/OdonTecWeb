package model;

public class Tratamento {
    private int id;
    private String tratamento;
    private double preco;
    private double duracao;

    public Tratamento(int id, String tratamento, double preco, double duracao) {
        this.id = id;
        this.tratamento = tratamento;
        this.preco = preco;
        this.duracao = duracao;
    }

    public int getId() {
        return id;
    }

    public String getTratamento() {
        return tratamento;
    }

    public double getPreco() {
        return preco;
    }

    public double getDuracao() {
        return duracao;
    }

}
