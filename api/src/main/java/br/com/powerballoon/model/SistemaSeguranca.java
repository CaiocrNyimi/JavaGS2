package br.com.powerballoon.model;

import java.util.ArrayList;
import java.util.List;

public class SistemaSeguranca {
    private List<Balao> baloes;

    public SistemaSeguranca(List<Balao> baloes) {
        this.baloes = baloes;
    }

    // Verificar se os balões usam hidrogênio
    public void verificarSegurancaBaloes() {
        List<Balao> baloesSeguros = new ArrayList<>();
        List<Balao> baloesPerigosos = new ArrayList<>();

        for (Balao balao : baloes) {
            if (balao.verificarGasInflamavel()) {
                baloesPerigosos.add(balao);
            } else {
                baloesSeguros.add(balao);
            }
        }

        if (!baloesPerigosos.isEmpty()) {
            System.out.println("Atenção! Os seguintes balões usam hidrogênio e necessitam de atenção:");
            for (Balao balao : baloesPerigosos) {
                System.out.println("Balão com rastreador " + balao.getRastreadorBalao());
            }
        }

        if (!baloesSeguros.isEmpty()) {
            System.out.println("Os seguintes balões não usam gases inflamáveis:");
            for (Balao balao : baloesSeguros) {
                System.out.println("Balão com rastreador " + balao.getRastreadorBalao());
            }
        }
    }

    // Monitorar temperatura e pressão dos balões
    public void monitorarCondicoes(double temperatura, double pressao) {
        for (Balao balao : baloes) {
            simularAjusteAltitudeBalao(balao, temperatura, pressao);
        }
    }

    // Ajustar altura dos balões
    private void simularAjusteAltitudeBalao(Balao balao, double temperatura, double pressao) {
        String ajuste = "";
        if (temperatura > 30) {
            ajuste = "subir 100 metros";
        } else if (pressao < 1000) {
            ajuste = "descer 100 metros";
        } else {
            ajuste = "manter altitude atual";
        }
        System.out.println("Ajustando altitude do balão com rastreador " + balao.getRastreadorBalao() + " para " + ajuste);
    }
}
