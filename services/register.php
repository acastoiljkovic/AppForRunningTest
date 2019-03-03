<?php
	require "conn.php";
	$username = $_POST["username"];
	$password = $_POST["password"];
	// $username = "test3";
	// $password = "test";

	$mysql_query = "INSERT INTO `accountslist`(`Username`, `Password`) VALUES ('$username','$password')";

	$result = mysqli_query($conn,$mysql_query);
	if($result){
		echo "SuccessR";#R kao registracija, uspesna registracija
	}
	else{
		echo "FailedR";#Neuspesna registracija
	}
?>