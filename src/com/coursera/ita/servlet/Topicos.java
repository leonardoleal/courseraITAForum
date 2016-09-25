package com.coursera.ita.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coursera.ita.rn.TopicoRN;
import com.coursera.ita.rn.UsuarioRN;

@WebServlet(name = "topicos", urlPatterns = { "/topicos" })
public class Topicos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!UsuarioRN.isLogado(request.getSession())) { response.sendRedirect("login"); return; }

		TopicoRN topicoRN = new TopicoRN();

		request.setAttribute("topicos", topicoRN.buscarTodosCabecalhos());
		request.getRequestDispatcher("topicos.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
