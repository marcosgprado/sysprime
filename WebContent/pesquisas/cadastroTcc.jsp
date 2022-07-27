 
<%@ page language="java" 
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         import="com.bean.*, com.dao.TccDAO , java.sql.Date"
         %>
<!DOCTYPE html PUBLIC "-//W3C//
DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.ArrayList"%>
<%@page import="com.dao.AlunoDAO"%><html>
<head>


<script>
 function Enviar(str)
 {
    document.getElementById("f_Tcc").action = str;
    document.getElementById("f_Tcc").submit(); 
 }
 
 function PreencherCamposTcc( id, ttl, tema, abs, ori, aqv, data, aln, clg)
 {
        document.getElementById("id_tcc").value = id;
        document.getElementById("titulo").value = ttl;
	    document.getElementById("tema").value = tema;
	    document.getElementById("abstract").value = abs;
        document.getElementById("orientador").value = ori;
        document.getElementById("arquivo").value = aqv;
        document.getElementById("data").value =data;
        document.getElementById("aluno").value = aln;
        document.getElementById("colegiado").value = clg;
 }
 
 function mascara(o,f)
 {
    v_obj=o
    v_fun=f
    setTimeout("execmascara()",1)
 }
 
 function execmascara()
 {
    v_obj.value=v_fun(v_obj.value)
 }
 
 function telefone(v){
    v=v.replace(/\D/g,"")                 //Remove tudo o que não é dígito
    v=v.replace(/(\d{2})(\d)/,"$1/$2") //Coloca parênteses em volta dos dois primeiros dígitos
    v=v.replace(/(\d{2})(\d)/,"$1/$2")
        
    return v
}
 
 
 
  


</script>



<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>SysPrime</title>

<link rel="stylesheet"  href="estilo.css" type="text/css" />

</head>
<body style="height: 716px">
  
      <div id="topo"> </div>        
      
      <div id="rodape"> </div>
      
   

<div id="form" style="height: 616px; width: 610px">

<label class="labelTopForm">Tcc:</label><div id="formTcc" style="width: 526px">



<form id="f_Tcc" method="post" name ="f_Tcc" >
 <table border="1" style="width: 580px">
 
  <tr>
    <td style="width: 108px">Titulo:</td>
    <td><input type="text" name = "titulo" style="width: 308px"></input></td></tr><tr>
    <td>Tema:</td>
    <td><input type="text" name = "tema" style="width: 308px"/></td>
    </tr>
    
    <tr>
    <td>Data:</td>
    <td><input type="text" onkeypress="mascara(this,telefone)" maxlength="10" name = "data" style="width: 308px"/></td>
    </tr>
    
    
    <tr>
    <td>Abstract:</td>
    <td><TEXTAREA COLS=40 ROWS=5 NAME="abstract">  </TEXTAREA></td>
    
    </tr>
    
    
    <tr>
    <td>Arquivo:</td>
    <td><input type="text" name = "arquivo" style="width: 308px"/></td>
    </tr>

    <tr>
    <td>Orientador:</td>
    
    <td> <input type="text" name="orientador" style="width: 308px; height: 24px"></td>
    </tr>
    
    <tr>
    <td>Aluno:</td>
    <td>
    
     <SELECT NAME="aluno" style="width: 308px">
     <%
       AlunoDAO dao1 = new AlunoDAO();       
       
             
       ArrayList<Aluno> al = (ArrayList<Aluno>)dao1.todosItens();
             
       for(Aluno aluno : al)
       {    
      %>
        <option value="<%=aluno.getId()%>"> <%=aluno.getNome()%> --  <%=aluno.getMatricula()%> </option>
      <%
       }
      %>          
     </SELECT> </td>
    </tr>
    
    <tr>
    <td>Colegiado:</td>
    <td><input type="text" name = "colegiado" style="width: 308px"/></td>
    </tr>
    
    
    <tr>
    <td><input type="text" name="id_tcc" style="width: 68px; visibility: hidden"> 
    
    </td>
        <td style="width: 311px">
        <input type = "button" value = "Cadastar" name = "salvar" onclick="Enviar('/Sysprime/cadastroTcc.jsp?cmd=cadastrar')"/>
        
     </td>
    </tr>   
   </table>
  </form>
 </div>
</div>


<%
       String cmd = request.getParameter("cmd");
	   Tcc tcc = new Tcc();
	   TccDAO dao = new TccDAO();
    
    
  if(cmd != null)
       {
         if(cmd.equals("cadastrar"))
         {
           String titulo = request.getParameter("titulo");
           String tema = request.getParameter("tema");
                  
           String data = request.getParameter("data");
         
           String abstract_ = request.getParameter("abstract");
           String arquivo = request.getParameter("arquivo");
          
           String orientador = request.getParameter("orientador");
          
           Long id_aluno = Long.parseLong(request.getParameter("aluno").toString().trim());         
           Long id_colegiado = Long.parseLong(request.getParameter("colegiado").toString().trim());  

           tcc.setTitulo(titulo);         
           tcc.setTema(tema);
           
           tcc.setData(data);
           tcc.setAbstract_(abstract_);
           
           tcc.setArquivo(arquivo);         
           tcc.setOrientador(orientador);
           tcc.setAluno(id_aluno);
           tcc.setColegiado(id_colegiado); 
           
           dao.salvar(tcc);
           tcc = (Tcc)dao.procurarItens(tcc);
                                 
           out.println("<script type=\"text/javascript\">Enviar('/Sysprime/cadastroTcc.jsp?cmd=editar&id=" + tcc.getIdTcc() + "\' ); alert('Cadastro realizado com sucesso') </script>");
                 
         }
         else if(cmd.equals("editar"))
         {
           String id = (String)request.getParameter("id");   
           out.println("<script type=\"text/javascript\">alert('>"+  id +"')</script>");
           
           tcc = (Tcc)dao.procurarItens(Long.parseLong(id));           
           out.println("<script type=\"text/javascript\"> PreencherCamposTcc('" + tcc.getIdTcc() + "','" + tcc.getTitulo() + "','" + tcc.getTema()+ "','" + tcc.getAbstract_() + "','" + tcc.getOrientador()  + "','" + tcc.getArquivo() + "','" + tcc.getData()+ "','" + tcc.getAluno() + "','" + tcc.getColegiado() + "')</script>");           
         }
                           
       }                     
 %>


</body>
</html>