package src.boardgame;

import src.boardgame.Players.*;
import java.util.*;

public class movimentosEspeciais {

    public static boolean verificarCasasEspeciais(Scanner input, ArrayList<Jogador> listaDeJogadores, Jogador jogador) {
        switch (jogador.getPosição()) {
            case 10:
            case 25:
            case 38:
                System.out.println("CASA ESPECIAL: " + jogador.getPosição()
                        + ". Ih, que azar! Você não jogará na próxima rodada.");
                jogador.setPulaProximaRodada(true);
                System.out.print("-----------------------------\n");
                break;
            case 5:
            case 15:
            case 30:
                if (!(jogador instanceof JogadorAzarado)) {
                    System.out.println("CASA ESPECIAL: " + jogador.getPosição()
                            + ". Aeeeee! É uma casa da sorte! Avance 3 casas.");
                    System.out.print("-----------------------------\n");
                    jogador.setPosição(jogador.getPosição() + 3);
                } else {
                    System.out
                            .println("CASA ESPECIAL: " + jogador.getPosição() + ", mas é azarado e não pode avançar.");
                    System.out.print("-----------------------------\n");
                }
                break;
            case 13:
                System.out.println("CASA ESPECIAL: " + jogador.getPosição() + ". Aperte enter para sair uma carta para mudar seu tipo de jogador.");
                System.out.print("Pressione Enter para continuar...");
                input.nextLine();
                String novoTipo = Cartas.tirarCarta();
                System.out.println("A nova carta indica que você deve se tornar um jogador do tipo: " + novoTipo);
                atualizarTipoJogador(listaDeJogadores, jogador, novoTipo);
                break;
            case 17:
            case 27:
                System.out.println("CASA ESPECIAL: " + jogador.getPosição());
                System.out.println("Escolha um jogador para voltar para o início:");

                String corJogadorInicio = null; 
                Jogador jogadorEscolhido = null;

                while (jogadorEscolhido == null) {
                    corJogadorInicio = input.nextLine().toLowerCase();

                    for (Jogador j : listaDeJogadores) {
                        if (j.getCor().equalsIgnoreCase(corJogadorInicio)) {
                            jogadorEscolhido = j;
                            break;
                        }
                    }
                    if (jogadorEscolhido == null) {
                        System.out.println("Cor inválida ou jogador não encontrado. Tente novamente:");
                    }
                }
                jogadorEscolhido.setPosição(0);
                System.out.println("O jogador com a cor " + corJogadorInicio + " voltou para o início.");
                break;
            case 20:
            case 35:
                int menorPosição = Integer.MAX_VALUE; // menor valor possivel
                Jogador jogadorMenorPosição = null;
                System.out.println("CASA ESPECIAL: " + jogador.getPosição() +
                        ". Trocará de casa com o jogador mais atrás.");
                for (Jogador g : listaDeJogadores) {
                    if (g.getPosição() < menorPosição) {
                        menorPosição = g.getPosição();
                        jogadorMenorPosição = g;
                    }
                }
                if (jogadorMenorPosição != null) {
                    int temp = jogador.getPosição();
                    jogador.setPosição(jogadorMenorPosição.getPosição());
                    jogadorMenorPosição.setPosição(temp);
                } else {
                    System.out.println("Você é o último jogador. Não há ninguém para trocar de posição.");
                }
                break;
        }

        return jogador.getPosição() > 40;
    }

    public static void atualizarTipoJogador(ArrayList<Jogador> listaDeJogadores, Jogador jogador, String novoTipo) {
        int index = listaDeJogadores.indexOf(jogador);
        if (index != -1) {
            Jogador jogadorAtualizado;
            int position = jogador.getPosição();
            String cor = jogador.getCor();

            switch (novoTipo) {
                case "Azarado":
                    jogadorAtualizado = new JogadorAzarado(position, cor);
                    break;
                case "Sortudo":
                    jogadorAtualizado = new JogadorSortudo(position, cor);
                    break;
                case "Normal":
                default:
                    jogadorAtualizado = new JogadorNormal(position, cor);
                    break;
            }
            listaDeJogadores.set(index, jogadorAtualizado);
        }
    }
}
