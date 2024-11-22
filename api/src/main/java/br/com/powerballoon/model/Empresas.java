package br.com.powerballoon.model;

public class Empresas {
    private long cnpj;
    private String nomEmpre;
    private int cepEmpre;
    private String planoEmpre;
    private int quantidadeBalao;

    public Empresas() {}

    public Empresas(long cnpj, String nomEmpre, int cepEmpre, String planoEmpre) {
        this.cnpj = cnpj;
        this.nomEmpre = nomEmpre;
        this.cepEmpre = cepEmpre;
        this.planoEmpre = planoEmpre;
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

    public int getQuantidadeBalao() {
        return quantidadeBalao;
    }

    public void setQuantidadeBalao(int quantidadeBalao) {
        this.quantidadeBalao = quantidadeBalao;
    }

    public boolean validarCnpj() {
        return String.valueOf(cnpj).length() == 14;
    }

    public String obterDescricaoPlano() {
        return "Plano: " + planoEmpre + " - Empresa: " + nomEmpre;
    }

    public boolean validarPlano() {
        return planoEmpre != null && !planoEmpre.isEmpty();
    }
}
