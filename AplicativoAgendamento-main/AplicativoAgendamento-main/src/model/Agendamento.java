package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Agendamento {
    private Cliente cliente;
    private Date horario;
    private Servico servico;

    public Agendamento(Cliente cliente, Date horario, Servico servico) {
        this.cliente = cliente;
        this.horario = horario;
        this.servico = servico;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Date getHorario() {
        return horario;
    }

    public Servico getServico() {
        return servico;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd/MM/yyyy 'às' HH:mm");
        return "Cliente: " + cliente.getNome() +
                " | Horário: " + sdf.format(horario) +
                " | Serviço: " + servico.getNome() +
                " | Duração: " + servico.getDuracao() + " min" +
                " | Valor: R$" + servico.getValor();
    }
}
