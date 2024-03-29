# 154: Обновление исполнителя задачи с указанием несуществующего исполнителя или id

|    Автор    |    Дата    | Средства тестирования | Результат |
|:-----------:|:----------:|:---------------------:|:---------:|
| Наумовец П. | 2023-06-15 |   Postman v10.14.9    |  Прошел   |

## Описание

Если будет указан не существующий id исполнителя или задачи об этом вернется соответствующее сообщение.

## Предусловия

Задача с атрибутами:

* id - 4,
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

Выполнится HTTP запрос с методом `PUT` на endpoint `http://localhost:8080/pms/api/v1/task/employee?taskId=100&employeeId=5`.
Выполнится HTTP запрос с методом `PUT` на endpoint `http://localhost:8080/pms/api/v1/task/employee?taskId=4&employeeId=100`.

## Ожидаемый результат

Вернется ответ со статус кодом `400` и сообщением плохой запрос.

## Фактический результат

* Указан несуществующий id задачи:

```
{
    "statusCode": 404,
    "message": "Task not found. ID: 100"
}
```

* Указан несуществующий id исполнителя:

```
{
    "statusCode": 404,
    "message": "Employee not found. ID: 100"
}
```