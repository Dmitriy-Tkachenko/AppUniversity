<?php
    require_once 'connect.php';
    if (isset($_POST['full_name']) && isset($_POST['login']) && isset($_POST['password'])) {
       $full_name = $_POST['full_name'];
       $login = $_POST['login'];
       $password = $_POST['password'];
       $password_hash = password_hash($password, PASSWORD_DEFAULT);
       
       $reg = "INSERT INTO users (user_full_name, login, password) VALUES ('$full_name', '$login', '$password_hash')";
       if (mysqli_query($connect, $reg)) {
            $response['isSuccess'] = 1;
            $response['message'] = "Успешная регистрация!";
            echo (json_encode($response));
        } else {
            $response['isSuccess'] = 0;
            $response['message'] = "Данное имя занято!";
            echo (json_encode($response));
        }
    }
    mysqli_close($connect);
?>