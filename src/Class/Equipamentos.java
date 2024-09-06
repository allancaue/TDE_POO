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

    public Equipamentos(String nome, String descricao, float peso, float largura, float comprimento, int estadoConservacao) {
        this.codigo = ++contadorCodigo;
        this.nome = nome;
        this.descricao = descricao;
        this.dataDeCompra = new Date();
        this.peso = peso;
        this.largura = largura;
        this.comprimento = comprimento;
        this.estadoConservacao = estadoConservacao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
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
                ", estadoConservacao=" + estadoConservacao +
                '}';
    }
}
