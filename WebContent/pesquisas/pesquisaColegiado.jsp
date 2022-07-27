<%@ page language="java" 
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         import="com.bean.Colegiado, com.dao.*, java.util.ArrayList, java.util.List"
         
         %>
<!DOCTYPE html PUBLIC "-//W3C//
DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.ArrayList"%><html>
<head>


<script language="JavaScript" src="scripts.js"></script>

<script>
 function Enviar(str)
 {
    document.getElementById("f_Colegiado").action = str;
    document.getElementById("f_Colegiado").submit(); 
 }
 
 function PreencherCamposColegiado( id, cur, dep)
 {
    document.getElementById("id_colegiado").value = id;
	document.getElementById("curso").value = cur;
	document.getElementById("departamento").value = dep;
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
	   Colegiado colegiado = new Colegiado();
	   ColegiadoDAO dao = new ColegiadoDAO();
	   
	   String curso = request.getParameter("curso");
       String departamento = request.getParameter("departamento");    
          
       colegiado.setCurso(curso);
       colegiado.setDepartamento(departamento);
	   	   
       
         if(cmd != null && cmd.equals("excluir"))
         {
            String id =  (String)request.getParameter("id");
            
            colegiado.setId_Colegiado(Long.parseLong(id));            
            dao.excluir(colegiado); 
            out.println("<script type=\"text/javascript\"> alert('O colegiado foi apagado com sucesso')</script>");        
         }
         
          else if(cmd != null && cmd.equals("listar"))
         {                    
 %>

<table border="1" style="width: 520px">
<tr>
	<td>Curso</td>
	<td>Departamento</td>
</tr>
<%
			List lista = dao.procurarColegiados(curso, departamento);
	        
			for(Object c : lista)
			{
				colegiado = (Colegiado)c;

%>

<tr>
	<td><%=colegiado.getCurso()%></td>
	<td><%=colegiado.getDepartamento()%></td>
	<td><a href="pesquisaColegiado.jsp?cmd=excluir&id=<%=colegiado.getId_Colegiado() %>">Excluir</a></td>
</tr>

<%
			}
%>
</table>
</div>

<%
}         else
         {
           
           request.getRequestDispatcher("/pesquisaColegiado.jsp?cmd=listar&curso="+colegiado.getCurso()+"&departamento="+colegiado.getDepartamento()).forward(request,response);  
        	   
         }

%>

</body>
</html>