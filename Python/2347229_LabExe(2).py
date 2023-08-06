# Create an empty list for flight delay prediction
print("\n")
flight = []

# Using append() to add elements to the list
flight.append({"flight_id": "AA123", "flight_time":"10:00",})

# Using insert() to add elements at specific positions in the list
flight.insert(1, {"flight_id": "UA789", "flight_time": "14:15",})

# Using extend() to add iterable elements to the list
additional_flights = [
    {"flight_id": "BA321", "flight_time": "16:45",},
    {"flight_id": "LH234", "flight_time": "18:20",}
]
flight.extend(additional_flights)

print(flight)

# Create a list with numeric elements
numeric_list = [10, 5, 20, 15, 30]

# Swap the first and last elements in the list
numeric_list[0], numeric_list[-1] = numeric_list[-1], numeric_list[0]
print("\n Swapped list:", numeric_list)

# Find the sum of the digits in the list
sum_of_digits = sum(numeric_list)
print("\n Sum of digits in the list:", sum_of_digits)

# Find the smallest element in the list
smallest_element = min(numeric_list)
print("\n Smallest element in the list:", smallest_element)


# Create a dictionary with numeric values
numeric_dict = {
    "a": 10,
    "b": 25,
    "c": 20,
    "d": 15,
    "e": 30
}

# Find the sum of all the values in the dictionary
sum_of_values = sum(numeric_dict.values())
print("\n Sum of values in the dictionary:", sum_of_values)

# Create a dictionary with numeric values
numeric_dict = {
    "b": 5,
    "d": 15,
    "e": 30,
    "c": 20,
    "a": 10
}

# Sort the dictionary in ascending order based on keys
sorted_dict = dict(sorted(numeric_dict.items()))

print("\n Sorted dictionary (ascending order based on keys):", sorted_dict)

# Create a dictionary with numeric values
numeric_dict = {
    "b": 5,
    "d": 15,
    "e": 30,
    "c": 20,
    "a": 10
}

# Sort the dictionary in descending order of values using a lambda function
sorted_dict_desc = dict(sorted(numeric_dict.items(), key=lambda item: item[1], reverse=True))

print("\n Sorted dictionary (descending order based on values):", sorted_dict_desc)
print("\n")
