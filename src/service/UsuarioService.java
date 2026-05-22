package service;

import dao.UsuarioDAO;
import model.Usuario;

public class UsuarioService {
    private final UsuarioDAO usuarioDAO;

    public UsuarioService() {
        this(new UsuarioDAO());
    }

    public UsuarioService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public Usuario autenticar(String login, String senha) {
        ValidacaoService.textoObrigatorio(login, "Login");
        ValidacaoService.textoObrigatorio(senha, "Senha");
        return usuarioDAO.autenticar(login, senha);
    }
}
