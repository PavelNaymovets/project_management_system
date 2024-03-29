# 76: Удаление команды без прав администратора

|    Автор    |    Дата    | Средства тестирования | Результат |
|:-----------:|:----------:|:---------------------:|:---------:|
| Наумовец П. | 2023-06-14 |   Postman v10.14.9    |  Прошел   |

## Описание

Команду может удалить только менеджер с правами администратора. Удалить можно только ту команду, на которую нет ссылок 
из других таблиц в базе данных. Перед тем как удалить команду должны быть удалены:

* Участники команды

## Предусловия

Команда с атрибутами:

* id - 4
* project - {
  id - 4
  }

, есть в базе данных. Менеджер с правами администратора авторизован в системе. Удалены участники команды.

## Шаги в postman

* Зайти в коллекцию `projectMenegementSystem`
* Перейти в папку `Team`
* Выбрать запрос `DeleteById`
* Нажать `Send`

Выполнится HTTP запрос с методом `DELETE` на endpoint `http://localhost:8080/pms/api/v1/team/4`.

## Ожидаемый результат

* Есть права - вернется ответ со статус кодом `200` и dto удаленного проекта
* Нет прав - вернется ответ со статус кодом `403` и сообщением, что действие запрещено

## Фактический результат

* Есть права менеджера и администратора:

```
{
    "id": 4,
    "project": {
        "id": 4,
        "code": 1684317,
        "name": "вычисление квадратных уравнений",
        "description": "Потребление отражает системный анализ, оптимизируя бюджеты Целевая аудитория пока плохо трансформирует рекламный блок",
        "status": "в тестировании"
    }
}
```

* Есть права менеджера, но нет прав администратора:

```
{
    "timestamp": "2023-06-14T15:02:05.630+00:00",
    "status": 403,
    "error": "Forbidden",
    "path": "/pms/api/v1/team/4"
}
```