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
OpenJDK 18, Spring framework (Boot, Web, Security, Data JPA), Hibernate, Liquibase, Lombok, Logback, Slf4j, PostgreSQL, Docker, Swagger.

### Структура проекта
Проект состоит из 5 логических модулей:
* _./dto_ - dto и исключения
* _./model_ - сущности
* _./controller_ - обработка запросов пользователя
* _./service_ - бизнес логика
* _./repository_ - работа с базой данных

И 3 папок:
* _[./dock/adr](https://github.com/PavelNaymovets/project_management_system/tree/develop/doc/adr)_ - содержит файлы с краткими записями решениий задач, а также архитектурных решений, принятых в проекте. 
Файлы имеют последовательную нумерацию и определенную структуру
* _[./doc/arch](https://github.com/PavelNaymovets/project_management_system/tree/develop/doc/arch)_ - содержит скрины диаграмм общей архитектуры проекта
* _[./docker-compose](https://github.com/PavelNaymovets/project_management_system/blob/develop/docker-compose/docker-compose.yml)_ - содержит файл, чтобы создать окружение для разработки, демонстрации и тестирования
* _[./docker-compose/app](https://github.com/PavelNaymovets/project_management_system/blob/develop/docker-compose/app/Dockerfile)_ - содержит файл, для создания образа программы
* _[./liquibase](https://github.com/PavelNaymovets/project_management_system/tree/develop/docker-compose/liquibase)_ - содержит файлы инициализации структуры базы данных

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
* Запустить программу через _docker-compose.yaml_ файл с помощью команды: `docker-compose-up`
* В браузере перейти по ссылке: `http://localhost:8080/pms/swagger-ui/index.html`

#### Log
Программа пишет логи в консоль и в файлы. Файлы расположены в папке _[./logs](https://github.com/PavelNaymovets/project_management_system/tree/develop/logs)_. Структура файлов:
* _auth-log_ - логи аутентификации, включая исключения
* _email-log_ - логи сборки и отправки сообщения, включая исключения
* _exception-log_ - логи исключений обрабатываемые в _GlobalExceptionHandler_, кроме исключений auth и email
* _service-log_ - логи работы всех сервисов

#### Email
При назначении исполнителя на задачу, программа шлет email уведомление сотруднику которому была поставлена задача. Пример
уведомления:

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

Файл запуска программы _[run-app.sh](https://github.com/PavelNaymovets/project_management_system/blob/develop/docker-compose/app/run-app.sh)_ расположен в папке _[./docker-compose/app](https://github.com/PavelNaymovets/project_management_system/tree/develop/docker-compose/app)_. 
Команда запуска из командной строки: `bash run-app.sh `. 

Во время запуска:
* Выполняться модульные и интеграционные тесты
* Создасться архив приложения `pms-instance-exec.jar`
* Создасться docker образ приложения `pms-app:latest`
* Запуститься `docker-compose` файл
* Создасться контейнер базы данных `postgres`, контейнер системы миграции `liqubase` (выполнит миграцию базы данных
в контейнер с базой данных `postgres`), контейнер приложения `pms-app`.