package model;

public class Cliente {
    private String nome;
    private String telefone;
    private int id;

    public Cliente(String nome, String telefone, int id) {
        this.nome = nome;
        this.telefone = telefone;
        this.id = id;
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

    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + nome + " | Telefone: " + telefone;
    }
}