# 141: Получение списка всех задач с фильтром поиска

|    Автор    |    Дата    | Средства тестирования | Результат |
|:-----------:|:----------:|:---------------------:|:---------:|
| Наумовец П. | 2023-06-15 |   Postman v10.14.9    |  Прошел   |

## Описание

Поиск осуществляется по значениям атрибутов:

* name - наименование задачи
* status - статус (новая, в работе, выполнена, закрыта)
* employee - исполнитель
* author - автор
* deadlineMin - минимальный крайний срок
* deadlineMax - максимальный крайний срок
* createdAtMin - минимальная дата создания
* createdAtMax - максимальная дата создания

## Предусловия

База данных содержит данные о задачах. Пользователь авторизован в системе.

## Шаги в postman

* Зайти в коллекцию `projectMenegementSystem`
* Перейти в папку `Task`
* Выбрать запрос `GetAll`
* Нажать `Send`

Выполнится HTTP запрос с методом `GET` на endpoint `http://localhost:8080/pms/api/v1/task`. Тело запроса:

* Поле `name`, возможность частичного поиска:

```
{
    "name": "Нов"
}
```

* Поле `status`:

```
{
    "status": "закрыта"
}
```

* Поле `employee`:

```
{
    "employee": {
        "id": 1
    }
}
```

* Поле `author`:

```
{
    "author": {
        "id": 1
    }
}
```

* Поле `deadlineMin`:

```
{
    "deadlineMin": "2025-05-23"
}
```

* Поле `deadlineMax`:

```
{
    "deadlineMax": "2024-05-23"
}
```

* Поле `createdAtMin`:

```
{
    "createdAtMin": "2023-06-15 00:00:00"
}
```

* Поле `createdAtMax`:

```
{
    "createdAtMax": "2023-06-15 12:00:00"
}
```

## Ожидаемый результат

Вернется ответ со статус кодом `200` и dto найденной/ых команд/ы.

## Фактический результат

* Поле `name`, возможность частичного поиска:

```
[
    {
        "id": 8,
        "name": "Новая задача",
        "description": "Описание новой задачи",
        "project": {
            "id": 3,
            "code": 1998955,
            "name": "плагин для jira",
            "description": "Медиаплан недостаточно переворачивает экспериментальный рейтинг, используя опыт предыдущих кампаний",
            "status": "черновик"
        },
        "employee": null,
        "laborCosts": 10,
        "deadline": "2023-05-24",
        "status": "новая",
        "author": {
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
        }
    }
]
```

* Поле `status`:

```
[
    {
        "id": 5,
        "name": "Реализовать базовые операции над сущностями с помощью JDBC",
        "description": "Выбрать какую-нибудь сущность(модель) и реализовать для нее CRUD операции + поиск",
        "project": {
            "id": 3,
            "code": 1998955,
            "name": "плагин для jira",
            "description": "Медиаплан недостаточно переворачивает экспериментальный рейтинг, используя опыт предыдущих кампаний",
            "status": "черновик"
        },
        "employee": {
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
        "laborCosts": 5,
        "deadline": "2026-03-14",
        "status": "закрыта",
        "author": {
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
    }
]
```

* Поле `employee`:

```
[
    {
        "id": 2,
        "name": "1 задача",
        "description": "На модели должны быть отражены таблицы(сущности) с необходимыми аттрибутами(в том числе типами) и связи между таблицами",
        "project": {
            "id": 4,
            "code": 1684317,
            "name": "вычисление квадратных уравнений",
            "description": "Потребление отражает системный анализ, оптимизируя бюджеты Целевая аудитория пока плохо трансформирует рекламный блок",
            "status": "в тестировании"
        },
        "employee": {
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
        "laborCosts": 11,
        "deadline": "2024-05-24",
        "status": "выполнена",
        "author": {
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
        }
    }
]
```

* Поле `author`:

```
[
    {
        "id": 5,
        "name": "Реализовать базовые операции над сущностями с помощью JDBC",
        "description": "Выбрать какую-нибудь сущность(модель) и реализовать для нее CRUD операции + поиск",
        "project": {
            "id": 3,
            "code": 1998955,
            "name": "плагин для jira",
            "description": "Медиаплан недостаточно переворачивает экспериментальный рейтинг, используя опыт предыдущих кампаний",
            "status": "черновик"
        },
        "employee": {
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
        "laborCosts": 5,
        "deadline": "2026-03-14",
        "status": "закрыта",
        "author": {
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
    }
]
```

* Поле `deadlineMin`:

```
[
    {
        "id": 5,
        "name": "Реализовать базовые операции над сущностями с помощью JDBC",
        "description": "Выбрать какую-нибудь сущность(модель) и реализовать для нее CRUD операции + поиск",
        "project": {
            "id": 3,
            "code": 1998955,
            "name": "плагин для jira",
            "description": "Медиаплан недостаточно переворачивает экспериментальный рейтинг, используя опыт предыдущих кампаний",
            "status": "черновик"
        },
        "employee": {
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
        "laborCosts": 5,
        "deadline": "2026-03-14",
        "status": "закрыта",
        "author": {
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
    }
]
```

* Поле `deadlineMax`:

```
[
    {
        "id": 8,
        "name": "Новая задача",
        "description": "Описание новой задачи",
        "project": {
            "id": 3,
            "code": 1998955,
            "name": "плагин для jira",
            "description": "Медиаплан недостаточно переворачивает экспериментальный рейтинг, используя опыт предыдущих кампаний",
            "status": "черновик"
        },
        "employee": null,
        "laborCosts": 10,
        "deadline": "2023-05-24",
        "status": "новая",
        "author": {
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
        }
    }
]
```

* Поле `createdAtMin`:

```
[
    {
        "id": 8,
        "name": "Новая задача",
        "description": "Описание новой задачи",
        "project": {
            "id": 3,
            "code": 1998955,
            "name": "плагин для jira",
            "description": "Медиаплан недостаточно переворачивает экспериментальный рейтинг, используя опыт предыдущих кампаний",
            "status": "черновик"
        },
        "employee": null,
        "laborCosts": 10,
        "deadline": "2023-05-24",
        "status": "новая",
        "author": {
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
        }
    },
    {
        "id": 5,
        "name": "Реализовать базовые операции над сущностями с помощью JDBC",
        "description": "Выбрать какую-нибудь сущность(модель) и реализовать для нее CRUD операции + поиск",
        "project": {
            "id": 3,
            "code": 1998955,
            "name": "плагин для jira",
            "description": "Медиаплан недостаточно переворачивает экспериментальный рейтинг, используя опыт предыдущих кампаний",
            "status": "черновик"
        },
        "employee": {
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
        "laborCosts": 5,
        "deadline": "2026-03-14",
        "status": "закрыта",
        "author": {
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
    },
    {
        "id": 2,
        "name": "1 задача",
        "description": "На модели должны быть отражены таблицы(сущности) с необходимыми аттрибутами(в том числе типами) и связи между таблицами",
        "project": {
            "id": 4,
            "code": 1684317,
            "name": "вычисление квадратных уравнений",
            "description": "Потребление отражает системный анализ, оптимизируя бюджеты Целевая аудитория пока плохо трансформирует рекламный блок",
            "status": "в тестировании"
        },
        "employee": {
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
        "laborCosts": 11,
        "deadline": "2024-05-24",
        "status": "выполнена",
        "author": {
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
        }
    },
    {
        "id": 3,
        "name": "2 задача",
        "description": " Создать файлик schema.sql(в resources). В нем должны быть скрипты на создание схемы БД с \"нуля\"",
        "project": {
            "id": 2,
            "code": 1713085,
            "name": "система обмена сообщениями",
            "description": "Селекция бренда решительно изменяет институциональный медиавес Опрос отражает институциональный медиамикс",
            "status": "завершен"
        },
        "employee": {
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
        "laborCosts": 11,
        "deadline": "2024-05-24",
        "status": "в работе",
        "author": {
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
        }
    },
    {
        "id": 4,
        "name": "2 задача",
        "description": "Создать файлик shema.md(в resources либо в каталоге docs). В нем должно краткое описание(что такое, для чего) всех таблиц и аттрибутов",
        "project": {
            "id": 3,
            "code": 1998955,
            "name": "плагин для jira",
            "description": "Медиаплан недостаточно переворачивает экспериментальный рейтинг, используя опыт предыдущих кампаний",
            "status": "черновик"
        },
        "employee": {
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
        "laborCosts": 11,
        "deadline": "2024-05-24",
        "status": "новая",
        "author": {
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
        }
    }
]
```

* Поле `createdAtMax`:

```
[
    {
        "id": 5,
        "name": "Реализовать базовые операции над сущностями с помощью JDBC",
        "description": "Выбрать какую-нибудь сущность(модель) и реализовать для нее CRUD операции + поиск",
        "project": {
            "id": 3,
            "code": 1998955,
            "name": "плагин для jira",
            "description": "Медиаплан недостаточно переворачивает экспериментальный рейтинг, используя опыт предыдущих кампаний",
            "status": "черновик"
        },
        "employee": {
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
        "laborCosts": 5,
        "deadline": "2026-03-14",
        "status": "закрыта",
        "author": {
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
    },
    {
        "id": 2,
        "name": "1 задача",
        "description": "На модели должны быть отражены таблицы(сущности) с необходимыми аттрибутами(в том числе типами) и связи между таблицами",
        "project": {
            "id": 4,
            "code": 1684317,
            "name": "вычисление квадратных уравнений",
            "description": "Потребление отражает системный анализ, оптимизируя бюджеты Целевая аудитория пока плохо трансформирует рекламный блок",
            "status": "в тестировании"
        },
        "employee": {
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
        "laborCosts": 11,
        "deadline": "2024-05-24",
        "status": "выполнена",
        "author": {
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
        }
    },
    {
        "id": 3,
        "name": "2 задача",
        "description": " Создать файлик schema.sql(в resources). В нем должны быть скрипты на создание схемы БД с \"нуля\"",
        "project": {
            "id": 2,
            "code": 1713085,
            "name": "система обмена сообщениями",
            "description": "Селекция бренда решительно изменяет институциональный медиавес Опрос отражает институциональный медиамикс",
            "status": "завершен"
        },
        "employee": {
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
        "laborCosts": 11,
        "deadline": "2024-05-24",
        "status": "в работе",
        "author": {
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
        }
    },
    {
        "id": 4,
        "name": "2 задача",
        "description": "Создать файлик shema.md(в resources либо в каталоге docs). В нем должно краткое описание(что такое, для чего) всех таблиц и аттрибутов",
        "project": {
            "id": 3,
            "code": 1998955,
            "name": "плагин для jira",
            "description": "Медиаплан недостаточно переворачивает экспериментальный рейтинг, используя опыт предыдущих кампаний",
            "status": "черновик"
        },
        "employee": {
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
        "laborCosts": 11,
        "deadline": "2024-05-24",
        "status": "новая",
        "author": {
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
        }
    }
]
```
