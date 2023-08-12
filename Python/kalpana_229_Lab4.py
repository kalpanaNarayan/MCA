# Multiple Inheritance

class PassengerInfo:
    def __init__(self, name, seat_number):
        self.name = name
        self.seat_number = seat_number

    def display_passenger_info(self):
        print("Passenger Information:")
        print(f"Name: {self.name}")
        print(f"Seat Number: {self.seat_number}")


class FlightDetails:
    def __init__(self, flight_number, origin, destination):
        self.flight_number = flight_number
        self.origin = origin
        self.destination = destination

    def display_flight_details(self):
        print("Flight Details:")
        print(f"Flight Number: {self.flight_number}")
        print(f"Origin: {self.origin}")
        print(f"Destination: {self.destination}")


class FlightPassenger(PassengerInfo, FlightDetails):
    def __init__(self, name, seat_number, flight_number, origin, destination):
        PassengerInfo.__init__(self, name, seat_number)
        FlightDetails.__init__(self, flight_number, origin, destination)

    def display_flight_passenger(self):
        self.display_passenger_info()
        self.display_flight_details()


flight_passenger = FlightPassenger(
    "John Doe",
    "21A",
    "AI202",
    "New York",
    "London"
)
flight_passenger.display_flight_passenger()


# Multilevel Inheritance

class Flight:
    def __init__(self, flight_number, origin, destination):
        self.flight_number = flight_number
        self.origin = origin
        self.destination = destination

    def display_flight_info(self):
        print("Flight Information:")
        print(f"Flight Number: {self.flight_number}")
        print(f"Origin: {self.origin}")
        print(f"Destination: {self.destination}")


class CrewMember(Flight):
    def __init__(self, flight_number, origin, destination, crew_name, role):
        super().__init__(flight_number, origin, destination)
        self.crew_name = crew_name
        self.role = role

    def display_crew_member(self):
        self.display_flight_info()
        print(f"Crew Member: {self.crew_name}, Role: {self.role}")


class Pilot(CrewMember):
    def __init__(self, flight_number, origin, destination, crew_name, pilot_license):
        super().__init__(flight_number, origin, destination, crew_name, "Pilot")
        self.pilot_license = pilot_license

    def display_pilot(self):
        self.display_crew_member()
        print(f"Pilot License: {self.pilot_license}")


class FlightAttendant(CrewMember):
    def __init__(self, flight_number, origin, destination, crew_name, language):
        super().__init__(flight_number, origin, destination, crew_name, "Flight Attendant")
        self.language = language

    def display_flight_attendant(self):
        self.display_crew_member()
        print(f"Language: {self.language}")


pilot = Pilot("AI202", "New York", "London", "Captain Smith", "Commercial")
pilot.display_pilot()

attendant = FlightAttendant("AI202", "New York", "London", "Emily", "English, French")
attendant.display_flight_attendant()


# Hierarchical Inheritance

class Aircraft:
    def __init__(self, aircraft_type, registration_number):
        self.aircraft_type = aircraft_type
        self.registration_number = registration_number

    def display_aircraft_info(self):
        print("Aircraft Information:")
        print(f"Aircraft Type: {self.aircraft_type}")
        print(f"Registration Number: {self.registration_number}")


class Airplane(Aircraft):
    def __init__(self, aircraft_type, registration_number, max_passengers):
        super().__init__(aircraft_type, registration_number)
        self.max_passengers = max_passengers

    def display_airplane(self):
        self.display_aircraft_info()
        print(f"Max Passengers: {self.max_passengers}")


class Helicopter(Aircraft):
    def __init__(self, aircraft_type, registration_number, max_altitude):
        super().__init__(aircraft_type, registration_number)
        self.max_altitude = max_altitude

    def display_helicopter(self):
        self.display_aircraft_info()
        print(f"Max Altitude: {self.max_altitude}")


airplane = Airplane("Boeing 737", "N12345", 150)
airplane.display_airplane()

helicopter = Helicopter("Bell 407", "N67890", "10,000 feet")
helicopter.display_helicopter()
