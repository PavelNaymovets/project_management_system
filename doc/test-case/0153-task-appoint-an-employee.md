# 153: Назначение исполнителя задачи

|    Автор    |    Дата    | Средства тестирования | Результат |
|:-----------:|:----------:|:---------------------:|:---------:|
| Наумовец П. | 2023-06-15 |   Postman v10.14.9    |  Прошел   |

## Описание

Задачи может быть назначен исполнитель. Исполнителем и назначателем задачи может быть только участник проекта.
При назначении исполнителя на задачу, исполнителю отправляет email уведомление на почту.

## Предусловия

Задача с атрибутами:

* id - 2,
* name - 2 задача,
* description - Создать файлик shema.md(в resources либо в каталоге docs). В нем должно краткое описание(что такое, для чего) всех таблиц и аттрибутов,
* laborCosts - 11,
* deadline - 2024-05-24,
* status - выполнена

, есть в базе данных. Пользователь авторизован в системе.

## Шаги в postman

* Зайти в коллекцию `projectMenegementSystem`
* Перейти в папку `Task`
* Выбрать запрос `AppointAnEmployee`
* Нажать `Send`

Исполнитель участник проекта:
Выполнится HTTP запрос с методом `PUT` на endpoint `http://localhost:8080/pms/api/v1/task/employee?taskId=4&employeeId=2`.

Исполнитель не участник проекта:
Выполнится HTTP запрос с методом `PUT` на endpoint `http://localhost:8080/pms/api/v1/task/employee?taskId=4&employeeId=5`.

## Ожидаемый результат

Вернется ответ со статус кодом `200`. Отправится уведомление на почту. Вернется dto задачи.

## Фактический результат

* Исполнитель участник проекта:

```
{
    "id": 4,
    "name": "2 задача",
    "description": "Создать файлик shema.md(в resources либо в каталоге docs). В нем должно краткое описание(что такое, для чего) всех таблиц и аттрибутов",
    "project": {
        "id": 3,
        "code": 1998955,
        "name": "плагин для jira",
        "description": "Медиаплан недостаточно переворачивает экспериментальный рейтинг, используя опыт предыдущих кампаний",
        "status": "черновик"
    },
    "employee": {
        "id": 1,
        "personalNumber": "384f508c-3016-3bf1-9334-6ccc1ca21c36",
        "lastName": "Прохорова",
        "firstName": "Инна",
        "middleName": "Андреевна",
        "position": "инженер ui тестирования",
        "login": null,
        "email": "vil534@example.com",
        "password": null,
        "status": "активный"
    },
    "laborCosts": 11,
    "deadline": "2024-05-24",
    "status": "закрыта",
    "author": {
        "id": 4,
        "personalNumber": "d4a5e6ff-8df2-377a-bd45-a7a698f76afb",
        "lastName": "Виноградова",
        "firstName": "Маргарита",
        "middleName": "Сергеевна",
        "position": "тим лид",
        "login": null,
        "email": "romanova.dina56@example.com",
        "password": null,
        "status": "активный"
    }
}
```

* Исполнитель не участник проекта:

```
{
    "statusCode": 400,
    "message": "Employee is not project member. He can`t be author or employee for this task. Login: akov.efremov"
}
```

* Исполнитель имеет статус - `удаленный`:

```
{
    "statusCode": 400,
    "message": "The employee has a status - deleted."
}
```

* Автор участник проекта:

```
{
    "id": 4,
    "name": "2 задача",
    "description": "Создать файлик shema.md(в resources либо в каталоге docs). В нем должно краткое описание(что такое, для чего) всех таблиц и аттрибутов",
    "project": {
        "id": 3,
        "code": 1998955,
        "name": "плагин для jira",
        "description": "Медиаплан недостаточно переворачивает экспериментальный рейтинг, используя опыт предыдущих кампаний",
        "status": "черновик"
    },
    "employee": {
        "id": 1,
        "personalNumber": "384f508c-3016-3bf1-9334-6ccc1ca21c36",
        "lastName": "Прохорова",
        "firstName": "Инна",
        "middleName": "Андреевна",
        "position": "инженер ui тестирования",
        "login": null,
        "email": "vil534@example.com",
        "password": null,
        "status": "активный"
    },
    "laborCosts": 11,
    "deadline": "2024-05-24",
    "status": "закрыта",
    "author": {
        "id": 4,
        "personalNumber": "d4a5e6ff-8df2-377a-bd45-a7a698f76afb",
        "lastName": "Виноградова",
        "firstName": "Маргарита",
        "middleName": "Сергеевна",
        "position": "тим лид",
        "login": null,
        "email": "romanova.dina56@example.com",
        "password": null,
        "status": "активный"
    }
}
```

* Автор не участник проекта:

```
{
    "statusCode": 400,
    "message": "Employee is not project member. He can`t be author or employee for this task. Login: akov.efremov"
}
```