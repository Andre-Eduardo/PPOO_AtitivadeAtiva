package fxmlController;

import Models.Curso;
import Models.Professor;
import gestaoAcademica.Main;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class FXMLGestaoProfessorController {

	Professor professorAtual;
	@FXML
	private Button btnEditProfessor;

	@FXML
	private Button btnNewProfessor;

	@FXML
	private ListView<Professor> lvProfessor;

	@FXML
	private Label lblNome;

	@FXML
	private Label lblCpf;

	@FXML
	private Label lblTelefone;

	@FXML
	private Label lblSalario;

	@FXML
	private Label lblTitulo;

	@FXML
	private Label txfAre;

	@FXML
	private Label lblAPEsquisa;

	@FXML
	private Label lblEndereco;

	@FXML
	private Button btnRetrun;

	@FXML
	private Button btnAddCurso;

	@FXML
	private Button btnRemoveCurso;

	@FXML
	private Button btnViewDisciplinas;

	@FXML
	private ListView<Curso> lvCursos;

	// verica se o controller pertence a tela em exibicão e inicializa valores
	@FXML
	protected void initialize() {
		Main.addOnChangeScreenListener(new Main.OnChangeScreen() {

			@Override
			public void onScreenChaged(String newScreen, Object userData) {
				if (newScreen.contentEquals("gestaoProfessor"))
					lblNome.setText("");
				lblCpf.setText("");
				lblEndereco.setText("");
				lblTelefone.setText("");
				lblSalario.setText("");
				lblTitulo.setText("");
				lblAPEsquisa.setText("");
				updateList();

			}
		});
	}

	// volta para a tela de selacao
	@FXML
	protected void btNavigateSelecao(ActionEvent event) {

		Main.changeScreen("selecao");

	}

	// exibe as diciplinas
	@FXML
	protected void discilinasMinitradasAction(ActionEvent event) {

		Main.changeScreen("addDisciplinaProfessor", professorAtual);

	}

	// direciona para pagina de cadrastra um novo professor
	@FXML
	protected void btNewProfessor(ActionEvent event) {

		Main.changeScreen("cadProfessor");

	}

	// direciona para pagina de editar um professor
	@FXML
	protected void btEditProfessor(ActionEvent event) {

		Main.changeScreen("cadProfessor", professorAtual);

	}

	// direciona para pagina de que adiciona um curso ao professor
	@FXML
	protected void btAddCurso(ActionEvent event) {

		Main.changeScreen("addCurso", professorAtual);

	}

	// botao que remove um curso do professor
	@FXML
	protected void btRemoveCurso(ActionEvent event) throws ClassNotFoundException {

		ObservableList<Curso> c = lvCursos.getSelectionModel().getSelectedItems();

		if (!c.isEmpty()) {
			Curso curso = c.get(0);

			professorAtual.delete(curso);
			atualiza(null);
		}
	}

	// direciona para pagina que lista e edita disciplinas minitradas
	@FXML
	protected void btListaDisciplinas(ActionEvent event) {

		Main.changeScreen("addDisciplinaProfessor");

	}

	// metodo que atualiza e exibe as listas na tela
	private void updateList() {
		lvProfessor.getItems().clear();

		try {
			for (Professor p : Professor.all()) {

				lvProfessor.getItems().add(p);

			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// deleta um professor
	@FXML
	protected void deleteProfessorAction(ActionEvent event) throws ClassNotFoundException {
		ObservableList<Professor> p = lvProfessor.getSelectionModel().getSelectedItems();
		if (!p.isEmpty()) {
			Professor professor = p.get(0);
			professor.delete();
			updateList();
		}
	}

	// metodo que verifica um click em determinado componente e atualiza
	@FXML
	protected void atualiza(MouseEvent event) throws ClassNotFoundException {
		lvCursos.getItems().clear();
		ObservableList<Professor> p = lvProfessor.getSelectionModel().getSelectedItems();

		if (!p.isEmpty()) {

			professorAtual = p.get(0);
			lblNome.setText(professorAtual.getNome());
			lblCpf.setText(professorAtual.getCpf());
			lblEndereco.setText(professorAtual.getEndereco());
			lblTelefone.setText(professorAtual.getTelefone());
			lblSalario.setText("R$ " + String.valueOf(professorAtual.getSalario()));
			lblTitulo.setText(professorAtual.getTitulacao());
			lblAPEsquisa.setText(professorAtual.getAreaDePesquisa());
			for (Curso c : professorAtual.findCursos()) {
				lvCursos.getItems().add(c);
			}
		}

	}

}
