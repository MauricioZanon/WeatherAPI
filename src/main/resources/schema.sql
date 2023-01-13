DROP TABLE IF EXISTS current_condition;  

CREATE TABLE current_condition (  
	nombre_ciudad VARCHAR PRIMARY KEY,  
	local_observation_date_time VARCHAR NOT NULL,  
	epoch_time VARCHAR NOT NULL,  
	weather_text VARCHAR NOT NULL,  
	weather_icon INTEGER NOT NULL,  
	has_precipitation BOOLEAN NOT NULL,  
	precipitation_type VARCHAR NOT NULL,  
	is_day_time BOOLEAN NOT NULL,  
	mobile_link VARCHAR NOT NULL,  
	link VARCHAR NOT NULL
);  