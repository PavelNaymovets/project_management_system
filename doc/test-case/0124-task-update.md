# 124: Изменение полей задачи

|    Автор    |    Дата    | Средства тестирования | Результат |
|:-----------:|:----------:|:---------------------:|:---------:|
| Наумовец П. | 2023-06-15 |   Postman v10.14.9    |  Прошел   |

## Описание

При изменении задачи должны изменяться поля карточки задачи:

* name - название
* description - описание
* project_id - ссылка на проект в котором поставлена задача
* employee_id - ссылка на исполнителя
* labor_costs - трудозатраты
* dead_line - крайний срок выполнения
* status - статус (новая, в работе, выполнена, закрыта)
* author_id - ссылка на автора

Примеров запросов с `created_at` и `updated_at` не будет, так как в dto эти поля отсутствуют. Не должны меняться поля:

* employee_id - назначается с помощью другого эндпоинта
* status - назначается с помощью другого эндпоинта
* author_id - назначается автоматически

При обновлении задачи автоматически изменяется автор задачи. Автором задачи может стать сотрудник, который является 
участником проекта и имеет статус `активный`.

## Предусловия

Задача с атрибутами:

* id - 6,
* name - новая задача,
* description - описание новой задачи,
* laborCosts - 10,
* deadline - 2023-05-24,
* status - новая

, есть в базе данных. Пользователь авторизован в системе.

## Шаги в postman

* Зайти в коллекцию `projectMenegementSystem`
* Перейти в папку `Task`
* Выбрать запрос `UpdateTask`
* Нажать `Send`

Выполнится HTTP запрос с методом `PUT` на endpoint `http://localhost:8080/pms/api/v1/task`. Варианты тела запроса:

* Автор является участником проекта и проект существует:

```
{
    "id": "6",
    "name": "Обновленная задача",
    "description": null,
    "project": {
        "id": 1
    },
    "laborCosts": "11",
    "deadline": "2024-05-24"
}
```

* Автор не является участником проекта и проект существует:

```
{
    "id": "2",
    "name": "2 задача",
    "description": null,
    "project": {
        "id": 3
    },
    "laborCosts": "11",
    "deadline": "2024-05-24"
}
```

## Ожидаемый результат

Id команды и id работника изменится у участника команды в базе данных. Вернется ответ со статус кодом `200` и dto 
обновленного участника команды.

## Фактический результат

![Image alt](https://github.com/PavelNaymovets/project_management_system/blob/develop/doc/test-case/screenshot/task/task_update.PNG)

* Автор является участником проекта и проект существует:

```
{
    "id": 6,
    "name": "Обновленная задача",
    "description": "Описание новой задачи",
    "project": {
        "id": 1,
        "code": null,
        "name": null,
        "description": null,
        "status": null
    },
    "employee": null,
    "laborCosts": 11,
    "deadline": "2024-05-24",
    "status": "новая",
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

* Автор не является участником проекта и проект существует:

```
{
    "statusCode": 400,
    "message": "Employee is not project member. He can`t be author or employee for this task. Login: romanova.dina"
}
```
