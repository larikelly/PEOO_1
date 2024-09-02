package src.boardgame;

import java.util.*;

import src.boardgame.Players.*;

public class App {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        GerenciadorCores gerenciadorCores = new GerenciadorCores();

        System.out.println("Quantas pessoas vão jogar? (Mínimo: 2 / Máximo: 6)");
        int numJogadores = input.nextInt();
        input.nextLine(); // Limpar o buffer do scanner

        if (numJogadores < 2 || numJogadores > 6) {
            System.out.println("Erro: Número de jogadores deve ser entre 2 e 6.");
            input.close();
            return;
        }

        List<String> coresDisponiveis = gerenciadorCores.getCoresDisponiveis();
        List<String> coresEscolhidas = new ArrayList<>();
        ArrayList<Jogador> listaDeJogadores = new ArrayList<>(numJogadores);
        String[] tipos = { "Azarado", "Sortudo", "Normal" };

        // Escolher cores e criar jogadores
        for (int i = 0; i < numJogadores; i++) {
            String cor = gerenciadorCores.escolherCor(input, coresDisponiveis, coresEscolhidas);

            coresEscolhidas.add(cor);

            Jogador jogador;
            String tipoDeJogador = tipos[new Random().nextInt(tipos.length)];

            // Seleciona o tipo de jogador
            switch (tipoDeJogador) {
                case "Azarado":
                    jogador = new JogadorAzarado(0, cor);
                    break;
                case "Sortudo":
                    jogador = new JogadorSortudo(0, cor);
                    break;
                case "Normal":
                default:
                    jogador = new JogadorNormal(0, cor);
                    break;
            }

            listaDeJogadores.add(jogador);
        }

        // Ajustar os tipos de jogadores para garantir que não existam dois jogadores do mesmo tipo
        ajustarTiposDeJogadores(listaDeJogadores);

        String vencedorCor = null;
        int vencedorRodadas = 0;
        boolean jogoFinalizado = false;

        // Loop principal do jogo
        while (!jogoFinalizado) {
            for (Jogador jogador : listaDeJogadores) {
                if (jogador.isPulaProximaRodada()) {
                    jogador.setPulaProximaRodada(false);
                    System.out.println("Jogador " + jogador.getCor() + " está pulando esta rodada.");
                    continue; // Pular a próxima rodada
                }

                System.out.print("\n");
                System.out.println("Rodada do jogador: " + jogador.getCor());
                System.out.println("Posição atual: " + jogador.getPosição());
                System.out.println("Aperte Enter para jogar os dados...");
                input.nextLine(); // Espera pelo Enter

                int resultadoDados = Dados.rolarDoisDados();
                jogador.setPosição(jogador.getPosição() + resultadoDados);

                System.out.println("O jogador " + jogador.getCor() + " obteve no total: " + resultadoDados);
                System.out.print("-----------------------------\n");

                jogoFinalizado = movimentosEspeciais.verificarCasasEspeciais(input, listaDeJogadores, jogador);
                if (jogoFinalizado) {
                    vencedorCor = jogador.getCor();
                    vencedorRodadas = jogador.getRodadas();
                    break;
                }
            }
        }

        // Exibir resultados finais
        exibirResultados(vencedorCor, vencedorRodadas, listaDeJogadores);
        input.close();
    }

    private static void exibirResultados(String vencedorCor, int vencedorRodadas, ArrayList<Jogador> listaDeJogadores) {
        
        System.out.println("Jogador Vencedor: " + vencedorCor);
       // System.out.println("Número de rodadas: " + vencedorRodadas);
        System.out.print("-----------------------------\n");

        for (Jogador jogador : listaDeJogadores) {
            System.out.println("Rodada do Jogador " + jogador.getCor() +
                  //  "\nNúmero de Rodadas: " + jogador.getRodadas() +
                    "\nPosição do jogador: " + jogador.getPosição());
        }
    }

    private static void ajustarTiposDeJogadores(List<Jogador> listaDeJogadores) {
        Set<String> tiposUsados = new HashSet<>();          //verificar a presença de um item e adicionar novos itens
        String[] tiposDisponiveis = { "Azarado", "Sortudo", "Normal" };

        for (Jogador jogador : listaDeJogadores) {
            String tipoAtual = jogador.getTipoDeJogador();

            if (tiposUsados.contains(tipoAtual)) {
                // Encontrar um tipo diferente que não tenha sido usado
                for (String tipo : tiposDisponiveis) {
                    if (!tiposUsados.contains(tipo)) {
                        jogador.setTipoDeJogador(tipo);
                        tiposUsados.add(tipo);
                        break;
                    }
                }
            } else {
                tiposUsados.add(tipoAtual);
            }
        }
    }
}
