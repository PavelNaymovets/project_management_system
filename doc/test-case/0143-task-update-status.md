# 143: Обновление статуса задачи

|    Автор    |    Дата    | Средства тестирования | Результат |
|:-----------:|:----------:|:---------------------:|:---------:|
| Наумовец П. | 2023-06-15 |   Postman v10.14.9    |  Прошел   |

## Описание

Статус задачи может принимать следующие значения: новая, в работе, выполнена, закрыта.

## Предусловия

Задача с атрибутами:

* id - 4,
* name - 2 задача,
* description - Создать файлик shema.md(в resources либо в каталоге docs). В нем должно краткое описание(что такое, для чего) всех таблиц и аттрибутов,
* laborCosts - 11,
* deadline - 2024-05-24,
* status - выполнена

, есть в базе данных. Пользователь авторизован в системе.

## Шаги в postman

* Зайти в коллекцию `projectMenegementSystem`
* Перейти в папку `Task`
* Выбрать запрос `UpdateTaskStatus`
* Нажать `Send`

Выполнится HTTP запрос с методом `PUT` на endpoint `http://localhost:8080/pms/api/v1/task/status?id=2&status=закрыта`.
Выполнится HTTP запрос с методом `PUT` на endpoint `http://localhost:8080/pms/api/v1/task/status?id=4&status=закрыта`.
## Ожидаемый результат

Вернется ответ со статус кодом `200`. В базе данных статус задачи изменится.

## Фактический результат

* Автор является участником проекта:

![Image alt](https://github.com/PavelNaymovets/project_management_system/blob/develop/doc/test-case/screenshot/task/task_update_status.PNG)

* Автор не является участником проекта:

```
{
    "statusCode": 400,
    "message": "Employee is not project member. He can`t be author or employee for this task. Login: akov.efremov"
}
```