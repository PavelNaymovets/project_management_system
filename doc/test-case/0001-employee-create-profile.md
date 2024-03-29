# 1: Создание профиля сотрудника

|    Автор    |    Дата    | Средства тестирования | Результат |
|:-----------:|:----------:|:---------------------:|:---------:|
| Наумовец П. | 2023-06-12 |   Postman v10.14.9    |  Прошел   |

## Описание

При создании сотрудника должна создаваться карточка сотрудника с набором атрибутов:

* id - уникальный идентификатор в таблице `обязательное поле`
* personal_number - уникальный идентификатор во всей программе `обязательное поле`
* last_name - фамилия `обязательное поле`
* first_name - имя `обязательное поле`
* middle_name - отчество
* position - должность
* login - учетная запись `обязательное поле`
* email - электронная почта
* status - статус сотрудника (активный, удаленный) `обязательное поле`
* password - пароль `обязательное поле`
* created_at - дата создания записи
* updated_at - дата обновления записи

, также статус сотрудника становится `активный`.

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

Выполнится HTTP запрос с методом `POST` на endpoint `http://localhost:8080/pms/api/v1/employee`. Тело запроса:

```
{
    "personalNumber": "384fqwe208c-a116-3bf1-9334-werfwefwerr2",
    "lastName": "Иванов",
    "firstName": "Глеб",
    "login": "ivan.ov",
    "password": "700"
}
```

## Ожидаемый результат

В базе данных, в таблице `employee`, появилась строка с данными нового пользователя. Вернется ответ со статус кодом 200
и dto созданного сотрудника. Поля `login` и `password` должны вернуться со значением null.

## Фактический результат

Пользователь добавлен:

![Image alt](https://github.com/PavelNaymovets/project_management_system/blob/develop/doc/test-case/screenshot/employee/employee_create_profile.png)

dto:

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
    "status": "активный"
}
```