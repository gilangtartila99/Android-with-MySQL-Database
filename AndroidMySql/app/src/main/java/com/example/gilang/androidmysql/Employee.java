package com.example.gilang.androidmysql;

/**
 * Created by GILANG on 04/06/2016.
 */
public class Employee extends Koneksi {
    String URL = "http://192.168.100.6/serverandroid/server.php";
    String url = "";
    String response = "";

    public String tampilEmployee() {
        try {
            url = URL + "?operasi=view";
            System.out.println("URL Tampil Employee: " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String insertEmployee(String name, String surname, String age, String username, String password) {

        name = name.replace(" ", "%20");
        surname = surname.replace(" ", "%20");

        try {
            url = URL + "?operasi=insert&name=" + name + "&surname=" + surname + "&age=" + age + "&username=" + username + "&password=" + password;
            System.out.println("URL Insert Employee : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String getEmployeeById(int id) {
        try {
            url = URL + "?operasi=get_employee_by_id&id=" + id;
            System.out.println("URL Insert Employee : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String updateEmployee(String id, String name, String surname, String age, String username, String password) {

        name = name.replace(" ", "%20");
        surname = surname.replace(" ", "%20");

        try {
            url = URL + "?operasi=update&id=" + id + "&name=" + name + "&surname=" + surname + "&age=" + age + "&username=" + username + "&password=" + password;
            System.out.println("URL Insert Employee : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String deleteEmployee(int id) {
        try {
            url = URL + "?operasi=delete&id=" + id;
            System.out.println("URL Insert Employee : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }
}
