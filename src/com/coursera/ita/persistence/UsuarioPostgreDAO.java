package com.coursera.ita.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.coursera.ita.entity.Usuario;
import com.coursera.ita.persistence.dao.UsuarioDAO;
import com.coursera.ita.util.ConnectionFactory;

public class UsuarioPostgreDAO implements UsuarioDAO {

	@Override
	public void inserir(Usuario usuario) {
		try {
			Connection conn = ConnectionFactory.getConnectionPostgre();
			PreparedStatement ps = conn.prepareStatement(
								"INSERT INTO usuario (nome, email, login, senha, pontos) "
								+ "VALUES (?, ?, ?, ?, 0); ");
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getEmail());
			ps.setString(3, usuario.getLogin());
			ps.setString(4, usuario.getSenha());

			ps.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void adcionarPontos(Usuario usuario, int pontos) {
		try {
			Connection conn = ConnectionFactory.getConnectionPostgre();
			PreparedStatement ps = conn.prepareStatement(
								"UPDATE usuario SET pontos = pontos + ? "
								+ "WHERE login = ?;");
			ps.setInt(1, pontos);
			ps.setString(2, usuario.getLogin());

			ps.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Usuario buscarPorLoginESenha(Usuario usuario) {
		try {
			Connection conn = ConnectionFactory.getConnectionPostgre();
			PreparedStatement ps = conn.prepareStatement(
								"SELECT login, email, nome, pontos "
								+ "FROM usuario WHERE login = ? AND senha = ?");
			ps.setString(1, usuario.getLogin());
			ps.setString(2, usuario.getSenha());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				usuario.setLogin(rs.getString("login"));
				usuario.setEmail(rs.getString("email"));
				usuario.setNome(rs.getString("nome"));
				usuario.setPontos(rs.getInt("pontos"));

				return usuario;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Usuario> buscarRankingPontos() {
		List<Usuario> usuarios = new ArrayList<>();

		try {
			Connection conn = ConnectionFactory.getConnectionPostgre();
			PreparedStatement ps = conn.prepareStatement(
									"SELECT nome, login, pontos "
									+ "FROM usuario "
									+ "ORDER BY pontos DESC;");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setNome(rs.getString("nome"));
				usuario.setLogin(rs.getString("login"));
				usuario.setPontos(rs.getInt("pontos"));

				usuarios.add(usuario);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return usuarios;
	}

}
