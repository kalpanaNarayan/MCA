#q1
import pandas as pd

# Step 1: Read the CSV file into a DataFrame
df = pd.read_csv('Titanic.csv')

# Step 2: Display the first and last 10 instances
first_10 = df.head(10)
last_10 = df.tail(10)
print("First 10 instances:")
print(first_10)
print("\nLast 10 instances:")
print(last_10)

# Step 3: Acquire information using df.info() and df.describe()
info_summary = df.info()
describe_summary = df.describe()

# Step 4: Retrieve the number of columns and rows
num_rows, num_columns = df.shape

# Displaying the results
print("\nDataFrame Information:")
print(info_summary)
print("\nDataFrame Description:")
print(describe_summary)
print(f"\nNumber of Rows: {num_rows}")
print(f"Number of Columns: {num_columns}")

#q2
import pandas as pd
import matplotlib.pyplot as plt

# Step 1: Read the CSV file into a DataFrame
df = pd.read_csv('Titanic.csv')

# Task 1: Visualize the Gender of Passengers using a Bar graph
gender_counts = df['Sex'].value_counts()
plt.figure(figsize=(6, 4))
plt.bar(gender_counts.index, gender_counts.values, color=['blue', 'pink'])
plt.xlabel('Gender')
plt.ylabel('Count')
plt.title('Gender Distribution of Passengers')
plt.show()

# Task 2: Visualize the Survival Count of Passengers using a Bar graph
survival_counts = df['Survived'].value_counts()
plt.figure(figsize=(6, 4))
plt.bar(['Not Survived', 'Survived'], survival_counts.values, color=['red', 'green'])
plt.xlabel('Survival Status')
plt.ylabel('Count')
plt.title('Survival Count of Passengers')
plt.show()

# Task 3: Visualize the Age of Passengers using a Histogram
plt.figure(figsize=(8, 6))
plt.hist(df['Age'].dropna(), bins=20, color='skyblue', edgecolor='black')
plt.xlabel('Age')
plt.ylabel('Count')
plt.title('Age Distribution of Passengers')
plt.show()

# Task 4: Visualize the comparison of Age and Fare of Passengers using a Scatterplot
plt.figure(figsize=(8, 6))
plt.scatter(df['Age'], df['Fare'], color='purple', alpha=0.5)
plt.xlabel('Age')
plt.ylabel('Fare')
plt.title('Age vs. Fare of Passengers')
plt.show()

