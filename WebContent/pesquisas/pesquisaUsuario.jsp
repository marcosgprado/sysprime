<%@ page language="java" 
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         import="com.bean.Usuario, com.dao.*, java.util.ArrayList, java.util.List"
         
         %>
<!DOCTYPE html PUBLIC "-//W3C//
DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.ArrayList"%><html>
<head>


<script language="JavaScript" src="scripts.js"></script>

<script>
 function Enviar(str)
 {
    document.getElementById("f_Usuario").action = str;
    document.getElementById("f_Usuario").submit(); 
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

	<li><a href="cadastroAlu no.jsp" title="Gerenciar Alunos">Cadastro Aluno</a></li>

    <li><a href="cadastroColegiado.jsp" title="Gerenciar Colegiados">Cadastro Colegiado</a></li>

	<li><a href="cadastroUsuario" title="Gerenciar Usuarios">Cadastro Usuario</a></li>
  
    <li><a href="pesquisarCadastros.jsp" title="Pesquisar Cadastros">Localizar Cadastros</a></li>

      </ul>
   </div>

<div id="form" style="height: 246px; width: 516px">


<%
	   String cmd = request.getParameter("cmd");
	   Usuario usuario = new Usuario();
	   UsuarioDAO dao = new UsuarioDAO();
	   
	   String login = request.getParameter("nome");
       String senha = request.getParameter("senha");
                 
       usuario.setNome(login);
       usuario.setSenha(senha);
         	   
       
       if(cmd != null && cmd.equals("excluir"))
         {
            String id =  (String)request.getParameter("id");
            
            usuario.setId(Long.parseLong(id));            
            dao.excluir(usuario); 
            out.println("<script type=\"text/javascript\"> alert('O usuário foi apagado com sucesso')</script>");        
         }
         
          else if(cmd != null && cmd.equals("listar"))
         {                    
 %>

<table border="1" style="width: 520px">
<tr>
	<td>Nome</td>
	<%--<td>Senha</td--%>
	<td>Tipo</td>
</tr>
<%
			List lista = dao.procurarUsuarios (login, senha);
	        
			for(Object c : lista)
			{
				usuario = (Usuario)c;

%>

<tr>
	<td><%=usuario.getNome()%></td>
	<%--<td><%=aluno.getSenha()%></td--%>
	<td><%=usuario.getTipo()%></td>
	<td><a href="pesquisaUsuario.jsp?cmd=excluir&id=<%=usuario.getId() %>">Excluir</a></td>
</tr>

<%
			}
%>
</table>
</div>

<%
}         else
         {
           
           request.getRequestDispatcher("/pesquisaUsuario.jsp?cmd=listar&nome="+usuario.getNome()+"&senha="+usuario.getSenha()).forward(request,response);  
        	   
         }

%>

</body>
</html>