/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientapp4;




public class WishDTO {
    private int Wish_id;
    private int User_id;
    private int Item_id;
    private String Wish_Date;
    private String Wish_Time;

    // Constructors
    public WishDTO(int Wish_id, int User_id, int Item_id, String Wish_Date, String Wish_Time) {
        this.Wish_id = Wish_id;
        this.User_id = User_id;
        this.Item_id = Item_id;
        this.Wish_Date = Wish_Date;
        this.Wish_Time = Wish_Time;
    }

    // Getters and Setters

    public int getWishId() {
        return Wish_id;
    }

    public void setWishId(int Wish_id) {
        this.Wish_id=Wish_id;
    }

    public int getUserId() {
        return User_id;
    }

    public void setUserId(int User_id) {
        this.User_id = User_id;
    }

    public int getItemId() {
        return Item_id;
    }

    public void setItemId(int Item_id) {
        this.Item_id= Item_id;
    }

    public String getWishDate() {
        return Wish_Date;
    }

    public void setWishDate(String Wish_Date) {
        this.Wish_Date = Wish_Date;
    }

    public String getWishTime() {
        return Wish_Time;
    }

    public void setWishTime(String Wish_Time) {
        this.Wish_Time = Wish_Time;
    }
}
