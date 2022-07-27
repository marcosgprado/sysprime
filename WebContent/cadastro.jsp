<%@ page language="java" 
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         import="com.bean.Colegiado"
         %>
<!DOCTYPE html PUBLIC "-//W3C//
DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css"> 
	#topo
	{
	 position: absolute; 
	 background-color: buttontext;
	 left: 0px;
	}
	#menu
	{
	 position: absolute; 
	 background-color: appworkspace; 
	 left: 0px; 
	 top: 78px;
	 border-color: black;
	 border: 1;
	 
	}
	#form
	{
	 position: absolute;
	 background-color: highlight;
	 left: 150px;
	 top: 78px;	
	}
	#formColegiado
	{
	 position: absolute;
	 left: 6px;
	 top: 32px;	
	}
	.labelTopForm
	{
	 font-style: normal;
	 font-size: 20;
	 font-family: sans-serif;
	}
	
	#labeTopo
	{
	 position: absolute;
	 font-size: 50px;
     color: white;
     left: 270px;
     top: 1px;	 
	}
	
	#labeMenu
	{
	 position: absolute;
	 left: 1px;
	 color: highlight;
     left: 2px;
     top: 130px;
     font-size: 50px;	 
	}
		
</style>

<script>
 function Enviar(str)
 {
  document.getElementById("f_Colegiado").action = str;
  document.getElementById("f_Colegiado").submit(); 
 }
 
 function PreencherFormColegiado()
 {
    f_Colegiado.curso.value = ;
    f_Colegiado.depar.value = ;
    f_Colegiado.id_colegiado.value = ;
    document.getElementById("f_Colegiado").value =
    document.getElementById("f_Colegiado").value = 
    document.getElementById("f_Colegiado").value =  
 }


</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>SysPrime</title>
</head>
<body style="height: 345px">

<div id="topo" style="height: 69px; width: 666px">
 <label id="labeTopo"> Topo</label>
</div>

<div id="menu" style="height: 245px; width: 145px">
 <label id="labeMenu"> Menu</label>

</div>

<div id="form" style="height: 246px; width: 516px">

<label class="labelTopForm">Colegiado:</label><div id="formColegiado" style="width: 526px">



<form id="f_Colegiado" method="post" name ="f_Colegiado" >
 <table border="1" style="width: 492px">
   <tr>
    <td style="width: 108px">Curso:</td>
    <td><input type="text" name = "curso" style="width: 308px"></input></td>
   </tr>
   <tr>
    <td>Departamento:</td>
    <td><input type="text" name = "departamento" style="width: 308px"/></td>
   </tr>
   <tr>
    <td><input type="hidden" name="id_colegiado""></td>
    <td style="width: 311px">
        <input type = "button" value = "Cadastar" name = "salvar" onclick="Enviar('/Sysprime/ColegiadoServlet1?cmd=cadastrar')"/>
        <input type = "button" value = "Excluir" name = "excluir" onclick="Enviar('/Sysprime/ColegiadoServlet1?cmd=excluir')"/>
        <input type = "button" value = "Atualizar" name = "atualizar" onclick="Enviar('/Sysprime/ColegiadoServlet1?cmd=atualizar')"/>
        <input type = "button" value = "Pesquisar" name = "pesquisar" onclick="Enviar('/Sysprime/ColegiadoServlet1?cmd=pesquisar')"/>
     </td>
    </tr>   
   </table>
  </form>
 </div>
</div>
</body>

<%
    String msg = (String)request.getAttribute("msg");
    Colegiado colegiado = (Colegiado)request.getAttribute("colegiado");
    
    
    if( msg != null)
    {
 %>
 <script type="text/javascript">
 alert('<%=msg%>');  
 </script>
<%
}
%>

</html>