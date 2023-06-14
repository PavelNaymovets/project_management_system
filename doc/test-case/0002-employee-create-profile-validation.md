# 2: Проверка обязательных полей при зоздание профиля сотрудника

|    Автор    |    Дата    | Средства тестирования | Результат |
|:-----------:|:----------:|:---------------------:|:---------:|
| Наумовец П. | 2023-06-12 |   Postman v10.14.9    |  Прошел   |

## Описание

При создании сотрудника должны проверяться обязательные атрибуты:

* personal_number - уникальный идентификатор во всей программе
* last_name - фамилия
* first_name - имя
* login - учетная запись
* password - пароль

При добавлении в базу данных:
* id - заполняется автоматически
* статус - становится `активный`

## Предусловия

Пользователя программы с атрибутами:

* personal_number - `384fqwe208c-a116-3bf1-9334-werfwefwerr2`
* last_name - `Иванов`
* first_name - `Глеб`
* login - `ivan.ov`
* password - `700`

,нет в базе данных. Администратор авторизован в системе.

## Шаги в postman

* Зайти в коллекцию `projectMenegementSystem`
* Перейти в папку `Employee`
* Выбрать запрос `CreateEmployee`
* Нажать `Send` 

Выполнится HTTP запрос с методом `POST` на endpoint `http://localhost:8080/pms/api/v1/employee`. В теле запроса могут отсутствовать 
обязательные поля. Пример вариантов тела запроса:

* Данные отсутствуют:

```
{
}
```

* Не указано поле `personalNumber`:

```
{
    "lastName": "Иванов",
    "firstName": "Глеб",
    "login": "ivan.ov",
    "password": "700"
}
```

* Не указано поле `lastName`:

```
{
    "personalNumber": "384fqwe208c-a116-3bf1-9334-werfwefwerr2",
    "firstName": "Глеб",
    "login": "ivan.ov",
    "password": "700"
}
```

* Не указано поле `firstName`:

```
{
    "personalNumber": "384fqwe208c-a116-3bf1-9334-werfwefwerr2",
    "lastName": "Иванов",
    "login": "ivan.ov",
    "password": "700"
}
```

* Не указано поле `login`:

```
{
    "personalNumber": "384fqwe208c-a116-3bf1-9334-werfwefwerr2",
    "lastName": "Иванов",
    "firstName": "Глеб",
    "password": "700"
}
```

* Не указано поле `password`:

```
{
    "personalNumber": "384fqwe208c-a116-3bf1-9334-werfwefwerr2",
    "lastName": "Иванов",
    "firstName": "Глеб",
    "login": "ivan.ov"
}
```

## Ожидаемый результат

Вернется ответ со статус кодом `400` и с сообщением о том, что данные отсутствуют.

## Фактический результат

* Данные отсутствуют;

```
{
    "statusCode": 400,
    "message": "Field personalNumber not filled.,Field lastName not filled.,Field firstName not filled.,Field login not filled.,Field password not filled."
}
```

* Не указано поле `personalNumber`:

```
{
    "statusCode": 400,
    "message": "Field personalNumber not filled."
}
```

* Не указано поле `lastName`:

```
{
    "statusCode": 400,
    "message": "Field lastName not filled."
}
```

* Не указано поле `firstName`:

```
{
    "statusCode": 400,
    "message": "Field firstName not filled."
}
```

* Не указано поле `login`:

```
{
    "statusCode": 400,
    "message": "Field login not filled."
}
```

* Не указано поле `password`:

```
{
    "statusCode": 400,
    "message": "Field password not filled."
}
```