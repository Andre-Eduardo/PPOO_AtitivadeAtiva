package fxmlController;

import Models.Curso;
import Models.Professor;
import gestaoAcademica.Main;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class FXMLAddCursoController {

	Professor professorAtual;
	@FXML
	private Button btnAdd;

	@FXML
	private Button btnReturn;

	@FXML
	private ListView<Curso> lvCurso;

	@FXML
	protected void initialize() {
		Main.addOnChangeScreenListener(new Main.OnChangeScreen() {

			// verica se o controller pertence a tela em exibicão e inicializa valores
			@Override
			public void onScreenChaged(String newScreen, Object userData) {
				if (newScreen.contentEquals("addCurso")) {
					professorAtual = (Professor) userData;
					updateList();
				}

			}
		});
	}

	@FXML
	protected void btReturn(ActionEvent event) { // volta para a tela anterior

		Main.changeScreen("gestaoProfessor");

	}

	// Adiciona uma Curso ao professor
	@FXML
	protected void btAddCurso(ActionEvent event) {

		ObservableList<Curso> c = lvCurso.getSelectionModel().getSelectedItems();
		if (!c.isEmpty()) {
			Curso curso = c.get(0);
			try {
				professorAtual.save(curso);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Main.changeScreen("gestaoProfessor");

	}

	// metodo que atualiza as listas
	private void updateList() {
		lvCurso.getItems().clear();

		try {
			for (Curso c : Curso.all()) {

				lvCurso.getItems().add(c);

			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
