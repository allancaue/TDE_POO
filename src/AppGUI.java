import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppGUI extends JFrame {
    private JPanel mainPanel;

    public AppGUI() {
        setTitle("Sistema de Gestão de Equipamentos");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel = new JPanel(new CardLayout());

        // Inicializar as telas
        mainPanel.add(createMenuPanel(), "Menu");
        mainPanel.add(createEquipamentoCadastroPanel(), "CadastroEquipamentos");
        mainPanel.add(createFuncionarioCadastroPanel(), "CadastroFuncionarios");

        add(mainPanel);
        setLocationRelativeTo(null);
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 1));
        panel.add(new JLabel("=== Menu Principal ===", SwingConstants.CENTER));

        JButton btnCadastroEquipamentos = new JButton("Cadastro de Equipamentos");
        btnCadastroEquipamentos.addActionListener(e -> showPanel("CadastroEquipamentos"));
        panel.add(btnCadastroEquipamentos);

        JButton btnCadastroFuncionarios = new JButton("Cadastro de Funcionários");
        btnCadastroFuncionarios.addActionListener(e -> showPanel("CadastroFuncionarios"));
        panel.add(btnCadastroFuncionarios);

        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(e -> System.exit(0));
        panel.add(btnSair);

        return panel;
    }

    private JPanel createEquipamentoCadastroPanel() {
        JPanel panel = new JPanel(new GridLayout(7, 2));
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
            // Lógica de cadastro de equipamento
            String nome = nomeField.getText();
            String descricao = descricaoField.getText();
            float peso = Float.parseFloat(pesoField.getText());
            float largura = Float.parseFloat(larguraField.getText());
            float comprimento = Float.parseFloat(comprimentoField.getText());
            int estado = Integer.parseInt(estadoField.getText());

            // Aqui deve ser implementada a lógica para salvar o equipamento
            JOptionPane.showMessageDialog(this, "Equipamento cadastrado com sucesso!");

            nomeField.setText("");
            descricaoField.setText("");
            pesoField.setText("");
            larguraField.setText("");
            comprimentoField.setText("");
            estadoField.setText("");
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
            // Lógica de cadastro de funcionário
            String nome = nomeField.getText();
            String funcao = funcaoField.getText();

            // Aqui deve ser implementada a lógica para salvar o funcionário
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
