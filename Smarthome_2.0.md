## Popis a motivace 
Aplikace představuje řešení pro správu a ovládání zařízení v chytré domácnosti. Původně byla aplikace navržena jako monolit, ale kvůli lepší škálovatelnosti, údržbě a rozšiřitelnosti byla architektura převedena na microservices.
Při spuštění aplikace se automaticky vytvoří budova s několika patry a místnostmi, do kterých je možné přidávat zařízení (například počítače, ledničky, televize atd). Každé zařízení lze ovládat samostatně (zapnout, vypnout, upravit hlasitost apod.) a jejich stav je možné monitorovat přes REST API.
Notifikační služba je odpovědná za informování uživatelů o změnách ve stavu zařízení (například přidání nového zařízení do místnosti) pomocí emailu nebo SMS. Komunikace mezi službami je realizována pomocí Apache Kafka.
Motivací pro tento projekt bylo navrhnout systém, který lze jednoduše rozšiřovat o další zařízení nebo funkce, zároveň nabídnout spolehlivé a škálovatelné řešení pro správu většího počtu chytrých zařízení v domácnosti.

## Funkční požadavky
**Správa budovy, pater a místností**:
 Uživatel může vytvořit budovu, přidávat patra a místnosti.
 
**Správa zařízení**:
 Vytváření, správa a ovládání různých typů zařízení v konkrétních místnostech.

**REST API**:
 Možnost získat seznam všech zařízení, detail konkrétního zařízení a stav API.

**Ovládání zařízení**:
 Zapnutí/vypnutí zařízení, úprava hlasitosti u TV, přidání produktů do lednice.

**Notifikace**:
 Odesíání oznámení (email, SMS) při přidání nového zařízení do místnosti.

**Indexace zařízení**:
 Ukládání informací o zařízeních do Elasticsearch pro efektivní vyhledávání.

## Nefunkční požadavky
**Škálovatelnost**:
 Systém musí být schopný obsluhovat více zařízení a uživatelů bez ztráty výkonu.
 
**Spolehlivost**:
 Komunikace mezi službami je zajištěna přes Apache Kafka, což zvyšuje spolehlivost zpracování událostí.
 
**Výkon**:
 Často používané informace (stav zařízení) jsou cachovány pomocí interní cache.
 
**Modularita**:
 Jednotlivé služby jsou samostatné a lze je nasazovat i vyvíjet nezávisle.

## Architektura
Celá aplikace je rozdělena na několik mikroslužeb, z nichž každá je zodpovědná za konkrétní oblast systému:
House Service:
 - Spravuje budovy, patra a místnosti.
 - Umožňuje vytvářet nové místnosti.

Device Service:
 - Spravuje zařízení – jejich vytváření, ovládání a ukládání.
 - Nabízí REST API pro externí přístup a komunikaci.

Notification Service:
 - Sleduje změny v systému a informuje uživatele (implementováno vzorem Observer).

Elasticsearch:
 - Indexuje zařízení pro rychlé vyhledávání.

Kafka:
 - Zajišťuje komunikaci a předávání událostí mezi službami (například vytvoření nového zařízení a jeho přidání do místnosti).

## Diagrams
###  Concept Diagram  
![image](https://github.com/user-attachments/assets/ec6548fd-721a-439c-aeac-2e6b30811ba6)

###  Class Diagram  
![image](https://github.com/user-attachments/assets/d4f2839b-65fd-4be4-80fd-01750893edb5)

###  Use Case Diagram  
![image](https://github.com/user-attachments/assets/58ee45bc-cba9-420c-87c2-370125302459)

### Component Diagram
![image](https://github.com/user-attachments/assets/473ef861-53fd-47d6-a7c4-d685548e6afb)

### Sequence Diagram
![image](https://github.com/user-attachments/assets/d3a2c2aa-607d-4969-ab78-368ce50ea537)
