package Models;

import java.util.List;

import sqlite.CursoSQLite;

public class Curso {

	private String nome = new String();
	private Integer codigo;
	private int duracao; // em semetres

	public Curso(String nome, int duracao) {

		this.nome = nome;
		this.duracao = duracao;

	}

	public Curso(int codigo, String nome, int duracao) {
		this.codigo = codigo;
		this.nome = nome;
		this.duracao = duracao;

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nome;
	}

	// implemantaçoes do DAO

	private static CursoSQLite dao = new CursoSQLite();

	public void save() throws ClassNotFoundException {
		if (codigo != null && dao.find(codigo) != null) {
			dao.Update(this);
		} else {
			dao.create(this);
		}
	}

	public void delete() throws ClassNotFoundException {
		if (dao.find(codigo) != null) {
			dao.delete(this);
		}
	}

	public static List<Curso> all() throws ClassNotFoundException {
		return dao.all();
	}

	public static Curso find(int pk) throws ClassNotFoundException {
		return dao.find(pk);
	}
}
