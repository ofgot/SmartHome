# Postup pro spuštění aplikace SmartHome
## Příprava prostředí
Pro správné fungování aplikace je potřeba mít Docker.

## Spuštění služeb Kafka a Elasticsearch
Otevřete terminál v kořenové složce projektu a spusťte následující příkaz:

**docker compose up -d**


Tímto se zajistí spuštění následujících služeb:
- Zookeeper

- Kafka

- Elasticsearch

Funkčnost Elasticsearch lze ověřit v internetovém prohlížeči na adrese http://localhost:9200.
Při správném spuštění se zobrazí JSON s klíčem cluster_name.

## Spuštění aplikací v IntelliJ IDEA
a) Nastavení spouštěcích konfigurací
V hlavním menu zvolte Run > Edit Configurations…
Přidejte novou konfiguraci typu Application pro každou z následujících služeb:
-  SmartHomeApp (sir.smarthome.SmartHomeApp)

- DeviceServiceApp (sir.smarthome.device_service.DeviceServiceApp)

- NotificationApp (sir.smarthome.notification_service.NotificationApp)

U jednotlivých konfigurací potřeba zaškrtnout možnost „Allow multiple instances“ pro paralelní běh aplikací.

b) Spuštění služeb
Doporučený postup spuštění služeb:

Nejprve spustit Kafka/Elasticsearch prostřednictvím Dockeru.

Poté spustit SmartHomeApp (zajišťuje vytvoření základní struktury domu a spuštění Kafka consumeru).

Následně spustit NotificationApp (poslouchá události z Kafka a rozesílá notifikace).

Nakonec spustit DeviceServiceApp (nabízí CLI pro správu zařízení).

Každá aplikace by měla běžet v samostatné konzoli, což umožňuje přehledné sledování výstupů a logů.

## Ovládání a ověření systému
Zařízení lze vytvářet v DeviceServiceApp pomocí příkazu, například:


**create tv LG 55 <roomId>**

Správné spuštění lze ověřit prostřednictvím dostupných API endpointů:

http://localhost:8080/status

http://localhost:8080/devices

http://localhost:8080/device?id=<uuid>

## Doporučení
Služby spouštět ve výše uvedeném pořadí:
Kafka → SmartHomeApp → NotificationApp → DeviceServiceApp
