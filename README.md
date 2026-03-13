# Project Setup

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