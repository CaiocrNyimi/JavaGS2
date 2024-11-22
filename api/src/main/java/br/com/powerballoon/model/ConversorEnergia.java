package br.com.powerballoon.model;

public class ConversorEnergia {
    private double eficiencia; // em porcentagem

    // Simular eficiência do conversor de energia
    public ConversorEnergia(double eficiencia) {
        if (eficiencia < 0 || eficiencia > 100) {
            throw new IllegalArgumentException("A eficiência deve estar entre 0 e 100.");
        }
        this.eficiencia = eficiencia;
    }

    public double getEficiencia() {
        return eficiencia;
    }

    public void setEficiencia(double eficiencia) {
        if (eficiencia < 0 || eficiencia > 100) {
            throw new IllegalArgumentException("A eficiência deve estar entre 0 e 100.");
        }
        this.eficiencia = eficiencia;
    }

    // Simular energia convertida
    public double converterEnergia(double energiaEntrada) {
        return energiaEntrada * (eficiencia / 100);
    }

    // Simular perda de energia
    public double calcularPerdaEnergia(double energiaEntrada) {
        return energiaEntrada - converterEnergia(energiaEntrada);
    }
}
