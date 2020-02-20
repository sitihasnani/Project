<?php

		require_once('dbConnect.php');
		
		$sql = "SELECT * FROM topic";
		$check = mysqli_query($con, $sql);
		$res = array();
		
		while($row = mysqli_fetch_array($check)){
			array_push($res,
			array('id'=>$row[0],
			'title'=>$row[1],
			'description'=>$row[2],
			'upvote'=>$row[3],
			'downvote'=>$row[4],
			));
		}
		
		echo json_encode(array("result"=>$res));
		
		mysqli_close($con);

?>