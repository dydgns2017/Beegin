<?php
//  outputJSON, design pattern....;;;;
function outputJSON($msg, $status = 'error'){

    if ($status == 'error') error_log (print_r($msg, true)." in ".__FILE__); 
    
    header('Content-Type: application/json');
    die(json_encode(array(
        'data' => $msg,
        'status' => $status
    )));
}

// $trace=yes;
$trace=false;
function s00_log($msg) {
    global $trace;
    if ($trace) error_log($msg);
} 
/////////////////////////////////////
// null service
$services['test'] = '_test';
function _test() { 
    s00_log ("Start ".__FUNCTION__);
    throw new Exception ( __FILE__.'is Available.');
};

// dir config
$json_data_dir = "./json_data";
/////////////////////////////////////
// login service
$services["login"] = "_login";
function _login(){
    global $json_data_dir;
    s00_log ("Start ".__FUNCTION__);
    $id = $_POST["id"];
    $pw = $_POST["pw"];
    outputJSON(array("id"=>$id, "pw"=>$pw), "success");
}
/////////////////////////////////////
// signup Service
$services["signup"] = "_signup";
function _signup(){
    global $json_data_dir;
    $fullname = $_POST["fullname"];
    $username = $_POST["username"];
    $email = $_POST["email"];
    $password = $_POST["password"];
    $passagain = $_POST["passagain"];
    error_log("$fullname : $username : $email : $password : $passagain ");
    if ( $password != $passagain ) outputJSON("password is wrong");
    
    // prev task
    // JSON 파일이 하나도 없을 경우
    if ( !(file_exists("$json_data_dir/loginData.json")) ){
        $array_data = array("all_count"=>1);
        array_push($array_data, array("fullname"=>$fullname, 
                                       "username"=>$username, 
                                       "email"=>$email, 
                                       "password"=>$password, 
                                       "passagain"=>$passagain));
    }

    // JSON 파일이 있는 경우
    if ( file_exists("$json_data_dir/loginData.json") ){
        $array_data = json_decode(file_get_contents("$json_data_dir/loginData.json"), true);
        // 계정 중복 확인
        $count = $array_data["all_count"];
        for ($i=0; $i < $count; $i++) { 
            # code...
            if ( $array_data[$i]["username"] == $username ) outputJSON("existsUser");
        }
        error_log("is it start?");
        $array_data["all_count"] = $array_data["all_count"]+1;
        array_push($array_data, array("fullname"=>$fullname, 
                                       "username"=>$username, 
                                       "email"=>$email, 
                                       "password"=>$password, 
                                       "passagain"=>$passagain));
    }
    // JSON 저장
    file_put_contents("$json_data_dir/loginData.json", json_encode($array_data));
    outputJSON("ok", "success");
}

$func = isset($_POST['func'])?$_POST["func"]:"test";

try {	
    call_user_func( $services[$func] );
    //s00_log2(4, print_r($services,true));
} catch (Exception $e) {
    s00_log($e->getLine().'@'.__FILE__."::".$e->getMessage());
    s00_log(print_r($e->getTrace(),true));
    outputJSON($e->getMessage());
}
?>