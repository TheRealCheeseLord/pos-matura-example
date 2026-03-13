# Pos Matura Example

A complete example and CRUD implementation based on a previous POS Matura, for the HTL Spengergasse.

## Matura Instructions (ANGABE.pdf)

### Domain

The PUML found in the `ANGABE.pdf` compared to the on in the project is slightly different.  
The project PUML contains more examples which could (most likely) come to a matura.  
The most accurate PUML is always found in the `domain` module under: `domain/src/main/resources/puml/domain.puml` 

## Maven Environment

The project is split into 3 modules (domain, service, web)  .
Each module has its own maven context and dependencies - and can/should mock the layers it depends on.  
(**Note:** The web module relies on the entities in the domain module - I didn't want to code it twice)

Each module has its own unit test environment, which can be run separately.

## SDKMAN/Java Environment

```bash
sdk install java 25-librca
sdk install maven 3.9.11
sdk env
``` 