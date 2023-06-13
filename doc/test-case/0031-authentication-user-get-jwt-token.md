# 31: Аутентификация пользователя. Получение jwt токена

|    Автор    |    Дата    | Средства тестирования | Результат |
|:-----------:|:----------:|:---------------------:|:---------:|
| Наумовец П. | 2023-06-13 |   Postman v10.14.9    |  Прошел   |

## Описание

Для получения `jwt` токена необходимо передать в теле запроса `login` и `password`.

## Предусловия

* Сотрудник не аутентифицирован и не авторизован в системе
* Есть `login` и `password`
* Статус - `активный`

## Шаги в postman

* Зайти в коллекцию `projectMenegementSystem`
* Перейти в папку `Employee`
* Выбрать запрос `Auth`
* Нажать `Send`

Выполнится HTTP запрос с методом `POST` на endpoint `http://localhost:8080/pms/api/v1/auth`. Тело запроса:

```
{
    "login": "akov.efremov",
    "password": "500"
}
```

## Ожидаемый результат

Вернется ответ со статус кодом `200` и dto содержащим `jwt` токен.

## Фактический результат

```
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJha292LmVmcmVtb3YiLCJyb2xlcyI6WyJST0xFX1VTRVIiXSwiZXhwIjoxNjg2NjY0MTY0LCJpYXQiOjE2ODY2NjA1NjR9.1QOiE-RmdBVg4z-A6hgmhiWANxuvvdlIqBRrs7ZLsgs"
}
```
