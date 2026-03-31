[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/pG3gvzt-)
# PCCCS495 – Term II Project

## Project Title
Inventory Management System using DAO Pattern + File persistence

---

## Problem Statement
This project presents a console-based Inventory Management System developed entirely in Java, with the primary objective of demonstrating the practical application of Object-Oriented Programming principles within a structured, multi-layered architecture.

The system is built around the Data Access Object (DAO) design pattern, which enforces a deliberate separation between the user interface, business logic, and data persistence layers. Each layer communicates only with the layer directly below it, ensuring that no component carries responsibilities beyond its defined role. This separation is the central design goal of the project.

The application supports core inventory operations including adding products with auto-generated identifiers, updating product details, adjusting stock levels through restock and reduction operations, and removing products from the system. All data is persisted to a CSV file, ensuring that inventory state is retained across sessions without the overhead of a database.

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
Development followed structured, incremental commits aligned with layered architecture implementation and iterative bug fixing.

#### Layered Development

- Established src/ packages: dao/, model/, service/, ui/, exceptions/.
    
- Implemented Product model with fields, constructor, getters/setters, toString(), display().
    
- Declared ProductDAO interface; implemented FileItemDAO with CSV read/write.
    
- Added InventoryService business logic with operations and exceptions.
    
- Created custom exceptions: ItemNotFoundException, InsufficientStockException, DuplicateSKUException.
    
- Built ConsoleMenu with main loop, cases, and try-catch handling.
    

#### Bug Fixes & Polish

- Fixed Windows FileSystemException via two-phase file writes (separate Files.move()).
    
- Added first-run file existence checks in read methods.
    
- Automated ID generation: removed user ID input, implemented getNextProductID(); added readInt()/readDouble() for input validation.
    
- Cleanup: duplicate SKU checks, void display() correction.
    

#### Finalization

- Added class diagram documentation in docs/, required report in report/ and presentation file in slides/.
    

Commit frequency: 8-9 small, focused changes over 4 days, enabling easy rollback and review. Messages used imperative mood (e.g., "Add...", "Fix...", "Debug...") with context on issues resolved.