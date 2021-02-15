<?php

require_once 'connect.php';

   if (isset($_POST['group_code'])) {
        $group_code = $_POST['group_code'];
        $result = mysqli_query($connect, "SELECT group_name FROM group_university WHERE group_name = '$group_code'");
        $code = mysqli_fetch_array($result);
        
        if (!empty($code)) {
            $response['isSuccess'] = 1;
            echo json_encode($response);
        } else {
            $response['isSuccess'] = 0;
            echo json_encode($response);
        }
   }
   
   mysqli_close($connect);
?>