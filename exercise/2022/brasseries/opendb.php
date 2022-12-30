<?php if (isset($_GET['source'])) die(highlight_file(__FILE__, 1)); ?>

<?php

$conn = new mysqli($db_host, $db_user, $db_password, $db_name);
if ($conn->connect_error)
	die("Connection failed: " . $conn->connect_error);

?>