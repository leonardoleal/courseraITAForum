package com.coursera.ita.rn;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.coursera.ita.entity.Usuario;
import com.coursera.ita.exception.FalhaAutenticacaoUsuario;
import com.coursera.ita.exception.FalhaCadastrarUsuario;
import com.coursera.ita.persistence.UsuarioPostgreDAO;
import com.coursera.ita.persistence.dao.UsuarioDAO;

public class UsuarioRN {

	public void cadastrar(Usuario usuario) throws FalhaCadastrarUsuario {
		UsuarioDAO usuarioDAO = new UsuarioPostgreDAO();

		if (usuario.getNome() == null || usuario.getNome().isEmpty()
				|| usuario.getEmail() == null || usuario.getEmail().isEmpty()
				|| usuario.getLogin() == null || usuario.getLogin().isEmpty()
				|| usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
			throw new FalhaCadastrarUsuario("Todos os campos devem ser preenchidos.");
		}

		usuarioDAO.inserir(usuario);
	}

	public Usuario autenticar(Usuario usuario) throws FalhaAutenticacaoUsuario {
		UsuarioDAO usuarioDAO = new UsuarioPostgreDAO();
		usuario = usuarioDAO.buscarPorLoginESenha(usuario);

		if (usuario == null)
			throw new FalhaAutenticacaoUsuario();

		return usuario;
	}

	public List<Usuario> buscarRanking() {
		UsuarioDAO usuarioDAO = new UsuarioPostgreDAO();

		return usuarioDAO.buscarRankingPontos();
	}

	public static boolean isLogado(HttpSession session) {
		return session.getAttribute("usuario") != null;
	}

}
