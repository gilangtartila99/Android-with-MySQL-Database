package com.example.gilang.androidmysql;

/**
 * Created by GILANG on 04/06/2016.
 */
public class Item extends Koneksi {
    String URL = "http://192.168.100.6/serverandroid/server.php";
    String url = "";
    String response = "";

    public String tampilItem() {
        try {
            url = URL + "?operasi=view_item";
            System.out.println("URL Tampil Item: " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String insertItem(String name, String type) {

        name = name.replace(" ", "%20");
        type = type.replace(" ", "%20");

        try {
            url = URL + "?operasi=insert_item&name=" + name + "&type=" + type;
            System.out.println("URL Insert Item : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String getItemById(int id) {
        try {
            url = URL + "?operasi=get_item_by_id&id=" + id;
            System.out.println("URL Insert Item : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String updateItem(String id, String name, String type) {

        name = name.replace(" ", "%20");
        type = type.replace(" ", "%20");

        try {
            url = URL + "?operasi=update_item&id=" + id + "&name=" + name + "&type=" + type;
            System.out.println("URL Insert Item : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String deleteItem(int id) {
        try {
            url = URL + "?operasi=delete_item&id=" + id;
            System.out.println("URL Insert Item : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }
}
