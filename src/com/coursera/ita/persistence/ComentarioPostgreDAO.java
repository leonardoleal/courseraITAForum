package com.coursera.ita.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.coursera.ita.entity.Comentario;
import com.coursera.ita.entity.Usuario;
import com.coursera.ita.persistence.dao.ComentarioDAO;
import com.coursera.ita.util.ConnectionFactory;

public class ComentarioPostgreDAO implements ComentarioDAO {

	@Override
	public List<Comentario> buscarPorIdTopico(int id) {

		List<Comentario> comentarios = new ArrayList<>();

		try {
			Connection conn = ConnectionFactory.getConnectionPostgre();
			PreparedStatement ps = conn.prepareStatement(
									"SELECT c.comentario, "
									+ "		u.nome, u.email, u.login "
									+ "FROM comentario AS c "
									+ "	INNER JOIN usuario AS u "
									+ "		ON u.login = c.login "
									+ "WHERE c.id_topico = ? "
									+ "ORDER BY c.id_comentario ASC");
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Comentario comentario = new Comentario();
				Usuario usuario = new Usuario();

				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setLogin(rs.getString("login"));

				comentario.setUsuario(usuario);
				comentario.setComentario(rs.getString("comentario"));

				comentarios.add(comentario);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return comentarios;
	}

	@Override
	public void inserir(Comentario comentario) {
		try {
			Connection conn = ConnectionFactory.getConnectionPostgre();
			PreparedStatement ps = conn.prepareStatement(
								"INSERT INTO comentario (comentario, id_topico, login) "
								+ "VALUES (?, ?, ?); ");
			ps.setString(1, comentario.getComentario());
			ps.setInt(2, comentario.getTopico().getId());
			ps.setString(3, comentario.getUsuario().getLogin());

			ps.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
