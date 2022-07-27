package com.bean;

public class Usuario {
	private Long id;
	private String nome;
	private String senha;
	private int tipo;
	
	public Usuario() {
		
	}
	
	public Usuario(Long id, String nome, String senha, int tipo) {
		this.id = id;
		this.nome = nome;
		this.senha = senha;
		this.tipo = tipo;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
}
