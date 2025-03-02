# 🏡 SmartHome System

**Authors:** Daria Kuznetsova & Khomenko Tymofii  

## 📌 Overview
The **SmartHome System** is a simulation designed to enhance home automation by integrating various smart devices and enabling seamless interaction between residents and automated systems. It also includes a **statistics module** to track and analyze system performance.

## 📋 Features
- 🌡 **Temperature Monitoring** – Automatically checks and adjusts home temperature.
- 📺 **TV Volume Control** – Increase or decrease TV volume remotely.
- ❄ **Smart Fridge Management** – Load and take products with ease.
- 🔌 **Device Control** – Turn devices on/off based on user input.
- 📊 **Statistics Tracking** – Collect and analyze data on device usage and system interactions.

## 🎭 Design Patterns Used
The system is designed using various **software design patterns** to ensure maintainability, scalability, and efficiency:
- 🔄 **Observer Pattern** – Used for monitoring system changes, such as temperature adjustments.
- 🏗 **Factory Method Pattern** – Manages the creation of smart home devices dynamically.
- 🎭 **Singleton Pattern** – Ensures a single instance of key system components (e.g., statistics manager).
- 🔌 **Command Pattern** – Allows encapsulating smart home device operations (turning on/off, adjusting settings) as commands.
- 🌳 **Composite Pattern** – Organizes smart home devices into hierarchical structures.
- 🎯 **Strategy Pattern** – Enables flexible behavior selection for data collection(statistic).
- ⏳ **Lazy Initialization** – Delays the creation of objects until they are actually needed, optimizing performance.

## 📜 Diagrams
### 📌 Class Diagram  
![Class Diagram](https://github.com/user-attachments/assets/1012b686-dd6a-4ea4-96c7-2b468319b624)

### 🎭 Use Case Diagram  
<img width="1108" alt="Use Case Diagram" src="https://github.com/user-attachments/assets/4afc04f9-466b-4688-ab44-3b0881444e31" />

## 🚀 Get Started
To explore and contribute to the project, check out the repository:  
🔗 [SmartHome GitHub Repository](https://github.com/ofgot/SmartHome)
