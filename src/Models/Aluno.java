package Models;

import java.util.List;

import sqlite.AlunoSQLite;
import sqlite.Disciplina_AlunoSQLite;

public class Aluno extends Pessoa {

	private Integer matricula;
	private Curso curso;
	private List<Disciplina> disciplina;

	public Aluno(String nome, String cpf, Curso curso) {
		super(nome, cpf);
		this.setCurso(curso);
	}

	public Aluno(int matricula, String nome, String cpf, Curso curso) {
		super(nome, cpf);
		this.matricula = matricula;
		this.setCurso(curso);
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	@Override
	public String toString() {

		return getNome();
	}

	public List<Disciplina> getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(List<Disciplina> disciplina) {
		this.disciplina = disciplina;
	}

	// implemantaçoes do DAO

	private static AlunoSQLite dao = new AlunoSQLite();
	private static Disciplina_AlunoSQLite CA = new Disciplina_AlunoSQLite();

	public void save() throws ClassNotFoundException {
		if (matricula != null && dao.find(matricula) != null) {
			dao.Update(this);
		} else {
			dao.create(this);
		}
	}

	public void save(Disciplina disciplina) throws ClassNotFoundException {

		CA.create(this, disciplina);

	}

	public void delete() throws ClassNotFoundException {
		if (dao.find(matricula) != null) {
			dao.delete(this);
		}
	}

	public void delete(Disciplina disciplina) throws ClassNotFoundException {

		CA.delete(disciplina);

	}

	public static List<Aluno> all() throws ClassNotFoundException {
		return dao.all();
	}

	public static Aluno find(int pk) throws ClassNotFoundException {

		return dao.find(pk);
	}

	public List<Disciplina> findDisciplinas() throws ClassNotFoundException {
		return CA.findByAluno(this);

	}
}
