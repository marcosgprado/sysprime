 
package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.Colegiado;
import com.bean.Tcc;
import com.util.ConnectionTccFactory;

public class TccDAO implements InterfaceDaoTcc {
	private Connection conn;
	
	
	public TccDAO() throws TccDaoException {
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
     
		
       Tcc tcc = (Tcc)object;
		
		PreparedStatement ps = null;
		Connection conn = null;

		if (tcc == null)
			throw new 
			  TccDaoException("O valor passado não pode ser nulo");

		try {
			String SQL = "UPDATE tcc SET titulo = ?, tema = ?, data = ?," + 
			             " abstract = ? ,arquivo = ?, orientador = ?, id_aluno = ?, colegiado = ?  " +
			             " WHERE tcc.id = ?,  ";  
			
			conn = ConnectionTccFactory.getConnection( );
			
			ps = conn.prepareStatement(SQL);
			
			ps.setString(1, tcc.getTitulo());
			ps.setString(2, tcc.getTema());
			ps.setString(3, tcc.getData());
			ps.setString(4, tcc.getAbstract_().toString());
			ps.setString(5, tcc.getArquivo());
			ps.setString(6, tcc.getOrientador());
			ps.setLong(7, tcc.getAluno());
			ps.setLong(8, tcc.getColegiado());
			ps.setLong(9, tcc.getIdTcc());
					
 
			ps.executeUpdate( );

		} catch (SQLException sqle) {
			throw new  
			TccDaoException("Erro ao atualizar dados: "+ sqle);
		} finally {
			ConnectionTccFactory.closeConnection(conn, ps);

		}
		
	}

	
	public void excluir(Object object) throws TccDaoException {
		
		Tcc tcc = (Tcc)object;
		
		PreparedStatement ps = null;
		Connection conn = ConnectionTccFactory.getConnection( );
		
		if (tcc == null)
			throw new 
			  TccDaoException("O valor passado não pode ser nulo");

		try {
			conn = ConnectionTccFactory.getConnection( );
			ps = conn.prepareStatement("delete from tcc where tcc.id=?");
			
			ps.setLong(1, tcc.getIdTcc());
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
			ps = conn.prepareStatement("select * from tcc where titulo = ?");
			
			ps.setString(1, consulta);
			rs = ps.executeQuery( );
          
		    if( !rs.next( ) ) {
              throw new 
                         TccDaoException( "Não foi encontrado nenhum " +
									"registro com o nome : " + consulta );
           }
		   
		   Long idTcc = rs.getLong( 1 );
		   String titulo = rs.getString( 2 );
		   String tema = rs.getString( 3 );
		   String data = rs.getString( 4 );
		   String abstract_ = rs.getString( 5 );
		   String arquivo = rs.getString( 6 );
		   String orientador = rs.getString( 7 );
		   Long aluno = rs.getLong(8);		   
		   Long colegiado = rs.getLong(9);
		   
		   return new Tcc(idTcc, titulo, tema, abstract_, orientador, arquivo, data, aluno, colegiado);

		} catch (SQLException sqle) {
			throw new  TccDaoException(sqle);
		} finally {
			ConnectionTccFactory.closeConnection(conn, ps, rs);
		}

		
		
		
	}

	public List procurarTccs(String titulo, String tema, String orientador) throws TccDaoException {
		 PreparedStatement ps = null;
		 Connection conn = ConnectionTccFactory.getConnection( );
		 ResultSet rs = null;

		try {
			conn = this.conn;
			ps = conn.prepareStatement("select * from tcc where titulo = ? OR tema = ? OR orientador = ?");
			ps.setString(1, titulo);
			ps.setString(2, tema);
			ps.setString(3, orientador);
			rs = ps.executeQuery();
		    List<Tcc> list = new ArrayList<Tcc>();
		      while( rs.next() )
		      {
		    	    Long idTcc = rs.getLong( 1 );
			        String tit = rs.getString( 2 );
			        String tem = rs.getString( 3 );
			        String abstract_ = rs.getString( 4 );
			        String orient = rs.getString( 5 );
			        String arquivo = rs.getString( 6 );
			        String data = rs.getString( 7 );
			        Long id_aluno = rs.getLong( 8 );
			        Long id_colegiado = rs.getLong( 9 );
			       	       
		        
		        list.add( new Tcc(idTcc, tit, tem, abstract_, orient, arquivo, data, id_aluno, id_colegiado));
		        
		      }
		      return list;

		} catch (SQLException sqle) {
			throw new  TccDaoException(sqle);
		} finally {
			ConnectionTccFactory.closeConnection(conn, ps, rs);
		}
	}


	
	public List<Tcc> pesquisarTitulo(String consulta) throws TccDaoException {
		
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;

		try {
			conn = ConnectionTccFactory.getConnection( );
			ps = conn.prepareStatement("select * from tcc where titulo like ?");
			
			ps.setString(1, "%" + consulta + "%");
			rs = ps.executeQuery( );
          
			List<Tcc> list = new ArrayList<Tcc>();
		    
		      while( rs.next() )
		      {
		    	   Long idTcc = rs.getLong( 1 );
				   String titulo = rs.getString( 2 );
				   String tema = rs.getString( 3 );
				   String data = rs.getString( 4 );
				   String abstract_ = rs.getString( 5 );
				   String arquivo = rs.getString( 6 );
				   String orientador = rs.getString( 7 );
				   Long aluno = rs.getLong(8);		   
				   Long colegiado = rs.getLong(9);
				   
		        
		        list.add( new Tcc(idTcc, titulo, tema, abstract_, orientador, 
		        		arquivo, data, aluno, colegiado));
		        
		      }
		      return list;
		      
		      
		} catch (SQLException sqle) {
			throw new  TccDaoException(sqle);
		} finally {
			ConnectionTccFactory.closeConnection(conn, ps, rs);
		}
	}

	public List<Tcc> pesquisarTema(String consulta) throws TccDaoException {
		
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
	
		try {
			conn = ConnectionTccFactory.getConnection( );
			ps = conn.prepareStatement("select * from tcc where tema like ?");
			
			ps.setString(1, "%" + consulta + "%");
			rs = ps.executeQuery( );
			List<Tcc> list = new ArrayList<Tcc>();
		    
		      while( rs.next() )
		      {
		    	   Long idTcc = rs.getLong( 1 );
				   String titulo = rs.getString( 2 );
				   String tema = rs.getString( 3 );
				   String data = rs.getString( 4 );
				   String abstract_ = rs.getString( 5 );
				   String arquivo = rs.getString( 6 );
				   String orientador = rs.getString( 7 );
				   Long aluno = rs.getLong(8);		   
				   Long colegiado = rs.getLong(9);
				   
		        
		        list.add( new Tcc(idTcc, titulo, tema, abstract_, orientador, 
		        		arquivo, data, aluno, colegiado));
		        
		      }
		      return list;
		      
	
		} catch (SQLException sqle) {
			throw new  TccDaoException(sqle);
		} finally {
			ConnectionTccFactory.closeConnection(conn, ps, rs);
		}
		
	}

	public List<Tcc> pesquisarOrientador(String consulta) throws TccDaoException {
		
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
	
		try {
			conn = ConnectionTccFactory.getConnection( );
			ps = conn.prepareStatement("select * from tcc where orientador like ?");
			
			ps.setString(1, "%" + consulta + "%");
			rs = ps.executeQuery( );
	      
			List<Tcc> list = new ArrayList<Tcc>();
		    
		      while( rs.next() )
		      {
		    	   Long idTcc = rs.getLong( 1 );
				   String titulo = rs.getString( 2 );
				   String tema = rs.getString( 3 );
				   String data = rs.getString( 4 );
				   String abstract_ = rs.getString( 5 );
				   String arquivo = rs.getString( 6 );
				   String orientador = rs.getString( 7 );
				   Long aluno = rs.getLong(8);		   
				   Long colegiado = rs.getLong(9);
				   
		        
		        list.add( new Tcc(idTcc, titulo, tema, abstract_, orientador, 
		        		arquivo, data, aluno, colegiado));
		        
		      }
		      return list;
		      
		} catch (SQLException sqle) {
			throw new  TccDaoException(sqle);
		} finally {
			ConnectionTccFactory.closeConnection(conn, ps, rs);
		}
	}
	
	public List<Tcc> pesquisarAbstract(String consulta) throws TccDaoException {
		
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
	
		try {
			conn = ConnectionTccFactory.getConnection( );
			ps = conn.prepareStatement("select * from tcc where abstract like ?");
			
			ps.setString(1, "%" + consulta + "%");
			rs = ps.executeQuery( );
	      
			List<Tcc> list = new ArrayList<Tcc>();
		    
		      while( rs.next() )
		      {
		    	   Long idTcc = rs.getLong( 1 );
				   String titulo = rs.getString( 2 );
				   String tema = rs.getString( 3 );
				   String data = rs.getString( 4 );
				   String abstract_ = rs.getString( 5 );
				   String arquivo = rs.getString( 6 );
				   String orientador = rs.getString( 7 );
				   Long aluno = rs.getLong(8);		   
				   Long colegiado = rs.getLong(9);
				   
		        
		        list.add( new Tcc(idTcc, titulo, tema, abstract_, orientador, 
		        		arquivo, data, aluno, colegiado));
		        
		      }
		      return list;
		      
		} catch (SQLException sqle) {
			throw new  TccDaoException(sqle);
		} finally {
			ConnectionTccFactory.closeConnection(conn, ps, rs);
		}
	}
	
	
	public List<Tcc> pesquisarAluno(String consulta) throws TccDaoException {
		
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
	
		try {
			conn = ConnectionTccFactory.getConnection( );
			ps = conn.prepareStatement("select tcc.* from tcc, aluno where aluno.id = tcc.id_aluno " +
				"and (aluno.nome = ? or aluno.matricula = ? or aluno.email = ?)");

			ps.setString(1, consulta);
			ps.setString(2, consulta);
			ps.setString(3, consulta);
			rs = ps.executeQuery( );
	      
			List<Tcc> list = new ArrayList<Tcc>();
		    
		      while( rs.next() )
		      {
		    	   Long idTcc = rs.getLong( 1 );
				   String titulo = rs.getString( 2 );
				   String tema = rs.getString( 3 );
				   String data = rs.getString( 4 );
				   String abstract_ = rs.getString( 5 );
				   String arquivo = rs.getString( 6 );
				   String orientador = rs.getString( 7 );
				   Long aluno = rs.getLong(8);		   
				   Long colegiado = rs.getLong(9);
				   
		        
		        list.add( new Tcc(idTcc, titulo, tema, abstract_, orientador, 
		        		arquivo, data, aluno, colegiado));
		        
		      }
		      return list;
		      
		} catch (SQLException sqle) {
			throw new  TccDaoException(sqle);
		} finally {
			ConnectionTccFactory.closeConnection(conn, ps, rs);
		}
	}

	public List<Tcc> pesquisarCurso(String consulta) throws TccDaoException {
		
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
	
		try {
			conn = ConnectionTccFactory.getConnection( );
			ps = conn.prepareStatement("select tcc.* from tcc, colegiado where colegiado.id = tcc.id_colegiado " +
			"and (colegiado.curso = ? or colegiado.departamento = ?)");
			
			ps.setString(1, consulta);
			ps.setString(2, consulta);
			rs = ps.executeQuery( );
	      
			List<Tcc> list = new ArrayList<Tcc>();
		    
		      while( rs.next() )
		      {
		    	   Long idTcc = rs.getLong( 1 );
				   String titulo = rs.getString( 2 );
				   String tema = rs.getString( 3 );
				   String data = rs.getString( 4 );
				   String abstract_ = rs.getString( 5 );
				   String arquivo = rs.getString( 6 );
				   String orientador = rs.getString( 7 );
				   Long aluno = rs.getLong(8);		   
				   Long colegiado = rs.getLong(9);
				   
		        
		        list.add( new Tcc(idTcc, titulo, tema, abstract_, orientador, 
		        		arquivo, data, aluno, colegiado));
		        
		      }
		      return list;
		      
		} catch (SQLException sqle) {
			throw new  TccDaoException(sqle);
		} finally {
			ConnectionTccFactory.closeConnection(conn, ps, rs);
		}
	}
	
	public List<Tcc> pesquisarData(int consulta) throws TccDaoException {
		
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
	
		try {
			conn = ConnectionTccFactory.getConnection( );
			ps = conn.prepareStatement("select * from tcc where day(data) = ? or" +
					" month(data) = ? or year(data) = ?");
			
			ps.setInt(1, consulta);
			ps.setInt(2, consulta);
			ps.setInt(3, consulta);
			rs = ps.executeQuery( );
	      
			List<Tcc> list = new ArrayList<Tcc>();
		    
		      while( rs.next() )
		      {
		    	   Long idTcc = rs.getLong( 1 );
				   String titulo = rs.getString( 2 );
				   String tema = rs.getString( 3 );
				   String data = rs.getString( 4 );
				   String abstract_ = rs.getString( 5 );
				   String arquivo = rs.getString( 6 );
				   String orientador = rs.getString( 7 );
				   Long aluno = rs.getLong(8);		   
				   Long colegiado = rs.getLong(9);
				   
		        
		        list.add( new Tcc(idTcc, titulo, tema, abstract_, orientador, 
		        		arquivo, data, aluno, colegiado));
		        
		      }
		      return list;
		      
		} catch (SQLException sqle) {
			throw new  TccDaoException(sqle);
		} finally {
			ConnectionTccFactory.closeConnection(conn, ps, rs);
		}
	}
	
	
	public void salvar(Object object) throws TccDaoException {
	
	Tcc tcc = (Tcc)object;
		
		PreparedStatement ps = null;
		Connection conn = null;
		if (tcc == null)
			throw new 
				TccDaoException("O valor passado não pode ser nulo");

		try {
			String SQL = "INSERT INTO tcc(titulo,tema,data,abstract,arquivo," +
						 "orientador,id_aluno,id_colegiado) " +
                         "VALUES(?,?,?,?,?,?,?,?)";
			
			conn = ConnectionTccFactory.getConnection( );			
			ps = conn.prepareStatement(SQL);
			
			ps.setString(1, tcc.getTitulo());
			ps.setString(2, tcc.getTema());
			ps.setString(3, tcc.getData());
			ps.setString(4, tcc.getAbstract_());
			ps.setString(5, tcc.getArquivo());
			ps.setString(6, tcc.getOrientador());
			ps.setLong(7, tcc.getAluno());
			ps.setLong(8, tcc.getColegiado());
			
			
			ps.executeUpdate( );

		} catch (SQLException sqle) {
			throw new  
				TccDaoException("Erro ao cadastrar tcc "+ sqle);
		} finally {
			ConnectionTccFactory.closeConnection(conn, ps);
		}

		
	}

	
	public List<Tcc> todosItens() throws TccDaoException {
		 PreparedStatement ps = null;
		 Connection conn = null;
		 ResultSet rs = null;

		try {
			conn = ConnectionTccFactory.getConnection( );
			ps = conn.prepareStatement("select * from tcc");
			rs = ps.executeQuery();
		    List<Tcc> list = new ArrayList<Tcc>();
		    
		      while( rs.next() )
		      {
		    	   Long idTcc = rs.getLong( 1 );
				   String titulo = rs.getString( 2 );
				   String tema = rs.getString( 3 );
				   String data = rs.getString( 4 );
				   String abstract_ = rs.getString( 5 );
				   String arquivo = rs.getString( 6 );
				   String orientador = rs.getString( 7 );
				   Long aluno = rs.getLong(8);		   
				   Long colegiado = rs.getLong(9);
				   
		        
		        list.add( new Tcc(idTcc, titulo, tema, abstract_, orientador, 
		        		arquivo, data, aluno, colegiado));
		        
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
			ps = conn.prepareStatement("select * from tcc where id = ?");

			ps.setLong(1, consulta);
			rs = ps.executeQuery( );

			if( !rs.next( ) )
			{
				throw new 
				TccDaoException( "Não foi encontrado nenhum " +
						"registro com o nome : " + consulta );
			}

			Long idTcc = rs.getLong( 1 );
			String titulo = rs.getString( 2 );
			String tema = rs.getString( 3 );
			String data = rs.getString( 4 );
			String abstract_ = rs.getString( 5 );
			String arquivo = rs.getString( 6 );
			String orientador = rs.getString( 7 );
			Long aluno = rs.getLong(8);		   
			Long colegiado = rs.getLong(9);

			return new Tcc(idTcc, titulo, tema, abstract_, orientador, arquivo, data, aluno, colegiado);

		} catch (SQLException sqle) {
			throw new  TccDaoException(sqle);
		} finally {
			ConnectionTccFactory.closeConnection(conn, ps, rs);

		}		

	}

	public Object procurarItens(Tcc tcc) throws TccDaoException {
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;

		try {
			conn = ConnectionTccFactory.getConnection( );
			ps = conn.prepareStatement("select * from tcc where titulo = ? and tema = ? and data = ?");



			ps.setString(1, tcc.getTitulo());
			ps.setString(2, tcc.getTema());
			ps.setString(3, tcc.getData());
						
			rs = ps.executeQuery( );
        
		   if( !rs.next( ) )
        {
            throw new 
                       TccDaoException( "Não foi encontrado nenhum " +
									"registro com o nome : " + tcc.getTitulo() );
        }

		   Long idTcc = rs.getLong( 1 );
		   String titulo = rs.getString( 2 );
		   String tema = rs.getString( 3 );
		   String data = rs.getString( 4 );
		   String abstract_ = rs.getString( 5 );
		   String arquivo = rs.getString( 6 );
		   String orientador = rs.getString( 7 );
		   Long aluno = rs.getLong(8);		   
		   Long colegiado = rs.getLong(9);

		   return new Tcc(idTcc, titulo, tema, abstract_, orientador, arquivo, data, aluno, colegiado);


		} catch (SQLException sqle) {
			throw new  TccDaoException(sqle);
		} finally {
			ConnectionTccFactory.closeConnection(conn, ps, rs);

		}		
		
	}
	
	
	
	
}
