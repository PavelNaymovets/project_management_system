# Система управления проектами

## Технические требования

Основные блоки и функции, которые необходимо реализовать в программе:
* Управление сотрудниками компании
* Управление проектами и их жизненным циклом
* Управление задачами внутри проекта и их жизненным циклом
* Управление командами внутри проекта
* Аутентификация в системе

Подробный список требований представлен по _[ссылке](https://drive.google.com/file/d/1fzuXa-ddR_7vQmpxIxJO9vTGTGaQ3bUE/view?usp=sharing)_.

## Реализация

#### ВАЖНО
Читайте *.md файлы на gitHub. Иначе не будут видны вставленные изображения.

#### Стэк
OpenJDK 18, Spring framework (Boot, Web, Security, Data JPA), Hibernate, Liquibase, Lombok, Logback, Slf4j, PostgreSQL, 
Docker, Swagger, RabbitMQ.

### Структура проекта
Проект состоит из 7 логических модулей:
* _[./app](https://github.com/PavelNaymovets/project_management_system/tree/develop/app)_ - конфигурация и запуск приложения
* _[./auth](https://github.com/PavelNaymovets/project_management_system/tree/develop/auth)_ - аутентификация, валидация, авторизация пользователей программы, работа с jwt токеном
* _[./controller](https://github.com/PavelNaymovets/project_management_system/tree/develop/controller)_ - обработка JSON/HTTP запросов пользователя
* _[./dto](https://github.com/PavelNaymovets/project_management_system/tree/develop/dto)_ - dto и исключения
* _[./model](https://github.com/PavelNaymovets/project_management_system/tree/develop/model)_ - сущности
* _[./repository](https://github.com/PavelNaymovets/project_management_system/tree/develop/repository)_ - работа с базой данных
* _[./service](https://github.com/PavelNaymovets/project_management_system/tree/develop/service)_ - бизнес логика

И 3 папок:
* _[./dock](https://github.com/PavelNaymovets/project_management_system/tree/develop/doc)_ - документация по проекту
* _[./docker-compose](https://github.com/PavelNaymovets/project_management_system/blob/develop/docker-compose)_ - непрерывная сборка и развертывание программы в `docker`
* _[./logs](https://github.com/PavelNaymovets/project_management_system/tree/develop/logs)_ - логи

### Структура папок:
#### dock:
* _[./dock/adr](https://github.com/PavelNaymovets/project_management_system/tree/develop/doc/adr)_ - архитектурные решения принятые в проекте
* _[./doc/arch](https://github.com/PavelNaymovets/project_management_system/tree/develop/doc/arch)_ - диаграммы архитектуры проекта (*.drawio, *.png)
* _[./doc/image](https://github.com/PavelNaymovets/project_management_system/tree/develop/doc/image/email)_ - скриншоты
* _[./doc/questions](https://github.com/PavelNaymovets/project_management_system/tree/develop/doc/questions)_ - вопросы куратору
* _[./doc/remarks](https://github.com/PavelNaymovets/project_management_system/blob/develop/doc/remarks/remark%60s%20list.md)_ - замечания от куратора и идеи по улучшению проекта
* _[./doc/test-case](https://github.com/PavelNaymovets/project_management_system/tree/develop/doc/test-case)_ - функциональные текст-кейсы. Составлены и реализованы (*.md)

#### docker-compose:
* _[./docker-compose/app](https://github.com/PavelNaymovets/project_management_system/tree/develop/docker-compose/app)_ - создание образа - `Dockerfile`. Запуск CI-CD - `run-app.sh`
* _[./docker-compose/imports](https://github.com/PavelNaymovets/project_management_system/tree/develop/docker-compose/imports/db)_ - создание базы данных `project` в `PostgreSQL`, при развертывании контейнера базы данных - `init.sh`
* _[./docker-compose/liquibase](https://github.com/PavelNaymovets/project_management_system/tree/develop/docker-compose/liquibase)_ - файлы миграции базы данных
* _[./docker-compose/docker-compose.yml](https://github.com/PavelNaymovets/project_management_system/blob/develop/docker-compose/docker-compose.yml)_ - развертывание программы в `docker`

#### logs:
* _[./logs/auth](https://github.com/PavelNaymovets/project_management_system/tree/develop/logs/auth)_ - логи аутентификации, включая исключения
* _[./logs/email](https://github.com/PavelNaymovets/project_management_system/tree/develop/logs/email)_ - логи сборки и отправки email уведомления на почту, включая исключения
* _[./logs/exception](https://github.com/PavelNaymovets/project_management_system/tree/develop/logs/exception)_ - логи исключений обрабатываемые в _[GlobalExceptionHandler](https://github.com/PavelNaymovets/project_management_system/blob/develop/controller/src/main/java/com/digdes/pms/controller/exception/GlobalExceptionHandler.java)_, кроме исключений auth и email
* _[./logs/service](https://github.com/PavelNaymovets/project_management_system/tree/develop/logs/service)_ - логи работы всех сервисов

### Архитектура:
Для описания архитектуры программы применены 5 диаграмм: 2 UML, 2 C4, 1 Physical model.

_[(первый вариант архитектуры)](https://github.com/PavelNaymovets/project_management_system/blob/develop/doc/arch/superseded/README.md)_

#### UML:
* _use case diagram_ - применено для отображения функциональных возможностей программы для пользователя
* _sequence diagram_ - применено для пояснения работы отдельных модулей программы

#### C4:
* _container diagram_ - применено для отображения развертываемых элементов программы и их взаимодействия между собой
* _deployment diagram_ - применено для пояснения развертывания элементов программы на сервере

#### Physical model:
Применено для отображения структуру таблиц и связей между таблицами в базе данных.

#### Use case diagram
Отображены функциональные возможности программы для пользователей согласно основным _[требованиям](https://github.com/PavelNaymovets/project_management_system/tree/develop#%D0%BE%D1%81%D0%BD%D0%BE%D0%B2%D0%BD%D1%8B%D0%B5-%D1%82%D0%B5%D0%B1%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D1%8F)_.

![Image alt](https://github.com/PavelNaymovets/project_management_system/blob/develop/doc/arch/use-case/use%20case%20diagram.png)

#### Container diagram
Отображены основные модули программы и их взаимодействие между собой. Отображена легенда в левом верхнем углу схемы.

![Image alt](https://github.com/PavelNaymovets/project_management_system/blob/develop/doc/arch/container/container%20diagram.png)

Дополнительно отображены контроллеры, которые поясняют основную функциональность программы более детально.

![Image alt](https://github.com/PavelNaymovets/project_management_system/blob/develop/doc/arch/container/controllers%20diagram.png)

* _auth_ - аутентификация пользователя в системе
* _employee_ - управление сотрудниками компании
* _project_ - управление проектами и их жизненным циклом
* _task_ - управление задачами внутри проекта и их жизненным циклом
* _team_ - управление командами внутри проекта

#### Deployment diagram
Отображено развертывание программы на сервере. В качестве сервера выступает мой рабочий компьютер. Пояснение по диаграмме: `х1` значит 1 докер контейнер.

![Image alt](https://github.com/PavelNaymovets/project_management_system/blob/develop/doc/arch/deployment/deployment%20diagram.png)

#### Physical model
Отображена структура основных таблицы в которых хранятся данные из программы и связи между этими таблицами.
Посмотреть краткое описание таблиц и их атрибутов можно _[тут](https://github.com/PavelNaymovets/project_management_system/blob/develop/doc/arch/database/SCHEME.md)_.

![Image alt](https://github.com/PavelNaymovets/project_management_system/blob/develop/doc/arch/database/physical%20diagram.png)

### Права доступа пользователей
Доступ к ресурсам программы зависит от роли пользователя:
* `/api/v1/employee/**` - роль `ADMIN`
* `/api/v1/project/**` - роль `MANAGER`
* `/api/v1/team/**` - роль `MANAGER`
* `/api/v1/task/**` - роль `USER`
* `/api/v1/auth/**` - доступно всем пользователям
* `/swagger-ui/**` - доступно всем пользователям

Метод `deleteById()` требует наличие роли `ADMIN` среди эндпоинтов:
* `/api/v1/employee/**`
* `/api/v1/project/**`
* `/api/v1/team/**`
* `/api/v1/task/**`

Удалить данные можно только через панель администратора.

### Дополнительный функционал

#### OpenApi
Для просмотра документации по проекту необходимо:
* Запустить программу с помощью CI-CD скрипта _[run-app.sh](https://github.com/PavelNaymovets/project_management_system/blob/develop/docker-compose/app/run-app.sh)_.
Команда запуска из командной строки: `bash run-app.sh `. При запуске из командной строки необходимо находиться в папке расположения файла `run-app.sh`.
* В браузере перейти по ссылке: `http://localhost:8080/pms/swagger-ui/index.html`

#### Log
Программа пишет логи в консоль и в файлы. Файлы расположены в папке _[./logs](https://github.com/PavelNaymovets/project_management_system/tree/develop/logs)_.

#### Email
При назначении исполнителя на задачу, программа асинхронно (с помощью `rabbitMQ`) отправит email уведомление сотруднику 
которому была поставлена задача:

![Image alt](https://github.com/PavelNaymovets/project_management_system/blob/develop/doc/arch/use-case/rabbitMQ.png)

Пример уведомления:

![Image alt](https://github.com/PavelNaymovets/project_management_system/blob/develop/doc/image/email/email_example.png)

### Тесты
Произведено функциональное тестирование программы согласно концепциям черного и белого ящика на соответсвие требованиям из ТЗ.

#### Черный ящик
Составлено и реализовано **160** _[тест-кейсов](https://github.com/PavelNaymovets/project_management_system/tree/develop/doc/test-case)_ для ручного тестирования API с помощью программы `postman`. Тест-кейсы имеют определенную 
структуру и скриншоты ожидаемого результата.

#### Белый ящик
Написаны _[модульные и интеграционные тесты](https://github.com/PavelNaymovets/project_management_system/tree/develop/service/src/test/java)_ для автоматизированной проверки функционала каждой из сущностей. 
Для выполнения интеграционных тестов создается отдельный докер контейнер с базой данных из образа `postgres`.

### Запуск приложения

Файл CI-CD программы _[run-app.sh](https://github.com/PavelNaymovets/project_management_system/blob/develop/docker-compose/app/run-app.sh)_ расположен в папке _[./docker-compose/app](https://github.com/PavelNaymovets/project_management_system/tree/develop/docker-compose/app)_. 
Команда запуска из командной строки: `bash run-app.sh `. При запуске из командной строки необходимо находиться в папке расположения файла `run-app.sh`.

Во время запуска:
* Выполняться модульные и интеграционные тесты
* Создасться архив приложения `pms-instance-exec.jar`
* Создасться docker образ приложения `pms-app:latest`
* Запуститься `docker-compose` файл
* Создасться контейнер базы данных `postgres`, контейнер системы миграции `liqubase` (выполнит миграцию базы данных
в контейнер с базой данных `postgres`), контейнер очереди `RabbitMQ`, контейнер приложения `pms-app`,

Параметры запуска:
* На локальной машине должен быть установлен `docker`, `maven`, `git bash`
* Версия `java`, которую использует `maven`, должна быть `18.0.2.1`. Убедитесь, что в переменной среды `JAVA_HOME` указана
именно эта версия. Иначе проект не скомпилируется, так как `maven` берет значение из этой переменной.