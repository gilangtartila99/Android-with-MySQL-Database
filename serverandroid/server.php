<?php

$server = "localhost";
$username = "root";
$password = "";
$database = "employee101";

mysql_connect($server, $username, $password, $database) or die("<h1>Koneksi Mysql Error : </h1>" . mysql_error());
mysql_select_db($database) or die("<h1>Koneksi Kedatabase Error : </h1>" . mysql_error());

@$operasi = $_GET['operasi'];

switch ($operasi) {
    case "view":
        /* Source code untuk Menampilkan Biodata */

        $query_tampil_employee = mysql_query("SELECT * FROM employee_data") or die(mysql_error());
        $data_array = array();
        while ($data = mysql_fetch_assoc($query_tampil_employee)) {
            $data_array[] = $data;
        }
        echo json_encode($data_array);

        break;
    case "insert":
        /* Source code untuk Insert data */
        @$name = $_GET['name'];
        @$surname = $_GET['surname'];
        @$age = $_GET['age'];
        @$username = $_GET['username'];
        @$password = $_GET['password'];
        $query_insert_data = mysql_query("INSERT INTO employee_data (name, surname, age, username, password) VALUES('$name', '$surname', '$age', '$username', '$password')");
        if ($query_insert_data) {
            echo "Data Berhasil Disimpan";
        } else {
            echo "Error Insert Data " . mysql_error();
        }

        break;
    case "get_employee_by_id":
        /* Source code untuk Edit data dan mengirim data berdasarkan id yang diminta */
        @$id = $_GET['id'];

        $query_tampil_employee = mysql_query("SELECT * FROM employee_data WHERE id='$id'") or die(mysql_error());
        $data_array = array();
        $data_array = mysql_fetch_assoc($query_tampil_employee);
        echo "[" . json_encode($data_array) . "]";


        break;
    case "update":
        /* Source code untuk Update Biodata */
        @$name = $_GET['name'];
        @$surname = $_GET['surname'];
        @$age = $_GET['age'];
        @$username = $_GET['username'];
        @$password = $_GET['password'];
        @$id = $_GET['id'];
        $query_update_employee = mysql_query("UPDATE employee_data SET name='$name', surname='$surname', age='$age', username='$username', password='$password' WHERE id='$id'");
        if ($query_update_employee) {
            echo "Update Data Berhasil";
        } else {
            echo mysql_error();
        }
        break;
    case "delete":
        /* Source code untuk Delete Biodata */
        @$id = $_GET['id'];
        $query_delete_employee = mysql_query("DELETE FROM employee_data WHERE id='$id'");
        if ($query_delete_employee) {
            echo "Delete Data Berhasil";
        } else {
            echo mysql_error();
        }

        break;



    case "view_item":
        /* Source code untuk Menampilkan Biodata */

        $query_tampil_item = mysql_query("SELECT * FROM item_data") or die(mysql_error());
        $data_array = array();
        while ($data = mysql_fetch_assoc($query_tampil_item)) {
            $data_array[] = $data;
        }
        echo json_encode($data_array);

        break;
    case "insert_item":
        /* Source code untuk Insert data */
        @$name = $_GET['name'];
        @$type = $_GET['type'];
        $query_insert_data = mysql_query("INSERT INTO item_data (name, type) VALUES('$name', '$type')");
        if ($query_insert_data) {
            echo "Data Berhasil Disimpan";
        } else {
            echo "Error Insert Data " . mysql_error();
        }

        break;
    case "get_item_by_id":
        /* Source code untuk Edit data dan mengirim data berdasarkan id yang diminta */
        @$id = $_GET['id'];

        $query_tampil_item = mysql_query("SELECT * FROM item_data WHERE id='$id'") or die(mysql_error());
        $data_array = array();
        $data_array = mysql_fetch_assoc($query_tampil_item);
        echo "[" . json_encode($data_array) . "]";


        break;
    case "update_item":
        /* Source code untuk Update Biodata */
        @$name = $_GET['name'];
        @$type = $_GET['type'];
        @$id = $_GET['id'];
        $query_update_item = mysql_query("UPDATE item_data SET name='$name', type='$type' WHERE id='$id'");
        if ($query_update_item) {
            echo "Update Data Berhasil";
        } else {
            echo mysql_error();
        }
        break;
    case "delete_item":
        /* Source code untuk Delete Biodata */
        @$id = $_GET['id'];
        $query_delete_item = mysql_query("DELETE FROM item_data WHERE id='$id'");
        if ($query_delete_item) {
            echo "Delete Data Berhasil";
        } else {
            echo mysql_error();
        }

        break;



    case "view_check":
        /* Source code untuk Menampilkan Biodata */

        $query_tampil_check = mysql_query("SELECT * FROM check_data") or die(mysql_error());
        $data_array = array();
        while ($data = mysql_fetch_assoc($query_tampil_check)) {
            $data_array[] = $data;
        }
        echo json_encode($data_array);

        break;
    case "insert_check":
        /* Source code untuk Insert data */
        @$employee_name = $_GET['employee_name'];
        @$item_name = $_GET['item_name'];
        @$status = $_GET['status'];
        $query_insert_data = mysql_query("INSERT INTO check_data (employee_name, item_name, status) VALUES('$employee_name', '$item_name', '$status')");
        if ($query_insert_data) {
            echo "Data Berhasil Disimpan";
        } else {
            echo "Error Insert Data " . mysql_error();
        }

        break;
    case "get_check_by_id":
        /* Source code untuk Edit data dan mengirim data berdasarkan id yang diminta */
        @$id = $_GET['id'];

        $query_tampil_check = mysql_query("SELECT * FROM check_data WHERE id='$id'") or die(mysql_error());
        $data_array = array();
        $data_array = mysql_fetch_assoc($query_tampil_check);
        echo "[" . json_encode($data_array) . "]";


        break;
    case "update_check":
        /* Source code untuk Update Biodata */
        @$employee_name = $_GET['employee_name'];
        @$item_name = $_GET['item_name'];
        @$status = $_GET['status'];
        @$id = $_GET['id'];
        $query_update_check = mysql_query("UPDATE check_data SET employee_name='$employee_name', item_name='$item_name', status='$status' WHERE id='$id'");
        if ($query_update_check) {
            echo "Update Data Berhasil";
        } else {
            echo mysql_error();
        }
        break;
    case "delete_check":
        /* Source code untuk Delete Biodata */
        @$id = $_GET['id'];
        $query_delete_check = mysql_query("DELETE FROM check_data WHERE id='$id'");
        if ($query_delete_check) {
            echo "Delete Data Berhasil";
        } else {
            echo mysql_error();
        }

        break;

    default:
        break;
}
?>