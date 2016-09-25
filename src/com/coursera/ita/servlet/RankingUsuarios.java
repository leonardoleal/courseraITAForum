package com.coursera.ita.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coursera.ita.rn.UsuarioRN;

@WebServlet(name = "ranking-usuarios", urlPatterns = { "/ranking-usuarios" })
public class RankingUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!UsuarioRN.isLogado(request.getSession())) { response.sendRedirect("login"); return; }

		UsuarioRN usuarioRN = new UsuarioRN();

		request.setAttribute("usuarios", usuarioRN.buscarRanking());
		request.getRequestDispatcher("ranking-usuarios.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
