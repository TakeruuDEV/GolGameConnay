package ACTIVITY1;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class JogoDoGolConvey implements ActionListener {

    private int[][] tabuleiro = new int[10][10];
    private JFrame janela;
    private JPanel grade;

    public JogoDoGolConvey() {
        janela = new JFrame("Jogo do Gol Convey");
        grade = new JPanel();
        grade.setLayout(new GridLayout(10, 10));

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tabuleiro[i][j] = random.nextInt(2);
                JButton botao = new JButton();
                if (tabuleiro[i][j] == 1) {
                    botao.setBackground(Color.BLACK);
                } else {
                    botao.setBackground(Color.WHITE);
                }
                botao.addActionListener(this);
                grade.add(botao);
            }
        }

        janela.add(grade);
        janela.pack();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarTabuleiro();
            }
        });
        timer.start();
    }

    private void atualizarTabuleiro() {
        int[][] novoTabuleiro = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int vizinhosVivos = contarVizinhosVivos(i, j);
                if (tabuleiro[i][j] == 1 && (vizinhosVivos < 2 || vizinhosVivos > 3)) {
                    novoTabuleiro[i][j] = 0;
                } else if (tabuleiro[i][j] == 0 && vizinhosVivos == 3) {
                    novoTabuleiro[i][j] = 1;
                } else {
                    novoTabuleiro[i][j] = tabuleiro[i][j];
                }
            }
        }
        tabuleiro = novoTabuleiro;
        atualizarGrade();
    }

    private int contarVizinhosVivos(int x, int y) {
        int contador = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i >= 0 && i < 10 && j >= 0 && j < 10 && !(i == x && j == y)) {
                    contador += tabuleiro[i][j];
                }
            }
        }
        return contador;
    }

    private void atualizarGrade() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JButton botao = (JButton) grade.getComponent(i * 10 + j);
                if (tabuleiro[i][j] == 1) {
                    botao.setBackground(Color.BLACK);
                } else {
                    botao.setBackground(Color.WHITE);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton botaoClicado = (JButton) e.getSource();
        int posicao = grade.getComponentZOrder(botaoClicado);
        int x = posicao / 10;
        int y = posicao % 10;
        tabuleiro[x][y] = 1 - tabuleiro[x][y];
        atualizarGrade();
    }

    public static void main(String[] args) {
        new JogoDoGolConvey();
    }
}
