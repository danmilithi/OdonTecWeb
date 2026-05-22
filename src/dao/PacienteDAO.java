package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Paciente;
import repository.CrudRepository;
import util.Conexao;

public class PacienteDAO implements CrudRepository<Paciente> {
    @Override
    public void salvar(Paciente paciente) {
        String sql = "INSERT INTO paciente (nome, telefone, data_nascimento, cep) VALUES (?, ?, STR_TO_DATE(?, '%d/%m/%Y'), ?)";
        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getTelefone());
            stmt.setString(3, paciente.getDataNascimento().trim());
            stmt.setString(4, paciente.getCep());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Erro ao salvar paciente.", e);
        }
    }

    @Override
    public List<Paciente> listarTodos() {
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM paciente";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Paciente p = new Paciente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("telefone"),
                        rs.getDate("data_nascimento") == null ? null : rs.getDate("data_nascimento").toLocalDate().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        rs.getString("cep")
                );
                lista.add(p);
            }

        } catch (SQLException e) {
            throw new DaoException("Erro ao listar pacientes.", e);
        }

        return lista;
    }

    @Override
    public Paciente buscarPorId(int id) {
        String sql = "SELECT * FROM paciente WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Paciente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("telefone"),
                        rs.getDate("data_nascimento") == null ? null : rs.getDate("data_nascimento").toLocalDate().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        rs.getString("cep")
                );
            }

        } catch (SQLException e) {
            throw new DaoException("Erro ao buscar paciente.", e);
        }

        return null;
    }

    @Override
    public void atualizar(Paciente paciente) {
        String sql = "UPDATE paciente SET nome = ?, telefone = ?, data_nascimento = STR_TO_DATE(?, '%d/%m/%Y'), cep = ? WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getTelefone());
            stmt.setString(3, paciente.getDataNascimento().trim());
            stmt.setString(4, paciente.getCep());
            stmt.setInt(5, paciente.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Erro ao atualizar paciente.", e);
        }
    }

    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM paciente WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Erro ao deletar paciente.", e);
        }
    }
}
