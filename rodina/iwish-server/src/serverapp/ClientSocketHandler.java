/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import org.json.JSONObject;

/**
 *
 * @author LENOVO
 */
public class ClientSocketHandler {

    private Socket socket;

    public ClientSocketHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() throws SQLException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            String request = in.readLine();
            JSONObject json = new JSONObject(request);

            String action = json.getString("action");
            WishItemDAO dao = new WishItemDAO();
            JSONObject response = new JSONObject();

            if (action.equals("add")) {
                dao.addWish(json.getInt("User_id"), json.getInt("Item_id"), json.getString("Wish_Date"), json.getString("Wish_Time"));
                response.put("message", "Item added successfully");
            } else if (action.equals("delete")) {
                dao.deleteWish(json.getInt("Wish_id"));
                response.put("message", "Wish deleted successfully");
            } else if (action.equals("fetch_items")) {
                response.put("items", dao.getAllItems());
            }
            out.println(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void start() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
