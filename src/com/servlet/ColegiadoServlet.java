package com.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Colegiado;
import com.dao.ColegiadoDAO;
import com.dao.TccDaoException;

/**
 * Servlet implementation class ColegiadoServlet
 */
public class ColegiadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ColegiadoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cmd = request.getParameter("cmd");
		Colegiado colegiado = new Colegiado();
		
		if(cmd != null && !cmd.equals("pesquisar"))
		{
			colegiado.setCurso(request.getParameter("curso"));
			colegiado.setDepartamento(request.getParameter("departamento"));
		}
		
		try
		{
			ColegiadoDAO dao = new ColegiadoDAO();
			RequestDispatcher rd = null;
			
			if(cmd.equals("cadastrar"))
			{
				dao.salvar(colegiado);
				request.setAttribute("msg", "Cadastro realizado com sucesso");
				rd = request.getRequestDispatcher("/cadastro.jsp");
				rd.forward(request, response);
			}
		}
		catch(TccDaoException e)
		{
			e.printStackTrace();
		}
		
		
		
	}

}
