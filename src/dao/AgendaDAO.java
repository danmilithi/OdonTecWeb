package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Agenda;
import repository.CrudRepository;
import util.Conexao;

public class AgendaDAO implements CrudRepository<Agenda> {
    @Override
    public void salvar(Agenda agenda) {
        String sql = "INSERT INTO agenda (paciente, dentista, data, hora, tratamento) VALUES (?, ?, STR_TO_DATE(?, '%d/%m/%Y'), ?, ?)";
        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, agenda.getPaciente());
            stmt.setString(2, agenda.getDentista());
            stmt.setString(3, agenda.getData().trim());
            stmt.setString(4, agenda.getHora());
            stmt.setString(5, agenda.getTratamento());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Erro ao salvar agendamento.", e);
        }
    }

    @Override
    public List<Agenda> listarTodos() {
        List<Agenda> lista = new ArrayList<>();
        String sql = "SELECT * FROM agenda";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Agenda a = new Agenda(
                        rs.getInt("id"),
                        rs.getString("paciente"),
                        rs.getString("dentista"),
                        rs.getDate("data").toLocalDate().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        rs.getString("hora"),
                        rs.getString("tratamento")
                );
                lista.add(a);
            }

        } catch (SQLException e) {
            throw new DaoException("Erro ao listar agendamentos.", e);
        }

        return lista;
    }

    @Override
    public Agenda buscarPorId(int id) {
        String sql = "SELECT * FROM agenda WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Agenda(
                        rs.getInt("id"),
                        rs.getString("paciente"),
                        rs.getString("dentista"),
                        rs.getDate("data").toLocalDate().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        rs.getString("hora"),
                        rs.getString("tratamento")
                );
            }

        } catch (SQLException e) {
            throw new DaoException("Erro ao buscar agendamento.", e);
        }

        return null;
    }

    @Override
    public void atualizar(Agenda agenda) {
        String sql = "UPDATE agenda SET paciente = ?, dentista = ?, data = STR_TO_DATE(?, '%d/%m/%Y'), hora = ?, tratamento = ? WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, agenda.getPaciente());
            stmt.setString(2, agenda.getDentista());
            stmt.setString(3, agenda.getData().trim());
            stmt.setString(4, agenda.getHora());
            stmt.setString(5, agenda.getTratamento());
            stmt.setInt(6, agenda.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Erro ao atualizar agendamento.", e);
        }
    }

    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM agenda WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Erro ao deletar agendamento.", e);
        }
    }
}
