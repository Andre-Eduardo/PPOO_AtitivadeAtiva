package fxmlController;

import Models.Professor;
import gestaoAcademica.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FXMLCadProfessorController {
	Professor professorAtual;
	@FXML
	private TextField txfNome;

	@FXML
	private TextField txfCpf;

	@FXML
	private TextField txfTelefone;

	@FXML
	private TextField txfEndereco;

	@FXML
	private TextField txfSalario;

	@FXML
	private TextField txfTitulo;

	@FXML
	private TextField txfAPesquisa;

	@FXML
	private Button btnReturn;

	@FXML
	private Button btnSave;

	@FXML
	private Label textTitle;

	// verica se o controller pertence a tela em exibicão e inicializa valores
	@FXML
	protected void initialize() {

		Main.addOnChangeScreenListener(new Main.OnChangeScreen() {

			@Override
			public void onScreenChaged(String newScreen, Object userData) {
				if (newScreen.contentEquals("cadProfessor")) {
					if (userData != null) {
						professorAtual = (Professor) userData;
						textTitle.setText("Editar Professor");
						txfNome.setText(professorAtual.getNome());
						txfCpf.setText(professorAtual.getCpf());
						txfEndereco.setText(professorAtual.getEndereco());
						txfTelefone.setText(professorAtual.getTelefone());
						txfSalario.setText(String.valueOf(professorAtual.getSalario()));
						txfTitulo.setText(professorAtual.getTitulacao());
						txfAPesquisa.setText(professorAtual.getAreaDePesquisa());

					} else {
						professorAtual = null;
						textTitle.setText("Cadastro de Professor");
						txfNome.setText("");
						txfCpf.setText("");
						txfEndereco.setText("");
						txfTelefone.setText("");
						txfSalario.setText("");
						txfTitulo.setText("");
						txfAPesquisa.setText("");

					}
				}

			}
		});
	}

	// volta para a tela anterior
	@FXML
	protected void btReturn(ActionEvent event) {

		Main.changeScreen("gestaoProfessor");

	}

	// salva os dados cadastrados
	@FXML
	protected void btSave(ActionEvent event) {

		String nome = txfNome.getText();
		String cpf = txfCpf.getText();
		String endereco = txfEndereco.getText();
		String telefone = txfTelefone.getText();
		String salario = txfSalario.getText();
		String titulo = txfTitulo.getText();
		String areaPesquisa = txfAPesquisa.getText();

		try {
			if (professorAtual != null) {
				professorAtual.setNome(nome);
				professorAtual.setCpf(cpf);
				professorAtual.setEndereco(endereco);
				professorAtual.setTelefone(telefone);
				professorAtual.setSalario(Float.parseFloat(salario));
				professorAtual.setTitulacao(titulo);
				professorAtual.setAreaDePesquisa(areaPesquisa);

				professorAtual.save();

			} else {
				Professor p = new Professor(nome, cpf, telefone, Float.parseFloat(salario), titulo, areaPesquisa,
						endereco);
				p.save();
			}
			Main.changeScreen("gestaoProfessor");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

	}

}
