package fxmlController;

import gestaoAcademica.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class FXMLSelecaoController {

	@FXML
	private Button btnProfesssor;

	@FXML
	private Button btnAluno;

	@FXML
	private Button btnDisciplina;

	// direciona para pagina do professor
	@FXML
	protected void btNavigateProfessor(ActionEvent event) {

		Main.changeScreen("gestaoProfessor", "teste de dados");

	}

	// direciona para pagina do Aluno
	@FXML
	protected void btNavigateAluno(ActionEvent event) {

		Main.changeScreen("gestaoAluno");

	}
	// direciona para pagina do curso e disciplinas

	@FXML
	protected void btNavigateDisciplina(ActionEvent event) {

		Main.changeScreen("gestaoCurso");

	}
}
