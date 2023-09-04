print("\n")
import numpy as np
arr1=np.random.randint(1,5,(3,3))
arr2=np.random.randint(1,6,(3,3))
print("Array1 :\n",arr1)
print("Array2 : \n",arr2)
print("Multiply : \n",np.multiply(arr1,arr2))
print("Product : \n",np.prod([arr1,arr2]))
print("Dot : \n",np.dot(arr1,arr2))

print("\n")

# import numpy as np
arr1=np.array([1,2,3,4])
arr2=np.array([3,4,5,6])
print("Union : ",np.union1d(arr1,arr2))

print("Intersection : ",np.intersect1d(arr1,arr2))

print("Set Difference : ",np.setdiff1d(arr1,arr2))

print("XOR : ",np.setxor1d(arr1,arr2))

print("\n")

# import numpy as np
arr1=np.random.randint(10 ,size=10)
print(arr1)
print("Cumulative sum : ",np.cumsum(arr1))
print("Cumulative Product : ",np.cumprod(arr1))
print("Discrete difference (with n=3) : ",np.diff(arr1, n=3))
print("The unique elements from the array : ",np.unique(arr1))

print("\n")

# import numpy as np
arr1=np.array([1,2,3,4])
arr2=np.array([3,4,5,6])
z=[]
for i,j in zip(arr1,arr2):
    z.append(i+j)
print(z)

print(np.add(arr1,arr2))

def adds(x,y):
    return x+y
result=np.frompyfunc(adds,2,1)
print(result(arr1,arr2))

print("\n")

# import numpy as np
arr1=np.array([[[1,2,3,4]]])
arr2=np.array([5,25,15,10])
print("LCM : ",np.lcm.reduce(arr1))
print("GCD : ",np.gcd.reduce(arr2))
print("\n")


# output

# Array1 :
#  [[3 1 1]
#  [3 2 3]
#  [3 3 4]]
# Array2 :
#  [[2 1 3]
#  [4 4 4]
#  [3 4 2]]
# Multiply :
#  [[ 6  1  3]
#  [12  8 12]
#  [ 9 12  8]]
# Product :
#  17915904
# Dot :
#  [[13 11 15]
#  [23 23 23]
#  [30 31 29]]


# Union :  [1 2 3 4 5 6]
# Intersection :  [3 4]
# Set Difference :  [1 2]
# XOR :  [1 2 5 6]


# [9 6 4 7 4 3 4 0 8 5]
# Cumulative sum :  [ 9 15 19 26 30 33 37 37 45 50]
# Cumulative Product :  [    9    54   216  1512  6048 18144 72576     0     0     0]
# Discrete difference (with n=3) :  [  4 -11   8   0  -7  17 -23]
# The unique elements from the array :  [0 3 4 5 6 7 8 9]


# [4, 6, 8, 10]
# [ 4  6  8 10]
# [4 6 8 10]


# LCM :  [[1 2 3 4]]
# GCD :  5

