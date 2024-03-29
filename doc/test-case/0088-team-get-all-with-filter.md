# 88: Получение списка всех команд с фильтром поиска

|    Автор    |    Дата    | Средства тестирования | Результат |
|:-----------:|:----------:|:---------------------:|:---------:|
| Наумовец П. | 2023-06-14 |   Postman v10.14.9    |  Прошел   |

## Описание

Поиск осуществляется по значениям атрибутов:

* project - {
  id - идентификатор проекта
  }

## Предусловия

База данных содержит данные о командах. Менеджер авторизован в системе.

## Шаги в postman

* Зайти в коллекцию `projectMenegementSystem`
* Перейти в папку `Team`
* Выбрать запрос `GetAll`
* Нажать `Send`

Выполнится HTTP запрос с методом `GET` на endpoint `http://localhost:8080/pms/api/v1/team`. Тело запроса:

```
{
    "project": {
        "id": 1
    } 
}
```

## Ожидаемый результат

Вернется ответ со статус кодом `200` и dto найденной/ых команд/ы.

## Фактический результат

```
[
    {
        "id": 1,
        "project": {
            "id": 1,
            "code": 1220680,
            "name": "система управления проектами",
            "description": "Взаимодействие корпорации и клиента традиционно специфицирует ролевой анализ зарубежного опыта Баинг и селлинг, безусловно, экономит продвигаемый повторный контакт",
            "status": "в разработке"
        }
    }
]
```