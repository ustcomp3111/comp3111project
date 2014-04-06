<?php 

require("config.inc.php");

if(!empty($_POST)) {
	$output = array();
	$username= $_POST['username'];
	

	$query = "SELECT * FROM event WHERE holder = '$username' ";
	
	$result = mysql_query($query) or die("Could not get response: " . mysql_error());

	while ($row = mysql_fetch_assoc($result))
		$output[] = $row;

	die(json_encode($output));

	mysql_close();

}
else {
	?>
	<h2>Sign in</h2> 
	<form action="event.php" method="post"> 
		Username:<br /> 
		<input type="text" name="username" placeholder="username" />
		<br /><br />
		
		<input type="submit" value="Submit" />
	</form>
	<?php
}

?>