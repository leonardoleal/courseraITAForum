package com.coursera.ita.persistence;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Before;
import org.junit.Test;

import com.coursera.ita.entity.Usuario;
import com.coursera.ita.util.ConnectionFactory;

public class UsuarioPostgreDAOTest {

	JdbcDatabaseTester jdt;

	@Before
	public void setup() throws Exception {
		resetSequences();

		jdt = new JdbcDatabaseTester("org.postgresql.Driver",
				"jdbc:postgresql://localhost:5432/forum_ita", "postgres", "postgres");

		FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
		jdt.setDataSet(loader.load("/inicio.xml"));
		jdt.onSetup();
	}

	@Test
	public void testInserir() throws Exception {
		Usuario u = new Usuario();
		u.setLogin("novo");
		u.setNome("Novo");
		u.setEmail("novo@usuario.com");
		u.setSenha("novo");

		new UsuarioPostgreDAO().inserir(u);

		IDataSet currentDataSet = jdt.getConnection().createDataSet();
		ITable currentTable = currentDataSet.getTable("usuario");

		FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
		IDataSet expectedDataSet = loader.load("/verifica-insercoes.xml");
		ITable expectedTable = expectedDataSet.getTable("usuario");

		Assertion.assertEquals(expectedTable, currentTable);
	}

	@Test
	public void testAdcionarPontos() throws Exception {
		Usuario u = new Usuario("leonardoleal");

		new UsuarioPostgreDAO().adcionarPontos(u, 10);

		IDataSet currentDataSet = jdt.getConnection().createDataSet();
		ITable currentTable = currentDataSet.getTable("usuario");

		FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
		IDataSet expectedDataSet = loader.load("/verifica-atualizacoes.xml");
		ITable expectedTable = expectedDataSet.getTable("usuario");

		Assertion.assertEquals(expectedTable, currentTable);
	}

	@Test
	public void testBuscarPorLoginESenha() throws Exception {
		Usuario u = new Usuario("guerra");
		u.setSenha("guerra");

		Usuario usuarioRetorno = new UsuarioPostgreDAO().buscarPorLoginESenha(u);

		assertEquals("guerra", usuarioRetorno.getLogin());
	}

	@Test
	public void testBuscarRankingPontos() throws Exception {
		List<Usuario> lista = new UsuarioPostgreDAO().buscarRankingPontos();

		assertEquals(3, lista.size());
		assertEquals("arthur", lista.get(0).getLogin());
		assertEquals("leonardoleal", lista.get(1).getLogin());
		assertEquals("guerra", lista.get(2).getLogin());
	}

	private void resetSequences() throws ClassNotFoundException {
		try {
			ConnectionFactory.getConnectionPostgre()
					.prepareStatement(
							"ALTER SEQUENCE comentario_id_comentario_seq RESTART WITH 7;"
							+ "ALTER SEQUENCE topico_id_topico_seq RESTART WITH 3;")
					.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
