	var map = new Array();
	var markersArray = new Array();

	function initialize(num,lat,lon) {
		var centar = new google.maps.LatLng(lat,lon);
		var mapOptions = {
			zoom: 12,
			center: centar,
			mapTypeId: google.maps.MapTypeId.ROADMAP
		};
		
		map[num] = new Array();
		
		map[num] =  new google.maps.Map(document.getElementById("map"+num), mapOptions);

		google.maps.event.addListener(map[num], 'click', function(event) {
			addMarker(event.latLng,num);
		});
	}
	
	function addMarker(location,num,lat,lon) {
		clearOverlays(num);
		
		if( lat && lon )
			var pos = new google.maps.LatLng(lat,lon);
		else
			var pos = location;
		
		marker = new google.maps.Marker({
			position: pos,
			map: map[num]
		});
		
		document.getElementById('lon_'+num).value=pos.lng();
		document.getElementById('lat_'+num).value=pos.lat();
		
		markersArray[num] = new Array();
		markersArray[num].push(marker);
	}

	// Removes the overlays from the map, but keeps them in the array
	function clearOverlays(num) {
		if (markersArray) {
			for (i in markersArray[num]) {
				markersArray[num][i].setMap(null);
			}
		}
	}
	
	function start_gmap()
	{
		for(var i=1; i<=1; i++)
		{
			if( $('#map'+i).length )
			{
				if($('#lat_'+i).val() == ''){
					document.getElementById('lat_'+i).value = 45.81348650;
				}
				if($('#lon_'+i).val() == ''){
					document.getElementById('lon_'+i).value = 15.93017578;
				}
				initialize(i,$('#lat_'+i).val(),$('#lon_'+i).val());
				addMarker(false,i,$('#lat_'+i).val(),$('#lon_'+i).val());
			}
		}
	}