# 🏡 A Microservice-Based Smart Home Control System

**Authors:**
- Daria Kuznetsova  
- Kseniia Shkolnaia  
- Diana Arnautova  
- Sofia Grechishkina

### 📌 Application Description and Motivation for Redesign

📊 **Description:**

Our project **SmartHome** is an application for managing a smart home. Through the app, users can turn devices on and off, monitor their status, and track energy consumption. The system does **not simulate residents or daily life**, but rather focuses on **practical real-time interaction with smart devices**.

The system models the household with buildings, floors, rooms, and devices (e.g. TVs, computers, refrigerators). Users interact with devices through a clear interface while the system collects data on device usage and energy consumption.

The system is designed using a **microservice architecture**, where each domain (e.g., devices, household structure, reports) is implemented as an independent service. Communication between services is done via REST APIs.

🎯 **Motivation for Redesign:**

- The original system was monolithic and difficult to maintain.
- We aim to improve **modularity, scalability, and fault tolerance**.
- A microservice approach enables **parallel team development** and independent deployment of system components.
- The result is a strong foundation for a **real-world IoT application**.
- This phase **does not include device failures or repair logic** – it focuses on basic control and energy tracking.

### 📋 Functional Requirements

1. View household structure (buildings, floors, rooms).
2. View list of devices in a room.
3. Turn on a device.
4. Turn off a device.
5. View device energy consumption.
6. Add a new device to a room.
7. Create a new household.

### 🎭 Non-Functional Requirements

1. The application must be available 24/7.
2. Each domain is implemented as a separate microservice.
3. REST API is used for communication between services.
4. Energy consumption data is stored for analytical purposes.
5. The system is secure, extendable, and scalable.

## 📦 Diagrams
### Class Diagram  
![Class Diagram]()

### Use Case Diagram  
![Use Case Diagram](docs/use%20case.png)

### Component Diagram  
![Component Diagram](docs/component%20diagram.png)

### Sequence Diagram  
![Sequence Diagram](docs/sequence%20diagram.png)
