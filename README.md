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
Проект состоит из 5 основных модулей:
* _./auth-service_ - аутентификация пользователя в системе
* _./employee-service_ - управление сотрудниками компании
* _./project-service_ - управление проектами и их жизненным циклом
* _./task-service_ - управление задачами внутри проекта и их жизненным циклом
* _./team-service_ - управление командами внутри проекта

1 общего:
* _./api_ - содержит файлы классов DTO

И 3 папок:
* _[./dock/adr](https://github.com/PavelNaymovets/project_management_system/tree/develop/doc/adr)_ - содержит файлы с краткими записями решениий задач, а также архитектурных решений, принятых в проекте. 
Файлы имеют последовательную нумерацию и определенную структуру
* _[./doc/arch](https://github.com/PavelNaymovets/project_management_system/tree/develop/doc/arch)_ - содержит скрины диаграмм общей архитектуры проекта
* _[./docker-compose](https://github.com/PavelNaymovets/project_management_system/blob/develop/docker-compose/docker-compose.yml)_ - содержит файл, чтобы создать окружение для разработки, демонстрации и тестирования
* _[./docker-compose/app](https://github.com/PavelNaymovets/project_management_system/blob/develop/docker-compose/app/Dockerfile)_ - содержит файл, для создания образа программы
* _[./flyway](https://github.com/PavelNaymovets/project_management_system/blob/develop/flyway/V1__init.sql)_ - содержит файлы инициализации структуры базы данных

Каждый модуль содержит папку `!**/doc/arch` с 1 диаграммой, поясняющей принцип работы конкретного модуля (микросервиса).
### Архитектура:
Для описания архитектуры программы применены 4 диаграммы (2 UML, 2 C4).

#### UML:
* _use case diagram_ - применено для отображения функциональных возможностей программы для пользователя
* _sequence diagram_ - применено для пояснения работы отдельных модулей программы

#### C4:
* _container diagram_ - применено для отображения развертываемых элементов программы и их взаимодействия между собой
* _deployment diagram_ - применено для пояснения развертывания элементов программы на сервере

#### Use case diagram

Отображены функциональные возможности программы для пользователя согласно основным _[требованиям](https://github.com/PavelNaymovets/project_management_system/tree/develop#%D0%BE%D1%81%D0%BD%D0%BE%D0%B2%D0%BD%D1%8B%D0%B5-%D1%82%D0%B5%D0%B1%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D1%8F)_.

![Image alt](https://github.com/PavelNaymovets/project_management_system/blob/develop/doc/arch/use-case/use%20case%20diagram.png)

#### Container diagram

Отображены основные элементы программы и их взаимодействие между собой. Отображена легенда в правом верхнем углу схемы.

![Image alt](https://github.com/PavelNaymovets/project_management_system/blob/develop/doc/arch/container/container%20diagram.png)

#### Deployment diagram

Отображено развертывание основных элементов программы на сервере. В качестве сервера выступает мой рабочий компьютер.
Пояснение по диаграмме: `х5` значит 5 контейнеров. Количество контейнеров = количество модулей в _[программе](https://github.com/PavelNaymovets/project_management_system/tree/develop#%D1%81%D1%82%D1%80%D1%83%D0%BA%D1%82%D1%83%D1%80%D0%B0-%D0%BF%D1%80%D0%BE%D0%B5%D0%BA%D1%82%D0%B0)_.

![Image alt](https://github.com/PavelNaymovets/project_management_system/blob/develop/doc/arch/deployment/deployment%20diagram.png)
