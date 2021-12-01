/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testetpiprob;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 *
 * @author Junior
 */
public class TesteTPIProb extends Application {
    
    Stage window;
    Scene scene1,scene2,scene3, scene4;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    private final TableView<Record> tableView = new TableView<>();
 
    private final ObservableList<Record> dataList = FXCollections.observableArrayList();
    
    public class Record {
        private SimpleStringProperty f1, f2;
 
        public String getF1() {
            return f1.get();
        }
 
        public String getF2() {
            return f2.get();
        }
 
 
        Record(String f1, String f2) {
            this.f1 = new SimpleStringProperty(f1);
            this.f2 = new SimpleStringProperty(f2);
        }
 
    }
    
    private void readCSV() {
 
        String CsvFile = "nomes.csv";
        String FieldDelimiter = ",";
 
        BufferedReader br;
 
        try {
            br = new BufferedReader(new FileReader(CsvFile));
 
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(FieldDelimiter, -1);
 
                Record record = new Record(fields[0], fields[1]);
                dataList.add(record);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TesteTPIProb.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TesteTPIProb.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException, IOException {
        window = primaryStage;
        // Importação do arquivo nomes.csv
        readCSV();
        TableColumn columnF1 = new TableColumn("Nome");
        columnF1.setCellValueFactory(
                new PropertyValueFactory<>("f1")); 
        TableColumn columnF2 = new TableColumn("Sexo");
        columnF2.setCellValueFactory(
                new PropertyValueFactory<>("f2"));
        dataList.remove(0);
        
        tableView.setItems(dataList);
        tableView.getColumns().addAll(
                columnF1, columnF2);
        
        // Janela Principal
        Button btn1 = new Button();
        Button btn2 = new Button();
        Button btn3 = new Button();
        Label label1 = new Label("Janela principal");
        btn1.setText("Inserir Nome");
        btn2.setText("Assumir genero");
        btn3.setText("Tabela de nomes");
        
        btn1.setOnAction(e -> window.setScene(scene2));
        btn2.setOnAction(e -> window.setScene(scene3));
        btn3.setOnAction(e -> window.setScene(scene4));
        VBox layout1 = new VBox(20);
        layout1.setAlignment(Pos.CENTER);
        layout1.getChildren().addAll(label1, btn1, btn2, btn3);
        scene1 = new Scene(layout1,400,400);
        
        
        // Janela de inserção de nome e genero
        Label label2 = new Label("Inserindo nome e genero");
        Label texto1 = new Label("Insira o nome:");
        Label texto2 = new Label("Genero:");
        Label mensagem1 = new Label("");
        // teste de pegar um nome
        // Label testexd = new Label(columnF1.getCellData(6).toString());
        TextField txtField = new TextField();
        txtField.setMaxWidth(120);
        Button voltarMenu1 = new Button();
        voltarMenu1.setText("Voltar");
        voltarMenu1.setOnAction(e -> window.setScene(scene1));
        ComboBox cbx1 = new ComboBox();        
        cbx1.getItems().addAll("M","F");
        cbx1.getSelectionModel().selectFirst();
        Button inserirNome = new Button();
        inserirNome.setText("Inserir");
        inserirNome.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                if(!"".equals(txtField.getText()))
                {
                    Record record2 = new Record(txtField.getText(), cbx1.getValue().toString());
                    dataList.add(record2);
                    mensagem1.setText(txtField.getText() + ", " + cbx1.getValue().toString() + " Inserido com sucesso!");
                    txtField.setText("");
                }
                else{
                    mensagem1.setText("Erro: Nome está em branco!");
                }
            }
        });
        VBox layout2 = new VBox(20);
        layout2.setAlignment(Pos.CENTER);
        layout2.getChildren().addAll(label2,texto1, txtField, texto2, cbx1, mensagem1, inserirNome, voltarMenu1);
        scene2 = new Scene(layout2,400,400);
        
        // Janela de inserção de nome e genero
        Label label4 = new Label("Fazer a predição de 3 nomes:");
        Label nome1 = new Label("Insira o nome 1:");
        Label nome2 = new Label("Insira o nome 2:");
        Label nome3 = new Label("Insira o nome 3:");
        Label mensagem2 = new Label("");
        TextField txtField1 = new TextField();
        TextField txtField2 = new TextField();
        TextField txtField3 = new TextField();
        txtField1.setMaxWidth(120);
        txtField2.setMaxWidth(120);
        txtField3.setMaxWidth(120);
        
        Button voltarMenu2 = new Button();
        voltarMenu2.setText("Voltar");
        voltarMenu2.setOnAction(e -> window.setScene(scene1));
        Button predict = new Button();
        predict.setText("Fazer Predição");
        
        predict.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
            }
        });
        VBox layout3 = new VBox(20);
        
        layout3.setAlignment(Pos.CENTER);
        layout3.getChildren().addAll(label4, nome1, txtField1, nome2, txtField2, nome3, txtField3, mensagem2, predict, voltarMenu2);
        scene3 = new Scene(layout3,400,400);
        
        Label texto6 = new Label("Relação de nomes");
        Button voltarMenu3 = new Button();
        voltarMenu3.setText("Voltar");
        voltarMenu3.setOnAction(e -> window.setScene(scene1));
        VBox layout4 = new VBox(20);
        layout4.setAlignment(Pos.CENTER);
        layout4.getChildren().addAll(texto6, tableView, voltarMenu3);
        
        
        scene4 = new Scene(layout4,400, 400);
        
        window.setScene(scene1);
        window.setTitle("Naive Bayes para Predição de Nomes");
        window.show();
        
    }
    
}
