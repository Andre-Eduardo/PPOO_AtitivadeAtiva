package fxmlController;

import Models.Aluno;
import Models.Curso;
import Models.Disciplina;
import gestaoAcademica.Main;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class FXMLAddDisciplinaAlunoController {
	Curso curso;
	Aluno aluno;

	@FXML
	private ListView<Disciplina> lvDisciplinas;

	@FXML
	private Button btnAdd;

	@FXML
	private Button btnReturn;

	@FXML
	protected void initialize() {

		Main.addOnChangeScreenListener(new Main.OnChangeScreen() {
			// verica se o controller pertence a tela em exibicão e inicializa valores

			@Override
			public void onScreenChaged(String newScreen, Object userData) {
				if (newScreen.contentEquals("addDisciplinaAluno")) {
					if (userData != null) {
						aluno = (Aluno) userData;
						curso = aluno.getCurso();
						updateList();

					}
				}

			}
		});
	}

	// retorna para tela anterior
	@FXML
	protected void btReturn(ActionEvent event) { // volta para a tela anterior

		Main.changeScreen("gestaoAluno");

	}

	// Adiciona uma disciplina ao aluno
	@FXML
	protected void btAddDisciplina(ActionEvent event) { // Adiciona uma disciplina ao professor

		ObservableList<Disciplina> d = lvDisciplinas.getSelectionModel().getSelectedItems();

		if (!d.isEmpty()) {
			Disciplina disciplina = d.get(0);

			try {
				aluno.save(disciplina);
				Main.changeScreen("gestaoAluno");
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			}

		}
	}

	// metodo que atualiza as listas na tela
	private void updateList() {

		try {

			for (Disciplina d : Disciplina.findByCurso(curso.getCodigo())) {

				lvDisciplinas.getItems().add(d);

			}
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

	}
}
