package model;
import java.util.Date;

public class Agendamento {
    private Cliente cliente;
    private Date horario;

    public Agendamento(Cliente cliente, Date horario) {
        this.cliente = cliente;
        this.horario = horario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Date getHorario() {
        return horario;
    }

    @Override
    public String toString() {
        return "Cliente: " + cliente.getNome() + " | Horário: " + horario;
    }
}