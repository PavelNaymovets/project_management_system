# 13: Удаление удаленного сотрудника

|    Автор    |    Дата    | Средства тестирования | Результат |
|:-----------:|:----------:|:---------------------:|:---------:|
| Наумовец П. | 2023-06-13 |   Postman v10.14.9    |  Прошел   |

## Описание

Удалить удаленного сотрудника нельзя.

## Предусловия

* id - 7
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
* Выбрать запрос `DeleteById`
* Нажать `Send`

Выполнится HTTP запрос с методом `DELETE` на endpoint `http://localhost:8080/pms/api/v1/employee/7`.

## Ожидаемый результат

Вернется ответ со статус кодом `400` и собщением, что сотрудник уже удален.

## Фактический результат

```
{
    "statusCode": 400,
    "message": "The employee has a status - deleted."
}
```