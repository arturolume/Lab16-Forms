package controladores;

import dbconector.DBconexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lab16.usuarios;

public class controladortabla implements Initializable{

    @FXML
    private Button btn_regresar;
    
    @FXML
    private TextField filterField;
    
    @FXML
    private TableColumn<usuarios, String> col_apellido;

    @FXML
    private TableColumn<usuarios, String> col_codigo;

    @FXML
    private TableColumn<usuarios, String> col_direccion;

    @FXML
    private TableColumn<usuarios, String> col_distrito;

    @FXML
    private TableColumn<usuarios, String> col_nombre;

    @FXML
    private TableView<usuarios> table_alumnos;
    
    @FXML
    void handleButtonAction(MouseEvent event) {
        
        if (event.getSource() == btn_regresar){
            try {
                
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/lab16/vistaregistrar.fxml")));
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                
                System.err.println(ex.getMessage());
              }
        }

    }
    
    ObservableList<usuarios> oblist = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        /*
        try(Connection con = DBconexion.conDB();
            CallableStatement procedimiento = con.prepareCall("{ call Mostrar_Alumnos(?, ?, ?, ?, ?) }");
            //ResultSet rs = con.createStatement().executeQuery("select * from alumnos");
            ResultSet rs = procedimiento.executeQuery();){
            
            while(rs.next()){
                
            }
            
        }catch (SQLException e){
            e.printStackTrace();
        }
        */
        try {
            Connection con = DBconexion.conDB();
            ResultSet rs = con.createStatement().executeQuery("select * from alumnos");
            while(rs.next()){
                oblist.add(new usuarios(rs.getString("codigo"), rs.getString("apellidos"), 
                        rs.getString("nombres"), rs.getString("direccion"), rs.getString("distrito")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        col_codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        col_apellido.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        col_nombre.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        col_direccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        col_distrito.setCellValueFactory(new PropertyValueFactory<>("distrito"));
        
        table_alumnos.setItems(oblist);
        
        FilteredList<usuarios> filtro = new FilteredList<>(oblist, b -> true);
        
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filtro.setPredicate(employee -> {
								
                            if (newValue == null || newValue.isEmpty()) {
				return true;
                            }
				
                            String lowerCaseFilter = newValue.toLowerCase();
				
                            if (employee.getCodigo().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
				return true; //busqueda por codigo
                            } else if (employee.getApellidos().toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true; //busqueda por apellido
                            }else if (employee.getNombres().toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true; //busqueda por nombre
                            }else if (employee.getDireccion().toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true; //busqueda por direccion
                            }else if (employee.getDistrito().toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true; //busqueda por distrito
                            }
                            else if (String.valueOf(employee.getApellidos()).indexOf(lowerCaseFilter)!=-1)
				return true;
                            else  
				return false;
			});
		});
                
        SortedList<usuarios> sortedData = new SortedList<>(filtro);
        sortedData.comparatorProperty().bind(table_alumnos.comparatorProperty());
        table_alumnos.setItems(sortedData);
        

        
    }
}