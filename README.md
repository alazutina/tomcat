REST API приложение, которое взаимодействует с файловым хранилищем и предоставляет возможность получать доступ к файлам и истории загрузок.

Сущности:

User

Event (File file)

File
User -> List<Events>

Описание:

    Реализованы все CRUD операции для каждой из сущностей
    Подхода MVC
    Для сборки проекта использован Maven
    Для взаимодействия с БД - Hibernate
    Для конфигурирования Hibernate - аннотации
    Инициализация БД реализована с помощью flyway
    Взаимодействие с пользователем необходимо реализовать с помощью Postman (https://www.getpostman.com/)
    Рабочее приложение развернуто на heroku.com

Технологии: Java, MySQL, Hibernate, HTTP, Servlets, Maven, Flyway.
