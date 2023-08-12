class Flight:
    def __init__(self):
        print("Flight is the parent class")

class Team(Flight):
    def __init__(self):
        Flight.__init__(self)
        print("Team is the child class of flight")

class pilot(Flight):
    def __init__(self):
        Flight.__init__(self)
        print("pilot is the child class of flight")

class Delay(Flight):
    def __init__(self):
        Flight.__init__(self)
        print("delay is the child class of flight")

class weather(Team, pilot):
    def __init__(self):
        Team.__init__(self)
        pilot.__init__(self)
        print("weather is the child class for team and pilot ")

class FlightDelayPrediction(weather, Delay):
    def __init__(self):
        weather.__init__(self)
        Delay.__init__(self)
        print("FlightDelayPrediction is the  sub child for weather and delay")

obj = FlightDelayPrediction()