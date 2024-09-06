import Class.Equipamentos;
import Class.Funcionario;
import Class.Emprestimo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class App {

    private static List<Equipamentos> listaEquipamentos = new ArrayList<>();
    private static List<Funcionario> listaFuncionarios = new ArrayList<>();
    private static List<Emprestimo> listaEmprestimos = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        while (opcao != 4) {
            System.out.println("=== Menu Principal ===");
            System.out.println("1. Cadastro de Equipamentos");
            System.out.println("2. Cadastro de Funcionários");
            System.out.println("3. Menu de Empréstimos");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    cadastroEquipamentos(scanner);
                    break;
                case 2:
                    cadastroFuncionarios(scanner);
                    break;
                case 3:
                    menuEmprestimos(scanner);
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
            System.out.println();
        }

        scanner.close();
    }

    private static void cadastroEquipamentos(Scanner scanner) {
        System.out.println("=== Cadastro de Equipamentos ===");
        System.out.print("Nome: ");
        String nome = scanner.next();
        System.out.print("Descrição: ");
        String descricao = scanner.next();
        System.out.print("Peso: ");
        float peso = scanner.nextFloat();
        System.out.print("Largura: ");
        float largura = scanner.nextFloat();
        System.out.print("Comprimento: ");
        float comprimento = scanner.nextFloat();
        System.out.print("Estado de Conservação (1 - Bom, 2 - Aceitável, 3 - Precisando de Manutenção): ");
        int estadoConservacao = scanner.nextInt();

        Equipamentos equipamento = new Equipamentos(nome, descricao, peso, largura, comprimento, estadoConservacao);
        listaEquipamentos.add(equipamento);

        System.out.println("Equipamento cadastrado com sucesso!");
        System.out.println(equipamento);
    }

    private static void cadastroFuncionarios(Scanner scanner) {
        System.out.println("=== Cadastro de Funcionários ===");
        System.out.print("Nome: ");
        String nome = scanner.next();
        System.out.print("Função: ");
        String funcao = scanner.next();

        Funcionario funcionario = new Funcionario(nome, funcao);
        listaFuncionarios.add(funcionario);

        System.out.println("Funcionário cadastrado com sucesso!");
        System.out.println(funcionario);
    }

    private static void menuEmprestimos(Scanner scanner) {
        int opcao = 0;

        while (opcao != 4) {
            System.out.println("=== Menu de Empréstimos ===");
            System.out.println("1. Retirada de Equipamento");
            System.out.println("2. Entrega de Equipamento");
            System.out.println("3. Visualizar Empréstimos em Andamento");
            System.out.println("4. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    retiradaEquipamento(scanner);
                    break;
                case 2:
                    entregaEquipamento(scanner);
                    break;
                case 3:
                    visualizarEmprestimos();
                    break;
                case 4:
                    System.out.println("Voltando ao Menu Principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
            System.out.println();
        }
    }

    private static void retiradaEquipamento(Scanner scanner) {
        System.out.println("=== Retirada de Equipamento ===");
        System.out.print("Código do Funcionário: ");
        int codigoFuncionario = scanner.nextInt();
        Funcionario funcionario = buscarFuncionarioPorCodigo(codigoFuncionario);

        if (funcionario == null) {
            System.out.println("Funcionário não encontrado.");
            return;
        }

        System.out.print("Código do Equipamento: ");
        int codigoEquipamento = scanner.nextInt();
        Equipamentos equipamento = buscarEquipamentoPorCodigo(codigoEquipamento);

        if (equipamento == null) {
            System.out.println("Equipamento não encontrado.");
            return;
        }

        System.out.print("Observações: ");
        String observacoes = scanner.next();

        Emprestimo emprestimo = new Emprestimo(funcionario, equipamento, observacoes);
        listaEmprestimos.add(emprestimo);

        System.out.println("Empréstimo realizado com sucesso!");
        System.out.println(emprestimo);
    }

    private static void entregaEquipamento(Scanner scanner) {
        System.out.println("=== Entrega de Equipamento ===");
        System.out.print("Código do Empréstimo: ");
        int codigoEmprestimo = scanner.nextInt();
        Emprestimo emprestimo = buscarEmprestimoPorCodigo(codigoEmprestimo);

        if (emprestimo == null) {
            System.out.println("Empréstimo não encontrado.");
            return;
        }

        if (emprestimo.getDataEntrega() != null) {
            System.out.println("Este empréstimo já foi concluído.");
            return;
        }

        emprestimo.setDataEntrega(new Date());
        System.out.println("Equipamento entregue com sucesso!");
        System.out.println(emprestimo);
    }

    private static void visualizarEmprestimos() {
        System.out.println("=== Empréstimos em Andamento ===");

        for (Emprestimo emprestimo : listaEmprestimos) {
            if (emprestimo.getDataEntrega() == null) {
                System.out.println(emprestimo);
            }
        }
    }

    private static Funcionario buscarFuncionarioPorCodigo(int codigo) {
        for (Funcionario funcionario : listaFuncionarios) {
            if (funcionario.getCodigo() == codigo) {
                return funcionario;
            }
        }
        return null;
    }

    private static Equipamentos buscarEquipamentoPorCodigo(int codigo) {
        for (Equipamentos equipamento : listaEquipamentos) {
            if (equipamento.getCodigo() == codigo) {
                return equipamento;
            }
        }
        return null;
    }

    private static Emprestimo buscarEmprestimoPorCodigo(int codigo) {
        for (Emprestimo emprestimo : listaEmprestimos) {
            if (emprestimo.getEquipamento().getCodigo() == codigo && emprestimo.getDataEntrega() == null) {
                return emprestimo;
            }
        }
        return null;
    }
}
