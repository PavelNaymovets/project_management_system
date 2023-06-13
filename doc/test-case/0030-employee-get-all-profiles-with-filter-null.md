# 30: Получение списка всех сотрудников с фильтром поиска = null

|    Автор    |    Дата    | Средства тестирования | Результат |
|:-----------:|:----------:|:---------------------:|:---------:|
| Наумовец П. | 2023-06-13 |   Postman v10.14.9    |  Прошел   |

## Описание

Поиск осуществляется по текстовому значению, которое проверяется по атрибутам:

* last_name - фамилия
* first_name - имя
* middle_name - отчество
* login - учетная запись
* email - электронная почта

Все атрибуту будут иметь значения null.

## Предусловия

База данных содержит данные о пользователях. Администратор авторизован в системе.

## Шаги в postman

* Зайти в коллекцию `projectMenegementSystem`
* Перейти в папку `Employee`
* Выбрать запрос `GetAll`
* Нажать `Send`

Выполнится HTTP запрос с методом `GET` на endpoint `http://localhost:8080/pms/api/v1/employee`. Тело запроса:

```
{
    "lastName": null,
    "firstName": null,
    "middleName": null,
    "login": null,
    "email": null
}
```

## Ожидаемый результат

Вернется ответ со статус кодом `200` и списком dto найденных сотруников со статусом `активный`.

## Фактический результат

```
[
    {
        "id": 1,
        "personalNumber": "384f508c-3016-3bf1-9334-6ccc1ca21c36",
        "lastName": "Прохорова",
        "firstName": "Инна",
        "middleName": "Андреевна",
        "position": "инженер ui тестирования",
        "login": null,
        "email": "vil534@example.com",
        "password": null,
        "status": "активный"
    },
    {
        "id": 2,
        "personalNumber": "5190a23e-98ac-3e82-88af-9c5cdd7ca811",
        "lastName": "Селезнёва",
        "firstName": "Ярослава",
        "middleName": "Борисовна",
        "position": "бизнес аналитик",
        "login": null,
        "email": "zlata.rybakov69@example.com",
        "password": null,
        "status": "активный"
    },
    {
        "id": 3,
        "personalNumber": "22a7edc2-d9a3-3bc5-9241-c6922a7a45c1",
        "lastName": "Ермаков",
        "firstName": "Назар",
        "middleName": "Максимович",
        "position": "java разработчик",
        "login": null,
        "email": "popova.oleg31@example.com",
        "password": null,
        "status": "активный"
    },
    {
        "id": 4,
        "personalNumber": "d4a5e6ff-8df2-377a-bd45-a7a698f76afb",
        "lastName": "Виноградова",
        "firstName": "Маргарита",
        "middleName": "Сергеевна",
        "position": "тим лид",
        "login": null,
        "email": "romanova.dina56@example.com",
        "password": null,
        "status": "активный"
    },
    {
        "id": 5,
        "personalNumber": "b62a18bf-53a0-3f30-9f28-30c7bf10f96d",
        "lastName": "Алексеев",
        "firstName": "Аркадий",
        "middleName": "Иванович",
        "position": "java разработчик",
        "login": null,
        "email": "akov.efremov25@example.com",
        "password": null,
        "status": "активный"
    },
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
]
```
