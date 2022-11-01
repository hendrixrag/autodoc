package org.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import org.modelo.ODS;

public class FXMLController {

    //objetos importados de principal-view.fmxl para añadir las funcionalidades
    @FXML
    private AnchorPane paneInicio;
    @FXML
    private AnchorPane paneCasos;
    @FXML
    private AnchorPane paneAbout;
    @FXML
    private TilePane btnInicio;
    @FXML
    private TilePane btnAgregar;
    @FXML
    private TilePane btnBuscar;
    @FXML
    private TilePane btnAbout;
    @FXML
    private ImageView btnCerrar;
    @FXML
    private TilePane btnMin;
    @FXML
    private Label lblAgg;
    @FXML
    private Label lblBuscar;
    @FXML
    private Label lblAbout;
    @FXML
    private Line lineaInicio;
    @FXML
    private Line lineaAgg;
    @FXML
    private Line lineaBuscar;
    @FXML
    private Line lineaAbout;
    @FXML
    private Text txtBienvenida;
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private Label lblResumen;
    @FXML
    private JFXComboBox<String> comboTipoCaso;
    @FXML
    private JFXComboBox<String> comboRelacionado;
    @FXML
    private JFXTextArea txtDoc;
    @FXML
    private JFXButton btnCopiarSol;
    @FXML
    private JFXComboBox<String> comboResumenes;
    @FXML
    private TextField txtResumen;
    //contenido del combobox de los tipos de casos
    ObservableList<String> contComboTipoCaso
            = FXCollections.observableArrayList(
                    "PROBLEMA",
                    "REQUERIMIENTO"
            );
    //contenido del combobox de caso relacionado a
    ObservableList<String> contComboRelacionado
            = FXCollections.observableArrayList(
                    "ASIGNACION DE EQUIPO",
                    "DESINCORPORACION",
                    "HARDWARE",
                    "SOFTWARE"
            );

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        String nameUser;
        nameUser = System.getProperty("user.name");
        txtBienvenida.setText("Bienvenido/a " + nameUser + " al Sistema Automatizado para la Documentación de casos,"
                + " seleccione agregar para guardar un nuevo caso a documentar o buscar para copiar la documentación "
                + "de un caso agregado previamente.");
        paneInicio.setVisible(true);

        comboTipoCaso.setItems(contComboTipoCaso);
        comboRelacionado.setItems(contComboRelacionado);
        initCombos();
        btnMin.setVisible(false);
        //poniendo saltos de linea en los label de los botones del menú
        /*String agg = "Agregar \ncaso";
       lblAgg.setText(agg);
       String buscar = "Buscar \ncaso";
       lblBuscar.setText(buscar);
       String about = "Acerca \nde...";
       lblAbout.setText(about);*/
    }

    public void initCombos() {
        ODS orden = new ODS();
        ObservableList<String> obsODS = orden.getODS();
        this.comboResumenes.setItems(obsODS);
    }

    //eventos en los botones
    @FXML
    private void clicInicio(MouseEvent event) {
        paneInicio.setVisible(true);
        paneCasos.setVisible(false);
        paneAbout.setVisible(false);
        lineaInicio.setVisible(true);
        lineaAgg.setVisible(false);
        lineaBuscar.setVisible(false);
        lineaAbout.setVisible(false);
        comboResumenes.setVisible(false);
    }

    @FXML
    private void clicAgregar(MouseEvent event) {
        paneInicio.setVisible(false);
        paneCasos.setVisible(true);
        paneAbout.setVisible(false);
        lineaInicio.setVisible(false);
        lineaAgg.setVisible(true);
        lineaBuscar.setVisible(false);
        lineaAbout.setVisible(false);
        lblResumen.setLayoutX(79);
        lblResumen.setLayoutY(206);
        btnCopiarSol.setVisible(false);
        btnGuardar.setVisible(true);
        comboResumenes.setVisible(false);
        txtResumen.setVisible(true);
    }

    @FXML
    private void clicBuscar(MouseEvent event) {
        paneInicio.setVisible(false);
        paneCasos.setVisible(true);
        paneAbout.setVisible(false);
        lineaInicio.setVisible(false);
        lineaAgg.setVisible(false);
        lineaBuscar.setVisible(true);
        lineaAbout.setVisible(false);
        btnGuardar.setVisible(false);
        lblResumen.setLayoutX(426);
        lblResumen.setLayoutY(79);
        btnCopiarSol.setVisible(true);
        txtResumen.setVisible(false);
        comboResumenes.setVisible(true);
    }

    @FXML
    private void clicAbout(MouseEvent event) {
        paneInicio.setVisible(false);
        paneCasos.setVisible(false);
        paneAbout.setVisible(true);
        lineaInicio.setVisible(false);
        lineaAgg.setVisible(false);
        lineaBuscar.setVisible(false);
        lineaAbout.setVisible(true);
        comboResumenes.setVisible(false);
    }

    //función para cerrar la app 
    @FXML
    private void clicCerrar(MouseEvent event) {
        Platform.exit();
        System.exit(0);
    }

    //función para minimizar la app
    @FXML
    private void clicMin(MouseEvent event) {

    }

    //guardar caso en la base de datos
    @FXML
    private void guardarCaso(ActionEvent event) {
        txtDoc.setText(txtDoc.getText().toUpperCase());
        txtResumen.setText(txtResumen.getText().toUpperCase());
        String tipo_caso = comboTipoCaso.getValue();
        String relacionadoA = comboRelacionado.getValue();
        String resumen_caso = "";
        resumen_caso = txtResumen.getText();
        String sol_caso = "";
        sol_caso = txtDoc.getText();
        //validaciones de errores al guardar
        String error = "";
        if (tipo_caso == null) {
            error += "-Debes seleccionar un tipo de caso\n";
        }
        if (relacionadoA == null) {
            error += "-Debes seleccionar un relacionado\n";
        }
        if (resumen_caso.isBlank()) {
            error += "-Debes escribir un resumen de caso\n";
        }
        if (sol_caso.isBlank()) {
            error += "-Debes escribir la documentacion del caso\n";
        }

        //guardar si no hay error
        if (error.isEmpty()) {
            ODS ods = new ODS(tipo_caso, relacionadoA, resumen_caso, sol_caso);
            if (ods.insertarODS()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Éxito");
                alert.setContentText("Se ha insertado el caso correctamente.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(error);
            alert.showAndWait();
        }

    }

    @FXML
    private void copiarSolucion(ActionEvent event) {
        txtDoc.setText(txtDoc.getText().toUpperCase());
        txtResumen.setText(txtResumen.getText().toUpperCase());
        txtDoc.copy();
    }

    @FXML
    private void selecTipoCaso(ActionEvent event) {
        String tipo_caso = comboTipoCaso.getValue();
        String relacionadoA = "";
        String resumen_caso = "";
        String sol_caso = "";

        ODS ods = new ODS(tipo_caso, relacionadoA, resumen_caso, sol_caso);
        comboResumenes.setItems(ods.setComboResumenes(tipo_caso, relacionadoA));
    }

    @FXML
    private void selecRelacionado(ActionEvent event) {
        String tipo_caso = comboTipoCaso.getValue();
        String relacionadoA = comboRelacionado.getValue();
        String resumen_caso = "";
        String sol_caso = "";

        ODS ods = new ODS(tipo_caso, relacionadoA, resumen_caso, sol_caso);
        comboResumenes.setItems(ods.setComboResumenes(tipo_caso, relacionadoA));
    }

    //Evento del combobox para seleccionar un resumen de caso
    @FXML
    private void selecResumen(ActionEvent event) {
        String tipo_caso = "";
        String relacionadoA = "";
        String resumen_caso = "";
        String sol_caso = "";
        String reg_caso = "";

        resumen_caso = comboResumenes.getValue();
        ODS ods = new ODS(tipo_caso, relacionadoA, resumen_caso, sol_caso);
        ods.getDatosODS(resumen_caso);
        //comboTipoCaso.setValue(ods.getTipoCaso());
        //comboRelacionado.setValue(ods.getRelacionado());
        txtDoc.setText(ods.getSolucion());

    }

    @FXML
    private void insertarTexto(KeyEvent event) {
        //String c =  event.getText();
        //txtDoc.setText(txtDoc.getText() + c.toUpperCase());
        //txtDoc.setText(txtDoc.getText().toUpperCase());
        
        //txtResumen.setText(txtResumen.getText().toUpperCase());
    }

  
}
