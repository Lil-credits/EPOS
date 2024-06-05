@echo off

rem Set database configurations
set DB_HOST=localhost
set DB_PORT=5432
set DB_NAME=epos
set DB_USER=postgres
set DB_PASSWORD=AXEe263eqPFqwy4z

rem Set Walt.id configurations
set WALTID_HOST=localhost
set WALTID_PORT=7002

gradlew clean run
```
