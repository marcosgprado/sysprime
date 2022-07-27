<%@ page language="java" 
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         import="com.bean.Aluno, com.dao.*, java.util.ArrayList, java.util.List"
         
         %>
<!DOCTYPE html PUBLIC "-//W3C//
DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.ArrayList"%><html>
<head>


<script language="JavaScript" src="scripts.js"></script>

<script>
 function Enviar(str)
 {
    document.getElementById("f_Aluno").action = str;
    document.getElementById("f_Aluno").submit(); 
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
	   Aluno aluno = new Aluno();
	   AlunoDAO dao = new AlunoDAO();
	   
	   String nome = request.getParameter("nome");
       String matricula = request.getParameter("matricula");
       String email = request.getParameter("email");    
          
       aluno.setNome(nome);
       aluno.setMatricula(matricula);
       aluno.setEmail(email);
	   	   
       
         if(cmd != null && cmd.equals("excluir"))
         {
            String id =  (String)request.getParameter("id");
            
            aluno.setId(Long.parseLong(id));            
            dao.excluir(aluno); 
            out.println("<script type=\"text/javascript\"> alert('O aluno foi apagado com sucesso')</script>");        
         }
         
          else if(cmd != null && cmd.equals("listar"))
         {                    
 %>

<table border="1" style="width: 520px">
<tr>
	<td>Nome</td>
	<td>Matrícula</td>
	<td>E-mail</td>
</tr>
<%
			List lista = dao.procurarAlunos (nome, matricula, email);
	        
			for(Object c : lista)
			{
				aluno = (Aluno)c;

%>

<tr>
	<td><%=aluno.getNome()%></td>
	<td><%=aluno.getMatricula()%></td>
	<td><%=aluno.getEmail()%></td>
	<td><a href="pesquisaAluno.jsp?cmd=excluir&id=<%=aluno.getId() %>">Excluir</a></td>
</tr>

<%
			}
%>
</table>
</div>

<%
}         else
         {
           
           request.getRequestDispatcher("/pesquisaAluno.jsp?cmd=listar&nome="+aluno.getNome()+"&matricula="+aluno.getMatricula()+"&email="+aluno.getEmail()).forward(request,response);  
        	   
         }

%>

</body>
</html>