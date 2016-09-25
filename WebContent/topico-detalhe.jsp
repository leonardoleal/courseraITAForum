<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

        <link rel="stylesheet" type="text/css" href="./assets/css/estilo.css" />
        <title>Forúm ITA - Detalhes do Tópico</title>
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

            <h3>Detalhes do Tópico</h3>
        
            <p class="msg alerta">
                ${msg}
            </p>
            
            <div class="topico-detalhe">
                <h2>${topico.titulo}</h2>
                <span>Criado por: ${topico.usuario.nome}</span>                             
                <hr />
                <p>${topico.conteudo}</p>
            </div>
            
            <c:forEach var="comentario" items="${comentarios}" varStatus="loop">
                <div class="comentario linha${loop.index%2}">
                    <span>${comentario.usuario.nome} disse:</span>
                    <p>${comentario.comentario}</p>
                </div>         
            </c:forEach>
            
            <div class="novo-comentario">
                <form action="#" method="post" class="topico">
                    <textarea name="comentario"></textarea>
                    <input type="submit" value="Enviar Comentário"/>
                </form>
            </div> 
        </div>
    </body>
</html>