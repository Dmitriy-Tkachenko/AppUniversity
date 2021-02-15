<?php
    $connect = // Подключение к БД
    if (mysqli_connect_errno($connect)) {
        echo "Failed to connect to MySQL: " . mysqli_connect_error(); 
        exit();
    }
?>