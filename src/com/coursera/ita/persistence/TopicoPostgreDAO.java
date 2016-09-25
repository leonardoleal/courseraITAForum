package com.coursera.ita.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.coursera.ita.entity.Topico;
import com.coursera.ita.entity.Usuario;
import com.coursera.ita.persistence.dao.TopicoDAO;
import com.coursera.ita.util.ConnectionFactory;

public class TopicoPostgreDAO implements TopicoDAO {

	@Override
	public List<Topico> buscarTodosCabecalhos() {
		List<Topico> topicos = new ArrayList<>();

		try {
			Connection conn = ConnectionFactory.getConnectionPostgre();
			PreparedStatement ps = conn.prepareStatement(
									"SELECT t.id_topico, t.titulo, u.nome, u.login "
									+ "FROM topico AS t "
									+ "	INNER JOIN usuario AS u "
									+ "		ON u.login = t.login "
									+ "ORDER BY t.id_topico DESC;");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Topico topico = new Topico();
				topico.setId(rs.getInt("id_topico"));
				topico.setTitulo(rs.getString("titulo"));
				topico.setUsuario(new Usuario(rs.getString("login"), rs.getString("nome")));

				topicos.add(topico);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return topicos;
	}

	@Override
	public void inserir(Topico topico) {
		try {
			Connection conn = ConnectionFactory.getConnectionPostgre();
			PreparedStatement ps = conn.prepareStatement(
								"INSERT INTO topico (titulo, conteudo, login) "
								+ "VALUES (?, ?, ?); ");
			ps.setString(1, topico.getTitulo());
			ps.setString(2, topico.getConteudo());
			ps.setString(3, topico.getUsuario().getLogin());

			ps.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Topico buscarPorId(int id) {
		Topico topico = null;

		try {
			Connection conn = ConnectionFactory.getConnectionPostgre();
			PreparedStatement ps = conn.prepareStatement(
									"SELECT * "
									+ "FROM topico AS t "
									+ "	INNER JOIN usuario AS u "
									+ "		ON u.login = t.login "
									+ "WHERE t.id_topico = ?"
									+ "ORDER BY t.id_topico DESC;");
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				topico = new Topico();
				Usuario usuario = new Usuario();

				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setLogin(rs.getString("login"));
				usuario.setPontos(rs.getInt("pontos"));

				topico.setUsuario(usuario);
				topico.setId(rs.getInt("id_topico"));
				topico.setTitulo(rs.getString("titulo"));
				topico.setConteudo(rs.getString("conteudo"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return topico;
	}

}
