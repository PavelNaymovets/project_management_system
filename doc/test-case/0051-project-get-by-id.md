# 51: Получение проекта по id

|    Автор    |    Дата    | Средства тестирования | Результат |
|:-----------:|:----------:|:---------------------:|:---------:|
| Наумовец П. | 2023-06-14 |   Postman v10.14.9    |  Прошел   |

## Описание

Получение карточки проекта по id.

## Предусловия

Проект с атрибутами:

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

Выполнится HTTP запрос с методом `GET` на endpoint `http://localhost:8080/pms/api/v1/project/1`.

## Ожидаемый результат

Вернется ответ со статус кодом `200` и dto карточки проекта.

## Фактический результат

```
{
    "id": 1,
    "code": 1220680,
    "name": "система управления проектами",
    "description": "Взаимодействие корпорации и клиента традиционно специфицирует ролевой анализ зарубежного опыта Баинг и селлинг, безусловно, экономит продвигаемый повторный контакт",
    "status": "в разработке"
}
```