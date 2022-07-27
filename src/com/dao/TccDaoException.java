package com.dao;

public class TccDaoException extends Exception  {
    
    public TccDaoException( ) {
    }
    
    public TccDaoException(String arg) {
        super(arg);
    }
    
    public TccDaoException(Throwable arg) {
        super(arg);
    }
    
    public TccDaoException(String arg, Throwable arg1) {
        super(arg, arg1);
    }
}
