package br.com.powerballoon.model;

public class ReceptorTerrestre {
    private double frequencia; // em GHz
    private double ganho; // em dBi

    public ReceptorTerrestre(double frequencia, double ganho) {
        this.frequencia = frequencia;
        this.ganho = ganho;
    }

    public double getFrequencia() {
        return frequencia;
    }

    public double getGanho() {
        return ganho;
    }

    // Calcular intensidade do sinal do transmissor
    public double calcularIntensidadeSinal(double potenciaTransmissor, double distancia) {
        return (potenciaTransmissor * ganho) / (distancia * distancia);
    }

    // Verificar se frequência está dentro do limite e se está livre de interferência
    public boolean verificarFrequenciaPermitida() {
        // Faixa permitida de 1 GHz a 300 GHz
        if (frequencia < 1 || frequencia > 300) {
            return false;
        }
        if (frequencia >= 1 && frequencia <= 6) {
            System.out.println("Frequência adequada para comunicação de baixa frequência.");
        } else if (frequencia > 6 && frequencia <= 30) {
            System.out.println("Frequência adequada para comunicação de micro-ondas.");
        } else if (frequencia > 30 && frequencia <= 300) {
            System.out.println("Frequência adequada para comunicações de alta frequência.");
        } else {
            return false;
        }
        return true;
    }
}
