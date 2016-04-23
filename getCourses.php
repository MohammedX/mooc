<?php 
	include 'Connection.php';
	
	$sql = "SELECT * FROM `course`";
	$result = mysqli_query($con, $sql) or die("Error in Selecting " . mysqli_error($con));
	
	$num_rows = mysqli_num_rows($result);
	
	//create an array
	$AdData = array();
	if ($num_rows > 0){
		while ($row = mysqli_fetch_assoc($result)) {
			$AdData[] = $row; 
		}
		echo json_encode($AdData);
		}else{
		echo "nodata";
	}
	
	//close the db connection
	mysqli_close($con);
?>