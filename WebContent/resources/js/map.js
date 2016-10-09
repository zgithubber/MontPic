L.mapbox.accessToken = 'pk.eyJ1Ijoiei1tYXBib3hlciIsImEiOiJhMTFiM2VjYjUwYjU1ZThiYWU5ZGMyZDNhMDcxOWY3NSJ9.WMbr3vHC0dcs4NGcpWhDgw';
var map = L.mapbox.map('map', 'mapbox.streets')
    .setView([imagesList[0].mountain_latitude, imagesList[0].mountain_longitude], 7);
var myLayer = L.mapbox.featureLayer().addTo(map);

var geoJson = [];

for(var image in imagesList){
	geoJson.push({
	    type: 'Feature',
	    "geometry": { "type": "Point", "coordinates": [imagesList[image].longitude, imagesList[image].latitude]},
	    "properties": {
	        "image": imagesList[image].url,
	        "mountain": imagesList[image].mountain_name,
	        "icon": {
	            "iconUrl": imagesList[image].url,
	            "iconSize": [65, 40],
	            "iconAnchor": [0, 0],
	            "popupAnchor": [0, 0],
	            "className": "dot"
	        }
		}
	});
}

// Add custom popups to each using our custom feature properties
myLayer.on('layeradd', function(e) {
    var marker = e.layer,
        feature = marker.feature;
	
	marker.setIcon(L.icon(feature.properties.icon));
    // Create custom popup content
    var popupContent =  '<a target="_blank" class="popup" href="' + feature.properties.url + '">' +
                            '<img src="' + feature.properties.image + '" />' +
                            feature.properties.mountain +
                        '</a>';

    // http://leafletjs.com/reference.html#popup
    marker.bindPopup(popupContent,{
        closeButton: false,
        minWidth: 320
    });
});

// Add features to the map
myLayer.setGeoJSON(geoJson);