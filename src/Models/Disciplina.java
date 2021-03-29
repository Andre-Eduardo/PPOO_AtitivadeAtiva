package Models;

import java.util.List;

import sqlite.DisciplinaSQLite;

public class Disciplina {
	private Integer codigo;
	private String nome = new String();
	private int cHoraria;
	private Curso curso;
	private Integer alunos = null;
	private Integer professor = null;

	public Disciplina(String nome, int cHoraria, Curso curso) {
		this.nome = nome;
		this.cHoraria = cHoraria;
		this.curso = curso;

	}

	public Disciplina(int codigo, String nome, int cHoraria, Curso curso) {
		this.codigo = codigo;
		this.nome = nome;
		this.cHoraria = cHoraria;
		this.curso = curso;

	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getcHoraria() {
		return cHoraria;
	}

	public void setcHoraria(int cHoraria) {
		this.cHoraria = cHoraria;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nome + " \t \t" + cHoraria + " Horas";
	}

	// implemantaçoes do DAO

	private static DisciplinaSQLite dao = new DisciplinaSQLite();

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

	public static List<Disciplina> all() throws ClassNotFoundException {
		return dao.all();
	}

	public static Disciplina find(int pk) throws ClassNotFoundException {
		return dao.find(pk);
	}

	public static List<Disciplina> findByCurso(int pk) throws ClassNotFoundException {
		return dao.findByCurso(pk);
	}

	public Integer getAlunos() {
		return alunos;
	}

	public void setAlunos(Integer alunos) {
		this.alunos = alunos;
	}

	public Integer getProfessor() {
		return professor;
	}

	public void setProfessor(Integer professor) {
		this.professor = professor;
	}

}
