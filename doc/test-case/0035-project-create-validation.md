# 35: Проверка обязательных полей при создании проекта

|    Автор    |    Дата    | Средства тестирования | Результат |
|:-----------:|:----------:|:---------------------:|:---------:|
| Наумовец П. | 2023-06-13 |   Postman v10.14.9    |  Прошел   |

## Описание

При создании проекта должны проверяться обязательные атрибуты:

* id - уникальный идентификатор в таблице `обязательное поле`
* code - уникальный идентификатор во всей программе `обязательное поле`
* name - название `обязательное поле`
* status - статус (черновик, в разработке, в тестировании, завершен) `обязательное поле`

При добавлении в базу данных:
* id - заполняется автоматически
* статус - становится `черновик`

## Предусловия

Проекта с атрибутами:

* code - `1231231231`
* name - `новый проект`

, нет в базе данных. Менеджер авторизован в системе.

## Шаги в postman

* Зайти в коллекцию `projectMenegementSystem`
* Перейти в папку `Project`
* Выбрать запрос `CreateProject`
* Нажать `Send`

Выполнится HTTP запрос с методом `POST` на endpoint `http://localhost:8080/pms/api/v1/project`. Варианты тела запроса:

* Данные отсутствуют:

```
{
}
```

* Не указано поле `code`:

```
{
    "name": "новый проект"
}
```

* Не указано поле `name`:

```
{
    "code": "1231231231"
}
```

## Ожидаемый результат

Вернется ответ со статус кодом `400` и с сообщением о том, что данные отсутствуют.

## Фактический результат


* Данные отсутствуют:

```
{
    "statusCode": 400,
    "message": "Field name not filled.,Field code not filled."
}
```

* Не указано поле `code`:

```
{
    "statusCode": 400,
    "message": "Field code not filled."
}
```

* Не указано поле `name`:

```
{
    "statusCode": 400,
    "message": "Field name not filled."
}
```
