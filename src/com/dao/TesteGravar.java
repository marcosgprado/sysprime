package com.dao;

import com.bean.Colegiado;

public class TesteGravar {
	public static void main(String[] args)
	{
		try
		{
			Colegiado colegiado = new Colegiado(0L, "Computação", "DCC");

			ColegiadoDAO dao = new ColegiadoDAO();
			dao.salvar(colegiado);
			
		}
		catch(TccDaoException e)
		{
			e.printStackTrace();
		}
		
	}

}
