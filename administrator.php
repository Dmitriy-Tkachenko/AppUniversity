<?php
    require_once 'connect.php';
    
    if (isset($_POST['message'])) {
        if ($_POST['message'] == 'allUsers') {
            $users = mysqli_query($connect, "SELECT id, login, role_id FROM users");
            
            $response = array();
            
            while ($result = mysqli_fetch_array($users)) {
                array_push($response,
                array(
                    'id' =>$result['id'],
                    'login' =>$result['login'],
                    'role' =>$result['role_id'])
                    );
            }
            
            echo json_encode($response);
        }
       
        if ($_POST['message'] == 'roleChange') {
           $id = $_POST['id'];
           $role_id = $_POST['role_id'];
           
           if (mysqli_query($connect, "UPDATE users SET role_id = '$role_id' WHERE id = '$id'")) {
               $response['isSuccess'] = 1;
           } else $response['isSuccess'] = 0;
           
           echo json_encode($response);
        }
        
        if ($_POST['message'] == 'searchUser') {
            $username = $_POST['username'];
            
            $user = mysqli_query($connect, "SELECT id, login, role_id FROM users WHERE login = '$username'");
            $result = mysqli_fetch_array($user);
            
            if (!empty($result)) {
                $response['isSuccess'] = '1';
                $response['id'] = $result['id'];
                $response['login'] = $result['login'];
                $response['role'] = $result['role_id'];
                echo json_encode($response);
            } else {
                $response['isSuccess'] = '0';
                echo json_encode($response);
            }
            
        }
        
        if ($_POST['message'] == 'deleteUser') {
           $id = $_POST['id'];
           
           if (mysqli_query($connect, "DELETE FROM users WHERE id = '$id'")) {
               $response['isSuccess'] = 1;
           } else $response['isSuccess'] = 0;
           
           echo json_encode($response);
        }
    }
    
        
    mysqli_close($connect);
?>