package br.com.powerballoon.model;

public class TransmissorMicroOndas {
    private double frequencia; // em GHz
    private double potencia; // em watts

    public TransmissorMicroOndas(double frequencia, double potencia) {
        if (frequencia < 1 || frequencia > 300) {
            throw new IllegalArgumentException("Frequência deve estar entre 1 GHz e 300 GHz.");
        }
        if (potencia <= 0) {
            throw new IllegalArgumentException("Potência deve ser positiva.");
        }
        this.frequencia = frequencia;
        this.potencia = potencia;
    }

    public double getFrequencia() {
        return frequencia;
    }

    public double getPotencia() {
        return potencia;
    }

    // Calcular alcance máximo da transmissão de energia
    public double calcularAlcanceMaximo(double ganhoAntena, double sensibilidadeReceptor) {
        if (ganhoAntena <= 0 || sensibilidadeReceptor <= 0) {
            throw new IllegalArgumentException("Ganho da antena e sensibilidade do receptor devem ser positivos.");
        }
        return Math.sqrt((potencia * ganhoAntena) / sensibilidadeReceptor);
    }

    // Ajustar a potência permitida pelo balão
    public void ajustarPotencia(double novaPotencia) {
        if (novaPotencia > 0) {
            this.potencia = novaPotencia;
        } else {
            throw new IllegalArgumentException("Potência deve ser positiva.");
        }
    }

    // Verificar a integridade do transmissor
    public boolean verificarIntegridade() {
        return frequencia >= 1 && frequencia <= 300 && potencia > 0;
    }
}
