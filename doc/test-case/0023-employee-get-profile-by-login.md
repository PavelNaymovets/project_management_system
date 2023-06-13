# 23: Получение профиля сотрудника по login

|    Автор    |    Дата    | Средства тестирования | Результат |
|:-----------:|:----------:|:---------------------:|:---------:|
| Наумовец П. | 2023-06-13 |   Postman v10.14.9    |  Прошел   |

## Описание

Получение карточки сотрудника по login.

## Предусловия

* id - 11
* personal_number - `555f508c-ak06-3bf1-9334-6ccc1ca21c36`
* last_name - `Петров`
* first_name - `Иван`
* status - `активный`,
* login - `iGleb`
* password - `800`

,есть в базе данных. Администратор авторизован в системе.

## Шаги в postman

* Зайти в коллекцию `projectMenegementSystem`
* Перейти в папку `Employee`
* Выбрать запрос `GetByLogin`
* Нажать `Send`

Выполнится HTTP запрос с методом `GET` на endpoint `http://localhost:8080/pms/api/v1/employee/login?login=iGleb`.

## Ожидаемый результат

Вернется ответ со статус кодом `200` и dto карточки сотрудника.

## Фактический результат

```
{
    "id": 11,
    "personalNumber": "555f508c-ak06-3bf1-9334-6ccc1ca21c36",
    "lastName": "Петров",
    "firstName": "Иван",
    "middleName": null,
    "position": null,
    "login": null,
    "email": null,
    "password": null,
    "status": "активный"
}
```
