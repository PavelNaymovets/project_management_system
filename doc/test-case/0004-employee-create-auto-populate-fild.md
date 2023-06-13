# 4: Создание сотрудника с полями, которые заполняются автоматически

|    Автор    |    Дата    | Средства тестирования | Результат |
|:-----------:|:----------:|:---------------------:|:---------:|
| Наумовец П. | 2023-06-12 |   Postman v10.14.9    |  Прошел   |

## Описание

При создании сотрудника есть атрибуты, которые заполняются автоматически:

* id - уникальный идентификатор в таблице
* status - статус сотрудника (активный, удаленный)
* created_at - дата создания записи
* updated_at - дата обновления записи

Попытка создать сотрудника со значениями полей, которые заполняются автоматически, должно привести к ошибке. Примеров
запросов с `created_at` и `updated_at` не будет, так как в dto эти поля отсутствуют.

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

Выполнится HTTP запрос с методом `POST` на endpoint `http://localhost:8080/pms/api/v1/employee`. Пример вариантов тела
запроса:

* С `id`:

```
{
    "id": 1,
    "personalNumber": "384fqwe208c-a116-3bf1-9334-werfwefwerr2",
    "lastName": "Иванов",
    "firstName": "Глеб",
    "login": "ivan.ov",
    "password": "700"
}
```

* С `status`:

```
{
    "status": "активный",
    "personalNumber": "984fqwe208c-a116-3bf1-9334-werfwefwerr2",
    "lastName": "Иванов",
    "firstName": "Глеб",
    "login": "ivan.ov",
    "password": "700"
}
```

## Ожидаемый результат

Вернется ответ со статус кодом `400` и с сообщением о том, что создать пользователя с указанием автозаполняемых данных нельзя.

## Фактический результат

* С `id`:

```
{
    "statusCode": 400,
    "message": "Can`t create employee with field id because id is an autofill."
}
```

* С `status`:

```
{
    "statusCode": 400,
    "message": "Can`t create employee with field status because status is an autofill."
}
```
