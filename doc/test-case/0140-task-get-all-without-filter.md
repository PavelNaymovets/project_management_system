# 110: Получение списка всех участников команды без фильтра поиска

|    Автор    |    Дата    | Средства тестирования | Результат |
|:-----------:|:----------:|:---------------------:|:---------:|
| Наумовец П. | 2023-06-14 |   Postman v10.14.9    |  Прошел   |

## Описание

Получение карточек всех участников команды без фильтра.

## Предусловия

База данных содержит данные об участниках команды. Менеджер авторизован в системе.

## Шаги в postman

* Зайти в коллекцию `projectMenegementSystem`
* Перейти в папку `TeamMember`
* Выбрать запрос `GetAll`
* Нажать `Send`

Выполнится HTTP запрос с методом `GET` на endpoint `http://localhost:8080/pms/api/v1/team/member`. Тело запроса:

```
{
}
```

## Ожидаемый результат

Вернется ответ со статус кодом `200` и списком dto найденных участников команды.

## Фактический результат

```
[
    {
        "id": 3,
        "team": {
            "id": 1,
            "project": {
                "id": 1,
                "code": 1220680,
                "name": "система управления проектами",
                "description": "Взаимодействие корпорации и клиента традиционно специфицирует ролевой анализ зарубежного опыта Баинг и селлинг, безусловно, экономит продвигаемый повторный контакт",
                "status": "в разработке"
            }
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
        "role": "разработчик"
    },
    {
        "id": 4,
        "team": {
            "id": 1,
            "project": {
                "id": 1,
                "code": 1220680,
                "name": "система управления проектами",
                "description": "Взаимодействие корпорации и клиента традиционно специфицирует ролевой анализ зарубежного опыта Баинг и селлинг, безусловно, экономит продвигаемый повторный контакт",
                "status": "в разработке"
            }
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
        "role": "руководитель проекта"
    },
    {
        "id": 5,
        "team": {
            "id": 1,
            "project": {
                "id": 1,
                "code": 1220680,
                "name": "система управления проектами",
                "description": "Взаимодействие корпорации и клиента традиционно специфицирует ролевой анализ зарубежного опыта Баинг и селлинг, безусловно, экономит продвигаемый повторный контакт",
                "status": "в разработке"
            }
        },
        "employee": {
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
        "role": "разработчик"
    },
    {
        "id": 6,
        "team": {
            "id": 3,
            "project": {
                "id": 3,
                "code": 1998955,
                "name": "плагин для jira",
                "description": "Медиаплан недостаточно переворачивает экспериментальный рейтинг, используя опыт предыдущих кампаний",
                "status": "черновик"
            }
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
        "role": "аналитик"
    },
    {
        "id": 7,
        "team": {
            "id": 3,
            "project": {
                "id": 3,
                "code": 1998955,
                "name": "плагин для jira",
                "description": "Медиаплан недостаточно переворачивает экспериментальный рейтинг, используя опыт предыдущих кампаний",
                "status": "черновик"
            }
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
        "role": "тестировщик"
    },
    {
        "id": 8,
        "team": {
            "id": 3,
            "project": {
                "id": 3,
                "code": 1998955,
                "name": "плагин для jira",
                "description": "Медиаплан недостаточно переворачивает экспериментальный рейтинг, используя опыт предыдущих кампаний",
                "status": "черновик"
            }
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
        "role": "руководитель проекта"
    },
    {
        "id": 9,
        "team": {
            "id": 3,
            "project": {
                "id": 3,
                "code": 1998955,
                "name": "плагин для jira",
                "description": "Медиаплан недостаточно переворачивает экспериментальный рейтинг, используя опыт предыдущих кампаний",
                "status": "черновик"
            }
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
        "role": "разработчик"
    },
    {
        "id": 10,
        "team": {
            "id": 3,
            "project": {
                "id": 3,
                "code": 1998955,
                "name": "плагин для jira",
                "description": "Медиаплан недостаточно переворачивает экспериментальный рейтинг, используя опыт предыдущих кампаний",
                "status": "черновик"
            }
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
        "role": "руководитель проекта"
    },
    {
        "id": 13,
        "team": {
            "id": 3,
            "project": {
                "id": 3,
                "code": 1998955,
                "name": "плагин для jira",
                "description": "Медиаплан недостаточно переворачивает экспериментальный рейтинг, используя опыт предыдущих кампаний",
                "status": "черновик"
            }
        },
        "employee": {
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
        "role": "руководитель проекта"
    },
    {
        "id": 15,
        "team": {
            "id": 2,
            "project": {
                "id": 2,
                "code": 1713085,
                "name": "система обмена сообщениями",
                "description": "Селекция бренда решительно изменяет институциональный медиавес Опрос отражает институциональный медиамикс",
                "status": "завершен"
            }
        },
        "employee": {
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
        "role": "руководитель проекта"
    }
]
```
