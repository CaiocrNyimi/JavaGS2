package br.com.powerballoon.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.util.Arrays;
import java.util.List;

public class Balao {

    public static final List<String> GASES_VALIDOS = Arrays.asList("Hélio", "Hidrogênio");
    public static final List<String> MATERIAIS_VALIDOS = Arrays.asList("Poliéster", "Nylon");
    public static final List<String> CABOS_VALIDOS = Arrays.asList("Cabo de aço", "Cabo de silicone", "Cabo de teflon");

    @NotBlank(message = "O gás não pode estar em branco")
    private String gas;

    @NotBlank(message = "O material do balão não pode estar em branco")
    private String matBalao;

    @NotBlank(message = "O cabo do balão não pode estar em branco")
    private String caboBalao;

    @Positive(message = "O rastreador do balão deve ser um número positivo")
    private int rastreadorBalao;

    @Positive(message = "O CNPJ deve ser um número positivo")
    @Size(min = 14, max = 14, message = "O CNPJ deve conter exatamente 14 dígitos")
    private long cnpj;

    public Balao() {}

    public Balao(String gas, String matBalao, String caboBalao, int rastreadorBalao, long cnpj) {
        if (!GASES_VALIDOS.contains(gas)) {
            throw new IllegalArgumentException("Gás inválido. Opções válidas: " + GASES_VALIDOS);
        }
        if (!MATERIAIS_VALIDOS.contains(matBalao)) {
            throw new IllegalArgumentException("Material inválido. Opções válidas: " + MATERIAIS_VALIDOS);
        }
        if (!CABOS_VALIDOS.contains(caboBalao)) {
            throw new IllegalArgumentException("Cabo inválido. Opções válidas: " + CABOS_VALIDOS);
        }

        this.gas = gas;
        this.matBalao = matBalao;
        this.caboBalao = caboBalao;
        this.rastreadorBalao = rastreadorBalao;
        this.cnpj = cnpj;
    }

    public String getGas() {
        return gas;
    }

    public void setGas(String gas) {
        if (!GASES_VALIDOS.contains(gas)) {
            throw new IllegalArgumentException("Gás inválido. Opções válidas: " + GASES_VALIDOS);
        }
        this.gas = gas;
    }

    public String getMatBalao() {
        return matBalao;
    }

    public void setMatBalao(String matBalao) {
        if (!MATERIAIS_VALIDOS.contains(matBalao)) {
            throw new IllegalArgumentException("Material inválido. Opções válidas: " + MATERIAIS_VALIDOS);
        }
        this.matBalao = matBalao;
    }

    public String getCaboBalao() {
        return caboBalao;
    }

    public void setCaboBalao(String caboBalao) {
        if (!CABOS_VALIDOS.contains(caboBalao)) {
            throw new IllegalArgumentException("Cabo inválido. Opções válidas: " + CABOS_VALIDOS);
        }
        this.caboBalao = caboBalao;
    }

    public int getRastreadorBalao() {
        return rastreadorBalao;
    }

    public void setRastreadorBalao(int rastreadorBalao) {
        if (this.rastreadorBalao == 0) {
            this.rastreadorBalao = rastreadorBalao;
        } else {
            throw new UnsupportedOperationException("Não é possível editar o rastreador do balão.");
        }
    }

    public long getCnpj() {
        return cnpj;
    }

    public void setCnpj(long cnpj) {
        this.cnpj = cnpj;
    }

    public boolean validarRastreador() {
        return String.valueOf(rastreadorBalao).length() == 8;
    }

    public boolean verificarGasInflamavel() {
        return "Hidrogênio".equalsIgnoreCase(gas);
    }
}
