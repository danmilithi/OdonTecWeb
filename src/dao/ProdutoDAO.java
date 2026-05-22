package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Produto;
import repository.CrudRepository;
import util.Conexao;

public class ProdutoDAO implements CrudRepository<Produto> {
    @Override
    public void salvar(Produto produto) {
        String sql = "INSERT INTO produto (nome, preco, fornecedor, quantidade) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setString(3, produto.getFornecedor());
            stmt.setInt(4, produto.getQuantidade());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Erro ao salvar produto.", e);
        }
    }

    @Override
    public List<Produto> listarTodos() {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produto";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Produto p = new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getString("fornecedor"),
                        rs.getInt("quantidade")
                );
                lista.add(p);
            }

        } catch (SQLException e) {
            throw new DaoException("Erro ao listar produtos.", e);
        }

        return lista;
    }

    @Override
    public Produto buscarPorId(int id) {
        String sql = "SELECT * FROM produto WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getString("fornecedor"),
                        rs.getInt("quantidade")
                );
            }

        } catch (SQLException e) {
            throw new DaoException("Erro ao buscar produto.", e);
        }

        return null;
    }

    @Override
    public void atualizar(Produto produto) {
        String sql = "UPDATE produto SET nome = ?, preco = ?, fornecedor = ?, quantidade = ? WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setString(3, produto.getFornecedor());
            stmt.setInt(4, produto.getQuantidade());
            stmt.setInt(5, produto.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Erro ao atualizar produto.", e);
        }
    }

    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM produto WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Erro ao deletar produto.", e);
        }
    }
}
