# 71: Изменение полей участника команды без указания id

|    Автор    |    Дата    | Средства тестирования | Результат |
|:-----------:|:----------:|:---------------------:|:---------:|
| Наумовец П. | 2023-06-14 |   Postman v10.14.9    |  Прошел   |

## Описание

При редактировании полей участника команды должно указываться поле id.

## Предусловия

Участник команды с атрибутами:

* "team": {
  "id": 1
  },
* "employee": {
  "id": 2
  },
* "role": "аналитик"

, есть в базе данных. Менеджер авторизован в системе.

## Шаги в postman

* Зайти в коллекцию `projectMenegementSystem`
* Перейти в папку `TeamMember`
* Выбрать запрос `UpdateTeamMember`
* Нажать `Send`

Выполнится HTTP запрос с методом `PUT` на endpoint `http://localhost:8080/pms/api/v1/team/member`. Тело запроса:

```
{
    "team": {
        "id": 3
    },
    "employee": {
        "id": 5
    }
}
```

## Ожидаемый результат

Вернется ответ со статус кодом `400` и сообщением, что id не указано.

## Фактический результат

```
{
    "statusCode": 400,
    "message": "Field id not specified."
}
```