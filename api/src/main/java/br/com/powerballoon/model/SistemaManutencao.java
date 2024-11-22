package br.com.powerballoon.model;

import java.util.List;

public class SistemaManutencao {
    private List<Balao> baloes;

    public SistemaManutencao(List<Balao> baloes) {
        this.baloes = baloes;
    }

    // Agendar manutenção de algum balão
    public void agendarManutencao() {
        for (Balao balao : baloes) {
            System.out.println("Manutenção agendada para o balão com rastreador: " + balao.getRastreadorBalao());
        }
    }

    // Concretizar a manutenção do balão
    public void realizarManutencao(Balao balao) {
        if (baloes.contains(balao)) {
            System.out.println("Manutenção realizada no balão com rastreador: " + balao.getRastreadorBalao());
        } else {
            System.out.println("Balão não encontrado no sistema de manutenção.");
        }
    }
}
