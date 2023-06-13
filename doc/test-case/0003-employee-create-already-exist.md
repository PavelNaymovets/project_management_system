# 3: Создание уже существующего пользователя

|    Автор    |    Дата    | Средства тестирования | Результат |
|:-----------:|:----------:|:---------------------:|:---------:|
| Наумовец П. | 2023-06-12 |   Postman v10.14.9    |  Прошел   |

## Описание

При создании сотрудника не должны повторяться уникальные атрибуты:

* id - уникальный идентификатор в таблице
* personal_number - уникальный идентификатор во всей программе
* login - учетная запись
* password - пароль

Поле `id` заполняется автоматически СУБД, поле `password` проверяется только на уникальность хэша. Пароль, который скрыт за хэшом
может повторяться.

## Предусловия

Пользователь программы с атрибутами:

* personal_number - `384fqwe208c-a116-3bf1-9334-werfwefwerr2`
* last_name - `Иванов`
* first_name - `Глеб`
* login - `ivan.ov`
* password - `700`

,есть в базе данных. Администратор авторизован в системе.

## Шаги в postman

* Зайти в коллекцию `projectMenegementSystem`
* Перейти в папку `Employee`
* Выбрать запрос `CreateEmployee`
* Нажать `Send` 

Выполнится HTTP запрос с методом `POST` на endpoint `http://localhost:8080/pms/api/v1/employee`. Пример вариантов тела
запроса:
* С уже существующим `personalNumber`:
```
{
    "personalNumber": "384fqwe208c-a116-3bf1-9334-werfwefwerr2",
    "lastName": "Иванов",
    "firstName": "Глеб",
    "login": "ivan.ov",
    "password": "700"
}
```
* С уже существующим `login`:
```
{
    "personalNumber": "984fqwe208c-a116-3bf1-9334-werfwefwerr2",
    "lastName": "Иванов",
    "firstName": "Глеб",
    "login": "ivan.ov",
    "password": "700"
}
```

## Ожидаемый результат

Вернется ответ со статус кодом `400` и с сообщением о том, что данные не уникальны.

## Фактический результат

* С уже существующим `personalNumber`:
```
{
    "statusCode": 400,
    "message": "ERROR: duplicate key value violates unique constraint \"employee_personal_number_key\"\n  Подробности: Key (personal_number)=(384fqwe208c-a116-3bf1-9334-werfwefwerr2) already exists."
}
```
* С уже существующим `login`:
```
{
    "statusCode": 400,
    "message": "ERROR: duplicate key value violates unique constraint \"employee_login_key\"\n  Подробности: Key (login)=(ivan.ov) already exists."
}
```