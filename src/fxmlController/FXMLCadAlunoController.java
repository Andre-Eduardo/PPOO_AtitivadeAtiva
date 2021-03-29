package fxmlController;

import Models.Aluno;
import Models.Curso;
import gestaoAcademica.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FXMLCadAlunoController {
	Aluno alunoAtual;

	@FXML
	private Label textTitle;

	@FXML
	private TextField txfNome;

	@FXML
	private TextField txfCpf;

	@FXML
	private ComboBox<Curso> cbCursos;

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
				if (newScreen.contentEquals("cadAluno")) {
					caregaCursos();
					if (userData != null) {
						alunoAtual = (Aluno) userData;
						textTitle.setText("Editar Aluno");
						txfNome.setText(alunoAtual.getNome());
						txfCpf.setText(alunoAtual.getCpf());

					} else {
						alunoAtual = null;
						textTitle.setText("Cadastra Aluno");
						txfNome.setText("");

					}
				}

			}
		});
	}

	// volta para tela anterior
	@FXML
	protected void btRetunAction(ActionEvent event) {

		Main.changeScreen("gestaoAluno");

	}

	// salva os dados do aluno no banco
	@FXML
	protected void btSave(ActionEvent event) {

		String nome = txfNome.getText();
		String cpf = txfCpf.getText();

		Curso c = cbCursos.getSelectionModel().getSelectedItem();

		try {

			if (alunoAtual != null) {
				alunoAtual.setNome(nome);
				alunoAtual.setCpf(cpf);
				;
				alunoAtual.setCurso(c);
				alunoAtual.save();
			} else {
				Aluno a = new Aluno(nome, cpf, c);
				a.save();
			}
			Main.changeScreen("gestaoAluno");
		} catch (Exception e) {
			e.printStackTrace();
		}

		Main.changeScreen("gestaoAluno");

	}

	// carega os cursos e exibe na tela
	public void caregaCursos() {
		ObservableList<Curso> curso;
		try {
			curso = FXCollections.observableArrayList(Curso.all());

			cbCursos.setItems(curso);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}
