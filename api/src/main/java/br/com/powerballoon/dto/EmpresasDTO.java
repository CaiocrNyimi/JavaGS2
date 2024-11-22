package br.com.powerballoon.dto;

public class EmpresasDTO {
    private long cnpj;
    private String nomEmpre;
    private int cepEmpre;
    private String planoEmpre;
    private int rastreadorBalao;

    // Construtor, Getters e Setters
    public EmpresasDTO(long cnpj, String nomEmpre, int cepEmpre, String planoEmpre, int rastreadorBalao) {
        this.cnpj = cnpj;
        this.nomEmpre = nomEmpre;
        this.cepEmpre = cepEmpre;
        this.planoEmpre = planoEmpre;
        this.rastreadorBalao = rastreadorBalao;
    }

    public long getCnpj() {
        return cnpj;
    }

    public void setCnpj(long cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomEmpre() {
        return nomEmpre;
    }

    public void setNomEmpre(String nomEmpre) {
        this.nomEmpre = nomEmpre;
    }

    public int getCepEmpre() {
        return cepEmpre;
    }

    public void setCepEmpre(int cepEmpre) {
        this.cepEmpre = cepEmpre;
    }

    public String getPlanoEmpre() {
        return planoEmpre;
    }

    public void setPlanoEmpre(String planoEmpre) {
        this.planoEmpre = planoEmpre;
    }

    public int getRastreadorBalao() {
        return rastreadorBalao;
    }

    public void setRastreadorBalao(int rastreadorBalao) {
        this.rastreadorBalao = rastreadorBalao;
    }
}
