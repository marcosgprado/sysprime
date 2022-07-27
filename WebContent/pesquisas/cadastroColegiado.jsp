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

	<li><a href="cadastroAluno.jsp" title="Gerenciar Alunos">Cadastro Aluno</a></li>

    <li><a href="cadastroColegiado.jsp" title="Gerenciar Colegiados">Cadastro Colegiado</a></li>

	<li><a href="cadastroUsuario.jsp" title="Gerenciar Usuarios">Cadastro Usuario</a></li>
  
    <li><a href="pesquisarCadastros.jsp" title="Pesquisar Cadastros">Localizar Cadastro</a></li>

      </ul>
   </div>







<div id="form" style="height: 246px; width: 516px">

<label class="labelTopForm">Colegiado:</label><div id="formColegiado" style="width: 526px">


<form id="f_Colegiado" method="post" name ="f_Colegiado" >
 <table border="1" style="width: 520px">
   <tr>
    <td style="width: 108px">Curso:</td>
    <td><input type="text" name = "curso" style="width: 308px" id="curso"></input></td>
   </tr>
   <tr>
    <td>Departamento:</td>
    <td><input type="text" name = "departamento" style="width: 308px" id="departamento"/></td>
   </tr>
   <tr>
    <td><input type="text" name="id_colegiado" style="width: 68px; visibility: hidden" id="id_colegiado"></td>
    <td style="width: 311px">
        <input type = "button" value = "Cadastar" name = "salvar" onclick="Enviar('/Sysprime/cadastroColegiado.jsp?cmd=cadastrar')"/>
    </td>
    </tr>
    </table>
  </form>



<%
	   String cmd = request.getParameter("cmd");
	   Colegiado colegiado = new Colegiado();
	   ColegiadoDAO dao = new ColegiadoDAO();
	   	   
       if(cmd != null)
       {
          String curso = request.getParameter("curso");
          String departamento = request.getParameter("departamento");    
          
          colegiado.setCurso(curso);
          colegiado.setDepartamento(departamento);         
       
         if(cmd.equals("cadastrar"))
         {
           dao.salvar(colegiado);
           colegiado = (Colegiado)dao.procurarItens(colegiado);
                     
           out.println("<script type=\"text/javascript\">Enviar('/Sysprime/cadastroColegiado.jsp?cmd=editar&id=" + colegiado.getId_Colegiado() + "\' ); alert('Cadastro realizado com sucesso') </script>");
                 
         }
         else if(cmd.equals("editar"))
         {
           String id =  (String)request.getParameter("id");   
                     
           colegiado = (Colegiado)dao.procurarItens(Long.parseLong(id));           
           out.println("<script type=\"text/javascript\"> PreencherCamposColegiado('" + colegiado.getId_Colegiado() + "','" + colegiado.getCurso() + "','" + colegiado.getDepartamento() + "')</script>");           
         }
         
%>         
</div>
</div>
<%
         }
      
%>

</body>
</html>