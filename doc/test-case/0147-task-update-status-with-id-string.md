# 147: Обновление статуса заадчи с указанием id строкового значения

|    Автор    |    Дата    | Средства тестирования | Результат |
|:-----------:|:----------:|:---------------------:|:---------:|
| Наумовец П. | 2023-06-15 |   Postman v10.14.9    |  Прошел   |

## Описание

Обновление статуса задачи с указанием id строкового значения

## Предусловия

Задача с атрибутами:

* id - 4,
* name - 2 задача,
* description - Создать файлик shema.md(в resources либо в каталоге docs). В нем должно краткое описание(что такое, для чего) всех таблиц и аттрибутов,
* laborCosts - 11,
* deadline - 2024-05-24,
* status - выполнена

, есть в базе данных. Пользователь авторизован в системе.

## Шаги в postman

* Зайти в коллекцию `projectMenegementSystem`
* Перейти в папку `Task`
* Выбрать запрос `UpdateTaskStatus`
* Нажать `Send`

Выполнится HTTP запрос с методом `PUT` на endpoint `http://localhost:8080/pms/api/v1/task/status?id=sdfdsf&status=закрыта`.

## Ожидаемый результат

Вернется ответ со статус кодом `400` и сообщением плохой запрос.

## Фактический результат

```
{
    "timestamp": "2023-06-15T14:22:16.722+00:00",
    "status": 400,
    "error": "Bad Request",
    "path": "/pms/api/v1/task/status"
}
```
