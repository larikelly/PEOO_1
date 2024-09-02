package src.boardgame.Players;

public class JogadorNormal extends Jogador {
    public JogadorNormal(int posição, String cor) {
        super(posição, cor);
    }

    @Override
    public String getTipoDeJogador() {
        return "Normal";
    }
    
    @Override
    public boolean somaDados(int somaDados) {
        return true; // Pode obter tanto valores altos quanto baixos
    }
}

