package fxmlController;

import Models.Curso;
import Models.Disciplina;
import gestaoAcademica.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FXMLCadDiscilinaController {

	private Disciplina DisciplinaAltual;
	int codigoCurso;
	@FXML
	private Label lblTitle;

	@FXML
	private TextField txfNome;

	@FXML
	private TextField txfCHoraria;

	@FXML
	private Button btnReturn;

	@FXML
	private Button btnSave;

	// verica se o controller pertence a tela em exibicão e inicializa valores
	@FXML
	protected void initialize() {

		Main.addOnChangeScreenListener(new Main.OnChangeScreen() {

			@Override
			public void onScreenChaged(String newScreen, Object userData) {
				if (newScreen.contentEquals("cadDisciplina")) {
					if (userData instanceof Disciplina) {
						DisciplinaAltual = (Disciplina) userData;

						lblTitle.setText("Editar Disciplina");
						txfNome.setText(DisciplinaAltual.getNome());
						txfCHoraria.setText(String.valueOf(DisciplinaAltual.getcHoraria()));

					} else {
						DisciplinaAltual = null;
						codigoCurso = (int) userData;
						lblTitle.setText("Cadastra disciplina");
						txfNome.setText("");
						txfCHoraria.setText("");

					}
				}

			}
		});
	}

	// volta para a tela anterior
	@FXML
	protected void btRetun(ActionEvent event) {

		Main.changeScreen("gestaoCurso");

	}

	// salva os dados cadastrados
	@FXML
	protected void btSave(ActionEvent event) {

		String nome = txfNome.getText();
		String CHoraria = txfCHoraria.getText();

		try {
			if (DisciplinaAltual != null) {
				DisciplinaAltual.setNome(nome);
				DisciplinaAltual.setcHoraria(Integer.parseInt(CHoraria));
				DisciplinaAltual.save();
			} else {
				Disciplina d = new Disciplina(nome, Integer.parseInt(CHoraria), Curso.find(codigoCurso));
				d.save();
			}

			Main.changeScreen("gestaoCurso");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
