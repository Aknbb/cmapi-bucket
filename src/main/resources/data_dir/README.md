This directory contains cmapi messages used by cmapibucket.

To change default cmapi data dir path, add 'CMAPI_DATA_DIR' environment variable.
To add cmapi cmapi file manually, please consider the structure.

1- Data Dir Structure: 
	- Channel Name
		* Title of cmapi file
		* Title of cmapi file
		...
		
Example:	
	- map.feature.plot
		* Circle on Ankara
		* Square with unitType
		
2- Cmapi File Structure:
	{
	 "channel": String - channel of the cmapi,
	 "title": String - title of the cmapi,
	 "labels": [String*] - labels of the cmapi,
	 "cmapi": Object - payload of the cmapi
	}
	
Example:	
	- Point on Ankara
		{
		 "channel": "map.feature.plot",
		 "title": "Point on Ankara",
		 "labels": [
			"example cmapi",
			"plot point",
			"point geojson"
		 ],
		 "cmapi": {
			"overlayId": "test",
			"featureId": "testId",
			"feature": {
				"type": "Feature",
				"geometry": {
					"type": "Point",
					"coordinates": [
						32,
						39
					]
				}
			}
		 }
		}