package com.bean;

public class Colegiado {
	private Long id_Colegiado;
	private String curso;
	private String departamento;
		
	public Colegiado() {
		
	}

	public Colegiado(Long idColegiado, String curso, String departamento) {
		super();
		id_Colegiado = idColegiado;
		this.curso = curso;
		this.departamento = departamento;
	}

	public Long getId_Colegiado() {
		return id_Colegiado;
	}

	public void setId_Colegiado(Long idColegiado) {
		id_Colegiado = idColegiado;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}	

}
