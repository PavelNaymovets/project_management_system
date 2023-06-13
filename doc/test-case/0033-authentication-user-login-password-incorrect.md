# 32: Аутентификация пользователя с некорректным логином и паролем

|    Автор    |    Дата    | Средства тестирования | Результат |
|:-----------:|:----------:|:---------------------:|:---------:|
| Наумовец П. | 2023-06-13 |   Postman v10.14.9    |  Прошел   |

## Описание

Для получения `jwt` токена `login` и `password` должны быть корректными.

## Предусловия

* Сотрудник не аутентифицирован и не авторизован в системе
* Есть `login` и `password`
* Статус - `активный`

## Шаги в postman

* Зайти в коллекцию `projectMenegementSystem`
* Перейти в папку `Employee`
* Выбрать запрос `GetAll`
* Нажать `Send`

Выполнится HTTP запрос с методом `GET` на endpoint `http://localhost:8080/pms/api/v1/employee`. Варианты тела запроса:

* Некорректный `login`:

```
{
    "login": "akov.efemov",
    "password": "500"
}
```

* Некорректный `password`:

```
{
    "login": "akov.efremov",
    "password": "00"
}
```

* Отсутствует `login`:

```
{
    "password": "500"
}
```

* Отсутствует `password`:

```
{
    "login": "akov.efemov"
}
```

* Отсутствует `login` и `password`:

```
{
}
```

* `login` - null:

```
{
    "login": null,
    "password": "500"
}
```

* `password` - null:

```
{
    "login": "akov.efremov",
    "password": null
}
```

* `login` - null и `password` - null:

```
{
    "login": null,
    "password": null
}
```

## Ожидаемый результат

Вернется ответ со статус кодом `401` и сообщением, что логин или пароль указаны некорректно.

## Фактический результат


* Некорректный `login`:

```
{
    "statusCode": 401,
    "message": "Incorrect login or password.akov.efemov"
}
```

* Некорректный `password`:

```
{
    "statusCode": 401,
    "message": "Incorrect login or password."
}
```

* Отсутствует `login`:

```
{
    "statusCode": 401,
    "message": "Incorrect login or password."
}
```

* Отсутствует `password`:

```
{
    "statusCode": 401,
    "message": "Incorrect login or password."
}
```

* Отсутствует `login` и `password`:

```
{
    "statusCode": 401,
    "message": "Incorrect login or password."
}
```

* `login` - null:

```
{
    "statusCode": 401,
    "message": "Incorrect login or password."
}
```

* `password` - null:

```
{
    "statusCode": 401,
    "message": "Incorrect login or password."
}
```

* `login` - null и `password` - null:

```
{
    "statusCode": 401,
    "message": "Incorrect login or password."
}
```
