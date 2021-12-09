
package controladores;

import dbconector.DBconexion;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;


import java.sql.DriverManager;
import java.sql.CallableStatement;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class controlador implements Initializable {
    
    @FXML
    private TextField txtcodigo;
    
    @FXML
    private TextField txtapellido;
    
    @FXML
    private TextField txtnombre;
    
    @FXML
    private TextField txtdireccion;
    
    @FXML
    private TextField txtdistrito;
        
    @FXML
    private Button btnregistrar;
    
    @FXML
    private Button btnmostrar;
         
    public void handleButtonAction(MouseEvent event){
        
        if (event.getSource() == btnregistrar){
            registrar();
        }
        if (event.getSource() == btnmostrar) {
            try {
                
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/lab16/vistatabla.fxml")));
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                
                System.err.println(ex.getMessage());
              }
        }
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
    }
    
    public controlador(){
        con = DBconexion.conDB();
    }    
    
    
    
    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    
    private void registrar(){
        
        String codigo = txtcodigo.getText();
        String apellido = txtapellido.getText();
        String nombre = txtnombre.getText();
        String direccion = txtdireccion.getText();
        String distrito = txtdistrito.getText();
        
        int i = 0;
        
        //query sql
        Connection conn = null;
                try {
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Matricula","root","Reyes562");
                    CallableStatement procedimiento = conn.prepareCall("{ call Registrar_Alumnos(?, ?, ?, ?, ?) }");
            
                    procedimiento.setString(1, codigo);
                    procedimiento.setString(2, apellido);
                    procedimiento.setString(3, nombre);
                    procedimiento.setString(4, direccion);
                    procedimiento.setString(5, distrito);
                    procedimiento.execute();
                    procedimiento.close();

                    i++;
                    

                } catch (SQLException e) {
                
                    System.out.println("SQLException: "+e.getMessage());
                    System.out.println("SQLState: "+e.getSQLState());
                    System.out.println("VendorError: "+e.getErrorCode());
                    i = 0;
                }

                if(i > 0){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("NICE");
                    alert.setContentText("Alumno registrado exitosamente");
                    alert.showAndWait();
                    i = 0;
                } else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("OH NO");
                    alert.setContentText("No se pudo agregar al alumnos");
                    alert.showAndWait();
                    i++;
                }
    }   
}
