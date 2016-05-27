
var dataTable;
var rowsDefault = 500;

$(document).ready(function() {

	performQuery('*:*', 12000);

});

function  performFilter() {
	
	var city = $('#city').val();
	var roomsNumber = $('#roomsNumber').val();
	var rows = $('#rows').val() || rowsDefault;
	
	
	var query = 'city:' + city + ' AND roomsNumber:' + roomsNumber ;
	dataTable.destroy();
	performQuery(query, rows);
}

function  performRange() {
	
	var initialDate = $('#initialDate').val();
	var endDate = $('#endDate').val();
	var rows = $('#rows').val() || rowsDefault;
	
	var query = 'mod_date:['+ initialDate +' TO ' + endDate +']';
	alert(query);
	dataTable.destroy();
	performQuery(query, rows);
}

function  performNumericRange() {
	
	var rangeField = $('#rangeField').val();
	var initialRange = $('#initialRange').val();
	var endRange = $('#endRange').val();
	var rows = $('#rows').val() || rowsDefault;
	
	var query = rangeField + ':['+ initialRange + ' TO ' + endRange +']';
	dataTable.destroy();
	performQuery(query, rows);
}

function performQuery( query, rows ) {
	
	var rentalsUrl = 'http://localhost:8080/trial/rest/' + query + '/' + rows;

	$.ajax({ 

		url: rentalsUrl,
	    type : "GET",
		data: '*:*',
	    dataType: "json",
	    error : function () {
	    	alert("Error calling the service! Details on Tomcat logs. Reloading page");
	    	location.reload();
	    }

	}).done(function(rentals) {
	    	
			var rentalsArray = [];
			for ( var i in rentals) {
				
				var lineArray = [rentals[i].id, rentals[i].city, rentals[i].province , rentals[i].country, rentals[i].zipCode,
				                 rentals[i].type, rentals[i].hasAirCondition,rentals[i].hasGarden, rentals[i].hasPool,
				                 rentals[i].closeToBeach, rentals[i].dailyPrice, rentals[i].currency, rentals[i].roomsNumber];
				
				rentalsArray.push(lineArray);
			}

			dataTable = $('#rentals').DataTable({
	    		data : rentalsArray,
	    		columns : [ {
	    			title : "id"
	    		}, {
	    			title : "city"
	    		}, {
	    			title : "province"
	    		}, {
	    			title : "country."
	    		}, {
	    			title : "zipCode"
	    		}, {
	    			title : "type"
	    		}, {
	    			title : "hasAirCondition"
	    		}, {
	    			title : "hasGarden"
	    		}, {
	    			title : "hasPool"
	    		}, {
	    			title : "closeToBeach"
	    		}, {
	    			title : "dailyPrice"
	    		}, {
	    			title : "currency"
	    		}, {
	    			title : "roomsNumber"
	    		} ]
	    	});
	    });
}