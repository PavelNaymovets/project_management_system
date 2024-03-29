# 8: Изменение полей удаленного сотрудника

|    Автор    |    Дата    | Средства тестирования | Результат |
|:-----------:|:----------:|:---------------------:|:---------:|
| Наумовец П. | 2023-06-13 |   Postman v10.14.9    |  Прошел   |

## Описание

Нельзя обновить удаленного сотрудника

## Предусловия

Пользователь программы с атрибутами:

* id - 9
* personal_number - `384fqwe208c-a116-3bf1-9334-werfwefwerr2`
* last_name - `Иванов`
* first_name - `Глеб`
* status - `удаленный`,
* login - `ivan.ov`
* password - `700`

,есть в базе данных. Администратор авторизован в системе.

## Шаги в postman

* Зайти в коллекцию `projectMenegementSystem`
* Перейти в папку `Employee`
* Выбрать запрос `UpdateEmployee`
* Нажать `Send`

Выполнится HTTP запрос с методом `PUT` на endpoint `http://localhost:8080/pms/api/v1/employee`. Тело запроса:

```
{
    "id": "9",
    "personalNumber": "555f508c-ak06-3bf1-9334-6ccc1ca21c36",
}
```

## Ожидаемый результат

Вернется ответ со статус кодом `400` и сообщением, что сотрудник удален.

## Фактический результат

```
{
    "statusCode": 400,
    "message": "The employee has a status - deleted."
}
```
