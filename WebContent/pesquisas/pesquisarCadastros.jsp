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
 function EnviarAluno(str)
 {
    document.getElementById("pesquisaAluno").action = str;
    document.getElementById("pesquisaAluno").submit(); 
 }
 function EnviarUsuario(str)
 {
    document.getElementById("pesquisaUsuario").action = str;
    document.getElementById("pesquisaUsuario").submit(); 
 }
 function EnviarColegiado(str)
 {
    document.getElementById("pesquisaColegiado").action = str;
    document.getElementById("pesquisaColegiado").submit(); 
 }
 function EnviarTcc(str)
 {
    document.getElementById("pesquisaTcc").action = str;
    document.getElementById("pesquisaTcc").submit(); 
 }
 
 function Pesquisar(str)
 {
    sel = document.getElementById("itensPesquisa").value;
     
    document.getElementById("f_Pesquisar").action = str + sel;
    document.getElementById("f_Pesquisar").submit(); 
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

	<li><a href="cadastroAlu no.jsp" title="Gerenciar Alunos">Cadastro Aluno</a></li>

    <li><a href="cadastroColegiado.jsp" title="Gerenciar Colegiados">Cadastro Colegiado</a></li>

	<li><a href="cadastroUsuario" title="Gerenciar Usuarios">Cadastro Usuario</a></li>
  
    <li><a href="pesquisarCadastros.jsp" title="Pesquisar Cadastros">Localizar Cadastro</a></li> 


      </ul>
   </div>



	
<%
  String pesquisa = request.getParameter("pesquisa");
  
  if(pesquisa !=null && pesquisa.equals("colegiado"))
  {
 %>
 
<div id="form" style="height: 246px; width: 516px">
<form name = "pesquisaColegiado" id = "pesquisaColegiado">
  <table>
   <tr>
    <td>Curso:</td>
    <td><input type="text" name="curso" style="width: 308px" id="curso"></td>
   </tr>
   <tr>
    <td>Departamento:</td>
    <td><input type="text" name="departamento" style="width: 308px" id="departamento"></td>
   </tr>
   <tr>
    <td></td>
    <td><input type = "button" value = "Pesquisar" name = "pesquisar" onclick="EnviarColegiado('/Sysprime/pesquisaColegiado.jsp')"/></td>
   </tr>
  </table>
</form>
 </div> 
 
 
<%
}	else if(pesquisa!=null && pesquisa.equals("aluno"))
	{
%>
 
<div id="form" style="height: 246px; width: 516px">
<form name = "pesquisaAluno" id = "pesquisaAluno">
  <table>
   <tr>
    <td>Nome:</td>
    <td><input type="text" name="nome" style="width: 308px" id="nome"></td>
   </tr>
   <tr>
    <td>Matrícula:</td>
    <td><input type="text" name="matricula" style="width: 308px" id="matricula"></td>
   </tr>
   <tr>
    <td>E-mail:</td>
    <td><input type="text" name="email" style="width: 308px" id="email"></td>
   </tr>
   <tr>
    <td></td>
    <td><input type = "button" value = "Pesquisar" name = "pesquisar" onclick="EnviarAluno('/Sysprime/pesquisaAluno.jsp')"/></td>   </tr>
   
  </table>
</form>
 </div> 

<%
}	else if(pesquisa!=null && pesquisa.equals("usuario"))
	{
%>
 
<div id="form" style="height: 246px; width: 516px">
<form name = "pesquisaUsuario" id = "pesquisaUsuario">
  <table>
   <tr>
    <td>Nome:</td>
    <td><input type="text" name="nome" style="width: 308px" id="nome"></td>
   </tr>
   <tr>
    <td></td>
    <td><input type="button" value="Pesquisar" name="salvar" onclick="EnviarUsuario('/Sysprime/pesquisaUsuario.jsp')"/></td>
   </tr>
   
  </table>
</form>
 </div> 

<%
}	else if(pesquisa!=null && pesquisa.equals("tcc"))
	{
%>
 
<div id="form" style="height: 246px; width: 516px">
<form name = "pesquisaTcc" id = "pesquisaTcc">
  <table>
   <tr>
    <td>Título:</td>
    <td><input type="text" name="titulo" style="width: 308px" id="titulo"></td>
   </tr>
   <tr>
    <td>Tema:</td>
    <td><input type="text" name="tema" style="width: 308px" id="tema"></td>
   </tr>
   <tr>
    <td>Orientador:</td>
    <td><input type="text" name="orientador" style="width: 308px" id="orientador"></td>
   </tr>
   <tr>
    <td></td>
    <td><input type="button" value="Pesquisar" name="salvar" onclick="EnviarTcc('/Sysprime/pesquisaTcc.jsp')"/></td>
   </tr>
  </table>
</form>
 </div> 


<%
} else{
%>
 
<div id="form" style="height: 246px; width: 516px">

<label class="labelTopForm">Pesquisar:</label><div id="formAluno" style="width: 526px">
<form id="f_Pesquisar" method="post" name ="f_Pesquisar" >

<select NAME="itensPesquisa" id="itensPesquisa" style="width: 308px">
  <option value="usuario">Usuário</option>
  <option value="tcc">TCC</option>
  <option value="aluno">Aluno</option>
  <option value="colegiado">Colegiado</option> 
</select>
 <input type = "button" value = "Pesquisar" name = "salvar" onclick="Pesquisar('/Sysprime/pesquisarCadastros.jsp?pesquisa=')"/>
</form>
 </div>
</div>

<%
}
%>

<%-- 
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
         else if(cmd.equals("excluir"))
         {
            String id =  (String)request.getParameter("id_aluno");
            
            aluno.setId(Long.parseLong(id));            
            dao.excluir(aluno); 
            out.println("<script type=\"text/javascript\"> alert('O aluno foi apagado com sucesso')</script>");        
         }
         else if(cmd.equals("atualizar"))
         {
            String id =  (String)request.getParameter("id_aluno");
                      
            aluno.setId(Long.parseLong(id));            
            dao.atualizar(aluno); 
            
            out.println("<script type=\"text/javascript\"> PreencherCamposAluno('" + aluno.getId() + "','" + aluno.getNome() + "','" + aluno.getMatricula() + "','" + aluno.getEmail() + "')</script>");
            out.println("<script type=\"text/javascript\"> alert('Atualização realizada com sucesso')</script>");        
         }      
                    
       }                     
 --%>


</body>
</html>