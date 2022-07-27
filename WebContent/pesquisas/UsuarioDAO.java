package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.Colegiado;
import com.bean.Usuario;
import com.util.ConnectionTccFactory;

public class UsuarioDAO implements InterfaceDaoTcc {
	private Connection conn;
	
	public UsuarioDAO() throws TccDaoException {
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
		Usuario usuario = (Usuario)object;
		
		PreparedStatement ps = null;
		Connection conn = ConnectionTccFactory.getConnection( );

		if (usuario == null)
			throw new 
			  TccDaoException("O valor passado não pode ser nulo");

		try {
			String SQL = "UPDATE usuario SET nome = ?, senha = ?, tipo = ?  WHERE usuario.id = ? ";
			
			conn = this.conn;
			
			ps = conn.prepareStatement(SQL);
			
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getSenha());
			ps.setInt(3, usuario.getTipo());
			ps.setLong(4, usuario.getId());
					

			ps.executeUpdate( );

		} catch (SQLException sqle) {
			throw new  
			TccDaoException("Erro ao atualizar dados: "+ sqle);
		} finally {
			ConnectionTccFactory.closeConnection(conn, ps);

		}
		
	}

	
	public void excluir(Object object) throws TccDaoException {
		Usuario usuario = (Usuario)object;
		
		PreparedStatement ps = null;
		Connection conn = null;
		
		if (usuario == null)
			throw new 
			  TccDaoException("O valor passado não pode ser nulo");

		try {
			conn = ConnectionTccFactory.getConnection( );
			ps = conn.prepareStatement("delete from usuario where usuario.id=?");
			
			ps.setLong(1, usuario.getId());
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
			ps = conn.prepareStatement("select * from usuario where nome = ?");
			
			ps.setString(1, consulta);
			rs = ps.executeQuery( );
          
		    if( !rs.next( ) ) {
              throw new 
                         TccDaoException( "Não foi encontrado nenhum " +
									"registro com o nome : " + consulta );
           }
		   
		   Long id = rs.getLong( 1 );
		   String nome = rs.getString( 2 );
		   String senha = rs.getString( 3 );
		   int tipo = rs.getInt( 4 );
		       	            
		   return new Usuario(id, nome, senha, tipo);

		} catch (SQLException sqle) {
			throw new  TccDaoException(sqle);
		} finally {
			ConnectionTccFactory.closeConnection(conn, ps, rs);
		}
	}

	public List procurarUsuarios(String nome, String senha) throws TccDaoException {
		 PreparedStatement ps = null;
		 Connection conn = ConnectionTccFactory.getConnection( );
		 ResultSet rs = null;

		try {
			conn = this.conn;
			ps = conn.prepareStatement("select * from usuario where nome = ? OR senha = ?");
			ps.setString(1, nome);
			ps.setString(2, senha);
			rs = ps.executeQuery();
		    List<Usuario> list = new ArrayList<Usuario>();
		      while( rs.next() )
		      {
		    	    Long idUsuario = rs.getLong( 1 );
			        String nom = rs.getString( 2 );
			        String sen = rs.getString( 3 );
			        int tipo = rs.getInt( 4 );       
		        
		        list.add( new Usuario(idUsuario, nom, sen, tipo));
		        
		      }
		      return list;

		} catch (SQLException sqle) {
			throw new  TccDaoException(sqle);
		} finally {
			ConnectionTccFactory.closeConnection(conn, ps, rs);
		}
	}


	
	public Object procurarId(Long id) throws TccDaoException {
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;

		try {
			conn = ConnectionTccFactory.getConnection();
			ps = conn.prepareStatement("select * from usuario where id = ?");
			
			ps.setLong(1, id);
			rs = ps.executeQuery( );
          
		    if( !rs.next( ) ) {
              throw new 
                         TccDaoException( "Não foi encontrado nenhum " +
									"registro com o nome : " + id );
           }
		   
		   Long idUsuario = rs.getLong( 1 );
		   String nome = rs.getString( 2 );
		   String senha = rs.getString( 3 );
		   int tipo = rs.getInt( 4 );
		       	            
		   return new Usuario(idUsuario, nome, senha, tipo);

		} catch (SQLException sqle) {
			throw new  TccDaoException(sqle);
		} finally {
			ConnectionTccFactory.closeConnection(conn, ps, rs);
		}
	}
	
	public Object procurarItens(Usuario usuario) throws TccDaoException {
		 PreparedStatement ps = null;
		 Connection conn = null;
		 ResultSet rs = null;

		try {
			conn = ConnectionTccFactory.getConnection( );
			ps = conn.prepareStatement("select * from usuario where nome = ? AND senha = ?");
			
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getSenha());
			//ps.setLong(3, usuario.getId());
			rs = ps.executeQuery( );
        
		   if( !rs.next( ) )
        {
            throw new 
                       TccDaoException( "Não foi encontrado nenhum " +
									"registro com o nome e a senha especificados");
        }
	
		   		Long id = rs.getLong( 1 );
		        String nome = rs.getString( 2 );
		        String senha = rs.getString( 3 );
		        int tipo = rs.getInt( 4 );
		       	       
		        
		        return new Usuario(id, nome, senha, tipo);

		} catch (SQLException sqle) {
			throw new  TccDaoException(sqle);
		} finally {
			ConnectionTccFactory.closeConnection(conn, ps, rs);

		}		
		
	}


	
	
	public void salvar(Object object) throws TccDaoException {
		Usuario usuario = (Usuario)object;
		
		PreparedStatement ps = null;
		Connection conn = null;
		if (usuario == null)
			throw new 
				TccDaoException("O valor passado não pode ser nulo");

		try {
			String SQL = "INSERT INTO usuario(nome,senha,tipo) " +
                         "VALUES(?,?,?)";
			
			conn = ConnectionTccFactory.getConnection( );			
			ps = conn.prepareStatement(SQL);
			
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getSenha());
			ps.setInt(3, usuario.getTipo());
			
			ps.executeUpdate( );

		} catch (SQLException sqle) {
			throw new  
				TccDaoException("Erro ao cadastrar usuario "+ sqle);
		} finally {
			ConnectionTccFactory.closeConnection(conn, ps);
		}
		
	}

	
	public List<Usuario> todosItens() throws TccDaoException {
		 PreparedStatement ps = null;
		 Connection conn = null;
		 ResultSet rs = null;

		try {
			conn = ConnectionTccFactory.getConnection( );
			ps = conn.prepareStatement("select * from usuario");
			rs = ps.executeQuery();
		    List<Usuario> list = new ArrayList<Usuario>();
		    
		      while( rs.next() )
		      {
		    	    Long id = rs.getLong( 1 );
			        String nome = rs.getString( 2 );
			        String senha = rs.getString( 3 );
			        int tipo = rs.getInt( 4 );
			       	       
		        
		        list.add( new Usuario(id, nome, senha, tipo));
		        
		      }
		      return list;

		} catch (SQLException sqle) {
			throw new  TccDaoException(sqle);
		} finally {
			ConnectionTccFactory.closeConnection(conn, ps, rs);
		}
	}
	
	public boolean verificaUsuario(Usuario usuario) throws TccDaoException {
		 PreparedStatement ps = null;
		 Connection conn = null;
		 ResultSet rs = null;

		try {
			conn = ConnectionTccFactory.getConnection( );
			ps = conn.prepareStatement("select * from usuario where nome = ? AND senha = ?");
			
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getSenha());
			//ps.setLong(3, usuario.getId());
			rs = ps.executeQuery( );
       
		   if( !rs.next( ) )
		   {
			   return false;
           }       	       
		        
		       return true;

		} catch (SQLException sqle) {
			throw new  TccDaoException(sqle);
		} finally {
			ConnectionTccFactory.closeConnection(conn, ps, rs);

		}		
		
	}
	
	

}
