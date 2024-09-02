package src.boardgame;

import java.util.Random;

public class Dados {
    private static final Random random = new Random();
    
    public static int rolarDoisDados() {
        int dado1 = random.nextInt(6) + 1; 
        int dado2 = random.nextInt(6) + 1; 
        int soma = dado1 + dado2; 
        
        System.out.println("Os valores dos dados são " + dado1 + " e " + dado2);
        
        while (dado1 == dado2) {
            System.out.println("Dados iguais, jogando novamente...");
            dado1 = random.nextInt(6) + 1;
            dado2 = random.nextInt(6) + 1;
            soma = dado1 + dado2; 
        }
        return soma;
    }
}

    