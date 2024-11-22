package br.com.powerballoon.model;

import java.util.List;

public class SistemaControleBalao {
    private List<Balao> baloes;

    public SistemaControleBalao(List<Balao> baloes) {
        this.baloes = baloes;
    }

    // Simula ajuste de altitude de todos os balões
    public void simularAjusteAltitude(double novaAltitude) {
        for (Balao balao : baloes) {
            simularAjusteAltitudeBalao(balao, novaAltitude);
        }
    }

    // Simular ajuste de altitude de balão específico
    private void simularAjusteAltitudeBalao(Balao balao, double novaAltitude) {
        // Simula o ajuste de altitude imprimindo uma mensagem
        System.out.println("Ajustando altitude do balão com rastreador " + balao.getRastreadorBalao() + " para " + novaAltitude + " metros.");
    }

    // Verificar integridade dos balões
    public void verificarIntegridadeBaloes() {
        for (Balao balao : baloes) {
            System.out.println("Balão com rastreador " + balao.getRastreadorBalao() + " tem gás inflamável: " + balao.verificarGasInflamavel());
        }
    }
}
