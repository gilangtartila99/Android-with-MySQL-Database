package com.example.gilang.androidmysql;

/**
 * Created by GILANG on 04/06/2016.
 */
public class Check extends Koneksi {
    String URL = "http://192.168.100.6/serverandroid/server.php";
    String url = "";
    String response = "";

    public String tampilCheck() {
        try {
            url = URL + "?operasi=view_check";
            System.out.println("URL Tampil Check: " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String insertCheck(String employee_name, String item_name, String status) {

        employee_name = employee_name.replace(" ", "%20");
        item_name = item_name.replace(" ", "%20");
        status = status.replace(" ", "%20");

        try {
            url = URL + "?operasi=insert_check&employee_name=" + employee_name + "&item_name=" + item_name + "&status=" + status;
            System.out.println("URL Insert Check : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String getCheckById(int id) {
        try {
            url = URL + "?operasi=get_check_by_id&id=" + id;
            System.out.println("URL Insert Check : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String updateCheck(String id, String employee_name, String item_name, String status) {

        employee_name = employee_name.replace(" ", "%20");
        item_name = item_name.replace(" ", "%20");
        status = status.replace(" ", "%20");

        try {
            url = URL + "?operasi=update_check&id=" + id + "&employee_name=" + employee_name + "&item_name=" + item_name + "&status=" + status;
            System.out.println("URL Insert Check : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String deleteCheck(int id) {
        try {
            url = URL + "?operasi=delete_check&id=" + id;
            System.out.println("URL Insert Check : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }
}
