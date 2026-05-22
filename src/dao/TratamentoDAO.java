package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Tratamento;
import repository.CrudRepository;
import util.Conexao;

public class TratamentoDAO implements CrudRepository<Tratamento> {
    @Override
    public void salvar(Tratamento tratamento) {
        String sql = "INSERT INTO tratamento (nome, preco, duracao) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tratamento.getTratamento());
            stmt.setDouble(2, tratamento.getPreco());
            stmt.setDouble(3, tratamento.getDuracao());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Erro ao salvar tratamento.", e);
        }
    }

    @Override
    public List<Tratamento> listarTodos() {
        List<Tratamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM tratamento";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Tratamento t = new Tratamento(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getDouble("duracao")
                );
                lista.add(t);
            }

        } catch (SQLException e) {
            throw new DaoException("Erro ao listar tratamentos.", e);
        }

        return lista;
    }

    @Override
    public Tratamento buscarPorId(int id) {
        String sql = "SELECT * FROM tratamento WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Tratamento(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getDouble("duracao")
                );
            }

        } catch (SQLException e) {
            throw new DaoException("Erro ao buscar tratamento.", e);
        }

        return null;
    }

    @Override
    public void atualizar(Tratamento tratamento) {
        String sql = "UPDATE tratamento SET nome = ?, preco = ?, duracao = ? WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tratamento.getTratamento());
            stmt.setDouble(2, tratamento.getPreco());
            stmt.setDouble(3, tratamento.getDuracao());
            stmt.setInt(4, tratamento.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Erro ao atualizar tratamento.", e);
        }
    }

    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM tratamento WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Erro ao deletar tratamento.", e);
        }
    }
}
