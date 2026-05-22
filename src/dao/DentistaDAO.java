package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Dentista;
import repository.CrudRepository;
import util.Conexao;

public class DentistaDAO implements CrudRepository<Dentista> {
    @Override
    public void salvar(Dentista dentista) {
        String sql = "INSERT INTO dentista (nome, especialidade) VALUES (?, ?)";
        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dentista.getNome());
            stmt.setString(2, dentista.getEspecialidade());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Erro ao salvar dentista.", e);
        }
    }

    @Override
    public List<Dentista> listarTodos() {
        List<Dentista> lista = new ArrayList<>();
        String sql = "SELECT * FROM dentista";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Dentista d = new Dentista(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("especialidade")
                );
                lista.add(d);
            }

        } catch (SQLException e) {
            throw new DaoException("Erro ao listar dentistas.", e);
        }

        return lista;
    }

    @Override
    public Dentista buscarPorId(int id) {
        String sql = "SELECT * FROM dentista WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Dentista(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("especialidade")
                );
            }

        } catch (SQLException e) {
            throw new DaoException("Erro ao buscar dentista.", e);
        }

        return null;
    }

    @Override
    public void atualizar(Dentista dentista) {
        String sql = "UPDATE dentista SET nome = ?, especialidade = ? WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dentista.getNome());
            stmt.setString(2, dentista.getEspecialidade());
            stmt.setInt(3, dentista.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Erro ao atualizar dentista.", e);
        }
    }

    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM dentista WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Erro ao deletar dentista.", e);
        }
    }
}
