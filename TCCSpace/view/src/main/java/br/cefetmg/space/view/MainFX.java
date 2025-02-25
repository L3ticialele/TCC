package br.cefetmg.space.view;

import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFX extends Application {

    private static Stage stage;

    private static Scene telaPerfil;
    private static Scene telaSuporte;
    private static Scene telaInicial;
    private static Scene telaCadastrarCubesat;
    private static Scene telaCadastrarUsuario;
    private static Scene telaLogin;
    private static Scene telaEditarCubesat;
    private static Scene graficos;
    private static Scene telaGui3d;
    private static Scene esqueceuSenha;
    private static Scene verificarSenha;

    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            stage = primaryStage;
            primaryStage.setTitle("CUTE");
            telaLogin = new Scene(loaderFXML("/fxml/TelaLogin"), 1280, 720);
            primaryStage.setScene(telaLogin);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void changedScreen(String tela, Object userData) throws IOException {
        switch (tela) {
            case "Gui3d":
                telaGui3d = new Scene(loaderFXML("/fxml/Data3DViewer"), 1280, 720);
                stage.setScene(telaGui3d);
                break;
            case "Perfil":
                telaPerfil = new Scene(loaderFXML("/fxml/TelaEditarPerfil"), 1280, 720);
                stage.setScene(telaPerfil);
                break;
            case "VerificarSenha":
                verificarSenha = new Scene(loaderFXML("/fxml/VerificarSenha"), 1280, 720);
                stage.setScene(verificarSenha);
                break;

            case "Suporte":
                telaSuporte = new Scene(loaderFXML("/fxml/TelaSuporte"), 1280, 720);
                stage.setScene(telaSuporte);
                break;
            case "Tela Inicial":
                telaInicial = new Scene(loaderFXML("/fxml/TelaInicial"), 1280, 720);
                stage.setScene(telaInicial);
                break;
            case "Cadastrar Cubesat":
                telaCadastrarCubesat = new Scene(loaderFXML("/fxml/TelaCadastrarCubesat"), 1280, 720);
                stage.setScene(telaCadastrarCubesat);
                break;
            case "Cadastrar Usuario":
                telaCadastrarUsuario = new Scene(loaderFXML("/fxml/TelaCadastrarUsuario"), 1280, 720);
                stage.setScene(telaCadastrarUsuario);
                break;
            case "Login":
                telaLogin = new Scene(loaderFXML("/fxml/TelaLogin"), 1280, 720);
                stage.setScene(telaLogin);
                break;
            case "Editar Cubesat":
                telaEditarCubesat = new Scene(loaderFXML("/fxml/TelaEditarCubesat"), 1280, 720);
                stage.setScene(telaEditarCubesat);
                break;
            case "Graficos":
                graficos = new Scene(loaderFXML("/fxml/Graficos"), 1280, 720);
                stage.setScene(graficos);
                break;
            case "Tela Esqueceu":
                esqueceuSenha = new Scene(loaderFXML("/fxml/EsqueceuSenha"), 1280, 720);
                stage.setScene(esqueceuSenha);
                break;
        }
        notifyAllListeners(tela, userData);

    }

    public static Parent loaderFXML(String caminho) throws IOException {
        return FXMLLoader.load(MainFX.class.getResource(caminho + ".fxml"));
    }

    private static final ArrayList<OnChangeScreen> listeners = new ArrayList<>();

    public static interface OnChangeScreen {

        void onScreenChanged(String newScreen, Object userData);
    }

    public static void addOnChangeScreenListener(OnChangeScreen newListener) {
        listeners.add(newListener);
    }

    private static void notifyAllListeners(String newScreen, Object userData) {
        for (OnChangeScreen l : listeners) {
            l.onScreenChanged(newScreen, userData);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
