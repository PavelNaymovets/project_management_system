# 54: Получение проекта с id = null

|    Автор    |    Дата    | Средства тестирования | Результат |
|:-----------:|:----------:|:---------------------:|:---------:|
| Наумовец П. | 2023-06-14 |   Postman v10.14.9    |  Прошел   |

## Описание

Получение карточки проекта с id = null.

## Предусловия

* id - 1
* code - `1220680`
* name - `система управления проектами`
* description - `Взаимодействие корпорации и клиента традиционно специфицирует ролевой анализ зарубежного опыта Баинг и селлинг, безусловно, экономит продвигаемый повторный контакт`
* status - `в разработке`,

, есть в базе данных. Менеджер авторизован в системе.

## Шаги в postman

* Зайти в коллекцию `projectMenegementSystem`
* Перейти в папку `Project`
* Выбрать запрос `GetById`
* Нажать `Send`

Выполнится HTTP запрос с методом `GET` на endpoint `http://localhost:8080/pms/api/v1/project/null`.

## Ожидаемый результат

Вернется ответ со статус кодом `400` и сообщение плохой запрос.

## Фактический результат

```
{
    "timestamp": "2023-06-14T10:18:36.737+00:00",
    "status": 400,
    "error": "Bad Request",
    "path": "/pms/api/v1/project/null"
}
```
