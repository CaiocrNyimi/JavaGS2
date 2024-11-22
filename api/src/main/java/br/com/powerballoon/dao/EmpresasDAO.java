package br.com.powerballoon.dao;

import br.com.powerballoon.factory.ConnectionFactory;
import br.com.powerballoon.model.Empresas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpresasDAO {

    public void inserir(Empresas empresas) throws SQLException, ClassNotFoundException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO T_PB_EMPRESAS (CNPJ, NOM_EMPRE, CEP_EMPRE, PLANO_EMPRE, QUANTIDADE_BALAO) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, empresas.getCnpj());
            stmt.setString(2, empresas.getNomEmpre());
            stmt.setInt(3, empresas.getCepEmpre());
            stmt.setString(4, empresas.getPlanoEmpre());
            stmt.setInt(5, empresas.getQuantidadeBalao());
            stmt.execute();
        }
    }

    public Empresas buscarPorCnpj(long cnpj) throws SQLException, ClassNotFoundException {
        Empresas empresas = null;
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT CNPJ, NOM_EMPRE, CEP_EMPRE, PLANO_EMPRE, QUANTIDADE_BALAO FROM T_PB_EMPRESAS WHERE CNPJ = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, cnpj);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                empresas = new Empresas(
                        rs.getLong("CNPJ"),
                        rs.getString("NOM_EMPRE"),
                        rs.getInt("CEP_EMPRE"),
                        rs.getString("PLANO_EMPRE")
                );
                empresas.setQuantidadeBalao(rs.getInt("QUANTIDADE_BALAO"));
            }
        }
        return empresas;
    }

    public List<Empresas> buscarTodas() throws SQLException, ClassNotFoundException {
        List<Empresas> empresasList = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT CNPJ, NOM_EMPRE, CEP_EMPRE, PLANO_EMPRE, QUANTIDADE_BALAO FROM T_PB_EMPRESAS";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Empresas empresas = new Empresas(
                        rs.getLong("CNPJ"),
                        rs.getString("NOM_EMPRE"),
                        rs.getInt("CEP_EMPRE"),
                        rs.getString("PLANO_EMPRE")
                );
                empresas.setQuantidadeBalao(rs.getInt("QUANTIDADE_BALAO"));
                empresasList.add(empresas);
            }
        }
        return empresasList;
    }

    public void atualizar(long cnpj, Empresas empresas) throws SQLException, ClassNotFoundException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "UPDATE T_PB_EMPRESAS SET NOM_EMPRE = ?, CEP_EMPRE = ?, PLANO_EMPRE = ?, QUANTIDADE_BALAO = ? WHERE CNPJ = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, empresas.getNomEmpre());
            stmt.setInt(2, empresas.getCepEmpre());
            stmt.setString(3, empresas.getPlanoEmpre());
            stmt.setInt(4, empresas.getQuantidadeBalao());
            stmt.setLong(5, cnpj);
            stmt.execute();
        }
    }

    public void deletar(long cnpj) throws SQLException, ClassNotFoundException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "DELETE FROM T_PB_EMPRESAS WHERE CNPJ = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, cnpj);
            stmt.execute();
        }
    }

    public void deletarTodas() throws SQLException, ClassNotFoundException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "DELETE FROM T_PB_EMPRESAS";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.execute();
        }
    }
}
