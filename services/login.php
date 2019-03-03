<?php 

	require "conn.php";
	$username = $_POST["username"];
	$password = $_POST["password"];
	// $username = "test";
	// $password = "test";

	$mysql_query = "SELECT * FROM `accountslist` WHERE Username LIKE '$username' AND Password LIKE '$password'";

	$result = mysqli_query($conn,$mysql_query);
	if(mysqli_num_rows($result)>0){
		echo "SuccessL";#L kao logovanje, uspesno logovanje
	}
	else{
		echo "FailedL";#Neuspesno logovanje
	}

 ?>