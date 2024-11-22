package br.com.powerballoon.model;

public class ArmazenamentoEnergia {
    private double capacidadeTotal; // em kWh
    private double energiaArmazenada; // em kWh

    public ArmazenamentoEnergia(double capacidadeTotal) {
        this.capacidadeTotal = capacidadeTotal;
        this.energiaArmazenada = 0;
    }

    public double getCapacidadeTotal() {
        return capacidadeTotal;
    }

    public double getEnergiaArmazenada() {
        return energiaArmazenada;
    }

    // Simular armazenamento de energia
    public void armazenarEnergia(double energia) {
        if (energiaArmazenada + energia <= capacidadeTotal) {
            energiaArmazenada += energia;
        } else {
            throw new IllegalArgumentException("Capacidade de armazenamento excedida.");
        }
    }

    // Simular energia restante
    public double calcularEnergiaRestante() {
        return capacidadeTotal - energiaArmazenada;
    }
}
