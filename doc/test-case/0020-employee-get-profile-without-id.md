# 20: Получение профиля сотрудника по id без указания id

|    Автор    |    Дата    | Средства тестирования | Результат |
|:-----------:|:----------:|:---------------------:|:---------:|
| Наумовец П. | 2023-06-13 |   Postman v10.14.9    |  Прошел   |

## Описание

Получение карточки сотрудника по id без указания id.

## Предусловия

Администратор авторизован в системе.

## Шаги в postman

* Зайти в коллекцию `projectMenegementSystem`
* Перейти в папку `Employee`
* Выбрать запрос `GetById`
* Нажать `Send`

Выполнится HTTP запрос с методом `GET` на endpoint `http://localhost:8080/pms/api/v1/employee/`.

## Ожидаемый результат

Вернется ответ со статус кодом `415` и сообщение, что метод не поддерживается.

## Фактический результат

```
{
    "timestamp": "2023-06-13T11:02:11.331+00:00",
    "status": 415,
    "error": "Unsupported Media Type",
    "path": "/pms/api/v1/employee/"
}
```