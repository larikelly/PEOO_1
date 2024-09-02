package src.boardgame;

import java.util.*;

public class GerenciadorCores {
    private List<String> coresDisponiveis;

    public GerenciadorCores() {
        this.coresDisponiveis = Arrays.asList("amarelo", "azul", "vermelho", "preto", "branco", "verde");
    }
    public String escolherCor(Scanner input, List<String> coresDisponiveis, List<String> coresEscolhidas) {
        String cor;
        do {
            System.out.println("Escolha uma cor: " + coresDisponiveis);
            cor = input.nextLine().toLowerCase(); 
            if (!coresDisponiveis.contains(cor)) {
                System.out.println("Cor inválida. Por favor, escolha uma cor da lista.");
            } else if (coresEscolhidas.contains(cor)) {
                System.out.println("Essa cor já foi escolhida. Escolha outra cor.");
            }
        } while (!coresDisponiveis.contains(cor) || coresEscolhidas.contains(cor));
        coresEscolhidas.add(cor);
        return cor;
    }

    public List<String> getCoresDisponiveis() {
        return coresDisponiveis;
    }
}
