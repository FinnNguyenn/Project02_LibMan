# Project02_LibMan

This document provides setup instructions and describes the main features of the project.

## 🚀 How to Run

To run specific modules, you need to set the **Relative URL** in the project's Run configuration:

* **Module: "Reader register as a reader card"**
    * Right-click on the project -> **Properties** -> **Run**
    * Set **Relative URL**: `./Reader/LoginView.jsp`

* **Module: "Reader return documents"**
    * Right-click on the project -> **Properties** -> **Run**
    * Set **Relative URL**: `./Staff/LoginView.jsp`

---

## 🎨 UI Overview

### 1. Module: "Reader register as a reader card"

<img width="1917" height="924" alt="Screenshot 2025-11-16 220841" src="https://github.com/user-attachments/assets/9cd52255-3370-4843-9d66-96b866a88a91" />

<br>
If the reader does not have an account or reader card, they can click "Register now" to navigate to the registration frame.

> 

<img width="1898" height="925" alt="Screenshot 2025-11-16 220855" src="https://github.com/user-attachments/assets/6d8e80e8-ccd7-484f-8539-5af1ae44ba97" />

<br>
Includes fields for inputting information, a "Reset" button to clear all filled information, and a "Register" button to submit.
*Note: All fields are required. Username and Student Code must be unique and can only be used once.*

> 

### 2. Module: "Reader return documents"

<img width="1918" height="928" alt="Screenshot 2025-11-16 221018" src="https://github.com/user-attachments/assets/88c76c3e-f911-4a87-a547-7ddf9eca114e" />


> 

<img width="1917" height="923" alt="Screenshot 2025-11-16 221028" src="https://github.com/user-attachments/assets/cfcc1d5f-80d1-45ce-b404-e2faea697013" />


> 

<img width="1916" height="923" alt="Screenshot 2025-11-16 221037" src="https://github.com/user-attachments/assets/e460bbf2-94e9-4f9b-82c4-fde4255c0924" />

<br>
The library staff enters the **Reader code** into the search bar and clicks "Find".

> 

<img width="1917" height="924" alt="Screenshot 2025-11-16 221046" src="https://github.com/user-attachments/assets/fc9cfc20-74ea-4c04-a364-d8bfb291aecf" />

<br>
The reader's information will be displayed below the "Find" button.

> 

<img width="1902" height="924" alt="Screenshot 2025-11-16 221055" src="https://github.com/user-attachments/assets/6d350930-b825-4ce9-bab9-5ed72364c49f" />

<br>
Displays all the books that the reader has borrowed and their status.

> 

<img width="1901" height="925" alt="Screenshot 2025-11-16 221109" src="https://github.com/user-attachments/assets/c784e38a-5f24-465f-8866-f148b2e58bb7" />

<br>
Displays each book and a list of predefined faults for the library staff to select if the book is damaged.

> 

**Invoice UI**
<br>
Shows the detailed invoice, including borrowing fees, potential fines, and the total amount due.

> 

[Image of Invoice]


**Payment UI**
<br>
A screen for the library staff to enter information about the payment type and any notes. Click "Submit payment" if the reader pays successfully.

> 

[Image of Payment]
