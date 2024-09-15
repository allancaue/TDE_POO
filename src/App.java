import Class.Emprestimo;
import Class.Equipamentos;
import Class.Funcionario;
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

        while (opcao != 5) {
            System.out.println("=== Menu Principal ===");
            System.out.println("1. Cadastro de Equipamentos");
            System.out.println("2. Cadastro de Funcionários");
            System.out.println("3. Menu de Empréstimos");
            System.out.println("4. Menu de Equipamentos");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

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
                    menuEquipamentos(scanner);
                    break;
                case 5:
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
            String nome = scanner.nextLine();
        System.out.print("Descrição: ");
            String descricao = scanner.nextLine();
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
        String nome = scanner.nextLine();
        System.out.print("Função: ");
        String funcao = scanner.nextLine();

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
            scanner.nextLine();

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

        if (equipamento.getEstadoConservacao() == 3) {
            System.out.println("Equipamento precisa de manutenção e não pode ser retirado.");
            return;
        }
    
        if (!equipamento.isDisponivel()) {
            System.out.println("Equipamento não está disponível.");
            return;
        }
    
        System.out.print("Observações: ");
        scanner.nextLine();
        String observacoes = scanner.nextLine();
    
        Emprestimo emprestimo = new Emprestimo(funcionario, equipamento, observacoes);
        listaEmprestimos.add(emprestimo);
        equipamento.setDisponivel(false);
    
        System.out.println("Empréstimo realizado com sucesso!");
        System.out.println(emprestimo);
    }

    private static void entregaEquipamento(Scanner scanner) {
        System.out.println("=== Entrega de Equipamento ===");
        System.out.print("Código do equipamento emprestado: ");
            int codigoEquipamento = scanner.nextInt();
        Emprestimo emprestimo = buscarEmprestimoPorCodigo(codigoEquipamento);

        if (emprestimo == null) {
            System.out.println("Empréstimo não encontrado.");
            return;
        }

        if (emprestimo.getDataEntrega() != null) {
            System.out.println("Este empréstimo já foi concluído.");
            return;
        }
        mudarEstado(scanner, codigoEquipamento);
        emprestimo.setDataEntrega(new Date());
        emprestimo.getEquipamento().setDisponivel(true);
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

    private static void menuEquipamentos(Scanner scanner) {
        int opcao = 0;

        while (opcao != 8) {
            System.out.println("=== Menu de Equipamentos ===");
            System.out.println("1. Lista de Equipamentos Disponíveis");
            System.out.println("2. Enviar Equipamento para Manutenção");
            System.out.println("3. Equipamentos em Manutenção");
            System.out.println("4. Retornar Equipamento da Manutenção");
            System.out.println("5. Histórico de Manutenção");
            System.out.println("6. Mudar Estado de Conservação do Equipamento");
            System.out.println("7. Excluir Equipamento");
            System.out.println("8. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    listaEquipamentosDisponiveis();
                    break;
                case 2:
                    enviarManutencaoEquipamento(scanner);
                    break;
                case 3:
                    equipamentosEmManutencao();
                    break;
                case 4:
                    retornarManutencaoEquipamento(scanner);
                    break;
                case 5:
                    historicoManutencao();
                    break;
                case 6:
                    System.out.print("Código do Equipamento: ");
                    int codigoEquipamento = scanner.nextInt();
                    mudarEstado(scanner, codigoEquipamento);
                    break;
                    case 7:
                    excluirEquipamento(scanner);
                    break;
                case 8:
                    System.out.println("Voltando ao Menu Principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
            System.out.println();
        }
    }

    private static void listaEquipamentosDisponiveis() {
        System.out.println("=== Equipamentos Disponíveis ===");
        boolean haEquipamentosDisponiveis = false;
    
        for (Equipamentos equipamento : listaEquipamentos) {
            if (equipamento.isDisponivel() && !equipamento.isEmManutencao() && equipamento.getEstadoConservacao() != 3) {
                System.out.println(equipamento);
                haEquipamentosDisponiveis = true;
            }
        }
    
        if (!haEquipamentosDisponiveis) {
            System.out.println("Nenhum equipamento disponível no momento.");
        }
    }

    private static void enviarManutencaoEquipamento(Scanner scanner) {
        System.out.println("=== Enviar Equipamento para Manutenção ===");
        System.out.print("Código do Equipamento: ");
            int codigoEquipamento = scanner.nextInt();
        Equipamentos equipamento = buscarEquipamentoPorCodigo(codigoEquipamento);
    
        if (equipamento == null) {
            System.out.println("Equipamento não encontrado.");
            return;
        }
        if (equipamento.getEstadoConservacao() != 3) {
            System.out.println("Equipamento não está na condição necessária para envio à manutenção.");
            return;
        }
        if (!equipamento.isDisponivel() || equipamento.isEmManutencao()) {
            System.out.println("Equipamento já está em uso ou em manutenção.");
            return;
        }
        equipamento.setDisponivel(false);
        equipamento.setEmManutencao(true); 
        equipamento.adicionarManutencao("Equipamento enviado para manutenção em: " + new Date());
        System.out.println("Equipamento enviado para manutenção com sucesso!");
    }

    private static void equipamentosEmManutencao() {
        System.out.println("=== Equipamentos em Manutenção ===");
        boolean equipamentosEmManutencao = false;
    
        for (Equipamentos equipamento : listaEquipamentos) {
            if (equipamento.isEmManutencao()) {
                System.out.println(equipamento);
                equipamentosEmManutencao = true;
            }
        }
    
        if (!equipamentosEmManutencao) {
            System.out.println("Nenhum equipamento em manutenção no momento.");
        }
    }

    private static void retornarManutencaoEquipamento(Scanner scanner) {
        System.out.println("=== Retornar Equipamento da Manutenção ===");
        System.out.print("Código do Equipamento: ");
            int codigoEquipamento = scanner.nextInt();
        Equipamentos equipamento = buscarEquipamentoPorCodigo(codigoEquipamento);
    
        if (equipamento == null || !equipamento.isEmManutencao()) {
            System.out.println("Equipamento não está em manutenção.");
            return;
        }
        equipamento.setDisponivel(true);
        equipamento.setEmManutencao(false);
        mudarEstado (scanner, codigoEquipamento);
        equipamento.adicionarManutencao("Equipamento retornado da manutenção em: " + new Date());
        System.out.println("Equipamento retornado da manutenção com sucesso!");
    }    

    private static void historicoManutencao() {
        System.out.println("=== Histórico de Manutenção ===");
        for (Equipamentos equipamento : listaEquipamentos) {
            if (!equipamento.getHistoricoManutencao().isEmpty()) {
                System.out.println("Equipamento Código: " + equipamento.getCodigo());
                System.out.println("Histórico de Manutenção: ");
                System.out.println(equipamento.getHistoricoManutencao());
            }
        }
    }

    private static void mudarEstado (Scanner scanner, int codigoEquipamento) {
        Equipamentos equipamento = buscarEquipamentoPorCodigo(codigoEquipamento);
            if (equipamento == null) {
                System.out.println("Equipamento não encontrado.");
            } else {
                System.out.println(equipamento);
                System.out.print("Estado de Conservação (1 - Bom, 2 - Aceitável, 3 - Precisando de Manutenção): ");
            int estadoConservacao = scanner.nextInt();
            equipamento.setEstadoConservacao(estadoConservacao);
            System.out.println("Equipamento modificado com sucesso!");
            
            System.out.println("Equipamento: " + equipamento.getNome() + " Estado de concervação: " + equipamento.getEstadoConservacao());
        }
    }

    private static void excluirEquipamento(Scanner scanner) {
        System.out.println("=== Excluir Equipamento ===");
        System.out.print("Código do Equipamento a ser excluído: ");
        int codigoEquipamento = scanner.nextInt();
        
        Equipamentos equipamento = buscarEquipamentoPorCodigo(codigoEquipamento);
        
        if (equipamento == null) {
            System.out.println("Equipamento não encontrado.");
            return;
        }
        
        listaEquipamentos.remove(equipamento);
        System.out.println("Equipamento excluído com sucesso!");
    }    
}