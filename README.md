# 📚 LibMan – README

This document provides a clear overview of the project setup and the primary features of each module.

---
##🔄 Work flow of module: Reader return documents

Here is Scenario v3 of the module:

> <img width="8211" height="6096" alt="Communication Diagram2" src="https://github.com/user-attachments/assets/7f7dfbd3-f1a2-4da9-b24a-132601bfce3e" />



## 🚀 How to Run the Modules

To run any specific module, configure the **Relative URL** in your project's **Run** settings.

### ▶️ Module: Reader Register as a reader card

1. Right‑click the project → **Properties**
2. Navigate to **Run**
3. Set **Relative URL** to:

   ```
   ./Reader/LoginView.jsp
   ```

### ▶️ Module: Reader Return Documents

1. Right‑click the project → **Properties**
2. Navigate to **Run**
3. Set **Relative URL** to:

   ```
   ./Staff/LoginView.jsp
   ```

---

## 🎨 User Interface Overview

### 1. Reader Register as a reader card

#### 🔐 Reader Login UI

If the reader does not yet have an account or reader card, they may click **"Register now"** to proceed to the registration interface.

> <img width="1917" height="924" alt="Screenshot 2025-11-16 220841" src="https://github.com/user-attachments/assets/71531a62-d111-44d1-bf6b-2972a7cc0864" />


#### 📝 Reader Registration UI

Includes fields to input personal details, a **Reset** button to clear all fields, and a **Register** button to submit the form.

All fields are required.

* **Username** and **Student Code** must be unique.
* Each can only be used one time.

> <img width="1898" height="925" alt="Screenshot 2025-11-16 220855" src="https://github.com/user-attachments/assets/85e3c2f2-f7c6-4b64-81a2-d05fe4a2067b" />


---

### 2. Reader Return Documents Module

#### 🔐 Library Staff Login UI

> <img width="1918" height="928" alt="Screenshot 2025-11-16 221018" src="https://github.com/user-attachments/assets/29798a2f-aa5d-439b-808b-a8b9c0b8162b" />


#### 🏠 Library Staff Home UI

> <img width="1917" height="923" alt="Screenshot 2025-11-16 221028" src="https://github.com/user-attachments/assets/f4a0cc41-3666-4399-8236-c1a290fd65ab" />


#### 🔍 Find Reader UI

Library staff enter the **Reader Code** into the search bar and click **Find**.

> <img width="1916" height="923" alt="Screenshot 2025-11-16 221037" src="https://github.com/user-attachments/assets/3db495d4-4324-428a-b551-0534a20ed6d8" />


#### 📄 Search Result UI

The corresponding reader information is displayed below the search section.

> <img width="1917" height="924" alt="Screenshot 2025-11-16 221046" src="https://github.com/user-attachments/assets/fd418bf2-a1d5-42d6-b419-ac2ad253a236" />


#### 📘 Choose Returned Documents UI

Shows all books currently borrowed by the reader along with their return status.

> <img width="1902" height="924" alt="Screenshot 2025-11-16 221055" src="https://github.com/user-attachments/assets/e047c6a8-14f4-4c3f-b0e8-1695a9c5ed93" />


#### ⚠️ Check for Fine UI

Displays each borrowed book along with a list of predefined faults. Staff can select applicable faults if the book is damaged.

> <img width="1899" height="926" alt="Screenshot 2025-11-16 224319" src="https://github.com/user-attachments/assets/79aafa57-e2f9-4db3-aa06-ec84f0b342d2" />


#### 🧾 Invoice UI

Presents detailed invoice information including:

* Borrowing fees
* Fines (if applicable)
* Total payment amount

> <img width="1901" height="925" alt="Screenshot 2025-11-16 221109" src="https://github.com/user-attachments/assets/2d33141c-7f88-4db6-b816-a849e696cfe8" />


#### 💳 Payment UI

Screen for staff to enter payment type and optional notes. Click **Submit payment** after the reader completes payment.

> <img width="1901" height="927" alt="Screenshot 2025-11-16 221118" src="https://github.com/user-attachments/assets/b9824af4-947e-49ea-84b1-069d246cdfd9" />


---

📌 *End of README*
