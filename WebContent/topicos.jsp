<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

        <link rel="stylesheet" type="text/css" href="./assets/css/estilo.css" />
        <title>Forúm ITA - Tópicos</title>
    </head>

    <body>
        <div align="center">
            <h1>Fórum ITA</h1>
            
            <table class="menu">
                <thead>
                    <tr>
                        <td>
                            <a href="topicos" class="button">Ver Tópicos</a>
                            <a href="novo-topico" class="button">Novo Tópico</a>
                            <a href="ranking-usuarios" class="button">Ranking</a>
                            <a href="login?logout=true" class="button">Logout</a>
                        </td>
                    </tr>
                </thead>
            </table>

            <h3>Tópicos</h3>

            <p class="msg alerta">
                ${msg}
            </p>

            <table class="topicos">
                <tbody>
                    <c:forEach var="topico" items="${topicos}" >
                        <tr>
                            <td>
                               <a href="topico-detalhe?id=${topico.id}">
                                   <span>${topico.titulo}</span>
                                   <small>Criado por: ${topico.usuario.nome}</small>                             
                               </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
                        
            </table>
        </div>
    </body>
</html>