package Class;

import java.util.Date;

public class Emprestimo {
    private Date dataEntrega;
    private Date dataRetirada;
    private Funcionario funcionario;
    private Equipamentos equipamento;
    private String observacoes;

    public Emprestimo(Funcionario funcionario, Equipamentos equipamento, String observacoes) {
        this.funcionario = funcionario;
        this.equipamento = equipamento;
        this.dataRetirada = new Date();
        this.observacoes = observacoes;
        this.dataEntrega = null;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public Equipamentos getEquipamento() {
        return equipamento;
    }


    @Override
    public String toString() {
        return "Emprestimo{" +
                "dataRetirada=" + dataRetirada +
                ", dataEntrega=" + (dataEntrega != null ? dataEntrega : "Em andamento") +
                ", funcionario=" + funcionario.getNome() +
                ", equipamento=" + equipamento.getNome() +
                ", observacoes='" + observacoes + '\'' +
                '}';
    }
}
