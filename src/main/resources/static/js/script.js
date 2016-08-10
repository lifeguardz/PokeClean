
alertify.set('notifier', 'position', 'top-right');  

// Pokémon Long Id Array
var idArray = [];
var pokemonId =$('#pokemonId').html();

 // jQuery DataTables
 $(document).ready(function() {
  var table = $('#example').DataTable({
    "aoColumnDefs" : [ {
      'bSortable' : false,
      'aTargets' : [ 'nosort' ]
    }, {
      'bSearchable' : false,
      'aTargets' : [ 'nosort' ]
    } ],
    "lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]],
    "pageLength": 10,
    "stateSave": true
  });
  
  var tableList = $('#list').DataTable({
	    "aoColumnDefs" : [ {
	      'bSortable' : false,
	      'aTargets' : [ 'nosort' ]
	    }, {
	      'bSearchable' : false,
	      'aTargets' : [ 'nosort' ]
	    } ],
	    "lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]],
	    "pageLength": 10,
	    "stateSave": true
	  });


  // table select rows
  $('#example tbody').on( 'click', 'tr', function () {
    $(this).toggleClass('w3-khaki');
  } );

  // transfer array method
  $('#transfer').click( function () {
    $('.w3-khaki #nr').each(function(){
      idArray.push($(this).html().split('@')[0]);
    });
    $('#poks').append('<input type="text" style="display: none;" name="longIds" id="longIds" type="text" value="' + idArray + '" />');
    
    $('#btnYes').click( function () {
    	$('.w3-khaki #nr').each(function(){
    		table.row(this).remove().draw(false);
    	 });
	 	var warnMsg = alertify.notify(
	 			'Please wait <i class="fa fa-smile-o" aria-hidden="true"></i>', 0);
	 	
	 	$.ajax({
			url : "transfer",
			dataType: "json",
			data: {'pokemonTransfer' : idArray.toString()},
			timeout : 100000,
			success : function(data) {
				alertify.dismissAll();
				var successMsg = alertify
				.success(
						'<div style="display: flex;"><div class="pogo pokemon-' + pokemonId  + '" ></div><p style="vertical-align: middle;"><b>Successful!</b></div>', 0);
			},
			error : function(e) {
				alertify.dismissAll();
				var errorMsg = alertify.error('<div style="display: flex;"><div class="pogo pokemon-' + pokemonId  + '" ></div><p style="vertical-align: middle;"><b>Error!</b><br/><p>Please refresh the site.<p/></div>', 0);
			},
			done : function(e) {
			}
		});
    });
	 
	 $('#btnNo').click( function () {
	   idArray = [];
	 });
  }); 
});

// Get the Sidenav
var mySidenav = document.getElementById("mySidenav");

// Get the DIV with overlay effect
var overlayBg = document.getElementById("myOverlay");

// Toggle between showing and hiding the sidenav, and add overlay effect
function w3_open() {
  if (mySidenav.style.display === 'block') {
    mySidenav.style.display = 'none';
    overlayBg.style.display = "none";
  } else {
    mySidenav.style.display = 'block';
    overlayBg.style.display = "block";
  }
}

// Close the sidenav with the close button
function w3_close() {
  mySidenav.style.display = "none";
  overlayBg.style.display = "none";
}
