# 53: Получение проекта по id без указания id

|    Автор    |    Дата    | Средства тестирования | Результат |
|:-----------:|:----------:|:---------------------:|:---------:|
| Наумовец П. | 2023-06-14 |   Postman v10.14.9    |  Прошел   |

## Описание

Получение карточки проекта по id без указания id.

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

Выполнится HTTP запрос с методом `GET` на endpoint `http://localhost:8080/pms/api/v1/project/`.

## Ожидаемый результат

Вернется ответ со статус кодом `415` и сообщение, что метод не поддерживается.

## Фактический результат

```
{
    "timestamp": "2023-06-14T10:18:14.953+00:00",
    "status": 415,
    "error": "Unsupported Media Type",
    "path": "/pms/api/v1/project/"
}
```