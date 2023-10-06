#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_STOCKS 10
#define MAX_USERS 10
#define MAX_NAME_LENGTH 50
#define MAX_TRANSACTIONS (MAX_USERS * MAX_STOCKS)

enum TransactionType {
    BUY,
    SELL,
    ADD_FUNDS,
    ADD_STOCKS
};

struct Stock {
    char symbol[10];
    char name[MAX_NAME_LENGTH];
    double price;
};

struct User {
    char username[MAX_NAME_LENGTH];
    double balance;
    struct Stock stocks[MAX_STOCKS];
    int stockQuantities[MAX_STOCKS];
    int stockCount;
};

struct Transaction {
    enum TransactionType type;
    int userIndex;
    int stockIndex;
    int quantity;
};

struct Stock stocks[MAX_STOCKS];
struct User users[MAX_USERS];
struct Transaction transactions[MAX_TRANSACTIONS];
int userCount = 0;
int stockCount = 0;
int transactionCount = 0;

void addTransaction(enum TransactionType type, int userIndex, int stockIndex, int quantity) {
    transactions[transactionCount].type = type;
    transactions[transactionCount].userIndex = userIndex;
    transactions[transactionCount].stockIndex = stockIndex;
    transactions[transactionCount].quantity = quantity;
    transactionCount++;
}

int findUserIndex(char username[]) {
    int i;
    for (i = 0; i < userCount; i++) {
        if (strcmp(users[i].username, username) == 0) {
            return i;
        }
    }
    return -1;
}

int findStockIndex(char symbol[]) {
    int i;
    for (i = 0; i < stockCount; i++) {
        if (strcmp(stocks[i].symbol, symbol) == 0) {
            return i;
        }
    }
    return -1;
}

void createStock(char symbol[], char name[], double price) {
    strcpy(stocks[stockCount].symbol, symbol);
    strcpy(stocks[stockCount].name, name);
    stocks[stockCount].price = price;
    stockCount++;
}

void createUser(char username[], double balance) {
    strcpy(users[userCount].username, username);
    users[userCount].balance = balance;
    users[userCount].stockCount = 0;
    userCount++;
}

void addFunds(int userIndex, double amount) {
    if (amount > 0) {
        users[userIndex].balance += amount;
        printf("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        printf("Successfully added %.2f to your balance. New balance: %.2f\n", amount, users[userIndex].balance);
        printf("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        addTransaction(ADD_FUNDS, userIndex, -1, amount);
    } else {
                printf("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        printf("Invalid amount. Please enter a positive amount.\n");
                printf("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

    }
}

void viewTransactionHistory(int userIndex) {
    // printf("---------------------------------------------");
    printf("\nTransaction History for %s:\n", users[userIndex].username);
    for (int i = 0; i < transactionCount; i++) {
        if (transactions[i].userIndex == userIndex) {
            const char* transactionTypeStr;
            switch (transactions[i].type) {
                case BUY:
                    transactionTypeStr = "Buy";
                    break;
                case SELL:
                    transactionTypeStr = "Sell";
                    break;
                case ADD_FUNDS:
                    transactionTypeStr = "Add Funds";
                    break;
                case ADD_STOCKS:
                    transactionTypeStr = "Add Stocks";
                    break;
                default:
                    transactionTypeStr = "Unknown";
            }
            // printf("---------------------------------------------");

            printf("Type: %s, Stock: %s, Quantity: %d\n", transactionTypeStr,
                   transactions[i].stockIndex == -1 ? "N/A" : stocks[transactions[i].stockIndex].symbol,
                   transactions[i].quantity);
        }
    }
}

void viewAllTransactions() {
        // printf("---------------------------------------------");

    printf("\nAll Transactions:\n");
    for (int i = 0; i < transactionCount; i++) {
        const char* transactionTypeStr;
        switch (transactions[i].type) {
            case BUY:
                transactionTypeStr = "Buy";
                break;
            case SELL:
                transactionTypeStr = "Sell";
                break;
            case ADD_FUNDS:
                transactionTypeStr = "Add Funds";
                break;
            case ADD_STOCKS:
                transactionTypeStr = "Add Stocks";
                break;
            default:
                transactionTypeStr = "Unknown";
        }
        printf("User: %s, Type: %s, Stock: %s, Quantity: %d\n",
               users[transactions[i].userIndex].username,
               transactionTypeStr,
               transactions[i].stockIndex == -1 ? "N/A" : stocks[transactions[i].stockIndex].symbol,
               transactions[i].quantity);
    }
}

void buyStock(int userIndex, int stockIndex, int quantity) {
    int i;
    int userStockIndex;
    double totalCost = stocks[stockIndex].price * quantity;

    if (users[userIndex].balance >= totalCost) {
        users[userIndex].balance -= totalCost;

        userStockIndex = -1;
        for (i = 0; i < users[userIndex].stockCount; i++) {
            if (strcmp(users[userIndex].stocks[i].symbol, stocks[stockIndex].symbol) == 0) {
                userStockIndex = i;
                break;
            }
        }

        if (userStockIndex != -1) {
            users[userIndex].stockQuantities[userStockIndex] += quantity;
        } else {
            userStockIndex = users[userIndex].stockCount;
            users[userIndex].stocks[userStockIndex] = stocks[stockIndex];
            users[userIndex].stockQuantities[userStockIndex] = quantity;
            users[userIndex].stockCount++;
        }
        printf("===========================================\n");
        printf("Successfully bought %d shares of %s\n", quantity, stocks[stockIndex].symbol);
                printf("===========================================\n");


        addTransaction(BUY, userIndex, stockIndex, quantity);
    } else {
        printf("===========================================\n");
        printf("Insufficient balance.\n");
         printf("===========================================\n");

    }
}

void sellStock(int userIndex, int stockIndex, int quantity) {
    int userStockIndex = -1;
    int i;

    for (i = 0; i < users[userIndex].stockCount; i++) {
        if (strcmp(users[userIndex].stocks[i].symbol, stocks[stockIndex].symbol) == 0) {
            userStockIndex = i;
            break;
        }
    }

    if (userStockIndex != -1 && quantity <= users[userIndex].stockQuantities[userStockIndex]) {
        double totalEarnings = stocks[stockIndex].price * quantity;
        users[userIndex].balance += totalEarnings;
        users[userIndex].stockQuantities[userStockIndex] -= quantity;

        if (users[userIndex].stockQuantities[userStockIndex] == 0) {
            for (i = userStockIndex; i < users[userIndex].stockCount - 1; i++) {
                users[userIndex].stocks[i] = users[userIndex].stocks[i + 1];
                users[userIndex].stockQuantities[i] = users[userIndex].stockQuantities[i + 1];
            }
            users[userIndex].stockCount--;
        }
        printf("*******************************************\n");
        printf("Successfully sold %d shares of %s\n", quantity, stocks[stockIndex].symbol);
        printf("*******************************************\n");


        addTransaction(SELL, userIndex, stockIndex, quantity);
    } else {
            printf("*******************************************\n");
        printf("Invalid quantity or insufficient shares.\n");
        printf("*******************************************\n");

    }
}

void printPortfolio(int userIndex) {
    int i;
    printf("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
    printf("Portfolio for %s:\n", users[userIndex].username);
    printf("Balance: %.2f\n", users[userIndex].balance);
    printf("Stocks:\n");
    printf("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");


    for (i = 0; i < users[userIndex].stockCount; i++) {
        printf("%s (%s): %.2f per share\n",
               users[userIndex].stocks[i].name,
               users[userIndex].stocks[i].symbol,
               users[userIndex].stocks[i].price);
        printf("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
        printf("Owned quantity: %d\n", users[userIndex].stockQuantities[i]);
        printf("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");

    }
}

void viewStockList() {
      printf("***************************************************\n");
    printf("\nList of Available Stocks:\n");
    for (int i = 0; i < stockCount; i++) {
        printf("%s (%s): %.2f per share\n", stocks[i].name, stocks[i].symbol, stocks[i].price);
    }
}

void viewStockPrices() {
     printf("_____________________________________\n");
    printf("\nCurrent Stock Prices:\n");
    for (int i = 0; i < stockCount; i++) {
        printf("%s (%s): %.2f per share\n", stocks[i].name, stocks[i].symbol, stocks[i].price);
    }
}

void addStocksToCompany(int stockIndex, int quantity) {
    if (stockIndex >= 0 && stockIndex < stockCount && quantity > 0) {
        stocks[stockIndex].price += quantity; // Increase the stock price
        printf("................................................................\n");
        printf("Successfully added %d stocks to %s. New price per share: %.2f\n",
               quantity, stocks[stockIndex].name, stocks[stockIndex].price);
        addTransaction(ADD_STOCKS, -1, stockIndex, quantity);
    } else {
         printf("................................................................\n");
        printf("Invalid input. Please enter a valid stock index and quantity.\n");
    }
}

int main() {
    createStock("APPLE", "Apple Inc.", 150.00);
    createStock("GOOGLE", "Alphabet Inc.", 2500.00);
    createStock("WIPRO", "Wipro Inc.", 175.00);
    createStock("INFOSYS", "Infosys Inc.", 1500.00);

    createUser("Tushar", 10000.00);
    createUser("Kalpana", 15000.00);
    createUser("Vikram", 12000.00);
    createUser("Jerry", 17000.00);

    while (1) {
        int i;
        int userIndex;
        int stockIndex;
        int choice;
        printf("\n................................................................\n");
        printf("\n1. Buy\n2. Sell\n3. View Portfolio\n4. View Stock List\n5. View Stock Prices\n6. Add Funds\n7. View Transaction History\n8. View All Transactions\n9. Add Stocks to Company\n10. Exit\n");

        scanf("%d", &choice);

        if (choice == 1) {
            char username[MAX_NAME_LENGTH];
            char symbol[10];
            int quantity;
            printf("Enter your username: ");
            scanf("%s", username);
            userIndex = findUserIndex(username);
            if (userIndex == -1) {
                printf("User not found.\n");
                continue;
            }
            printf("Enter stock symbol: ");
            scanf("%s", symbol);
            stockIndex = findStockIndex(symbol);
            if (stockIndex == -1) {
                printf("Stock not found.\n");
                continue;
            }
            printf("Enter quantity: ");
            scanf("%d", &quantity);
            buyStock(userIndex, stockIndex, quantity);
        } else if (choice == 2) {
            char username[MAX_NAME_LENGTH];
            char symbol[10];
            int quantity;
            printf("Enter your username: ");
            scanf("%s", username);
            userIndex = findUserIndex(username);
            if (userIndex == -1) {
                printf("User not found.\n");
                continue;
            }
            printf("Enter stock symbol: ");
            scanf("%s", symbol);
            stockIndex = findStockIndex(symbol);
            if (stockIndex == -1) {
                printf("Stock not found.\n");
                continue;
            }
            printf("Enter quantity: ");
            scanf("%d", &quantity);
            sellStock(userIndex, stockIndex, quantity);
        } else if (choice == 3) {
            char username[MAX_NAME_LENGTH];
            printf("Enter your username: ");
            scanf("%s", username);
            userIndex = findUserIndex(username);
            if (userIndex == -1) {
                printf("User not found.\n");
                continue;
            }
            printPortfolio(userIndex);
        } else if (choice == 4) {
            viewStockList();
        } else if (choice == 5) {
            viewStockPrices();
        } else if (choice == 6) {
            char username[MAX_NAME_LENGTH];
            double amount;
            printf("Enter your username: ");
            scanf("%s", username);
            userIndex = findUserIndex(username);
            if (userIndex == -1) {
                printf("User not found.\n");
                continue;
            }
            printf("Enter the amount to add: ");
            scanf("%lf", &amount);
            addFunds(userIndex, amount);
        } else if (choice == 7) {
            char username[MAX_NAME_LENGTH];
            printf("Enter your username: ");
            scanf("%s", username);
            userIndex = findUserIndex(username);
            if (userIndex == -1) {
                printf("User not found.\n");
                continue;
            }
            viewTransactionHistory(userIndex);
        } else if (choice == 8) {
            viewAllTransactions();
        } else if (choice == 9) {
            int stockIndex;
            int quantity;
            printf("Enter stock index to add stocks to: ");
            scanf("%d", &stockIndex);
            printf("Enter quantity to add: ");
            scanf("%d", &quantity);
            addStocksToCompany(stockIndex, quantity);
        } else if (choice == 10) {
            break;
        } else {
            printf("Invalid choice.\n");
        }
    }

    return 0;
}
