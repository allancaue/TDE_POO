import Class.Emprestimo;
import Class.Equipamentos;
import Class.Funcionario;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;

public class AppGUI extends JFrame {
    private JPanel mainPanel;
    private ArrayList<Funcionario> listaFuncionarios;
    private ArrayList<Equipamentos> listaEquipamentos;
    private ArrayList<Emprestimo> listaEmprestimos;

    public AppGUI() {
        setTitle("Sistema de Gestão de Equipamentos");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        listaFuncionarios = new ArrayList<>();
        listaEquipamentos = new ArrayList<>();
        listaEmprestimos = new ArrayList<>();
        
        mainPanel = new JPanel(new CardLayout());

        // Inicializar as telas
        mainPanel.add(createMenuPanel(), "Menu");
        mainPanel.add(createEquipamentoCadastroPanel(), "CadastroEquipamentos");
        mainPanel.add(createFuncionarioCadastroPanel(), "CadastroFuncionarios");
        mainPanel.add(createMenuEquipamentosPanel(), "MenuEquipamentos");
        mainPanel.add(createEmprestimosPanel(), "Emprestimos");

        add(mainPanel);
        setLocationRelativeTo(null);
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel(new GridLayout(7, 1));
        panel.add(new JLabel("=== Menu Principal ===", SwingConstants.CENTER));

        JButton btnCadastroEquipamentos = new JButton("Cadastro de Equipamentos");
        btnCadastroEquipamentos.addActionListener(e -> showPanel("CadastroEquipamentos"));
        panel.add(btnCadastroEquipamentos);

        JButton btnCadastroFuncionarios = new JButton("Cadastro de Funcionários");
        btnCadastroFuncionarios.addActionListener(e -> showPanel("CadastroFuncionarios"));
        panel.add(btnCadastroFuncionarios);

        JButton btnMenuEquipamentos = new JButton("Gestão de Equipamentos");
        btnMenuEquipamentos.addActionListener(e -> showPanel("MenuEquipamentos"));
        panel.add(btnMenuEquipamentos);
        
        JButton btnEmprestimos = new JButton("Gestão de Empréstimos");
        btnEmprestimos.addActionListener(e -> showPanel("Emprestimos"));
        panel.add(btnEmprestimos);

        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(e -> System.exit(0));
        panel.add(btnSair);

        return panel;
    }

    private JPanel createEquipamentoCadastroPanel() {
        JPanel panel = new JPanel(new GridLayout(8, 2));
        panel.add(new JLabel("Nome:"));
        JTextField nomeField = new JTextField();
        panel.add(nomeField);

        panel.add(new JLabel("Descrição:"));
        JTextField descricaoField = new JTextField();
        panel.add(descricaoField);

        panel.add(new JLabel("Peso:"));
        JTextField pesoField = new JTextField();
        panel.add(pesoField);

        panel.add(new JLabel("Largura:"));
        JTextField larguraField = new JTextField();
        panel.add(larguraField);

        panel.add(new JLabel("Comprimento:"));
        JTextField comprimentoField = new JTextField();
        panel.add(comprimentoField);

        panel.add(new JLabel("Estado de Conservação (1 - Bom, 2 - Aceitável, 3 - Precisando de Manutenção):"));
        JTextField estadoField = new JTextField();
        panel.add(estadoField);

        JButton btnCadastrar = new JButton("Cadastrar Equipamento");
        btnCadastrar.addActionListener(e -> {
            try {
                String nome = nomeField.getText();
                String descricao = descricaoField.getText();
                float peso = Float.parseFloat(pesoField.getText());
                float largura = Float.parseFloat(larguraField.getText());
                float comprimento = Float.parseFloat(comprimentoField.getText());
                int estado = Integer.parseInt(estadoField.getText());

                Equipamentos equipamento = new Equipamentos(nome, descricao, peso, largura, comprimento, estado);
                listaEquipamentos.add(equipamento);

                JOptionPane.showMessageDialog(this, "Equipamento cadastrado com sucesso!");

                nomeField.setText("");
                descricaoField.setText("");
                pesoField.setText("");
                larguraField.setText("");
                comprimentoField.setText("");
                estadoField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Preencha os campos corretamente!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(btnCadastrar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> showPanel("Menu"));
        panel.add(btnVoltar);

        return panel;
    }

    private JPanel createFuncionarioCadastroPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Nome:"));
        JTextField nomeField = new JTextField();
        panel.add(nomeField);

        panel.add(new JLabel("Função:"));
        JTextField funcaoField = new JTextField();
        panel.add(funcaoField);

        JButton btnCadastrar = new JButton("Cadastrar Funcionário");
        btnCadastrar.addActionListener(e -> {
            String nome = nomeField.getText();
            String funcao = funcaoField.getText();

            Funcionario funcionario = new Funcionario(nome, funcao);
            listaFuncionarios.add(funcionario);

            JOptionPane.showMessageDialog(this, "Funcionário cadastrado com sucesso!");

            nomeField.setText("");
            funcaoField.setText("");
        });
        panel.add(btnCadastrar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> showPanel("Menu"));
        panel.add(btnVoltar);

        return panel;
    }

    private JPanel createEmprestimosPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 1));
        panel.add(new JLabel("=== Gestão de Empréstimos ===", SwingConstants.CENTER));

        JButton btnRetirada = new JButton("Retirada de Equipamento");
        btnRetirada.addActionListener(e -> realizarRetirada());
        panel.add(btnRetirada);

        JButton btnEntrega = new JButton("Entrega de Equipamento");
        btnEntrega.addActionListener(e -> realizarEntrega());
        panel.add(btnEntrega);

        JButton btnVisualizar = new JButton("Visualizar Empréstimos em Andamento");
        btnVisualizar.addActionListener(e -> visualizarEmprestimos());
        panel.add(btnVisualizar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> showPanel("Menu"));
        panel.add(btnVoltar);

        return panel;
    }

    private void realizarRetirada() {
        JTextField codFuncionario = new JTextField();
        JTextField codEquipamento = new JTextField();
        JTextField observacoes = new JTextField();
    
        Object[] message = {
            "Código do Funcionário:", codFuncionario,
            "Código do Equipamento:", codEquipamento,
            "Observações:", observacoes,
        };
    
        int option = JOptionPane.showConfirmDialog(this, message, "Retirada de Equipamento", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int codigoFuncionario = Integer.parseInt(codFuncionario.getText());
                int codigoEquipamento = Integer.parseInt(codEquipamento.getText());
    
                Funcionario funcionario = buscarFuncionarioPorCodigo(codigoFuncionario);
                Equipamentos equipamento = buscarEquipamentoPorCodigo(codigoEquipamento);
    
                if (funcionario == null || equipamento == null) {
                    JOptionPane.showMessageDialog(this, "Funcionário ou Equipamento não encontrados.");
                    return;
                }
    
                if (!equipamento.isDisponivel()) {
                    JOptionPane.showMessageDialog(this, "Equipamento não está disponível.");
                    return;
                }
    
                // Criar o empréstimo e atualizar o status do equipamento
                Emprestimo emprestimo = new Emprestimo(funcionario, equipamento, observacoes.getText());
                listaEmprestimos.add(emprestimo);
                equipamento.setDisponivel(false);
    
                JOptionPane.showMessageDialog(this, "Empréstimo realizado com sucesso!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, insira valores válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }    

    private void realizarEntrega() {
        JTextField codEquipamento = new JTextField();
        Object[] message = {
            "Código do Equipamento:", codEquipamento
        };
    
        int option = JOptionPane.showConfirmDialog(this, message, "Entrega de Equipamento", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int codigoEquipamento = Integer.parseInt(codEquipamento.getText());
    
                Equipamentos equipamento = buscarEquipamentoPorCodigo(codigoEquipamento);
    
                if (equipamento == null) {
                    JOptionPane.showMessageDialog(this, "Equipamento não encontrado.");
                    return;
                }
    
                Emprestimo emprestimo = null;
    
                // Verifica se o equipamento está emprestado
                for (Emprestimo e : listaEmprestimos) {
                    if (e.getEquipamento().equals(equipamento) && e.getDataEntrega() == null) {
                        emprestimo = e;
                        break;
                    }
                }
    
                if (emprestimo == null) {
                    JOptionPane.showMessageDialog(this, "Equipamento não está emprestado.");
                    return;
                }
    
                // Atualiza o estado do empréstimo e equipamento
                emprestimo.setDataEntrega(new Date());
                equipamento.setDisponivel(true);
    
                JOptionPane.showMessageDialog(this, "Equipamento entregue com sucesso!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, insira um código válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }    

    private void visualizarEmprestimos() {
        StringBuilder emprestimos = new StringBuilder("=== Empréstimos em Andamento ===\n");
        boolean existemEmprestimos = false;
    
        for (Emprestimo e : listaEmprestimos) {
            if (e.getDataEntrega() == null) {
                emprestimos.append(e).append("\n");
                existemEmprestimos = true;
            }
        }
    
        if (existemEmprestimos) {
            JOptionPane.showMessageDialog(this, emprestimos.toString());
        } else {
            JOptionPane.showMessageDialog(this, "Nenhum empréstimo em andamento.");
        }
    }    

    private Funcionario buscarFuncionarioPorCodigo(int codigo) {
        for (Funcionario f : listaFuncionarios) {
            if (f.getCodigo() == codigo) return f;
        }
        return null;
    }

    private Equipamentos buscarEquipamentoPorCodigo(int codigo) {
        for (Equipamentos e : listaEquipamentos) {
            if (e.getCodigo() == codigo) return e;
        }
        return null;
    }

    private JPanel createMenuEquipamentosPanel() {
        JPanel panel = new JPanel(new GridLayout(9, 1));
        panel.add(new JLabel("=== Gestão de Equipamentos ===", SwingConstants.CENTER));
    
        JButton btnListarDisponiveis = new JButton("Listar Equipamentos Disponíveis");
        btnListarDisponiveis.addActionListener(e -> listarEquipamentosDisponiveis());
        panel.add(btnListarDisponiveis);
    
        JButton btnEnviarManutencao = new JButton("Enviar Equipamento para Manutenção");
        btnEnviarManutencao.addActionListener(e -> enviarParaManutencao());
        panel.add(btnEnviarManutencao);
    
        JButton btnEquipamentosEmManutencao = new JButton("Equipamentos em Manutenção");
        btnEquipamentosEmManutencao.addActionListener(e -> listarEquipamentosEmManutencao());
        panel.add(btnEquipamentosEmManutencao);
    
        JButton btnRetornarManutencao = new JButton("Retornar Equipamento da Manutenção");
        btnRetornarManutencao.addActionListener(e -> retornarDaManutencao());
        panel.add(btnRetornarManutencao);
    
        JButton btnHistoricoManutencao = new JButton("Histórico de Manutenção");
        btnHistoricoManutencao.addActionListener(e -> historicoManutencao());
        panel.add(btnHistoricoManutencao);
    
        JButton btnAlterarEstado = new JButton("Alterar Estado de Conservação");
        btnAlterarEstado.addActionListener(e -> alterarEstadoConservacao());
        panel.add(btnAlterarEstado);
    
        JButton btnExcluirEquipamento = new JButton("Excluir Equipamento");
        btnExcluirEquipamento.addActionListener(e -> excluirEquipamento());
        panel.add(btnExcluirEquipamento);
    
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> showPanel("Menu"));
        panel.add(btnVoltar);
    
        return panel;
    }
    

    private void listarEquipamentosDisponiveis() {
        StringBuilder disponiveis = new StringBuilder("=== Equipamentos Disponíveis ===\n");
        boolean haEquipamentos = false;
    
        for (Equipamentos equipamento : listaEquipamentos) {
            if (equipamento.isDisponivel() && !equipamento.isEmManutencao() && equipamento.getEstadoConservacao() != 3) {
                disponiveis.append(equipamento).append("\n");
                haEquipamentos = true;
            }
        }
    
        JOptionPane.showMessageDialog(this, haEquipamentos ? disponiveis.toString() : "Nenhum equipamento disponível no momento.");
    }
    
    private void enviarParaManutencao() {
        JTextField codEquipamento = new JTextField();
        Object[] message = {"Código do Equipamento:", codEquipamento};
    
        int option = JOptionPane.showConfirmDialog(this, message, "Enviar para Manutenção", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int codigo = Integer.parseInt(codEquipamento.getText());
                Equipamentos equipamento = buscarEquipamentoPorCodigo(codigo);
    
                if (equipamento == null) {
                    JOptionPane.showMessageDialog(this, "Equipamento inválido.");
                    return;
                }
                if (equipamento.getEstadoConservacao() != 3) {
                    JOptionPane.showMessageDialog(this, "Equipamento não está na condição necessária para envio à manutenção.");
                    return;
                }
                if (!equipamento.isDisponivel() || equipamento.isEmManutencao()) {
                    JOptionPane.showMessageDialog(this, "Equipamento já está em uso ou em manutenção.");
                    return;
                }
    
                equipamento.setDisponivel(false);
                equipamento.setEmManutencao(true);
                equipamento.adicionarManutencao("Equipamento enviado para manutenção em: " + new Date());
    
                JOptionPane.showMessageDialog(this, "Equipamento enviado para manutenção com sucesso!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Código do equipamento inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }    
    
    private void listarEquipamentosEmManutencao() {
        StringBuilder emManutencao = new StringBuilder("=== Equipamentos em Manutenção ===\n");
        boolean haManutencao = false;
    
        for (Equipamentos equipamento : listaEquipamentos) {
            if (equipamento.isEmManutencao()) {
                emManutencao.append(equipamento).append("\n");
                haManutencao = true;
            }
        }
    
        JOptionPane.showMessageDialog(this, haManutencao ? emManutencao.toString() : "Nenhum equipamento em manutenção no momento.");
    }
    
    private void retornarDaManutencao() {
        JTextField codEquipamento = new JTextField();
        Object[] message = {"Código do Equipamento:", codEquipamento};
    
        int option = JOptionPane.showConfirmDialog(this, message, "Retornar da Manutenção", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            int codigo = Integer.parseInt(codEquipamento.getText());
            Equipamentos equipamento = buscarEquipamentoPorCodigo(codigo);
    
            if (equipamento == null || !equipamento.isEmManutencao()) {
                JOptionPane.showMessageDialog(this, "Equipamento não está em manutenção ou não existe.");
                return;
            }
    
            equipamento.setEmManutencao(false);
            equipamento.setDisponivel(true);
            equipamento.adicionarManutencao("Equipamento retornado da manutenção em: " + new Date());
    
            JOptionPane.showMessageDialog(this, "Equipamento retornado da manutenção com sucesso!");
        }
    }
    
    private void historicoManutencao() {
        StringBuilder historico = new StringBuilder("=== Histórico de Manutenção ===\n");
        boolean haHistorico = false;
    
        for (Equipamentos equipamento : listaEquipamentos) {
            if (!equipamento.getHistoricoManutencao().isEmpty()) {
                historico.append("Equipamento Código: ").append(equipamento.getCodigo()).append("\n");
                historico.append("Histórico: ").append(equipamento.getHistoricoManutencao()).append("\n");
                haHistorico = true;
            }
        }
    
        JOptionPane.showMessageDialog(this, haHistorico ? historico.toString() : "Nenhum histórico de manutenção disponível.");
    }
    
    private void alterarEstadoConservacao() {
        JTextField codEquipamento = new JTextField();
        JTextField estadoConservacao = new JTextField();
        Object[] message = {
            "Código do Equipamento:", codEquipamento,
            "Estado de Conservação (1 - Bom, 2 - Aceitável, 3 - Manutenção):", estadoConservacao
        };
    
        int option = JOptionPane.showConfirmDialog(this, message, "Alterar Estado de Conservação", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            int codigo = Integer.parseInt(codEquipamento.getText());
            int estado = Integer.parseInt(estadoConservacao.getText());
    
            Equipamentos equipamento = buscarEquipamentoPorCodigo(codigo);
            if (equipamento == null) {
                JOptionPane.showMessageDialog(this, "Equipamento não encontrado.");
                return;
            }
    
            equipamento.setEstadoConservacao(estado);
            JOptionPane.showMessageDialog(this, "Estado de conservação atualizado com sucesso!");
        }
    }
    
    private void excluirEquipamento() {
        JTextField codEquipamento = new JTextField();
        Object[] message = {"Código do Equipamento:", codEquipamento};
    
        int option = JOptionPane.showConfirmDialog(this, message, "Excluir Equipamento", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            int codigo = Integer.parseInt(codEquipamento.getText());
            Equipamentos equipamento = buscarEquipamentoPorCodigo(codigo);
    
            if (equipamento == null) {
                JOptionPane.showMessageDialog(this, "Equipamento não encontrado.");
                return;
            }
            if (option == JOptionPane.OK_OPTION) {
                listaEquipamentos.remove(equipamento);
                JOptionPane.showMessageDialog(this, "Equipamento excluído com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Exclusão cancelada.");
            }            
        }
    }    

    private void showPanel(String panelName) {
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, panelName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppGUI app = new AppGUI();
            app.setVisible(true);
        });
    }
}