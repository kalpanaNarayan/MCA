def encode_decode_string(stringsplit):
    # split the encode string using underscores
    div = stringsplit.split('_')

    #Assuming the order is: name,domain name,register number
    if len(div) !=3:
        return None # Return None if the string doesn't have all three parts
    
    #Creating a dictionery with keys 'name','domain',and'register num'
    decode_dict = {
        'Name':div[0],
        'Domain':div[1],
        'Register Number':div[2]
    }
    return decode_dict

#output
encode_string = "Kalpana_Flightdelayprediction_2347229"
output = encode_decode_string(encode_string)
if output is not None:
    print(output)
else:
    print("Invalid")    