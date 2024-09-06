package Class;

import java.util.Date;

public class Funcionario {
    private static int contadorCodigo = 0;
    private int codigo;
    private String nome;
    private String funcao;
    private Date dataDeAdmissao;

    public Funcionario(String nome, String funcao) {
        this.codigo = ++contadorCodigo;
        this.nome = nome;
        this.funcao = funcao;
        this.dataDeAdmissao = new Date();
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", funcao='" + funcao + '\'' +
                ", dataDeAdmissao=" + dataDeAdmissao +
                '}';
    }
}
