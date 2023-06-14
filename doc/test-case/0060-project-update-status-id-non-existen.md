# 60: Обновление статуса проекта с указанием несуществующего статуса или id

|    Автор    |    Дата    | Средства тестирования | Результат |
|:-----------:|:----------:|:---------------------:|:---------:|
| Наумовец П. | 2023-06-14 |   Postman v10.14.9    |  Прошел   |

## Описание

Статус проекта может принимать следующие значения: черновик, в разработке, в тестировании, завершен. Если будет указан
не существующий статус или id об этом вернется соответствующее сообщение.

## Предусловия

* id - 1
* code - `1220680`
* name - `система управления проектами`
* description - `Взаимодействие корпорации и клиента традиционно специфицирует ролевой анализ зарубежного опыта Баинг и селлинг, безусловно, экономит продвигаемый повторный контакт`
* status - `в разработке`,

, есть в базе данных. Менеджер авторизован в системе.

## Шаги в postman

* Зайти в коллекцию `projectMenegementSystem`
* Перейти в папку `Employee`
* Выбрать запрос `UpdateProjectStatus`
* Нажать `Send`

* Выполнится HTTP запрос с методом `GET` на endpoint `http://localhost:8080/pms/api/v1/project/status?id=10&status=черновик`. 
* Выполнится HTTP запрос с методом `GET` на endpoint `http://localhost:8080/pms/api/v1/project/status?id=1&status=sfdf`.

## Ожидаемый результат

Вернется ответ со статус кодом `400` и сообщением плохой запрос.

## Фактический результат

* Указан несуществующий id:

```
{
    "statusCode": 404,
    "message": "Project not found. ID: 10"
}
```

* Указан несуществующий status:

```
{
    "statusCode": 400,
    "message": "Field status incorrect."
}
```