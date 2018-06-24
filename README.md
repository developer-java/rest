

### Requirements
* Java 8
* Postgres 9.6+

### Getting started
1. Создаём в postgres схему rest и роль rest (можно изменить по желанию) 
    1.1 create user rest with password '123qweasd';  (создание роля)
    1.2 create database rest; (создание схему)
    1.3 grant all privileges on database rest to rest; (назначение прав)
2. В конфигурации запуска, в поле Program arguments прописываем --spring.profiles.active=dev
