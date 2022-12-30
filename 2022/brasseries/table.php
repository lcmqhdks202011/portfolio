<?php if (isset($_GET['source'])) die(highlight_file(__FILE__, 1)); ?>


<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title></title>
	<style>

	</style>
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
</head>
<body>
	<table id="myTable">

			<?php

			include("config.php");
			include("opendb.php");


			$res = mysqli_query($conn, "SELECT * FROM $db_table;");
			$col_name = mysqli_fetch_assoc($res);

			//thead
			echo '<thead><tr>';
			foreach ($col_name as $key=>$value) {
				echo '<td>',utf8_encode($key), '</td>';
			}
			echo '</tr></thead><tbody>';

			//tbody
			while( $row = mysqli_fetch_assoc($res) )  {
				echo '<tr>';
				foreach ($row as $key=>$value) {
					echo '<td>',utf8_encode($value), '</td>';
				}
				echo '</tr>';
			}

			//tfoot
			echo '</tbody><tfoot>';
			foreach ($col_name as $key=>$value) {
				echo '<td>',utf8_encode($key), '</td>';
			}
			echo '</tr></tfoot>';


			mysqli_free_result($res);
			include('closedb.php');

			?>
	</table>
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript">
		$(document).ready( function () {
			$('#myTable').DataTable();
		} );
	</script>
</body>
</html>