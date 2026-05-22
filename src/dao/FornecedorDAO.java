package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Fornecedor;
import repository.CrudRepository;
import util.Conexao;

public class FornecedorDAO implements CrudRepository<Fornecedor> {
    @Override
    public void salvar(Fornecedor fornecedor) {
        String sql = "INSERT INTO fornecedor (nome, telefone, produto) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getTelefone());
            stmt.setString(3, fornecedor.getProduto());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Erro ao salvar fornecedor.", e);
        }
    }

    @Override
    public List<Fornecedor> listarTodos() {
        List<Fornecedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM fornecedor";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Fornecedor f = new Fornecedor(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("telefone"),
                        rs.getString("produto")
                );
                lista.add(f);
            }

        } catch (SQLException e) {
            throw new DaoException("Erro ao listar fornecedores.", e);
        }

        return lista;
    }

    @Override
    public Fornecedor buscarPorId(int id) {
        String sql = "SELECT * FROM fornecedor WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Fornecedor(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("telefone"),
                        rs.getString("produto")
                );
            }

        } catch (SQLException e) {
            throw new DaoException("Erro ao buscar fornecedor.", e);
        }

        return null;
    }

    @Override
    public void atualizar(Fornecedor fornecedor) {
        String sql = "UPDATE fornecedor SET nome = ?, telefone = ?, produto = ? WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getTelefone());
            stmt.setString(3, fornecedor.getProduto());
            stmt.setInt(4, fornecedor.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Erro ao atualizar fornecedor.", e);
        }
    }

    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM fornecedor WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Erro ao deletar fornecedor.", e);
        }
    }
}
