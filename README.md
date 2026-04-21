# 🎓 Student Management System

A desktop application built using **Java Swing** and **JDBC** to manage student records with a MySQL database.
This project demonstrates basic database operations with a simple and clean graphical interface.

---

## ✨ Features

* ➕ Add new student details (ID, Name, Course)
* 📋 View all student records
* 🔍 Search student by ID
* 🧹 Automatic field clearing after actions
* 💾 Data stored in MySQL database

---

## 🛠️ Tech Stack

* ☕ Java (Swing for GUI)
* 🔗 JDBC (Database connectivity)
* 🗄️ MySQL (Database)

---

## 📁 Project Structure

```id="y6g9g3"
JavaSwingProject/
│
├── JavaSwingProject.java
├── .gitignore
└── README.md
```

---

## ⚙️ Setup Instructions

### 1️⃣ Clone the Repository

```bash id="94nb7u"
git clone https://github.com/Dhaniya4/JDBC-Project.git
cd JDBC-Project
```

---

### 2️⃣ Setup MySQL Database

Run the following in MySQL:

```sql id="r2qsk9"
CREATE DATABASE dhaniya;

USE dhaniya;

CREATE TABLE students (
    id INT PRIMARY KEY,
    name VARCHAR(50),
    course VARCHAR(50)
);
```

---

### 3️⃣ Add JDBC Driver

⬇️ Download MySQL Connector/J from:
https://dev.mysql.com/downloads/connector/j/

➡️ Add the `.jar` file to your project classpath

---

### 4️⃣ Update Database Credentials

Edit your connection code:

```java id="0m3s2g"
conn = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/dhaniya?useSSL=false&allowPublicKeyRetrieval=true",
    "root",
    "your_password"
);
```

---

## ▶️ How to Run

```bash id="0p7h9p"
javac JavaSwingProject.java
java JavaSwingProject
```

---

## 📌 Usage

* Enter student details → Click **Add** ➕ (Insert)
* Enter ID → Click **Show** 🔍 (Fetch specific record)
* Leave ID empty → Click **Show** 📋 (Fetch all records)

---

## ⚠️ Notes

* 🚫 `.class` files are ignored using `.gitignore`
* ▶️ Make sure MySQL server is running
* 🔑 Ensure correct DB credentials

---

## 👤 Author

**Dhaniya**
