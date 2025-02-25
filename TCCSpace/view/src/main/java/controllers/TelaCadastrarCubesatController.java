package controllers;

import br.cefetmg.space.controller.ManipularImagem;
import br.cefetmg.space.dao.CubeSatDAO;
import br.cefetmg.space.entidades.CubeSat;
import br.cefetmg.space.entidades.Imagem;
import br.cefetmg.space.entidades.Usuario;
import br.cefetmg.space.idao.ICubeSatDAO;
import br.cefetmg.space.idao.exception.PersistenciaException;
import br.cefetmg.space.view.MainFX;
import java.awt.Desktop;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

public class TelaCadastrarCubesatController implements Initializable {

    @FXML
    private Button botaoCadastrarCubesat;

    private Usuario user;

    @FXML
    private Button botaoCubesat;

    @FXML
    private TextArea textDescricao;

    @FXML
    private Button botaoSuporte;

    @FXML
    private Button botaoPerfil;

    @FXML
    private Button botaoHome;

    @FXML
    private TextField textNomeCubesat;

    @FXML
    private Label labelNomeCubesat;

    @FXML
    private ImageView iconeSuporte;

    @FXML
    private ImageView iconePerfil;

    @FXML
    private ImageView iconeSair;

    @FXML
    private Button botaoImagemCubesat;

    @FXML
    private ImageView perfilCubesat;

    private final FileChooser fileChooser = new FileChooser();

    private final Desktop desktop = Desktop.getDesktop();

    private File arquivo;

    private final ManipularImagem manipuladorImagem = new ManipularImagem();

    private Imagem imagem;

    @FXML
    void cadastrarToPourple(MouseEvent event) {
        botaoCadastrarCubesat.setStyle("-fx-text-fill: #8C52FF;"
                + "-fx-background-color: 0;");
    }

    void setToNull() {
        textNomeCubesat.setText(null);
        textDescricao.setText(null);
    }

    @FXML
    void cadastrarToWhite(MouseEvent event) {
        botaoCadastrarCubesat.setStyle("-fx-text-fill: white;"
                + "-fx-background-color: 0;");
    }

    @FXML
    void sairToPourple(MouseEvent event) {
        iconeSair.setImage(new Image("file:src/main/resources/images/iconeSairLilas.png"));
    }

    @FXML
    void sairToWhite(MouseEvent event) {
        iconeSair.setImage(new Image("file:src/main/resources/images/iconeSair.png"));
    }

    @FXML
    void perfilToPourple(MouseEvent event) {
        botaoPerfil.setStyle("-fx-text-fill: #8C52FF;"
                + "-fx-background-color: 0;");
        iconePerfil.setImage(new Image("file:src/main/resources/images/userLilas.png"));
    }

    @FXML
    void perfilToWhite(MouseEvent event) {
        botaoPerfil.setStyle("-fx-text-fill: white;"
                + "-fx-background-color: 0;");
        iconePerfil.setImage(new Image("file:src/main/resources/images/user.png"));
    }

    @FXML
    void suporteToPourple(MouseEvent event) {
        botaoSuporte.setStyle("-fx-text-fill: #8C52FF;"
                + "-fx-background-color: 0;");
        iconeSuporte.setImage(new Image("file:src/main/resources/images/suporteLilas.png"));
    }

    @FXML
    void suporteToWhite(MouseEvent event) {
        botaoSuporte.setStyle("-fx-text-fill: white;"
                + "-fx-background-color: 0;");
        iconeSuporte.setImage(new Image("file:src/main/resources/images/suport.png"));
    }

    public static Image transformarImagemParaJavaFX(Imagem imagem) {
        // Converte o array de bytes em um InputStream
        ByteArrayInputStream inputStream = new ByteArrayInputStream(imagem.getDados());

        // Cria o objeto Image do JavaFX
        return new Image(inputStream);
    }

    @FXML
    void adicionarImagem(ActionEvent event) throws PersistenciaException {
        imagem = manipuladorImagem.selecionarImagem();
        if (imagem != null) {
            perfilCubesat.setImage(transformarImagemParaJavaFX(imagem));
        }
    }

    public static void configurarFileChooser(final FileChooser fileChooser) {
        FileChooser f = new FileChooser();
        f.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Todas as imagens", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg")
        );
    }

    @FXML
    void cadastrarCubesat(ActionEvent event) throws PersistenciaException {
        try {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
            Alert erro = new Alert(Alert.AlertType.ERROR);
            CubeSat cube = new CubeSat();
            ICubeSatDAO cubeDAO = new CubeSatDAO();

            if (textNomeCubesat.getText() == null || textNomeCubesat.getText().isEmpty()) {
                alert.setHeaderText("Por favor, informe o nome do CubeSat.");
                alert.show();
            } else if (cubeDAO.procurarPorNome(textNomeCubesat.getText()) != null) {
                alert.setHeaderText("Já existe um CubeSat com esse nome.");
                alert.show();
            } else {
                LocalDateTime dataCadastro = LocalDateTime.now();
                cube.setDataCadastro(dataCadastro.toString());
                cube.setNome(textNomeCubesat.getText());
                cube.setPessoa(user);
                cube.setDescricao(textDescricao.getText());
                if (imagem != null) {
                        cube.setImagem(imagem);
                }
                user.getCubeSat().add(cube);
                if (cubeDAO.inserir(cube)) {
                    confirmacao.setHeaderText("Cubesat cadastrado com sucesso!");
                    confirmacao.show();
                } else {
                    erro.setHeaderText("Houve um erro ao inserir o cubesat.");
                    erro.show();
                }

                textNomeCubesat.setText(null);
                textDescricao.setText(null);

                MainFX.changedScreen("Tela Inicial", user);
            }

        } catch (PersistenciaException | IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void apresentaTelaSuporte(ActionEvent event) throws IOException {
        setToNull();
        MainFX.changedScreen("Suporte", user);
    }

    @FXML
    void apresentaTelaPerfil(ActionEvent event) throws IOException {
        MainFX.changedScreen("VerificarSenha", user);
    }

    @FXML
    void apresentarTelaInicial(ActionEvent event) throws IOException {
        setToNull();
        MainFX.changedScreen("Tela Inicial", user);
    }

    @FXML
    private void nomeDoCubesat() {
        if (textNomeCubesat.getText().isEmpty()) {
            labelNomeCubesat.setText("<NOME>");
        } else {
            labelNomeCubesat.setText("<" + textNomeCubesat.getText().toUpperCase() + ">");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        MainFX.addOnChangeScreenListener((String newString, Object viewData) -> {
            if (viewData instanceof Usuario) {
                user = (Usuario) viewData;
            }
        });
    }

}
