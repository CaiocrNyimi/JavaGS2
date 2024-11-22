package br.com.powerballoon.dto;

public class BalaoDTO {
    private String gas;
    private String matBalao;
    private String caboBalao;
    private int rastreadorBalao;
    private long cnpj;

    // Construtor, Getters e Setters
    public BalaoDTO(String gas, String matBalao, String caboBalao, int rastreadorBalao, long cnpj) {
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
        this.gas = gas;
    }

    public String getMatBalao() {
        return matBalao;
    }

    public void setMatBalao(String matBalao) {
        this.matBalao = matBalao;
    }

    public String getCaboBalao() {
        return caboBalao;
    }

    public void setCaboBalao(String caboBalao) {
        this.caboBalao = caboBalao;
    }

    public int getRastreadorBalao() {
        return rastreadorBalao;
    }

    public void setRastreadorBalao(int rastreadorBalao) {
        this.rastreadorBalao = rastreadorBalao;
    }

    public long getCnpj() {
        return cnpj;
    }

    public void setCnpj(long cnpj) {
        this.cnpj = cnpj;
    }
}
