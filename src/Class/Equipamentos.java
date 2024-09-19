package Class;

import java.util.Date;

public class Equipamentos {
    private static int contadorCodigo = 0;
    private int codigo;
    private String nome;
    private String descricao;
    private Date dataDeCompra;
    private float peso;
    private float largura;
    private float comprimento;
    private int estadoConservacao;
    private boolean disponivel = true;
    private boolean emManutencao = false;
    private String historicoManutencao;

    public Equipamentos(String nome, String descricao, float peso, float largura, float comprimento, int estadoConservacao) {
        this.codigo = ++contadorCodigo;
        this.nome = nome;
        this.descricao = descricao;
        this.dataDeCompra = new Date();
        this.peso = peso;
        this.largura = largura;
        this.comprimento = comprimento;
        this.estadoConservacao = estadoConservacao;
        this.historicoManutencao = "";

    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    
    public boolean isDisponivel() {
        return disponivel;
    }

    public boolean isEmManutencao() {
        return emManutencao;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
        if(disponivel = true){
            emManutencao = false;
        }
    }

    public String getHistoricoManutencao() {
        return historicoManutencao;
    }

    public int getEstadoConservacao() {
        return estadoConservacao;
    }
    
    public void setEstadoConservacao(int estadoConservacao) {
        this.estadoConservacao = estadoConservacao;
    }


    public void adicionarManutencao(String manutencao) {
        historicoManutencao += manutencao + "\n";
    }

    public void setEmManutencao(boolean emManutencao) {
        this.emManutencao = emManutencao;
    }

    @Override
    public String toString() {
        return "Equipamentos{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataDeCompra=" + dataDeCompra +
                ", peso=" + peso +
                ", largura=" + largura +
                ", comprimento=" + comprimento +
                ", estadoConservacao=" + estadoConservacao +'}';
    }
}


