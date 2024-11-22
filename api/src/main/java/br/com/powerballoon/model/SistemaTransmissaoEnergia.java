package br.com.powerballoon.model;

import java.util.List;

public class SistemaTransmissaoEnergia {
    private List<Balao> baloes;

    public SistemaTransmissaoEnergia(List<Balao> baloes) {
        this.baloes = baloes;
    }

    // Iniciar a transmissão de energia dos dados dos balões
    public void iniciarTransmissao() {
        for (Balao balao : baloes) {
            System.out.println("Iniciando transmissão de dados do balão com rastreador: " + balao.getRastreadorBalao());
        }
    }
    // Parar a transmissão de energia dos dados dos balões
    public void pararTransmissao() {
        for (Balao balao : baloes) {
            System.out.println("Parando transmissão de dados do balão com rastreador: " + balao.getRastreadorBalao());
        }
    }
    // Verificar a transmissão de energia dos dados dos balões
    public void verificarIntegridadeSinal() {
        for (Balao balao : baloes) {
            System.out.println("Verificando integridade do sinal do balão com rastreador: " + balao.getRastreadorBalao());
        }
    }
}
