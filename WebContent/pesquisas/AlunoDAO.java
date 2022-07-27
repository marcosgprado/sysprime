package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.Aluno;
import com.bean.Colegiado;
import com.util.ConnectionTccFactory;

public class AlunoDAO implements InterfaceDaoTcc {
	private Connection conn;
	
	public AlunoDAO() throws TccDaoException {
		 try
	        {
	        	this.conn = ConnectionTccFactory.getConnection( );	    	      	
	        } 
	        catch( Exception e )
	        {
	            throw new TccDaoException( "Erro: " +
	                                         ":\n" + e.getMessage( ) );
	        }		
	}
	
	
	
	public void atualizar(Object object) throws TccDaoException {
		Aluno aluno = (Aluno)object;
		
		PreparedStatement ps = null;
		Connection conn = null;

		if (aluno == null)
			throw new 
			  TccDaoException("O valor passado não pode ser nulo");

		try {
			String SQL = "UPDATE aluno SET nome = ?, matricula = ?, email = ? " +
			             " WHERE aluno.id = ? ";
			
			conn = ConnectionTccFactory.getConnection( );
			
			ps = conn.prepareStatement(SQL);
			
			ps.setString(1, aluno.getNome());
			ps.setString(2, aluno.getMatricula());
			ps.setString(3, aluno.getEmail());
			ps.setLong(4, aluno.getId());
					

			ps.executeUpdate( );

		} catch (SQLException sqle) {
			throw new  
			TccDaoException("Erro ao atualizar dados: "+ sqle);
		} finally {
			ConnectionTccFactory.closeConnection(conn, ps);

		}
		
	}

	
	public void excluir(Object object) throws TccDaoException {
		Aluno aluno = (Aluno)object;
		
		PreparedStatement ps = null;
		Connection conn = ConnectionTccFactory.getConnection( );
		
		if (aluno == null)
			throw new 
			  TccDaoException("O valor passado não pode ser nulo");

		try {
			conn = ConnectionTccFactory.getConnection( );
			ps = conn.prepareStatement("delete from aluno where aluno.id=?");
			
			ps.setLong(1, aluno.getId());
			ps.executeUpdate( );


		} catch (SQLException sqle) {
			throw new  
			     TccDaoException("Erro ao excluir dados:" + sqle);
	
		} finally {
			ConnectionTccFactory.closeConnection(conn, ps);
		}
		
	}

	
	public Object procurarItens(String matricula_consulta) throws TccDaoException {
		PreparedStatement ps = null;
		Connection conn = ConnectionTccFactory.getConnection( );
		ResultSet rs = null;

		try {
			conn = ConnectionTccFactory.getConnection( );
			ps = conn.prepareStatement("select * from aluno where matricula = ?");
			
			ps.setString(1, matricula_consulta);
			rs = ps.executeQuery( );
          
		    if( !rs.next( ) ) {
              throw new 
                         TccDaoException( "Não foi encontrado nenhum " +
									"registro com o nome : " + matricula_consulta );
           }
		   
		   Long idAluno = rs.getLong( 1 );
		   String nome = rs.getString( 2 );
		   String matricula = rs.getString( 3 );
		   String email = rs.getString( 4 );
		       	            
		   return new Aluno(idAluno, nome, matricula, email);

		} catch (SQLException sqle) {
			throw new  TccDaoException(sqle);
		} finally {
			ConnectionTccFactory.closeConnection(conn, ps, rs);
		}
	}

    
	public Object procurarId(Long id) throws TccDaoException {
		PreparedStatement ps = null;
		Connection conn = ConnectionTccFactory.getConnection( );
		ResultSet rs = null;

		try {
			conn = ConnectionTccFactory.getConnection( );
			ps = conn.prepareStatement("select * from aluno where id = ?");
			
			ps.setLong(1, id);
			rs = ps.executeQuery( );
          
		    if( !rs.next( ) ) {
              throw new 
                         TccDaoException( "Não foi encontrado nenhum " +
									"registro com o nome : " + id );
           }
		   
		   Long idAluno = rs.getLong( 1 );
		   String nome = rs.getString( 2 );
		   String matricula = rs.getString( 3 );
		   String email = rs.getString( 4 );
		       	            
		   return new Aluno(idAluno, nome, matricula, email);

		} catch (SQLException sqle) {
			throw new  TccDaoException(sqle);
		} finally {
			ConnectionTccFactory.closeConnection(conn, ps, rs);
		}
	}
	
	public List procurarAlunos(String nome, String matricula, String email) throws TccDaoException {
		 PreparedStatement ps = null;
		 Connection conn = ConnectionTccFactory.getConnection( );
		 ResultSet rs = null;

		try {
			conn = this.conn;
			ps = conn.prepareStatement("select * from aluno where nome = ? OR matricula = ? OR email = ?");
			ps.setString(1, nome);
			ps.setString(2, matricula);
			ps.setString(3, email);
			rs = ps.executeQuery();
		    List<Aluno> list = new ArrayList<Aluno>();
		      while( rs.next() )
		      {
		    	    Long idAluno = rs.getLong( 1 );
			        String nom = rs.getString( 2 );
			        String mat = rs.getString( 3 );
			        String mail = rs.getString( 4 );
			       	       
		        
		        list.add( new Aluno(idAluno, nom, mat, mail));
		        
		      }
		      return list;

		} catch (SQLException sqle) {
			throw new  TccDaoException(sqle);
		} finally {
			ConnectionTccFactory.closeConnection(conn, ps, rs);
		}
	}
	
	
	public void salvar(Object object) throws TccDaoException {
		Aluno aluno = (Aluno)object;
		
		PreparedStatement ps = null;
		Connection conn = ConnectionTccFactory.getConnection( );
		if (aluno == null)
			throw new 
				TccDaoException("O valor passado não pode ser nulo");

		try {
			String SQL = "INSERT INTO aluno(nome,matricula,email) " +
                         "VALUES(?,?,?)";
			
			conn = this.conn;			
			ps = conn.prepareStatement(SQL);
			
			ps.setString(1, aluno.getNome());
			ps.setString(2, aluno.getMatricula());
			ps.setString(3, aluno.getEmail());
			
			ps.executeUpdate( );

		} catch (SQLException sqle) {
			throw new  
				TccDaoException("Erro ao cadastrar aluno "+ sqle);
		} finally {
			ConnectionTccFactory.closeConnection(conn, ps);
		}
		
	}

	
	public List<Aluno> todosItens() throws TccDaoException {
		 PreparedStatement ps = null;
		 Connection conn = ConnectionTccFactory.getConnection( );
		 ResultSet rs = null;

		try {
			conn = ConnectionTccFactory.getConnection( );
			ps = conn.prepareStatement("select * from aluno");
			rs = ps.executeQuery();
		    List<Aluno> list = new ArrayList<Aluno>();
		    
		      while( rs.next() )
		      {
		    	    Long idAluno = rs.getLong( 1 );
			        String nome = rs.getString( 2 );
			        String matricula = rs.getString( 3 );
			        String email = rs.getString( 4 );
			       	       
		        
		        list.add( new Aluno(idAluno, nome, matricula, email));
		        
		      }
		      return list;

		} catch (SQLException sqle) {
			throw new  TccDaoException(sqle);
		} finally {
			ConnectionTccFactory.closeConnection(conn, ps, rs);
		}
	}

}
