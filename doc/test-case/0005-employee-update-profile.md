# 5: Изменение полей профиля сотрудника

|    Автор    |    Дата    | Средства тестирования | Результат |
|:-----------:|:----------:|:---------------------:|:---------:|
| Наумовец П. | 2023-06-13 |   Postman v10.14.9    |  Прошел   |

## Описание

При редактировании сотрудника должны редактировать поля профиля сотрудника:

* personal_number - уникальный идентификатор во всей программе
* last_name - фамилия
* first_name - имя
* middle_name - отчество
* position - должность
* login - учетная запись
* email - электронная почта
* password - пароль

Примеров запросов с `created_at` и `updated_at` не будет, так как в dto эти поля отсутствуют.

## Предусловия

Пользователь программы с атрибутами:

* id - 9
* personal_number - `384fqwe208c-a116-3bf1-9334-werfwefwerr2`
* last_name - `Иванов`
* first_name - `Глеб`
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
    "lastName": "Петров",
    "firstName": "Иван",
    "login": "iGleb",
    "password": "800"
}
```

## Ожидаемый результат

Вернется ответ со статус кодом `200` и dto с обновленными данными сотрудника.

## Фактический результат

![Image alt](https://github.com/PavelNaymovets/project_management_system/blob/develop/doc/test-case/screenshot/employee/employee_update_profile.png)

dto:
```
{
    "id": 9,
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