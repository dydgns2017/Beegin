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
$trace=0;
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

/////////////////////////////////////
// login service
$services["login"] = "_login";
function _login(){
    s00_log ("Start ".__FUNCTION__);
    $id = $_POST["id"];
    $pw = $_POST["pw"];
    outputJSON(array("id"=>$id, "pw"=>$pw), "success");
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