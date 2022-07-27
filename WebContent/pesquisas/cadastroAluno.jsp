<%@ page language="java" 
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         import="com.bean.Aluno, com.dao.*, java.util.*"
         
         %>
<!DOCTYPE html PUBLIC "-//W3C//
DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script language="JavaScript" src="scripts.js"></script>



<script>
 function Enviar(str)
 {
    document.getElementById("f_Aluno").action = str;
    document.getElementById("f_Aluno").submit(); 
 }
 
 function PreencherCamposAluno( id, nome, matricula, email)
 {
    document.getElementById("id_aluno").value = id;
	document.getElementById("nome").value = nome;
	document.getElementById("matricula").value = matricula;
	document.getElementById("email").value = email;
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

<label class="labelTopForm">Aluno:</label><div id="formAluno" style="width: 526px">

<form id="f_Aluno" method="post" name ="f_Aluno" >
 <table border="1" style="width: 520px">
   <tr>
    <td style="width: 108px">Nome:</td>
    <td><input type="text" name = "nome" style="width: 308px"></input></td>
   </tr>
   <tr>
    <td>Matrícula:</td>
    <td><input type="text" name = "matricula" style="width: 308px"/></td>
   </tr>
   <tr>
    <td>Email:</td>
    <td><input type="text" name = "email" style="width: 308px"/></td>
   </tr>
   <tr>
    <td><input type="text" name="id_aluno" style="width: 68px; visibility: hidden"></td>
    <td style="width: 311px">
        <input type = "button" value = "Cadastar" name = "salvar" onclick="Enviar('/Sysprime/cadastroAluno.jsp?cmd=cadastrar')"/>
       </td>
    </tr>   
   </table>
  </form>
 </div>
</div>

<%
	   String cmd = request.getParameter("cmd");
	   Aluno aluno = new Aluno();
	   AlunoDAO dao = new AlunoDAO();
	   	   
       if(cmd != null)
       {
          String nome = request.getParameter("nome");
          String matricula = request.getParameter("matricula");
          String email = request.getParameter("email");
          
          aluno.setNome(nome);
          aluno.setMatricula(matricula);
          aluno.setEmail(email);         
       
         if(cmd.equals("cadastrar"))
         {
           dao.salvar(aluno);
           aluno = (Aluno)dao.procurarItens(aluno.getMatricula());
           
           request.setAttribute("cmd","editar");           
           out.println("<script type=\"text/javascript\">Enviar('/Sysprime/cadastroAluno.jsp?cmd=editar&id=" + aluno.getId() + "\' ); alert('Cadastro realizado com sucesso') </script>");
                 
         }
         else if(cmd.equals("editar"))
         {
           String id =  (String)request.getParameter("id");   
           //out.println("<script type=\"text/javascript\">alert('>"+  id +"')</script>");
           
           aluno = (Aluno)dao.procurarId(Long.parseLong(id));           
           out.println("<script type=\"text/javascript\"> PreencherCamposAluno('" + aluno.getId() + "','" + aluno.getNome() + "','" + aluno.getMatricula() + "', '" + aluno.getEmail() + "')</script>");           
         }
                          
       }                     
 %>


</body>
</html>