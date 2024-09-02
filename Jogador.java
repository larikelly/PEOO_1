package src.boardgame.Players;

import src.boardgame.Dados;

public abstract class Jogador {
    protected int posição, rodadas;
    protected String cor;
    protected boolean pulaProximaRodada;
    protected String tipoDeJogador;

    public Jogador(int posição, String cor) {           //construtor
        this.posição = posição;
        this.cor = cor;
        this.rodadas = 0;
        this.pulaProximaRodada = false;
    }

    public abstract boolean somaDados(int somaDados);       //metodos abstratos - outras classes

    public abstract String getTipoDeJogador();

    public void jogarDados() {
        int somadosDados = Dados.rolarDoisDados();
        if (somaDados(somadosDados)) {
            posição += somadosDados;
            System.out.println("Jogador " + cor + " jogou os dados e obteve " + somadosDados);
        } else {
            System.out.println("Jogador " + cor + " não conseguiu obter a soma adequada.");
        }
        incrementarRodadas();
    }

    public void incrementarRodadas() {
        rodadas++;
    }

    public int getPosição() {
        return posição;
    }

    public String getCor() {
        return cor;
    }

    public int getRodadas() {
        return rodadas;
    }

    public boolean isPulaProximaRodada() {
        return pulaProximaRodada;
    }

    public void setPulaProximaRodada(boolean pulaProximaRodada) {
        this.pulaProximaRodada = pulaProximaRodada;
    }

    public void setPosição(int posição) {
        this.posição = posição;
    }

    public void setTipoDeJogador(String tipoDeJogador) {
        this.tipoDeJogador = tipoDeJogador;
    }

}

