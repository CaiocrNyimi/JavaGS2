package br.com.powerballoon.model;

public class PainelSolar {
    private double area; // em metros quadrados
    private double eficiencia; // em porcentagem

    // Calcular eficiência dos paineis solares
    public PainelSolar(double area, double eficiencia) {
        if (eficiencia < 0 || eficiencia > 100) {
            throw new IllegalArgumentException("A eficiência deve estar entre 0 e 100.");
        }
        this.area = area;
        this.eficiencia = eficiencia;
    }

    public double getArea() {
        return area;
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

    // Calcular a energia gerada pela irradiação solar (kWh/m^2)
    public double calcularEnergiaGerada(double irradiacaoSolar) {
        return area * irradiacaoSolar * (eficiencia / 100);
    }
}
