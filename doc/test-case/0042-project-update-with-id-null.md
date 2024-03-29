# 42: Изменение полей проекта с указанием id = null

|    Автор    |    Дата    | Средства тестирования | Результат |
|:-----------:|:----------:|:---------------------:|:---------:|
| Наумовец П. | 2023-06-13 |   Postman v10.14.9    |  Прошел   |

## Описание

Изменение полей проекта с указанием id = null

## Предусловия

Проект с атрибутами:

* code - `1231231231`
* name - `новый проект`
* status - `черновик`

, есть в базе данных. Менеджер авторизован в системе.

## Шаги в postman

* Зайти в коллекцию `projectMenegementSystem`
* Перейти в папку `Project`
* Выбрать запрос `UpdateProject`
* Нажать `Send`

Выполнится HTTP запрос с методом `PUT` на endpoint `http://localhost:8080/pms/api/v1/project`. Тело запроса:

```
{
    "id": null,
    "code": 12234231231,
    "name": "еще один проект",
    "description": "какое-то описание"
}
```

## Ожидаемый результат

Вернется ответ со статус кодом `400` и сообщением, что id не указан.

## Фактический результат

```
{
    "statusCode": 400,
    "message": "Field id not specified."
}
```