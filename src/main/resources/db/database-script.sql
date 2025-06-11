-- 1) Create the database (if not exists)
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'FlightDB')
BEGIN
    CREATE DATABASE FlightDB
    COLLATE Latin1_General_100_CI_AS_SC_UTF8;
END
GO

USE FlightDB;

GO 


IF NOT EXISTS (SELECT * FROM  sys.objects WHERE object_id = OBJECT_ID('flights') AND type = 'U')
BEGIN 
	CREATE TABLE dbo.flights (
	  id BIGINT IDENTITY(1, 1) NOT NULL PRIMARY KEY, 
	  origin NVARCHAR(100) NOT NULL, 
	  destination NVARCHAR(100) NOT NULL, 
	  departureDate DATE NOT NULL, 
	  passengers INT NOT NULL, 
	  timeRange NVARCHAR(50) NULL, 
	  specificAirline NVARCHAR(100) NULL, 
	  specificFlight NVARCHAR(100) NULL
	);

	CREATE TABLE flight_preferred_connecting_cities (
	   id BIGINT IDENTITY(1, 1) NOT NULL PRIMARY KEY, 
	  flight_id BIGINT FOREIGN KEY (flight_id) REFERENCES Flights(id), 
	  city NVARCHAR(255) NOT NULL
	);

	CREATE TABLE flight_preferred_airlines (
	   id BIGINT IDENTITY(1, 1) NOT NULL PRIMARY KEY, 
	  flight_id BIGINT FOREIGN KEY (flight_id) REFERENCES Flights(id), 
	  airline NVARCHAR(255) NOT NULL 
	);

	CREATE TABLE flight_types (
	  id BIGINT IDENTITY(1, 1) NOT NULL PRIMARY KEY, 
	  flight_id BIGINT FOREIGN KEY (flight_id) REFERENCES Flights(id), 
	  type NVARCHAR(255) NOT NULL	
	);
END 

/*
DROP TABLE flight_types;
DROP TABLE flight_preferred_airlines;
DROP TABLE flight_preferred_connecting_cities;
DROP TABLE flights;
*/


INSERT INTO dbo.Flights (origin, destination, departureDate, passengers, timeRange, specificAirline, specificFlight) VALUES ('New York', 'San Diego', '2025-08-11', 4, 'Afternoon', 'Southwest Airlines', 'SW501');
INSERT INTO dbo.Flights (origin, destination, departureDate, passengers, timeRange, specificAirline, specificFlight) VALUES ('Denver', 'San Diego', '2025-10-30', 1, 'Evening', 'American Airlines', 'AA118');
INSERT INTO dbo.Flights (origin, destination, departureDate, passengers, timeRange, specificAirline, specificFlight) VALUES ('New York', 'Austin', '2025-08-04', 5, 'Morning', 'JetBlue Airways', 'JB753');
INSERT INTO dbo.Flights (origin, destination, departureDate, passengers, timeRange, specificAirline, specificFlight) VALUES ('Dallas', 'Orlando', '2025-06-18', 1, 'Afternoon', 'Delta Air Lines', 'DL223');
INSERT INTO dbo.Flights (origin, destination, departureDate, passengers, timeRange, specificAirline, specificFlight) VALUES ('Boston', 'Las Vegas', '2025-08-17', 3, 'Night', 'United Airlines', 'UA719');
INSERT INTO dbo.Flights (origin, destination, departureDate, passengers, timeRange, specificAirline, specificFlight) VALUES ('Miami', 'Philadelphia', '2025-11-26', 2, 'Afternoon', 'Southwest Airlines', 'SW781');
INSERT INTO dbo.Flights (origin, destination, departureDate, passengers, timeRange, specificAirline, specificFlight) VALUES ('Houston', 'Orlando', '2025-10-21', 1, 'Morning', 'American Airlines', 'AA539');
INSERT INTO dbo.Flights (origin, destination, departureDate, passengers, timeRange, specificAirline, specificFlight) VALUES ('Houston', 'Philadelphia', '2025-11-25', 4, 'Evening', 'United Airlines', 'UA595');
INSERT INTO dbo.Flights (origin, destination, departureDate, passengers, timeRange, specificAirline, specificFlight) VALUES ('Los Angeles', 'Charlotte', '2025-06-18', 5, 'Evening', 'Southwest Airlines', 'SW807');
INSERT INTO dbo.Flights (origin, destination, departureDate, passengers, timeRange, specificAirline, specificFlight) VALUES ('Seattle', 'Philadelphia', '2025-10-06', 3, 'Afternoon', 'American Airlines', 'AA340');
INSERT INTO dbo.Flights (origin, destination, departureDate, passengers, timeRange, specificAirline, specificFlight) VALUES ('Dallas', 'San Francisco', '2025-10-11', 1, 'Morning', 'Delta Air Lines', 'DL501');
INSERT INTO dbo.Flights (origin, destination, departureDate, passengers, timeRange, specificAirline, specificFlight) VALUES ('Seattle', 'Orlando', '2025-08-31', 5, 'Morning', 'Delta Air Lines', 'DL399');
INSERT INTO dbo.Flights (origin, destination, departureDate, passengers, timeRange, specificAirline, specificFlight) VALUES ('Houston', 'Austin', '2025-10-22', 4, 'Afternoon', 'JetBlue Airways', 'JB879');
INSERT INTO dbo.Flights (origin, destination, departureDate, passengers, timeRange, specificAirline, specificFlight) VALUES ('Atlanta', 'Orlando', '2025-07-18', 5, 'Afternoon', 'American Airlines', 'AA899');
INSERT INTO dbo.Flights (origin, destination, departureDate, passengers, timeRange, specificAirline, specificFlight) VALUES ('New York', 'Philadelphia', '2025-06-12', 4, 'Night', 'Delta Air Lines', 'DL912');

INSERT INTO dbo.[flight_preferred_airlines] (flight_id, airline) VALUES (1,'AVIANCA');
INSERT INTO dbo.[flight_preferred_airlines] (flight_id, airline) VALUES (2,'EMIRATOS');
INSERT INTO dbo.[flight_preferred_airlines] (flight_id, airline) VALUES (3,'Qatar Airways');
INSERT INTO dbo.[flight_preferred_airlines] (flight_id, airline) VALUES (4,'LACSA');
INSERT INTO dbo.[flight_preferred_airlines] (flight_id, airline) VALUES (5,'AMEXA');
INSERT INTO dbo.[flight_preferred_airlines] (flight_id, airline) VALUES (6,'LA NICA');
INSERT INTO dbo.[flight_preferred_airlines] (flight_id, airline) VALUES (7,'COPA');
INSERT INTO dbo.[flight_preferred_airlines] (flight_id, airline) VALUES (8,'AIR FRANCE');
INSERT INTO dbo.[flight_preferred_airlines] (flight_id, airline) VALUES (9,'KLM');
INSERT INTO dbo.[flight_preferred_airlines] (flight_id, airline) VALUES (10,'IBERIA');
INSERT INTO dbo.[flight_preferred_airlines] (flight_id, airline) VALUES (11,'Delta Air Lines');
INSERT INTO dbo.[flight_preferred_airlines] (flight_id, airline) VALUES (12,'UNITED AIRLINES');
INSERT INTO dbo.[flight_preferred_airlines] (flight_id, airline) VALUES (13,'Southwest Airlines');
INSERT INTO dbo.[flight_preferred_airlines] (flight_id, airline) VALUES (14,'Virgin Atlantic');
INSERT INTO dbo.[flight_preferred_airlines] (flight_id, airline) VALUES (15,'Lufthansa');

INSERT INTO dbo.[flight_preferred_airlines] (flight_id, airline) VALUES (15,'AVIANCA');
INSERT INTO dbo.[flight_preferred_airlines] (flight_id, airline) VALUES (14,'EMIRATOS');
INSERT INTO dbo.[flight_preferred_airlines] (flight_id, airline) VALUES (13,'Qatar Airways');
INSERT INTO dbo.[flight_preferred_airlines] (flight_id, airline) VALUES (12,'LACSA');
INSERT INTO dbo.[flight_preferred_airlines] (flight_id, airline) VALUES (11,'AMEXA');
INSERT INTO dbo.[flight_preferred_airlines] (flight_id, airline) VALUES (10,'LA NICA');
INSERT INTO dbo.[flight_preferred_airlines] (flight_id, airline) VALUES (9,'COPA');
INSERT INTO dbo.[flight_preferred_airlines] (flight_id, airline) VALUES (8,'IBERIA');
INSERT INTO dbo.[flight_preferred_airlines] (flight_id, airline) VALUES (7,'KLM');
INSERT INTO dbo.[flight_preferred_airlines] (flight_id, airline) VALUES (6,'IBERIA');
INSERT INTO dbo.[flight_preferred_airlines] (flight_id, airline) VALUES (5,'Delta Air Lines');
INSERT INTO dbo.[flight_preferred_airlines] (flight_id, airline) VALUES (4,'UNITED AIRLINES');
INSERT INTO dbo.[flight_preferred_airlines] (flight_id, airline) VALUES (3,'Southwest Airlines');
INSERT INTO dbo.[flight_preferred_airlines] (flight_id, airline) VALUES (2,'Virgin Atlantic');
INSERT INTO dbo.[flight_preferred_airlines] (flight_id, airline) VALUES (1,'Lufthansa');

INSERT INTO dbo.flight_preferred_connecting_cities (flight_id, city) VALUES (1,'Miami');
INSERT INTO dbo.flight_preferred_connecting_cities (flight_id, city) VALUES (1,'New York');

INSERT INTO dbo.flight_preferred_connecting_cities (flight_id, city) VALUES (2,'Tokio');
INSERT INTO dbo.flight_preferred_connecting_cities (flight_id, city) VALUES (2,'Paris');

INSERT INTO dbo.flight_preferred_connecting_cities (flight_id, city) VALUES (3,'Roma');
INSERT INTO dbo.flight_preferred_connecting_cities (flight_id, city) VALUES (3,'Orlando');

INSERT INTO dbo.flight_preferred_connecting_cities (flight_id, city) VALUES (4,'Paris');
INSERT INTO dbo.flight_preferred_connecting_cities (flight_id, city) VALUES (4,'Georgia');

INSERT INTO dbo.flight_preferred_connecting_cities (flight_id, city) VALUES (5,'Berlin');
INSERT INTO dbo.flight_preferred_connecting_cities (flight_id, city) VALUES (5,'Orlando');

INSERT INTO dbo.flight_preferred_connecting_cities (flight_id, city) VALUES (6,'San Petesburgo');
INSERT INTO dbo.flight_preferred_connecting_cities (flight_id, city) VALUES (6,'Bangkok');

INSERT INTO dbo.flight_preferred_connecting_cities (flight_id, city) VALUES (7,'Miami');
INSERT INTO dbo.flight_preferred_connecting_cities (flight_id, city) VALUES (7,'Nueva Delhi');

INSERT INTO dbo.flight_preferred_connecting_cities (flight_id, city) VALUES (8,'Pekin');
INSERT INTO dbo.flight_preferred_connecting_cities (flight_id, city) VALUES (8,'New York');

INSERT INTO dbo.flight_preferred_connecting_cities (flight_id, city) VALUES (9,'Miami');
INSERT INTO dbo.flight_preferred_connecting_cities (flight_id, city) VALUES (9,'Dubai');

INSERT INTO dbo.flight_preferred_connecting_cities (flight_id, city) VALUES (10,'Londres');
INSERT INTO dbo.flight_preferred_connecting_cities (flight_id, city) VALUES (10,'New York');

INSERT INTO dbo.flight_preferred_connecting_cities (flight_id, city) VALUES (11,'Miami');
INSERT INTO dbo.flight_preferred_connecting_cities (flight_id, city) VALUES (11,'Amsterdan');

INSERT INTO dbo.flight_preferred_connecting_cities (flight_id, city) VALUES (12,'Mexico');
INSERT INTO dbo.flight_preferred_connecting_cities (flight_id, city) VALUES (12,'Buenos Aires');

INSERT INTO dbo.flight_preferred_connecting_cities (flight_id, city) VALUES (13,'Managua');
INSERT INTO dbo.flight_preferred_connecting_cities (flight_id, city) VALUES (13,'San Jose');

INSERT INTO dbo.flight_preferred_connecting_cities (flight_id, city) VALUES (14,'San Pedro');
INSERT INTO dbo.flight_preferred_connecting_cities (flight_id, city) VALUES (14,'Managua');

INSERT INTO dbo.flight_preferred_connecting_cities (flight_id, city) VALUES (15,'Madrid');
INSERT INTO dbo.flight_preferred_connecting_cities (flight_id, city) VALUES (15,'Ciudad de Panama');

INSERT INTO dbo.flight_types (flight_id, [type]) values (1,'NonStop');
INSERT INTO dbo.flight_types (flight_id, [type]) values (2,'OneStop');
INSERT INTO dbo.flight_types (flight_id, [type]) values (3,'NonStop');
INSERT INTO dbo.flight_types (flight_id, [type]) values (4,'OneStop');
INSERT INTO dbo.flight_types (flight_id, [type]) values (5,'NonStop');
INSERT INTO dbo.flight_types (flight_id, [type]) values (6,'OneStop');
INSERT INTO dbo.flight_types (flight_id, [type]) values (7,'NonStop');
INSERT INTO dbo.flight_types (flight_id, [type]) values (8,'OneStop');
INSERT INTO dbo.flight_types (flight_id, [type]) values (9,'NonStop');
INSERT INTO dbo.flight_types (flight_id, [type]) values (10,'OneStop');
INSERT INTO dbo.flight_types (flight_id, [type]) values (11,'NonStop');
INSERT INTO dbo.flight_types (flight_id, [type]) values (12,'OneStop');
INSERT INTO dbo.flight_types (flight_id, [type]) values (13,'NonStop');
INSERT INTO dbo.flight_types (flight_id, [type]) values (14,'OneStop');
INSERT INTO dbo.flight_types (flight_id, [type]) values (15,'NonStop');