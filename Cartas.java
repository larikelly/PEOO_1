package src.boardgame;

import java.util.*;

public class Cartas {
    private static final Random random = new Random();
    private static final List<String> TIPOS_DE_JOGADOR = Arrays.asList("Azarado", "Sortudo", "Normal");

    public static String tirarCarta() {
        return TIPOS_DE_JOGADOR.get(random.nextInt(TIPOS_DE_JOGADOR.size()));
    }
}
