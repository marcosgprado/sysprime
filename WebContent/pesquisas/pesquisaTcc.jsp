<%@ page language="java" 
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         import="com.bean.Tcc, com.dao.*, java.util.ArrayList, java.util.List"
         
         %>
<!DOCTYPE html PUBLIC "-//W3C//
DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.ArrayList"%><html>
<head>


<script language="JavaScript" src="scripts.js"></script>

<script>
 function Enviar(str)
 {
    document.getElementById("f_Tcc").action = str;
    document.getElementById("f_Tcc").submit(); 
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
	   Tcc tcc = new Tcc();
	   TccDAO dao = new TccDAO();
	   
	   String titulo = request.getParameter("titulo");
       String tema = request.getParameter("tema"); 
	   String orientador = request.getParameter("orientador");   
          
       tcc.setTitulo(titulo);
       tcc.setTema(tema);
       tcc.setOrientador(orientador);
       
	   	   
       
         if(cmd != null && cmd.equals("excluir"))
         {
            String id =  (String)request.getParameter("id");
           
            tcc.setIdTcc(Long.parseLong(id));            
            dao.excluir(tcc); 
            out.println("<script type=\"text/javascript\"> alert('O tcc foi apagado com sucesso')</script>");        
         }
         
          else if(cmd != null && cmd.equals("listar"))
         {                    
 %>

<table border="1" style="width: 520px">
<tr>
	<td>Título</td>
	<td>Tema</td>
	<td>Orientador</td>
</tr>
<%
			List lista = dao.procurarTccs(titulo, tema, orientador);
	        
			for(Object c : lista)
			{
				tcc = (Tcc)c;

%>

<tr>
	<td><%=tcc.getTitulo()%></td>
	<td><%=tcc.getTema()%></td>
	<td><%=tcc.getOrientador()%></td>
	<td><a href="pesquisaTcc.jsp?cmd=excluir&id=<%=tcc.getIdTcc() %>">Excluir</a></td>
</tr>

<%
			}
%>
</table>
</div>

<%
}         else
         {
           
           request.getRequestDispatcher("/pesquisaTcc.jsp?cmd=listar&titulo="+tcc.getTitulo()+"&tema="+tcc.getTema()+"&orientador="+tcc.getOrientador()).forward(request,response);  
        	   
         }

%>

</body>
</html>