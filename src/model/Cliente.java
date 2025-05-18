package model;

public class Cliente {
    private String nome;
    private String telefone;
    private int id;
    private int prioridade; // 1 é a prioridade mais alta e 3 é a mais baixa

    public Cliente(String nome, String telefone, int id, int prioridade) {
        this.nome = nome;
        this.telefone = telefone;
        this.id = id;
        this.prioridade = prioridade;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public int getId() {
        return id;
    }

    public int getPrioridade() {
        return prioridade;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + nome + " | Telefone: " + telefone + " | Prioridade: " + prioridade;
    }
}
