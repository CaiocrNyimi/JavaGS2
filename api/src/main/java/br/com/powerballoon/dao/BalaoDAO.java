package br.com.powerballoon.dao;

import br.com.powerballoon.factory.ConnectionFactory;
import br.com.powerballoon.model.Balao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BalaoDAO {

    public void inserir(Balao balao) throws SQLException, ClassNotFoundException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO T_PB_BALAO (GAS, MAT_BALAO, CABO_BALAO, RASTREADOR_BALAO, CNPJ) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, balao.getGas());
            stmt.setString(2, balao.getMatBalao());
            stmt.setString(3, balao.getCaboBalao());
            stmt.setInt(4, balao.getRastreadorBalao());
            stmt.setLong(5, balao.getCnpj());
            stmt.execute();

            atualizarQuantidadeBaloes(balao.getCnpj());
        }
    }

    private void atualizarQuantidadeBaloes(long cnpj) throws SQLException, ClassNotFoundException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "UPDATE T_PB_EMPRESAS SET QUANTIDADE_BALAO = (SELECT COUNT(*) FROM T_PB_BALAO WHERE CNPJ = ?) WHERE CNPJ = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, cnpj);
            stmt.setLong(2, cnpj);
            stmt.execute();
        }
    }

    public List<Balao> buscarTodos() throws SQLException, ClassNotFoundException {
        List<Balao> baloes = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM T_PB_BALAO";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Balao balao = new Balao(
                        rs.getString("GAS"),
                        rs.getString("MAT_BALAO"),
                        rs.getString("CABO_BALAO"),
                        rs.getInt("RASTREADOR_BALAO"),
                        rs.getLong("CNPJ")
                );
                baloes.add(balao);
            }
        }
        return baloes;
    }

    public Balao buscarPorRastreador(int rastreadorBalao) throws SQLException, ClassNotFoundException {
        Balao balao = null;
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM T_PB_BALAO WHERE RASTREADOR_BALAO = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, rastreadorBalao);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                balao = new Balao(
                        rs.getString("GAS"),
                        rs.getString("MAT_BALAO"),
                        rs.getString("CABO_BALAO"),
                        rs.getInt("RASTREADOR_BALAO"),
                        rs.getLong("CNPJ")
                );
            }
        }
        return balao;
    }

    public void atualizar(Balao balao) throws SQLException, ClassNotFoundException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "UPDATE T_PB_BALAO SET GAS = ?, MAT_BALAO = ?, CABO_BALAO = ? WHERE RASTREADOR_BALAO = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, balao.getGas());
            stmt.setString(2, balao.getMatBalao());
            stmt.setString(3, balao.getCaboBalao());
            stmt.setInt(4, balao.getRastreadorBalao());
            stmt.execute();

            atualizarQuantidadeBaloes(balao.getCnpj());
        }
    }

    public void deletar(int rastreadorBalao) throws SQLException, ClassNotFoundException {
        long cnpj = 0;
        try (Connection conn = ConnectionFactory.getConnection()) {
            String selectSql = "SELECT CNPJ FROM T_PB_BALAO WHERE RASTREADOR_BALAO = ?";
            PreparedStatement selectStmt = conn.prepareStatement(selectSql);
            selectStmt.setInt(1, rastreadorBalao);
            ResultSet rs = selectStmt.executeQuery();
            if (rs.next()) {
                cnpj = rs.getLong("CNPJ");
            }

            String deleteSql = "DELETE FROM T_PB_BALAO WHERE RASTREADOR_BALAO = ?";
            PreparedStatement deleteStmt = conn.prepareStatement(deleteSql);
            deleteStmt.setInt(1, rastreadorBalao);
            deleteStmt.execute();

            if (cnpj != 0) {
                atualizarQuantidadeBaloes(cnpj);
            }
        }
    }
}
