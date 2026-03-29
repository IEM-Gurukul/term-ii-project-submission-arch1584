[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/pG3gvzt-)
# PCCCS495 – Term II Project

## Project Title
Inventory Management System

---

## Problem Statement
Small and medium-scale warehouses often rely on manual methods to track 
product stock, leading to errors in stock levels, pricing, and product 
records. This project addresses that problem by providing a console-based 
Inventory Management System built entirely in Java. The system allows 
warehouse staff to add new products with auto-generated IDs, update pricing 
and descriptions, track and adjust stock levels through restock and reduction 
operations, and delete discontinued products. All data is persisted to a CSV 
file so inventory state is retained across sessions. The system is designed 
using the DAO (Data Access Object) pattern, ensuring a clean separation 
between business logic, data access, and the user interface. It demonstrates 
core Object-Oriented Programming principles including abstraction, 
encapsulation, inheritance, polymorphism, and exception handling.

---

## Target User
Warehouse staff or small business owners who need a simple, 
reliable system to manage product inventory from the command line.

---

## Core Features
- Add, update, and delete products with SKU-based identification
- Restock and reduce stock with real-time quantity tracking
- File-based persistence using CSV, inventory is saved across sessions

---

## OOP Concepts Used

- Abstraction: ProductDAO interface defines data operations without exposing 
  how they are performed. InventoryService depends only on this interface, 
  not on the concrete FileItemDAO implementation.

- Inheritance: Custom exception classes (ItemNotFoundException, 
  InsufficientStockException, DuplicateSKUException) extend RuntimeException, 
  inheriting all exception handling behaviour while adding meaningful names.

- Polymorphism: FileItemDAO implements the ProductDAO interface. 
  InventoryService holds a reference of type ProductDAO — at runtime, 
  Java dispatches calls to the FileItemDAO implementation transparently.

- Exception Handling: Custom exceptions are thrown by InventoryService for 
  invalid operations (product not found, insufficient stock, duplicate SKU) 
  and caught in ConsoleMenu with user-friendly error messages.

- Collections: java.util.List and java.util.ArrayList are used throughout 
  FileItemDAO and InventoryService to load, filter, and return product records.

---

## Proposed Architecture Description
The system follows a layered architecture with four distinct packages.
The UI layer (com.inventory.ui) handles all user interaction via a console 
menu. The service layer (com.inventory.service) contains all business logic 
and validates operations before delegating to the data layer. The DAO layer 
(com.inventory.dao) defines a ProductDAO interface and implements it in 
FileItemDAO, which performs all CSV file read/write operations. The model 
layer (com.inventory.model) contains the Product class representing inventory 
items. The exception package (com.inventory.exception) holds three custom 
exception classes used across the service and UI layers.

---

## How to Run
1. Ensure JDK 11 or above is installed
2. Clone the repository
3. Navigate to the src/ directory
4. Compile:
   javac com/inventory/ui/ConsoleMenu.java
5. Run:
   java com.inventory.ui.ConsoleMenu
6. A data/ folder will be created automatically in the project root
   containing inventory.csv where all data is persisted

---

## Git Discipline Notes
Minimum 10 meaningful commits required.