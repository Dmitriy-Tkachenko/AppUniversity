<?php
    require_once 'connect.php';
    
   if (isset($_POST['group_code'])) {
        $group_code = $_POST['group_code'];
        
        $result = mysqli_query($connect, "SELECT id FROM group_university WHERE group_name = '$group_code'");
        $group_id = mysqli_fetch_array($result);
        $timetable = mysqli_query($connect, "SELECT * FROM timetable WHERE group_id = '$group_id[id]'");
        
        $response = array();
        
        while ($result = mysqli_fetch_array($timetable)) {
            $select = mysqli_query($connect, "SELECT day_name FROM day WHERE id = '$result[day_id]'");
            $day = mysqli_fetch_array($select);
            
            $select = mysqli_query($connect, "SELECT audience_name, number_seats FROM audience WHERE id = '$result[audience_id]'");
            $audience = mysqli_fetch_array($select);
            
            $select = mysqli_query($connect, "SELECT teacher_full_name FROM teacher WHERE id = '$result[teacher_id]'");
            $teacher = mysqli_fetch_array($select);
            
            $select = mysqli_query($connect, "SELECT DATE_FORMAT(class_start_time, '%H:%i') AS class_start_time, DATE_FORMAT(class_end_time, '%H:%i') AS class_end_time FROM class_time WHERE id = '$result[class_time_id]'");
            $class_time = mysqli_fetch_array($select);
            
            $select = mysqli_query($connect, "SELECT discipline_name FROM discipline WHERE id = '$result[discipline_id]'");
            $discipline = mysqli_fetch_array($select);
            
            $select = mysqli_query($connect, "SELECT lesson_type_name FROM lesson_type WHERE id = '$result[lesson_type_id]'");
            $lesson_type = mysqli_fetch_array($select);
            
            array_push($response,
            array(
                'day_name' =>$day['day_name'],
                'class_start_time' =>$class_time['class_start_time'],
                'class_end_time' =>$class_time['class_end_time'],
                'audience_name' =>$audience['audience_name'],
                'discipline_name' =>$discipline['discipline_name'],
                'lesson_type_name' =>$lesson_type['lesson_type_name'],
                'teacher_full_name' =>$teacher['teacher_full_name'])
                );
        }
        echo json_encode($response);
    }  
        
    mysqli_close($connect);
?>