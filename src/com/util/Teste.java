package com.util;

import java.sql.Connection;

import com.dao.TccDaoException;

public class Teste {
	public static void main(String[] args)
	{
		try {
			Connection conn = ConnectionTccFactory.getConnection();
			System.out.println("OK");
		} catch (TccDaoException e) {
			
			e.printStackTrace();
		}
		
	}

}
