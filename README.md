

### Requirements
* Java 8
* Postgres 9.6+

### Getting started
1. Создаём в postgres схему rest и роль rest (можно изменить по желанию) <a href="https://community.vscale.io/hc/ru/community/posts/211866305-%D0%A3%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B0-%D0%B8-%D0%BF%D0%B5%D1%80%D0%B2%D0%B8%D1%87%D0%BD%D0%B0%D1%8F-%D0%BD%D0%B0%D1%81%D1%82%D1%80%D0%BE%D0%B9%D0%BA%D0%B0-PostgreSQL-%D0%BD%D0%B0-Ubuntu-16-04">ссылка на туториал по postgres</a> <br/>
    1.1 create user rest with password '123qweasd';  (создание роля) <br/>
    1.2 create database rest; (создание схему) <br/>
    1.3 grant all privileges on database rest to rest; (назначение прав) <br/>
2. В конфигурации запуска, в поле Program arguments прописываем --spring.profiles.active=dev
