package com.bean;

import java.util.Date;

public class Tcc {
	
	private Long idTcc;
	private String titulo;
    private String tema;
    private StringBuffer abstract_;
    private String orientador;
    private String arquivo;
    private Date data;
    private Aluno aluno;
    private Colegiado colegiado;
	
        
    
    public Tcc() { }



	public Tcc(Long idTcc, String titulo, String tema, StringBuffer abstract1,
			String orientador, String arquivo, Date data, Aluno aluno,
			Colegiado colegiado) {
		
		super();
		this.idTcc = idTcc;
		this.titulo = titulo;
		this.tema = tema;
		abstract_ = abstract1;
		this.orientador = orientador;
		this.arquivo = arquivo;
		this.data = data;
		this.aluno = aluno;
		this.colegiado = colegiado;
	}



	public Long getIdTcc() {
		return idTcc;
	}



	public void setIdTcc(Long idTcc) {
		this.idTcc = idTcc;
	}



	public String getTitulo() {
		return titulo;
	}



	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}



	public String getTema() {
		return tema;
	}



	public void setTema(String tema) {
		this.tema = tema;
	}



	public StringBuffer getAbstract_() {
		return abstract_;
	}



	public void setAbstract_(StringBuffer abstract1) {
		abstract_ = abstract1;
	}



	public String getOrientador() {
		return orientador;
	}



	public void setOrientador(String orientador) {
		this.orientador = orientador;
	}



	public String getArquivo() {
		return arquivo;
	}



	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}



	public Date getData() {
		return data;
	}



	public void setData(Date data) {
		this.data = data;
	}



	public Aluno getAluno() {
		return aluno;
	}



	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}



	public Colegiado getColegiado() {
		return colegiado;
	}



	public void setColegiado(Colegiado colegiado) {
		this.colegiado = colegiado;
	}

    





}

 
