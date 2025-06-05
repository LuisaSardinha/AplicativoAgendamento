package model;

public class Servico {
    private String nome;
    private int duracao; // em minutos
    private double valor;

    public Servico(String nome, int duracao, double valor) {
        this.nome = nome;
        this.duracao = duracao;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public int getDuracao() {
        return duracao;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return nome + " | Duração: " + duracao + " min | Valor: R$" + valor;
    }
}
