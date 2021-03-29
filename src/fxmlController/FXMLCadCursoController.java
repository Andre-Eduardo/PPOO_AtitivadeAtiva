package fxmlController;

import Models.Curso;
import gestaoAcademica.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FXMLCadCursoController {

	private Curso cursoAltual;
	
    @FXML
    private Label textTitle;

    @FXML
    private TextField txfNome;

    @FXML
    private TextField txfDuracao;

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
				if(newScreen.contentEquals("cadCurso")) {
					if(userData != null) {
						cursoAltual = (Curso) userData;
						textTitle.setText("Editar Curso");
						txfNome.setText(cursoAltual.getNome());
						txfDuracao.setText(String.valueOf(cursoAltual.getDuracao()));
					}else {
						cursoAltual = null;
						textTitle.setText("Cadastra Curso");
						txfNome.setText("");
						txfDuracao.setText("");
					}
				}
				
			}
		});
    }
	// retorna para tela anterior
	@FXML
	protected void btRetun(ActionEvent event) { 

		Main.changeScreen("gestaoCurso");

	}

	// salva os dados cadastrados no banco
	@FXML
    protected  void btSave(ActionEvent event) { 
      	
      	
      	String nome = txfNome.getText();
      	String duracao =  txfDuracao.getText();
      	
      	
      	
      	try {
      		if(cursoAltual != null) {
      			cursoAltual.setNome(nome);
      			cursoAltual.setDuracao(Integer.parseInt(duracao));
      			cursoAltual.save();
      		}else {
      			Curso c = new Curso(nome,Integer.parseInt( duracao));
      			c.save();
      		}
      		
			
			Main.changeScreen("gestaoCurso");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      }

}
