# 18: Получение профиля сотрудника по id

|    Автор    |    Дата    | Средства тестирования | Результат |
|:-----------:|:----------:|:---------------------:|:---------:|
| Наумовец П. | 2023-06-13 |   Postman v10.14.9    |  Прошел   |

## Описание

Получение карточки сотрудника по id.

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
* Выбрать запрос `GetById`
* Нажать `Send`

Выполнится HTTP запрос с методом `GET` на endpoint `http://localhost:8080/pms/api/v1/employee/7`.

## Ожидаемый результат

Вернется ответ со статус кодом `200` и dto карточки сотрудника.

## Фактический результат

```
{
    "id": 7,
    "personalNumber": "384fqwe208c-a116-3bf1-9334-werfwefwerr2",
    "lastName": "Иванов",
    "firstName": "Глеб",
    "middleName": null,
    "position": null,
    "login": null,
    "email": null,
    "password": null,
    "status": "удаленный"
}
```
