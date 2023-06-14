# 46: Удаление проекта с задачами, командой, участниками команды

|    Автор    |    Дата    | Средства тестирования | Результат |
|:-----------:|:----------:|:---------------------:|:---------:|
| Наумовец П. | 2023-06-14 |   Postman v10.14.9    |  Прошел   |

## Описание

Удалить можно только тот проект, на который не имеют ссылки другие таблицы в базе данных. Перед тем как удалить проект 
должны быть удалены:

* Задачи по проекту
* Команда проекта
* Участники команды

## Предусловия

Проект с атрибутами:

* code - `1998955`
* name - `плагин для jira`
* description - `Медиаплан недостаточно переворачивает экспериментальный рейтинг, используя опыт предыдущих кампаний`
* status - `черновик`

, есть в базе данных. Менеджер с правами администратора авторизован в системе. Не удалены задачи прикрепленные к проекту,
команда проекта и участники команды.

## Шаги в postman

* Зайти в коллекцию `projectMenegementSystem`
* Перейти в папку `Project`
* Выбрать запрос `DeleteById`
* Нажать `Send`

Выполнится HTTP запрос с методом `DELETE` на endpoint `http://localhost:8080/pms/api/v1/project/1`.

## Ожидаемый результат

Вернется ответ со статус кодом `400` и сообщением, что на проект есть ссылки в других таблицах

## Фактический результат

```
{
    "statusCode": 400,
    "message": "ERROR: update or delete on table \"project\" violates foreign key constraint \"task_project_id_fkey\" on table \"task\"\n  Подробности: Key (id)=(1) is still referenced from table \"task\"."
}
```