# 9: Изменение полей несуществующего сотрудника

|    Автор    |    Дата    | Средства тестирования | Результат |
|:-----------:|:----------:|:---------------------:|:---------:|
| Наумовец П. | 2023-06-13 |   Postman v10.14.9    |  Прошел   |

## Описание

Нельзя обновить несуществующего сотрудника

## Предусловия

Администратор авторизован в системе.

## Шаги в postman

* Зайти в коллекцию `projectMenegementSystem`
* Перейти в папку `Employee`
* Выбрать запрос `UpdateEmployee`
* Нажать `Send`

Выполнится HTTP запрос с методом `PUT` на endpoint `http://localhost:8080/pms/api/v1/employee`. Тело запроса:

```
{
    "id": "567",
    "personalNumber": "555f508c-ak06-3bf1-9334-6ccc1ca21c36",
}
```

## Ожидаемый результат

Вернется ответ со статус кодом `404` и сообщением, что сотрудник не найден.

## Фактический результат

```
{
    "statusCode": 404,
    "message": "Employee not found. ID: 567"
}
```
