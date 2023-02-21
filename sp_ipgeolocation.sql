create or replace procedure sp_ipgeolocation()
language plpgsql
as $$

declare
	reg_gl geo_location%ROWTYPE;
	
	_ip_from bigint;
	_ip_to bigint;

begin
	CREATE INDEX IF NOT EXISTS idx_geo_location_data
		ON geo_location_data(country_code, country, region, city, latitude, longitude, time_zone, status);

	INSERT INTO geo_location
		(country_code, country, region, city, latitude, longitude, time_zone, total_ranges, status)
	SELECT country_code, country, region, city, latitude, longitude, time_zone, COUNT(*), 'L'
		FROM geo_location_data
		WHERE status = 'L'
		GROUP BY country_code, country, region, city, latitude, longitude, time_zone;
	
	FOR reg_gl IN SELECT * FROM geo_location WHERE status = 'L' LOOP 
		INSERT INTO geo_location_ip
		(ip_from, ip_to, fk_geo_location)	
		SELECT ip_from, ip_to, reg_gl.id
		FROM geo_location_data
		WHERE country_code = reg_gl.country_code
		AND country = reg_gl.country
		AND region = reg_gl.region
		AND city = reg_gl.city
		AND latitude = reg_gl.latitude
		AND longitude = reg_gl.longitude
		AND time_zone = reg_gl.time_zone
		AND status = 'L';
	END LOOP;
	
	UPDATE geo_location_data SET status = 'V' WHERE status = 'L';
	UPDATE geo_location SET status = 'V' WHERE status = 'L';
	
end; $$