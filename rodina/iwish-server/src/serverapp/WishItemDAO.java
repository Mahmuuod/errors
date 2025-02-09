package serverapp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.json.JSONArray;
import org.json.JSONObject;
import serverapp.DatabaseConnection;

public class WishItemDAO {
    private Connection conn;
    
    public WishItemDAO() {
        try { this.conn = DatabaseConnection.getConnection(); }
        catch (Exception e) { e.printStackTrace(); }
    }
    
    public void addWish(int User_id, int Item_id, String Wish_Date, String Wish_Time) throws SQLException {
        String sql = "INSERT INTO wish (User_id, Item_id, Wish_Date, Wish_Time) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1,  User_id);
            stmt.setInt(2, Item_id);
            stmt.setString(3, Wish_Date);
            stmt.setString(4, Wish_Time);
            stmt.executeUpdate();
        }
    }

    public void deleteWish(int wishId) throws SQLException {
        String sql = "DELETE FROM wish WHERE Wish_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, wishId);
            stmt.executeUpdate();
        }
    }
     public JSONArray getAllItems() throws SQLException {
        String sql = "SELECT * FROM item";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            JSONArray itemsArray = new JSONArray();
            while (rs.next()) {
                JSONObject item = new JSONObject();
                item.put("itemId", rs.getInt("Item_id"));
                item.put("name", rs.getString("Name"));
                item.put("category", rs.getString("Category"));
                item.put("price", rs.getDouble("Price"));
                itemsArray.put(item);
            }
            return itemsArray;
        }
    }
}

