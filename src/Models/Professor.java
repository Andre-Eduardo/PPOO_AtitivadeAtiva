package Models;

import java.util.List;

import sqlite.ProfessorSQLite;
import sqlite.ProfessorXCurso;
import sqlite.ProfessorXDisciplina;

public class Professor extends Pessoa {
	private Integer _id;
	private String endereco = new String();
	private String telefone = new String();
	private float salario;
	private String titulacao = new String();
	private String areaDePesquisa = new String();

	public Professor(String nome, String cpf, String telefone, float salario, String titulacao, String areaDePesquisa,
			String endereco) {
		super(nome, cpf);
		this.telefone = telefone;
		this.salario = salario;
		this.titulacao = titulacao;
		this.areaDePesquisa = areaDePesquisa;
		this.endereco = endereco;
	}

	public Professor(int id, String nome, String cpf, String telefone, float salario, String titulacao,
			String areaDePesquisa, String endereco) {
		super(nome, cpf);
		this._id = id;
		this.telefone = telefone;
		this.salario = salario;
		this.titulacao = titulacao;
		this.areaDePesquisa = areaDePesquisa;
		this.endereco = endereco;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}

	public String getTitulacao() {
		return titulacao;
	}

	public void setTitulacao(String titulacao) {
		this.titulacao = titulacao;
	}

	public String getAreaDePesquisa() {
		return areaDePesquisa;
	}

	public void setAreaDePesquisa(String areaDePesquisa) {
		this.areaDePesquisa = areaDePesquisa;
	}

	public Integer get_id() {
		return _id;
	}

	public void set_id(Integer _id) {
		this._id = _id;
	}

	@Override
	public String toString() {

		return getNome();
	}

	// implemantaçoes do DAO

	private static ProfessorSQLite dao = new ProfessorSQLite();
	private static ProfessorXCurso PC = new ProfessorXCurso();
	private static ProfessorXDisciplina PD = new ProfessorXDisciplina();

	public void save() throws ClassNotFoundException {
		if (_id != null && dao.find(_id) != null) {
			dao.Update(this);
		} else {
			dao.create(this);
		}
	}

	public void save(Curso c) throws ClassNotFoundException { // curso
		PC.create(this, c);
	}

	public void save(Disciplina d) throws ClassNotFoundException { // Disciplina
		PD.create(this, d);
	}

	public void delete() throws ClassNotFoundException {
		if (dao.find(_id) != null) {
			dao.delete(this);
		}
	}

	public void delete(Curso c) throws ClassNotFoundException { // curso
		PC.delete(c);
	}

	public void delete(Disciplina d) throws ClassNotFoundException { // disciplina
		PD.delete(d);
	}

	public static List<Professor> all() throws ClassNotFoundException {
		return dao.all();
	}

	public static Professor find(int pk) throws ClassNotFoundException {
		return dao.find(pk);
	}

	public List<Disciplina> findDisciplinas() throws ClassNotFoundException { // disciplina
		return PD.findByProfessor(this);

	}

	public List<Curso> findCursos() throws ClassNotFoundException { // curso
		return PC.findByProfessor(this);

	}
}
