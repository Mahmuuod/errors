package clientapp4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import org.json.JSONObject;

public class ClientSocket {
  
    public static JSONObject sendRequest(JSONObject json) {
        try (Socket socket = new Socket("localhost", 5000);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            
            out.println(json.toString()); // Send JSON request
            return new JSONObject(in.readLine()); // Receive JSON response
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
