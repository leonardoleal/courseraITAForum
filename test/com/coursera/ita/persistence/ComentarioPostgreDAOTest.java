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

import com.coursera.ita.entity.Comentario;
import com.coursera.ita.entity.Topico;
import com.coursera.ita.entity.Usuario;
import com.coursera.ita.util.ConnectionFactory;

public class ComentarioPostgreDAOTest {

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
	public void testBuscarPorIdTopico() throws Exception {
		List<Comentario> lista = new ComentarioPostgreDAO().buscarPorIdTopico(1);

		assertEquals(3, lista.size());
		assertEquals("Comentário 1 Tópico 1", lista.get(0).getComentario());
		assertEquals("Comentário 2 Tópico 1", lista.get(1).getComentario());
		assertEquals("Comentário 3 Tópico 1", lista.get(2).getComentario());
	}

	@Test
	public void testInserir() throws Exception {
		Comentario c = new Comentario();
		c.setComentario("Novo comentário");
		c.setTopico(new Topico(2));
		c.setUsuario(new Usuario("leonardoleal"));

		new ComentarioPostgreDAO().inserir(c);

		IDataSet currentDataSet = jdt.getConnection().createDataSet();
		ITable currentTable = currentDataSet.getTable("comentario");

		FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
		IDataSet expectedDataSet = loader.load("/verifica-insercoes.xml");
		ITable expectedTable = expectedDataSet.getTable("comentario");

		Assertion.assertEquals(expectedTable, currentTable);
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
