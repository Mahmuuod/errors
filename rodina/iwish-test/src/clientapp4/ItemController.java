package clientapp4;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

public class ItemController implements Initializable {

    @FXML
    private Button itemsbtn;
    @FXML
    private Button MyWishlistbtn;

    @FXML
    private Button AddItembtn;

    private TableColumn<ItemDTO, String> nameColumn;

    @FXML
    private TableView<ItemDTO> itemTable;
    @FXML
    private AnchorPane Item_id;
    @FXML
    private TableColumn<ItemDTO, String> Name;
    @FXML
    private TableColumn<ItemDTO, String> Category;
    @FXML
    private TableColumn<ItemDTO, Integer> Price;
    @FXML
    private TableColumn<ItemDTO, Integer> ItemId;

    private void fetchItems() {
        Task<ObservableList<ItemDTO>> task = new Task<ObservableList<ItemDTO>>() {
            @Override
            protected ObservableList<ItemDTO> call() {
                ObservableList<ItemDTO> itemList = FXCollections.observableArrayList();

                try {
                    JSONObject request = new JSONObject();
                    request.put("action", "fetch_items");

                    // 🔹 Make sure this call is NOT blocking the UI
                    JSONObject response = ClientSocket.sendRequest(request);

                    if (response != null && response.has("items")) {
                        JSONArray itemsArray = response.getJSONArray("items");
                        for (int i = 0; i < itemsArray.length(); i++) {
                            JSONObject obj = itemsArray.getJSONObject(i);
                            ItemDTO item = new ItemDTO();
                            item.setItem_id(obj.getInt("Item_id"));
                            item.setName(obj.getString("Name"));
                            item.setCategory(obj.getString("Category"));
                            item.setPrice(obj.getDouble("Price"));
                            itemList.add(item);
                        }
                    }
                } catch (Exception e) {
                    System.err.println("Error fetching items: " + e.getMessage());
                    e.printStackTrace();
                }

                return itemList;
            }
        };

        // 🔹 Ensure UI updates happen on the JavaFX Application Thread
        task.setOnSucceeded(event -> {
            itemTable.setItems(task.getValue());
        });

        task.setOnFailed(event -> {
            System.err.println("Task failed: " + task.getException().getMessage());
            task.getException().printStackTrace();
        });

        // 🔹 Run in background to prevent UI freezing
        Thread backgroundThread = new Thread(task);
        backgroundThread.setDaemon(true); // Allow JavaFX to exit when the window closes
        backgroundThread.start();
    }

    @FXML
    public void addItemToWishList() {
        ItemDTO selectedItem = itemTable.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            return;
        }

        JSONObject request = new JSONObject();
        request.put("action", "add");
        request.put("User_id", 1); // Example user
        request.put("Item_id", selectedItem.getItemId());
        request.put("Wish_Date", "2025-02-05");
        request.put("Wish_Time", "10:30 AM");

        JSONObject response = ClientSocket.sendRequest(request);
        System.out.println("Server Response: " + response.getString("message"));
    }

    @FXML
    public void MyWishListBtn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("WishList.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }

    private ObservableList<ItemDTO> wishlistData = FXCollections.observableArrayList();

    public void initialize(URL location, ResourceBundle resources) {
        Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        Category.setCellValueFactory(new PropertyValueFactory<>("Category"));
        Price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        ItemId.setCellValueFactory(new PropertyValueFactory<>("ItemId"));
        
              wishlistData.add(new ItemDTO(123, "s", 123, "2025-02-09"));
        wishlistData.add(new ItemDTO(2, "s", 124, "2025-02-10"));
        wishlistData.add(new ItemDTO(3, "s", 125, "2025-02-11"));

        // Bind the TableView to the ObservableList
        itemTable.setItems(wishlistData);

    }
}
