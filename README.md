# Система управления проектами

## Задание

### Описание
Реализовать программу для ведения проектов. Функционал:
* Планирование
* Управление
* Реализация
* Поддержка

### Требования
Подробный список требований представлен по _[ссылке](https://docs.google.com/document/d/1wT8dNAiJR8H30aMUjIr2DR9rXZTVNGMgpoWF-B-KgwE/edit)_.

#### Основные тебования:
Основные блоки и функции, которые необходимо реализовать в программе:
* Управление сотрудниками компании
* Управление проектами и их жизненным циклом
* Управление задачами внутри проекта и их жизненным циклом
* Управление командами внутри проекта
* Аутентификация в системе

## Реализация
#### Стэк: OpenJDK 18, lombok
### Структура проекта
Проект состоит из 5 модулей:
* _./auth-service_ - аутентификация пользователя в системе
* _./employee-service_ - управление сотрудниками компании
* _./project-service_ - управление проектами и их жизненным циклом
* _./task-service_ - управление задачами внутри проекта и их жизненным циклом
* _./team-service_ - управление командами внутри проекта

И 3 папок:
* _./dock/adr_ - содержит файлы с краткими записями решениий задач, а также архитектурных решений, принятых в проекте. 
Файлы имеют последовательную нумерацию и определенную структуру. Ссылка _[тут](https://github.com/PavelNaymovets/project_management_system/tree/develop/doc/adr)_.
* _./doc/arch_ - содержит скрины диаграмм общей архитектуры проекта. Ссылка _[тут](https://github.com/PavelNaymovets/project_management_system/tree/develop/doc/arch)_.
* _./docker-compose_ - содержит файл, чтобы создать окружение для разработки, демонстрации и тестирования. Ссылка _[тут](https://github.com/PavelNaymovets/project_management_system/blob/develop/docker-compose/docker-compose.yml)_.
* _./docker-compose/app_ - содержит файл, для создания образа программы. Ссылка _[тут](https://github.com/PavelNaymovets/project_management_system/blob/develop/docker-compose/app/Dockerfile)_.
* _./flyway_ - содержит файлы инициализации структуры базы данных. Ссылка _[тут](https://github.com/PavelNaymovets/project_management_system/blob/develop/flyway/V1__init.sql)_. 

Каждый модуль содержит папку `!**/doc/arch` с 1 диаграммой, поясняющей принцип работы конкретного модуля (микросервиса).
### Архитектура:
![Image alt](https://github.com/PavelNaymovets/project_management_system/blob/develop/doc/arch/use-case/use%20case%20diagram.png)