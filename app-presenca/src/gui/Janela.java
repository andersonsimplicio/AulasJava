package gui;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Janela {
    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu menuArquivo;
    private JMenuItem itemAbrir;
    private JMenuItem itemSalvar;
    private  String numeroTurma;
    private JLabel labelNumeroTurma;
    private JPanel panel;
    private JTable tabela;
    private String dataAtual;

    public Janela(String titulo){
        frame = new JFrame(titulo);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);


         // Cria a barra de menu
        menuBar = new JMenuBar();
        itemAbrir = new JMenuItem("Abrir");
        itemSalvar = new JMenuItem("Salvar");
        menuArquivo = new JMenu("Arquivo");

        // Adiciona os itens de menu ao menu
        menuArquivo.add(itemAbrir);
        menuArquivo.add(itemSalvar);


        menuArquivo.addSeparator(); 

         // Adiciona ações aos itens de menu
        itemAbrir.addActionListener(new AbrirActionListener());
        itemSalvar.addActionListener(new SalvarActionListener());
        // Adiciona o menu ao menuBar
        menuBar.add(menuArquivo);

        // Define a barra de menu da janela
        frame.setJMenuBar(menuBar);

        // Cria o painel para o conteúdo
        panel = new JPanel(new BorderLayout());
        // Cria o JLabel para o número da turma
        labelNumeroTurma = new JLabel("Número da turma: Não definido");
        panel.add(labelNumeroTurma, BorderLayout.NORTH);

        // Cria o modelo da tabela
        DefaultTableModel model = new DefaultTableModel(){
            @Override
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;  // Coluna "Nome"
                    case 1:
                        return Boolean.class; // Coluna "Presença"
                    default:
                        return Object.class;
                }
            }
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1; // Apenas a coluna de presença é editável
            }

        };
        model.addColumn("Nome");
        // Obtém a data atual
        dataAtual = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        model.addColumn(dataAtual);
    

        // Cria a tabela usando o modelo
        tabela = new JTable(model);
        panel.add(new JScrollPane(tabela), BorderLayout.CENTER);

        // Adiciona o painel à janela
        frame.add(panel);
    }
    public void mostrar(){
        frame.setVisible(true);
    }

    // Classe interna para lidar com a ação de abrir
    private class AbrirActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            numeroTurma = JOptionPane.showInputDialog(frame, "Digite o número da turma:");

            if (numeroTurma != null && !numeroTurma.trim().isEmpty()) {
                // Atualiza o JLabel com o número da turma
                labelNumeroTurma.setText("Número da turma: " + numeroTurma);

                JFileChooser fileChooser = new JFileChooser();
                int retorno = fileChooser.showOpenDialog(frame);

                if (retorno == JFileChooser.APPROVE_OPTION) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(fileChooser.getSelectedFile()))) {
                        List<String> nomes = new ArrayList<>();
                        String linha;

                    while ((linha = reader.readLine()) != null) {
                        String[] campos = linha.split(",");
                        if (campos.length > 0) {
                            String nome = campos[0].trim();  // Adiciona o nome (primeiro campo) à lista
                            if (!nome.equalsIgnoreCase("nome")) {
                                nomes.add(nome);  // Adiciona o nome à lista, exceto se for "Nome"
                            }
                        }
                    }

                    // Ordena os nomes em ordem alfabética
                    Collections.sort(nomes);

                    // Obtém o modelo da tabela
                    DefaultTableModel model = (DefaultTableModel) tabela.getModel();
                    // Limpa a tabela antes de adicionar novos dados


                    
                    model.setRowCount(0);
                       // Adiciona os nomes à tabela
                    for (String nome : nomes) {
                        model.addRow(new Object[]{nome,false});
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, "Erro ao ler o arquivo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Número da turma não fornecido.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    // Classe interna para lidar com a ação de salvar
    private class SalvarActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Exibe um JFileChooser para salvar o arquivo
            String nomeArquivo = "lista_" +numeroTurma+ ".csv";
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setSelectedFile(new java.io.File(nomeArquivo));
            int retorno = fileChooser.showSaveDialog(frame);
           

            if (retorno == JFileChooser.APPROVE_OPTION) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileChooser.getSelectedFile()))) {
                    // Escreve o cabeçalho com número da turma e data
                    writer.write("Número da turma: " + numeroTurma+ ","+dataAtual);
                    writer.newLine();
                    // Escreve os dados dos alunos
                    DefaultTableModel model = (DefaultTableModel) tabela.getModel();
                    int rowCount = model.getRowCount();
                    for (int i = 0; i < rowCount; i++) {
                        String nome = model.getValueAt(i, 0).toString();                       
                        boolean marcado = (boolean) model.getValueAt(i, 1);
                        String status = marcado ? "F" : "*"; // Inverte a lógica
                        writer.write(nome + "," + status);
                        writer.newLine();
                    }

                    JOptionPane.showMessageDialog(frame, "Arquivo salvo com sucesso.");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao salvar o arquivo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
