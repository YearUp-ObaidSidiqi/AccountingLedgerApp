
# Accounting Ledger Application

This project is part of the Java Development Fundamentals course and serves as the first of three capstone projects. The Accounting Ledger Application is a Command Line Interface (CLI) tool designed to help users manage and track financial transactions, including deposits and payments, for personal or business purposes.


## Features
The application allows users to record and review financial transactions, stored in a CSV file. Key features include:
- Recording a Deposit & Payment
- Generating pre-defined Reoports


## Menu
The application has below menu:
| Home Screen Menu | Deposit Menu | Payment Menu | Ledger Menu | Generate Reports |
|---------------------|----------------------|----------------|--------------|--------------|
| *D) Add Deposit* | *N) Add a recent deposit* | *N) Add a recent payment* | *A) View All Transactions* | *1) This Month's Transactions* |
| *P) Make Payment* | *O) Add an older deposit* | *O) Add an older payment* | *D) View Deposits* | *2) Last Month's Transactions* |
| *L) Ledger* | *X) Return to Main Menu* | *X) Return to Main Menu* | *P) View Payments* | *3) This Year's Transactions* |
| *X) Exit* |  |  | *R) Generate Reports* | *4) Last Year's Transactions* |
|  |  |  |  | *5) Find Transactions by Vendor* |


### Interesting Code
One interesting part of the project is the getTransactionDetails Method, which allows to add a recent or an old, deposit or payments. This method demonstrates the power Boolean manipulation and date handling in Java to perform different tasks.

### CSV File Format

Transactions are saved as individual lines in a CSV file (transactions.csv) with the following format:



```bash
date|time|description|vendor|amount
2024-10-10 | 09:30:15 | salary payment | Salary | 7000.00 |
2024-09-05 | 16:25:50 | consultation fee | Consultation | 6000.00 |

```
## Installation

Install the Accounting Ledger Application with the following commands:

```bash
  # Clone the repository
git clone https://github.com/YearUp-ObaidSidiqi/AccountingLedgerApp

  # Navigate to the project directory
cd accounting-ledger-app

  # Compile and run the application
javac AccountingLedgerApp.java
java AccountingLedgerApp

```

## License
This project is licensed under the MIT License. See the [LICENSE](https://choosealicense.com/licenses/mit/) file for more details.



## 🚀 About Me
I'm a developer...
Find me in  [Linkedln](https://www.linkedin.com/in/obaid-sidiqi/)


