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

import com.coursera.ita.entity.Topico;
import com.coursera.ita.entity.Usuario;
import com.coursera.ita.util.ConnectionFactory;

public class TopicoPostgreDAOTest {

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
	public void testBuscarTodosCabecalhos() throws Exception {
		List<Topico> lista = new TopicoPostgreDAO().buscarTodosCabecalhos();

		assertEquals(2, lista.size());
		assertEquals("Tópico 2", lista.get(0).getTitulo());
		assertEquals("Tópico 1", lista.get(1).getTitulo());
	}

	@Test
	public void testInserir() throws Exception {
		Topico t = new Topico();
		t.setTitulo("Tópico 3");
		t.setUsuario(new Usuario("guerra"));
		t.setConteudo("Conteúdo 3");

		new TopicoPostgreDAO().inserir(t);

		IDataSet currentDataSet = jdt.getConnection().createDataSet();
		ITable currentTable = currentDataSet.getTable("topico");

		FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
		IDataSet expectedDataSet = loader.load("/verifica-insercoes.xml");
		ITable expectedTable = expectedDataSet.getTable("topico");

		Assertion.assertEquals(expectedTable, currentTable);
	}

	@Test
		public void testBuscarPorId() throws Exception {
			Topico topico = new TopicoPostgreDAO().buscarPorId(2);

			assertEquals("Tópico 2", topico.getTitulo());
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
