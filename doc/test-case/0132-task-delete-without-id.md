# 132: Удаление задачи без указания id

|    Автор    |    Дата    | Средства тестирования | Результат |
|:-----------:|:----------:|:---------------------:|:---------:|
| Наумовец П. | 2023-06-15 |   Postman v10.14.9    |  Прошел   |

## Описание

Удалить задачу без указания id нельзя

## Предусловия

Пользователь с правами администратора авторизован в системе.

## Шаги в postman

* Зайти в коллекцию `projectMenegementSystem`
* Перейти в папку `Task`
* Выбрать запрос `DeleteById`
* Нажать `Send`

Выполнится HTTP запрос с методом `DELETE` на endpoint `http://localhost:8080/pms/api/v1/task/`.

## Ожидаемый результат

Вернется ответ со статус кодом `405` и сообщением, что метод не допустим

## Фактический результат

```
{
    "timestamp": "2023-06-15T13:19:59.836+00:00",
    "status": 405,
    "error": "Method Not Allowed",
    "path": "/pms/api/v1/task/"
}
```