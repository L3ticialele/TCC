package controllers;

import br.cefetmg.space.model.dao.CubeSatDAO;
import br.cefetmg.space.model.dto.CubeSatDTO;
import br.cefetmg.space.model.dto.UsuarioDTO;
import br.cefetmg.space.model.idao.ICubeSatDAO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;
import br.cefetmg.space.view.MainFX;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class TelaEditarCubesatController implements Initializable {

    private BufferedImage imagem;

    private CubeSatDTO cubesat;

    @FXML
    private Button botaoCubesat;

    @FXML
    private TextArea textDescricao;

    @FXML
    private Button botaoEquipe;

    @FXML
    private Button botaoExplorar;

    @FXML
    private Button botaoHome;

    @FXML
    private ChoiceBox<String> choiceBoxAcesso;

    @FXML
    private Label labelDataCadastro;

    @FXML
    private TextField textNomeCubesat;

    private String[] acesso = {"Público", "Privado"};

    @FXML
    private ImageView iconeEquipes;

    @FXML
    private ImageView iconeExplorar;

    @FXML
    private ImageView iconeSair;

    @FXML
    private Button botaoImagemCubesat;

    @FXML
    private Label labelIdCubesat;

    @FXML
    private ImageView perfilCubesat;

    private final FileChooser fileChooser = new FileChooser();

    private final Desktop desktop = Desktop.getDesktop();

    private File arquivo;

    @FXML
    private Button botaoSalvarCubesat;

    @FXML
    private Button botaoExcluirCubesat;

    private Stage dialogStage;
    private boolean okClicked = false;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void textsFields() {
        textNomeCubesat.setText(cubesat.getNome());
        textDescricao.setText(cubesat.getDescricao());
        choiceBoxAcesso.setValue(cubesat.getAcesso());
        labelDataCadastro.setText(cubesat.getDataCadastro());
        labelIdCubesat.setText("ID: " + cubesat.getId());
    }

    public boolean isOkClicked() {
        return this.okClicked;
    }

    @FXML
    void excluirCubesat(ActionEvent event) throws PersistenciaException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Tem certeza que deseja excluir o cubesat ?"); //+ cubesat.getNome() + "?");
        alert.show();
    }

    @FXML
    void salvarAlteracoesCubesat(ActionEvent event) throws PersistenciaException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
        Alert erro = new Alert(Alert.AlertType.ERROR);
        CubeSatDTO cube = new CubeSatDTO();
        ICubeSatDAO cubeDAO = new CubeSatDAO();

        if (textNomeCubesat.getText() == null || textNomeCubesat.getText().isEmpty()) {
            alert.setHeaderText("Por favor, informe o nome do CubeSat.");
            alert.show();
        } else {
            LocalDateTime dataCadastro = LocalDateTime.now();
            cube.setDataCadastro(dataCadastro.toString());
            cube.setNome(textNomeCubesat.getText());
            cube.setAcesso(choiceBoxAcesso.getValue());
            cube.setPessoa(cubesat.getUsuario());
            cube.setDescricao(textDescricao.getText());

            if (cubeDAO.atualizar(cube)) {
                confirmacao.setHeaderText("Cubesat atualizado!");
                confirmacao.show();
            } else {
                erro.setHeaderText("Houve um erro ao atualizar o cubesat.");
                erro.show();
            }

            MainFX.changedScreen("Cubesat", cubesat.getUsuario());
        }
    }

    @FXML
    void salvarToPourple(MouseEvent event) {
        botaoSalvarCubesat.setStyle("-fx-text-fill: white;"
                + "-fx-background-color:  #8C52FF;");
    }

    @FXML
    void salvarToWhite(MouseEvent event) {
        botaoSalvarCubesat.setStyle("-fx-text-fill: white;"
                + "-fx-background-color:  #73668B;");
    }

    @FXML
    void excluirToPourple(MouseEvent event) {
        botaoExcluirCubesat.setStyle("-fx-text-fill: white;"
                + "-fx-background-color:  #8C52FF;");
    }

    @FXML
    void excluirToWhite(MouseEvent event) {
        botaoExcluirCubesat.setStyle("-fx-text-fill: white;"
                + "-fx-background-color:  #73668B;");
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
    void explorarToPourple(MouseEvent event) {
        botaoExplorar.setStyle("-fx-text-fill: #8C52FF;"
                + "-fx-background-color: 0;");
        iconeExplorar.setImage(new Image("file:src/main/resources/images/iconeExplorarLilas.png"));
    }

    @FXML
    void explorarToWhite(MouseEvent event) {
        botaoExplorar.setStyle("-fx-text-fill: white;"
                + "-fx-background-color: 0;");
        iconeExplorar.setImage(new Image("file:src/main/resources/images/iconeExplorar.png"));
    }

    @FXML
    void equipesToPourple(MouseEvent event) {
        botaoEquipe.setStyle("-fx-text-fill: #8C52FF;"
                + "-fx-background-color: 0;");
        iconeEquipes.setImage(new Image("file:src/main/resources/images/iconeEquipesLilas.png"));
    }

    @FXML
    void equipesToWhite(MouseEvent event) {
        botaoEquipe.setStyle("-fx-text-fill: white;"
                + "-fx-background-color: 0;");
        iconeEquipes.setImage(new Image("file:src/main/resources/images/iconeEquipes.png"));
    }

    @FXML
    void adicionarImagem(ActionEvent event) throws PersistenciaException {
        try {
            configurarFileChooser(fileChooser);
            arquivo = fileChooser.showOpenDialog(new Stage());
            if (arquivo != null) {
                perfilCubesat.setImage(new Image("file:" + arquivo.getPath()));
                perfilCubesat.setStyle("-fx-border-radius: 50%;");
            }
        } catch (Exception e) {
            e.printStackTrace();
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
    void apresentaTelaCubesat(ActionEvent event) {
        MainFX.changedScreen("Cubesat", cubesat.getUsuario());
    }

    @FXML
    void apresentaTelaEquipe(ActionEvent event) {
        MainFX.changedScreen("Equipes", cubesat.getUsuario());
    }

    @FXML
    void apresentaTelaExplorar(ActionEvent event) {
        MainFX.changedScreen("Explorar", cubesat.getUsuario());
    }

    @FXML
    void apresentarTelaInicial(ActionEvent event) {
        MainFX.changedScreen("Tela Inicial", cubesat.getUsuario());
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        MainFX.addOnChangeScreenListener(new MainFX.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newString, Object viewData) {
                System.out.println("viewData.clss: " + viewData.getClass().getName());

                if (viewData instanceof CubeSatDTO) {
                    cubesat = (CubeSatDTO) viewData;

                    choiceBoxAcesso.setValue(cubesat.getAcesso());
                    textDescricao.setText(cubesat.getDescricao());
                    textNomeCubesat.setText(cubesat.getNome());
                } else {
                    System.out.println("cubesat null!");
                }

            }
        });

    }

}
