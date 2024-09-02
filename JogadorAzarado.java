package src.boardgame.Players;

public class JogadorAzarado extends Jogador {                          //herança
    public JogadorAzarado(int posição, String cor) {
        super(posição, cor);
    }

    @Override                                                       //polimorfismo
    public String getTipoDeJogador() {
        return "Azarado";
    }

    @Override
    public boolean somaDados(int somaDados) {
        return somaDados <= 6;
    }
    
}



