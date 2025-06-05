package model;

import java.text.SimpleDateFormat;
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
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd/MM/yyyy 'às' HH:mm");
        return "Cliente: " + cliente.getNome() + " | Horário: " + sdf.format(horario);
    }
}