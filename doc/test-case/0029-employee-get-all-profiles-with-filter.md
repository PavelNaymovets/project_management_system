# 29: Получение списка всех сотрудников с фильтром поиска

|    Автор    |    Дата    | Средства тестирования | Результат |
|:-----------:|:----------:|:---------------------:|:---------:|
| Наумовец П. | 2023-06-13 |   Postman v10.14.9    |  Прошел   |

## Описание

Поиск осуществляется по текстовому значению, которое проверяется по атрибутам:

* last_name - фамилия `возможность поиска по части слова`
* first_name - имя `возможность поиска по части слова`
* middle_name - отчество `возможность поиска по части слова`
* login - учетная запись
* email - электронная почта

## Предусловия

База данных содержит данные о пользователях. Администратор авторизован в системе.

## Шаги в postman

* Зайти в коллекцию `projectMenegementSystem`
* Перейти в папку `Employee`
* Выбрать запрос `GetAll`
* Нажать `Send`

Выполнится HTTP запрос с методом `GET` на endpoint `http://localhost:8080/pms/api/v1/employee`. Варианты тела запроса:

* Фильтр по lastName, firstName и login:

```
{
    "lastName": "Петров",
    "firstName": "Иван",
    "middleName": null,
    "login": "iGleb",
    "email": null
}
```

* Фильтр по middleName:

```
{
    "middleName": "Андреевна"
}
```

* Фильтр по email:

```
{
    "email": "vil534@example.com"
}
```

* Фильтр по status - `удаленный`:

```
{
    "status": "удаленный"
}
```

* Фильтр по status - null:

```
{
    "status": null
}
```

* Фильтр по status - `sdfsdfd`:

```
{
    "status": "sdfsdfd"
}
```

* Фильтр по lastName - `сеев` содержащих в фамилии `%сеев`:

```
{
    "lastName": "с"
}
```

## Ожидаемый результат

Вернется ответ со статус кодом `200` и dto найденного/ых сотруника/ов со статусом `активный`.

## Фактический результат

* Фильтр по lastName, firstName и login:

```
[
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

* Фильтр по middleName:

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
    }
]
```

* Фильтр по email:

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
    }
]
```

* Фильтр по status - `удаленный`:

```
[
    {
        "id": 6,
        "personalNumber": "b2sd18bf-5fa0-3430-9218-30cios5sf96d",
        "lastName": "Иванов",
        "firstName": "Иван",
        "middleName": "Иванович",
        "position": "java разработчик",
        "login": null,
        "email": "ivan.ivanov41@example.org",
        "password": null,
        "status": "удаленный"
    },
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
]
```

* Фильтр по status - null:

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

* Фильтр по status - `sdfsdfd`:

```
{
    "statusCode": 400,
    "message": "Field status incorrect."
}
```

* Фильтр по lastName - `сеев` содержащих в фамилии `%сеев`:

```
[
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
    }
]
```