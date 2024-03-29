# 34: Создание проекта

|    Автор    |    Дата    | Средства тестирования | Результат |
|:-----------:|:----------:|:---------------------:|:---------:|
| Наумовец П. | 2023-06-13 |   Postman v10.14.9    |  Прошел   |

## Описание

При создании проекта должны быть заполнены все обязательные поля:

* id - уникальный идентификатор в таблице `обязательное поле`
* code - уникальный идентификатор во всей программе `обязательное поле`
* name - название `обязательное поле`
* description - описание
* status - статус (черновик, в разработке, в тестировании, завершен) `обязательное поле`
* created_at - дата создания записи
* updated_at - дата обновления записи

, проект создается в статусе `черновик`.

## Предусловия

Проекта с атрибутами:

* code - `1231231231`
* name - `новый проект`

,нет в базе данных. Менеджер авторизован в системе.

## Шаги в postman

* Зайти в коллекцию `projectMenegementSystem`
* Перейти в папку `Project`
* Выбрать запрос `CreateProject`
* Нажать `Send`

Выполнится HTTP запрос с методом `POST` на endpoint `http://localhost:8080/pms/api/v1/project`. Тело запроса:

```
{
    "code": "1231231231",
    "name": "новый проект"
}
```

## Ожидаемый результат

В базе данных, в таблице `project`, появилась строка с данными нового проекта. Вернется ответ со статус кодом `200` и 
dto созданного проекта.

## Фактический результат

Проект добавлен:

![Image alt](https://github.com/PavelNaymovets/project_management_system/blob/develop/doc/test-case/screenshot/project/project_create.PNG)

dto:

```
{
    "id": 5,
    "code": 1231231231,
    "name": "новый проект",
    "description": null,
    "status": "черновик"
}
```