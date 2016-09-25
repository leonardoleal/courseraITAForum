package com.coursera.ita.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coursera.ita.entity.Topico;
import com.coursera.ita.entity.Usuario;
import com.coursera.ita.exception.FalhaInserirTopico;
import com.coursera.ita.rn.TopicoRN;
import com.coursera.ita.rn.UsuarioRN;

@WebServlet(name = "novo-topico", urlPatterns = { "/novo-topico" })
public class NovoTopico extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!UsuarioRN.isLogado(request.getSession())) { response.sendRedirect("login"); return; }

		request.getRequestDispatcher("novo-topico.jsp")
								.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!UsuarioRN.isLogado(request.getSession())) { response.sendRedirect("login"); return; }

		Topico topico = new Topico();
		topico.setTitulo(request.getParameter("titulo"));
		topico.setConteudo(request.getParameter("conteudo"));
		topico.setUsuario((Usuario) request.getSession().getAttribute("usuario"));

		try {
			new TopicoRN().cadastrar(topico);

			response.sendRedirect("topicos");

		} catch (FalhaInserirTopico fit) {
			request.setAttribute("topico", topico);
			request.setAttribute("msg", fit.getMessage());

			request.getRequestDispatcher("novo-topico.jsp")
									.forward(request, response);
		}
	}

}
