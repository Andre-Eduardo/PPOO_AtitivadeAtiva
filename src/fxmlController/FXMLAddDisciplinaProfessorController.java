package fxmlController;

import Models.Curso;
import Models.Disciplina;
import Models.Professor;
import gestaoAcademica.Main;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class FXMLAddDisciplinaProfessorController {
	Professor professorAtual;
	Curso cursoAtual;
	@FXML
	private ListView<Curso> lvCursos;

	@FXML
	private Button btnAddDisciplina;

	@FXML
	private Button btnReturn;

	@FXML
	private Button btnRemoveDisciplina;

	@FXML
	private ListView<Disciplina> lvDisciplinaMinistradas;

	@FXML
	private ListView<Disciplina> lvDisciplinaDisponivel;

	// verica se o controller pertence a tela em exibicão e inicializa valores
	@FXML
	protected void initialize() {
		Main.addOnChangeScreenListener(new Main.OnChangeScreen() {

			@Override
			public void onScreenChaged(String newScreen, Object userData) {
				if (newScreen.contentEquals("addDisciplinaProfessor")) {
					professorAtual = (Professor) userData;
					updateList();
				}

			}
		});
	}

	// volta para a tela anterior
	@FXML
	protected void btReturn(ActionEvent event) {

		Main.changeScreen("gestaoProfessor");

	}

	// Adiciona uma disciplina ao professor
	@FXML
	protected void btAddDisciplina(ActionEvent event) {

		ObservableList<Disciplina> d = lvDisciplinaDisponivel.getSelectionModel().getSelectedItems();
		if (!d.isEmpty()) {
			Disciplina disciplina = d.get(0);
			try {
				professorAtual.save(disciplina);
				updateList();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

	}

	// remove uma disciplina do professor
	@FXML
	protected void removeDisciplinaAction(ActionEvent event) throws ClassNotFoundException {
		ObservableList<Disciplina> d = lvDisciplinaMinistradas.getSelectionModel().getSelectedItems();

		if (!d.isEmpty()) {
			Disciplina disciplina = d.get(0);

			professorAtual.delete(disciplina);
			updateList();
			atualiza(null);
		}
	}

	// atualiza listas na tela
	private void updateList() {
		lvCursos.getItems().clear();
		lvDisciplinaMinistradas.getItems().clear();

		try {
			for (Disciplina d : professorAtual.findDisciplinas()) {
				lvDisciplinaMinistradas.getItems().add(d);
			}

			for (Curso c : Curso.all()) {

				lvCursos.getItems().add(c);

			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// verifica se ocorreu um click dentro do componente
	@FXML
	void atualiza(MouseEvent event) throws ClassNotFoundException {
		lvDisciplinaDisponivel.getItems().clear();

		ObservableList<Curso> c = lvCursos.getSelectionModel().getSelectedItems();

		if (!c.isEmpty()) {
			cursoAtual = c.get(0);

			for (Disciplina d : Disciplina.findByCurso(cursoAtual.getCodigo())) {

				lvDisciplinaDisponivel.getItems().add(d);

			}

		}
	}

}
