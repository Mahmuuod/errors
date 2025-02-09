/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientapp4;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class WishListController implements Initializable {

    private TableView<WishDTO> wishTable;
    @FXML
    private Button wishlistbtn;
    @FXML
    private Button friendrequestbtn;
    @FXML
    private Button friendlistbtn;
    @FXML
    private Button contributionlistbtn;
    @FXML
    private Button addbalancebtn;
    @FXML
    private Button notificationbtn;
    @FXML
    private Button logoutbtn;
    @FXML
    private TableView<?> wishlisttable;
    @FXML
    private TableColumn<?, ?> Wishidbtn;
    @FXML
    private TableColumn<?, ?> Itemidtn;
    @FXML
    private TableColumn<?, ?> useridbtn;
    @FXML
    private TableColumn<?, ?> wishidbtn;
    @FXML
    private TableColumn<?, ?> s;
    @FXML
    private Button updatebtn;
    @FXML
    private Button Deletebtn;
  
 @FXML
public void deleteWish() {
    WishDTO selectedWish = wishTable.getSelectionModel().getSelectedItem();
    if (selectedWish == null) return;

    JSONObject request = new JSONObject();
    request.put("action", "delete");
    request.put("Wish_id", selectedWish.getWishId());

    JSONObject response = ClientSocket.sendRequest(request);
    System.out.println("Server Response: " + response.getString("message"));
}


 @FXML
public void updateWish(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("Item.fxml")); 
    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    stage.setScene(new Scene(root));
    stage.show();
    
}
 @FXML
public void itemsBtn (ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("Item.fxml")); 
    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    stage.setScene(new Scene(root));
    stage.show();
    
}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
