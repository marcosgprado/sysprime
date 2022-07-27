package com.dao;

import java.util.List;

public interface InterfaceDaoTcc {
	void atualizar(Object object) throws TccDaoException;

    void excluir(Object object) throws TccDaoException;

    void salvar(Object object) throws TccDaoException;

    List todosItens( ) throws TccDaoException;
    
    Object procurarItens(String consulta) throws  TccDaoException;	

}
