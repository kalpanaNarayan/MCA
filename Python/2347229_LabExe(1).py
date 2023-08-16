print("\nFlight delay prediction is an important area of research in aviation. Predicting flight delays accurately can help airlines and passengers plan better.")
intro="Introducing kalpana.N, a passionate programmer and avid reader, with a roll number of 2347229. In the year 2023-24, Kalpana.N has ventured into the domain of flight delay prediction using Python. With a deep appreciation for books and a keen interest in technology."

print("\n<--word counting-->")
def count_word_frequency(paragraph, target_word):
    words = paragraph.split()
    word_count = 0
    for word in words:
        word = word.strip('.,!?()[]{}"\'')
        if word.lower() == target_word.lower():
            word_count += 1
    return word_count
paragraph = input("\nEnter the paragraph from which you want to find the word count:")
target_word = input("Enter the word that you want to count:")
frequency = count_word_frequency(paragraph, target_word)
print(f"The word '{target_word}' appears {frequency} times in the paragraph.\n\n")


num=["0","1","2","3","4","5","6","7","8","9"]
spld_word=intro.split(" ")
for i in spld_word:
    for j in i:
        if j in num:
            if "." in i:
                print(i," is float")
                break
            else:
                print(i,"is int")
                break
        else:
            print(i," : is string")
            break
def count_characters(paragraph):
    alphabets = 0
    numerics = 0
    specials = 0

    for char in paragraph:
        if char.isalpha():
            alphabets += 1
        elif char.isnumeric():
            numerics += 1
        else:
            specials += 1

    return alphabets, numerics, specials
paragraph = "Introducing kalpana.N, a passionate programmer with a roll number of 2347230. she has scored 95.5 marks in the year 2022-23."

alphabets, numerics, specials = count_characters(paragraph)

print(f"Alphabets: {alphabets}")
print(f"Numeric characters: {numerics}")
print(f"Special symbols: {specials}")


#sorting the set
print("\n<--sorting the set-->")
def set_operations_example():
    string_set = {"Flight", "weather", "Aircraft", "airport", "Airportsecurity"}
    print("Initial Set:", string_set)
    sorted_set = sorted(string_set, reverse=True)
    print("Sorted Set (Descending Order):", sorted_set)
set_operations_example()

#packing and unpacking of tuple
print("<--packing and unpacking of tuple-->")
def tuple_operations_example():
    #packing
    fruits = ("Apple", "Mango", "grapes", "Chiku", "Orange")
    print("Original Tuple:", fruits)
    #unpacking
    first_fruit, second_fruit, third_fruit, fourth_fruit, fifth_fruit = fruits
    print("\nUnpacked Variables:")
    print("First fruit:", first_fruit)
    print("Second fruit:", second_fruit)
    print("Third fruit:", third_fruit)
    print("Fourth fruit:", fourth_fruit)
    print("Fifth fruit:", fifth_fruit)
tuple_operations_example()

dmn_name=("F","l","i","g","h","t","","D","e","l","a","y")
count=0
for i in dmn_name:
    count=count+1
print("\n\ncount of words:",count)


#tuple slicing
print("\n\n<--tuple slicing-->")
def slicing_and_negative_indexing(domain_name):
    print("Original Domain Name:", domain_name)
    print("\nPositive Slicing:")
    print("1. Slicing from index 3 to 9:", domain_name[3:10])
    print("2. Slicing from index 0 to 7:", domain_name[:8])
    print("3. Slicing from index 5 to the end:", domain_name[5:])
    print("4. Slicing from index 2 to 11 with step 2:", domain_name[2:12:2])
    print("\nNegative Slicing:")
    print("1. Slicing from the end -8 to the end -3:", domain_name[-8:-2])
    print("2. Slicing from the end -11 to the end -1 with step 2:", domain_name[-11:-1:2])
    print("\nNegative Indexing:")
    print("Last character:", domain_name[-1])
    print("Second to last character:", domain_name[-2])
domain_name = "Flight Delay Prediction"
slicing_and_negative_indexing(domain_name)

print("\n\n<--push and pop -->")
def set_operations_example():
    mixed_set = {7, 3.14, "srinidhi", True, (1, 2)}
    print("Initial Set:", mixed_set)
    popped_element = mixed_set.pop()
    print("\nElement popped:", popped_element)
    print("Updated Set after pop:", mixed_set)
    mixed_set.clear()
    print("\nSet after clear:", mixed_set)
    mixed_set.add(42)
    mixed_set.add("29.3")
    mixed_set.add("newyork")
    mixed_set.add(False)
    mixed_set.add((3, 4))
    mixed_set.update(["Apple", "Banana", "Cherry", "Date", "Elderberry"])  # Adding string attributes
    print("Set after adding elements:", mixed_set)
    mixed_set.discard("World")
    print("\nThe Set is empty after deletion\n")
    del mixed_set
set_operations_example()