package com.example.imtools;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.*;
import java.util.Objects;


public class MainController {

    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    @FXML
    Button backButton;
    // Root view
    @FXML
    Button parsePdfToNpnButton;
    @FXML
    Button npnToSubmissionButton;
    @FXML
    Button cmsPostLicenceUploaderButton;
    @FXML
    Button cmsUploadMapBuilderButton;

    // Parse PDF NPN View
    @FXML
    TextField pdfPath;
    @FXML
    Pane appBar;
    @FXML
    private ProgressBar loadingBarPdfNpn;

    public void switchToRootScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("main.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void switchToParsePdfScene(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("parse-pdf-npn-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("main.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void switchToNpnSubmissionScene(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("npn-submission-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("main.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void switchToCmsPostLicenceUploaderScene(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("cms-post-licence-uploader-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("main.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void switchToCmsUploadMapBuilderScene(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("cms-upload-map-builder-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("main.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void runPythonTest() {

        String text = pdfPath.getText();
        updateProgress(0.10);
        try {
            String pythonInterpreterPath = "C:\\Users\\Robert\\Github-repo\\IM-Tools-App\\src\\main\\resources\\com\\example\\imtools\\python-interpreter\\python\\python.exe";
            String pythonScriptPath = "C:\\Users\\Robert\\Github-repo\\IM-Tools-App\\src\\main\\resources\\com\\example\\imtools\\main.py";


            ProcessBuilder processBuilder = new ProcessBuilder(pythonInterpreterPath, pythonScriptPath, text);
            Process process = processBuilder.start();

            // Read the output from the Python script
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Wait for the process to complete
            int exitCode = process.waitFor();
            System.out.println("Python script execution completed with exit code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        updateProgress(1.0);
        // Show a message box
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText("Complete");
        // Retrieve the DialogPane and add a CSS file or inline styles
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("main.css")).toExternalForm());
        alert.showAndWait();
        updateProgress(0.0);

    }
    public void updateProgress(double progress) {
        loadingBarPdfNpn.setProgress(progress);
    }



}