<?php
    require_once 'connect.php';
    
    if(isset($_POST['login']) && isset($_POST['password'])) {
        $login = $_POST['login'];
        $password = $_POST['password'];
        
        $validation = mysqli_query($connect, "SELECT login, role_id, password FROM `users` WHERE login = '$login'");
        if (!empty($validation)) {
            $result = mysqli_fetch_array($validation);
            if (password_verify($password, $result['password'])) {
                $response['isSuccess'] = 1;
                $response['role'] = $result['role_id'];
                $response['message'] = "Вход";
                echo (json_encode($response));
            } else {
                $response['isSuccess'] = 0;
                $response['message'] = "Ошибка! Проверьте введенные данные!";
                echo (json_encode($response));
            }
        }
    }
    
     mysqli_close($connect);
?>