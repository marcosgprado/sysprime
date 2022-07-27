package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.Colegiado;
import com.util.ConnectionTccFactory;



public class ColegiadoDAO implements InterfaceDaoTcc {
	private Connection conn;
	

	public ColegiadoDAO() throws TccDaoException {
		 try
	        {
	        	this.conn = ConnectionTccFactory.getConnection( );
	   	    
	    	      	
	        } 
	        catch( Exception e )	        {
	        	
	            throw new TccDaoException( "Erro: " +
	                                         ":\n" + e.getMessage( ) +"\n __________________" );
	        }		
	}

	public void atualizar(Object object) throws TccDaoException {
		Colegiado colegiado = (Colegiado)object;
		
		PreparedStatement ps = null;
		Connection conn = null;

		if (colegiado == null)
			throw new 
			  TccDaoException("O valor passado não pode ser nulo");

		try {
			String SQL = "UPDATE colegiado SET curso = ?, departamento = ? " +
			             " WHERE colegiado.id = ? ";
			
			conn = ConnectionTccFactory.getConnection( );
			
			ps = conn.prepareStatement(SQL);
			
			ps.setString(1, colegiado.getCurso());
			ps.setString(2, colegiado.getDepartamento());
			ps.setLong(3, colegiado.getId_Colegiado());
					

			ps.executeUpdate( );

		} catch (SQLException sqle) {
			throw new  
			TccDaoException("Erro ao atualizar dados: "+ sqle);
		} finally {
			ConnectionTccFactory.closeConnection(conn, ps);

		}

	}

	
	public void excluir(Object object) throws TccDaoException {
		Colegiado colegiado = (Colegiado)object;
		
		PreparedStatement ps = null;
		Connection conn = null;
		
		if (colegiado == null)
			throw new 
			  TccDaoException("O valor passado não pode ser nulo");

		try {
			conn = ConnectionTccFactory.getConnection( );
			ps = conn.prepareStatement("delete from colegiado where colegiado.id=?");
			
			ps.setLong(1, colegiado.getId_Colegiado());
			ps.executeUpdate( );


		} catch (SQLException sqle) {
			throw new  
			     TccDaoException("Erro ao excluir dados:" + sqle);
	
		} finally {
			ConnectionTccFactory.closeConnection(conn, ps);
		}

	}

	
	public Object procurarItens(String consulta) throws TccDaoException {
		 PreparedStatement ps = null;
		 Connection conn = null;
		 ResultSet rs = null;

		try {
			conn = ConnectionTccFactory.getConnection( );
			ps = conn.prepareStatement("select * from colegiado where curso = ?");
			
			ps.setString(1, consulta);
			rs = ps.executeQuery( );
           
		   if( !rs.next( ) )
           {
               throw new 
                          TccDaoException( "Não foi encontrado nenhum " +
									"registro com o nome : " + consulta );
           }
		   
		   		Long idColegiado = rs.getLong( 1 );
		        String curso = rs.getString( 2 );
		        String departamento = rs.getString( 3 );
		       	       
		        
		        return new Colegiado(idColegiado, curso, departamento);

		} catch (SQLException sqle) {
			throw new  TccDaoException(sqle);
		} finally {
			ConnectionTccFactory.closeConnection(conn, ps, rs);

		}		
		
	}

	
	public void salvar(Object object) throws TccDaoException {
		Colegiado colegiado = (Colegiado)object;
		
		PreparedStatement ps = null;
		Connection conn = null;
		if (colegiado == null)
			throw new 
				TccDaoException("O valor passado não pode ser nulo");

		try {
			String SQL = "INSERT INTO colegiado(curso,departamento) " +
                         "VALUES(?,?)";
			
			conn = ConnectionTccFactory.getConnection( );			
			ps = conn.prepareStatement(SQL);
			
			ps.setString(1, colegiado.getCurso());
			ps.setString(2, colegiado.getDepartamento());
			
			ps.executeUpdate( );

		} catch (SQLException sqle) {
			throw new  
				TccDaoException("Erro ao cadastrar colegiado "+ sqle);
		} finally {
			ConnectionTccFactory.closeConnection(conn, ps);

		}
		

	}

	
	public List todosItens() throws TccDaoException {
		 PreparedStatement ps = null;
		 Connection conn = ConnectionTccFactory.getConnection( );
		 ResultSet rs = null;

		try {
			conn = this.conn;
			ps = conn.prepareStatement("select * from colegiado");
			rs = ps.executeQuery();
		    List<Colegiado> list = new ArrayList<Colegiado>();
		      while( rs.next() )
		      {
		    	    Long idColegiado = rs.getLong( 1 );
			        String curso = rs.getString( 2 );
			        String departamento = rs.getString( 3 );
			       	       
		        
		        list.add( new Colegiado(idColegiado, curso, departamento));
		        
		      }
		      return list;

		} catch (SQLException sqle) {
			throw new  TccDaoException(sqle);
		} finally {
			ConnectionTccFactory.closeConnection(conn, ps, rs);
		}
	}
	
	public List procurarColegiados(String curso, String departamento) throws TccDaoException {
		 PreparedStatement ps = null;
		 Connection conn = ConnectionTccFactory.getConnection( );
		 ResultSet rs = null;

		try {
			conn = this.conn;
			ps = conn.prepareStatement("select * from colegiado where curso = ? OR  departamento = ?");
			ps.setString(1, curso);
			ps.setString(2, departamento);
			rs = ps.executeQuery();
		    List<Colegiado> list = new ArrayList<Colegiado>();
		      while( rs.next() )
		      {
		    	    Long idColegiado = rs.getLong( 1 );
			        String cur = rs.getString( 2 );
			        String dep = rs.getString( 3 );
			       	       
		        
		        list.add( new Colegiado(idColegiado, cur, dep));
		        
		      }
		      return list;

		} catch (SQLException sqle) {
			throw new  TccDaoException(sqle);
		} finally {
			ConnectionTccFactory.closeConnection(conn, ps, rs);
		}
	}

	public Object procurarItens(Long consulta) throws TccDaoException {
		 PreparedStatement ps = null;
		 Connection conn = null;
		 ResultSet rs = null;

		try {
			conn = ConnectionTccFactory.getConnection( );
			ps = conn.prepareStatement("select * from colegiado where id = ?");
			
			ps.setLong(1, consulta);
			rs = ps.executeQuery( );
          
		   if( !rs.next( ) )
          {
              throw new 
                         TccDaoException( "Não foi encontrado nenhum " +
									"registro com o nome : " + consulta );
          }
		   
		   		Long idColegiado = rs.getLong( 1 );
		        String curso = rs.getString( 2 );
		        String departamento = rs.getString( 3 );
		       	       
		        
		        return new Colegiado(idColegiado, curso, departamento);

		} catch (SQLException sqle) {
			throw new  TccDaoException(sqle);
		} finally {
			ConnectionTccFactory.closeConnection(conn, ps, rs);

		}		
		
	}
	
	public Object procurarItens(Colegiado colegiado) throws TccDaoException {
		 PreparedStatement ps = null;
		 Connection conn = null;
		 ResultSet rs = null;

		try {
			conn = ConnectionTccFactory.getConnection( );
			ps = conn.prepareStatement("select * from colegiado where curso = ? AND departamento = ?");
			
			ps.setString(1, colegiado.getCurso());
			ps.setString(2, colegiado.getDepartamento());
			rs = ps.executeQuery( );
         
		   if( !rs.next( ) )
         {
             throw new 
                        TccDaoException( "Não foi encontrado nenhum " +
									"registro com o nome : " + colegiado.getCurso() );
         }
		   
		   		Long idColegiado = rs.getLong( 1 );
		        String curso = rs.getString( 2 );
		        String departamento = rs.getString( 3 );
		       	       
		        
		        return new Colegiado(idColegiado, curso, departamento);

		} catch (SQLException sqle) {
			throw new  TccDaoException(sqle);
		} finally {
			ConnectionTccFactory.closeConnection(conn, ps, rs);

		}		
		
	}

}
