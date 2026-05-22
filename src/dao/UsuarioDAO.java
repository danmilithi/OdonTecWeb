package dao;

import model.TipoUsuario;
import model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.Conexao;

public class UsuarioDAO {
    public Usuario autenticar(String login, String senha) {
        String sql = "SELECT id, login, senha, tipo_usuario FROM usuario WHERE login = ? AND senha = ?";

        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, login);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                            rs.getInt("id"),
                            rs.getString("login"),
                            rs.getString("senha"),
                            TipoUsuario.valueOf(rs.getString("tipo_usuario"))
                    );
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Erro ao autenticar usuario.", e);
        }

        return null;
    }
}
