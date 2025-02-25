package controllers;

import br.cefetmg.space.controller.UsuarioController;
import br.cefetmg.space.entidades.Usuario;
import br.cefetmg.space.idao.exception.PersistenciaException;
import br.cefetmg.space.view.MainFX;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TelaLoginController implements Initializable {

    @FXML
    private Button BotaoLogin;

    @FXML
    private Button BotaoSenha;

    @FXML
    private TextField CampoEmail;

    @FXML
    private PasswordField CampoSenha;

    @FXML
    private Button BotaoVerSenha;
    
    @FXML
   private ImageView imagemBotao;

    @FXML
    private TextField TextFieldVisualizarSenha;

    private Usuario usuario;

    private UsuarioController usuarioController = new UsuarioController();
    
    
    @FXML
    private Label msgErro;

    boolean visualizador = false;

    @FXML
    void botaoVisualizarSenha(ActionEvent event) {
        if (visualizador) {
            TextFieldVisualizarSenha.setVisible(false);
            CampoSenha.setVisible(true);
            imagemBotao.setImage(new Image("file:src/main/resources/images/iconeOlho.jpg"));
            visualizador = false;
        } else {
            TextFieldVisualizarSenha.setVisible(true);
            CampoSenha.setVisible(false);
           imagemBotao.setImage(new Image("file:src/main/resources/images/iconeNOlho.jpg"));
            visualizador = true;
        }
    }

    public void loginButaoErro(ActionEvent e) throws PersistenciaException, IOException {
        String email = CampoEmail.getText();
        String senha = CampoSenha.getText();
        if (CampoEmail.getText().isBlank() == true || CampoSenha.getText().isBlank() == true) {
            msgErro.setText("Preencha todos os campos!");
        } else if (usuarioController.login(email, senha) != null) {
            usuario = usuarioController.login(email, senha);
            MainFX.changedScreen("Tela Inicial", usuario);
        } else {
            msgErro.setText("E-mail ou senha incorreto!");
        }
    }

    public void esqueceuSenha(ActionEvent e) throws IOException {

        MainFX.changedScreen("Tela Esqueceu", usuario);
    }

    public void cadastroButao(ActionEvent e) throws IOException {
        MainFX.changedScreen("Cadastrar Usuario", null);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TextFieldVisualizarSenha.textProperty().bindBidirectional(CampoSenha.textProperty());
        MainFX.addOnChangeScreenListener((String newString, Object viewData) -> {
        });
    }

}
