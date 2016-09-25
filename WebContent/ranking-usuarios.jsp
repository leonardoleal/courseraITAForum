<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

        <link rel="stylesheet" type="text/css" href="./assets/css/estilo.css" />
        <title>Forúm ITA - Rankig de Usuários</title>
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
            <h3>Rankig de Usuários</h3>

            <p class="msg alerta">
                ${msg}
            </p>

            <table class="topicos">
                <thead>
                    <tr style="background-color: coral;">
                        <td align="center">#</td>
                        <td>Nome</td>
                        <td align="center">Pontos</td>
                    </tr>
                </thead>
                
                <tbody>
                    <c:forEach var="usuario" items="${usuarios}" varStatus="loop" >
                        <tr>
                            <td align="center">${loop.index+1}</td>
                            <td>
                               <span>${usuario.nome}</span>
                               <small>Nickname: ${usuario.login}</small>                             
                            </td>
                            <td align="center">${usuario.pontos}</td>
                        </tr>
                    </c:forEach>
                </tbody>
                        
            </table>
        </div>
    </body>
</html>