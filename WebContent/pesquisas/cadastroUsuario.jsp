<%@ page language="java" 
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         import="com.bean.Usuario, com.dao.*"
         
         %>
<!DOCTYPE html PUBLIC "-//W3C//
DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%--<%@page import="sun.security.krb5.internal.crypto.u"%>--%>
<html>
<head>

<script language="JavaScript" src="scripts.js"></script>


<script>
 function Enviar(str)
 {
    document.getElementById("f_Usuario").action = str;
    document.getElementById("f_Usuario").submit(); 
 }
 
 function PreencherCamposColegiado( id, log, sen)
 {
    document.getElementById("id_Usuario").value = id;
	document.getElementById("login").value = log;
	document.getElementById("senha").value = sen;
 } 


</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>SysPrime</title>

<link rel="stylesheet"  href="estilo.css" type="text/css" />

</head>
<body style="height: 345px">

     <div id="topo"> </div>        
      
      <div id="rodape"> </div>

 <div id="menubv">

      <ul id="menuver">
	<li><a href="index.jsp" title="Entrada no site">Home</a></li>

	<li><a href="cadastroTcc.jsp" title="Gerenciar TCC's">Cadastro TCC</a></li>

	<li><a href="cadastroAluno.jsp" title="Gerenciar Alunos">Cadastro Aluno</a></li>

    <li><a href="cadastroColegiado.jsp" title="Gerenciar Colegiados">Cadastro Colegiado</a></li>

	<li><a href="cadastroUsuario.jsp" title="Gerenciar Usuarios">Cadastro Usuario</a></li>
  
    <li><a href="pesquisarCadastros.jsp" title="Pesquisar Cadastros">Localizar Cadastro</a></li>

      </ul>
   </div>

<div id="form" style="height: 246px; width: 516px">

<label class="labelTopForm">Usuario:</label><div id="formUsuario" style="width: 526px">

<form id="f_Usuario" method="post" name ="f_Usuario" >
 <table border="1" style="width: 520px">
   <tr>
    <td style="width: 108px">Login:</td>
    <td><input type="text" name = "login" style="width: 308px"></input></td>
   </tr>
   <tr>
    <td>Senha:</td>
    <td><input type="password" name = "senha" style="width: 308px"/></td>
   </tr>
   <tr>
    <td><input type="text" name="id_Usuario" style="width: 68px; visibility: hidden"></td>
    <td style="width: 311px">
        <input type = "button" value = "Cadastar" name = "salvar" onclick="Enviar('/Sysprime/cadastroUsuario.jsp?cmd=cadastrar')"/>
        
     </td>
    </tr>   
   </table>
  </form>
 </div>
</div>

<%
	   String cmd = request.getParameter("cmd");
	   Usuario usuario = new Usuario();
	   UsuarioDAO dao = new UsuarioDAO();
	   	   
       if(cmd != null)
       {
          String nome = request.getParameter("login");
          String senha = request.getParameter("senha");    
          //String tipo = request.getParameter("tipo");

          usuario.setNome(nome);
          usuario.setSenha(senha);
		  //usuario.setTipo(Integer.parseInt(tipo));         
       
         if(cmd.equals("cadastrar"))
         {
           
           dao.salvar(usuario);
           usuario = (Usuario)dao.procurarItens(usuario);
           
           out.println("<script type=\"text/javascript\">Enviar('/Sysprime/cadastroUsuario.jsp?cmd=editar&id=" + usuario.getId() + "\' ); alert('Cadastro realizado com sucesso') </script>");   
                     
               	                    
         }
         else if(cmd.equals("editar"))
         {
           String id =  (String)request.getParameter("id").trim();   
           out.println("<script type=\"text/javascript\">alert('>"+  id +"<')</script>");
           
           usuario = (Usuario)dao.procurarId(Long.parseLong(id));           
           out.println("<script type=\"text/javascript\"> PreencherCamposColegiado('" + usuario.getId() + "','" + usuario.getNome() + "','" + usuario.getSenha() + "')</script>");           
         }
                                    
       }                     
 %>


</body>
</html>
