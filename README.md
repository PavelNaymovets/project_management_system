# Тестовое задание

### Описание
Реализовать некоторое подобие языка управления данными в коллекции. Основные команды, которые должны поддерживаться:
* Вставка элементов в коллекцию
* Удаление элемента из коллекции
* Поиск элементов в коллекции
* Изменение элементов в коллекции

Структура коллекции - таблица, в которой есть наименования колонок и каждая строчка в таблице это элемент коллекции. Пример:

![Image alt](https://github.com/PavelNaymovets/interview_task_digDes/blob/master/image/%D0%9F%D1%80%D0%B8%D0%BC%D0%B5%D1%80%20%D1%82%D0%B0%D0%B1%D0%BB%D0%B8%D1%86%D1%8B.png)

### Требования
Подробный список требований представлен по _[ссылке](https://docs.google.com/document/d/1QNtu5L3ppvNF-o06ho7eU4jm1R2MQK-For_DWnNZRRE/edit)_.

#### Основные тебования:
* Необходимо реализовать метод, который на вход получает команду в виде строки
* Команда должна выполнять четыре основные операции:

```
INSERT - вставка элемента в коллекцию
UPDATE - изменение элемента в коллекции
DELETE - удаление элемента из коллекции
SELECT - поиск элементов в коллекции
```

* При изменении, удалении и поиске должны поддерживаться условия выборки из коллекции
* На выход список элементов в коллекции, которые были: найдены, изменены, добавлены, удалены

## Решение
#### Стэк: OpenJDK 18
#### Полезные ссылки
* Видео с подробным объяснением работы программы и её структуры можно посмотреть по ссылке на ютюб _[тут](https://youtu.be/HOmlhfF05-Y)_
* Ведо с демонстрацией работы программы можно посмотреть по ссылке на ютюб _[тут](https://youtu.be/kDTdmCD9TwU)_
* Условная схема поясняющая работу приложения:

![Image alt](https://github.com/PavelNaymovets/interview_task_digDes/blob/master/image/%D0%A3%D1%81%D0%BB%D0%BE%D0%B2%D0%BD%D0%B0%D1%8F%20%D1%81%D1%85%D0%B5%D0%BC%D0%B0%20%D1%80%D0%B0%D0%B1%D0%BE%D1%82%D1%8B%20%D0%BF%D1%80%D0%B8%D0%BB%D0%BE%D0%B6%D0%B5%D0%BD%D0%B8%D1%8F.png)

Далее представлено краткое описание структуры и возможностей программы.

#### Приложение - консольное:
В консоль можно писать запрос в текстовом виде. Пример:

```
INSERT VALUES ‘lastName’ = ‘fiodorow’ , ‘id’=3, ‘age’=40, ‘active’=true
```

В ответ на запрос, в консоль отрисуется таблица с параметрами. Пример:

```
draw 1. Table users
-----------------------------------------------------------
|   id   |   lastname   |   age   |   cost   |   active   |
-----------------------------------------------------------
|   3    |   fiodorow   |   40    |   null   |    true    |<-modified row
-----------------------------------------------------------
```

`<-modified row` - указатель на строку в которой произошли изменения согласно запросу.

Если в запросе есть ошибка, то сообщение об ошибке в запросе выводится в консоль. Пример:

```
INSERT VALUES ‘lastame’ = ‘fiodorow’ , ‘id’ = 3, ‘age’ = 40, ‘active’ = true

Error query. No correct param: lastame
Valid request example: SELECT id WHERE age >= 30 and lastName like %wordPart%
Possible problems: spelling, no blanks    ^  ^                    ^
```

#### Программа поддерживает:
* Запросы со всеми математическими параметрами согласно требованиями. В том числе с `or` и `and`. Пример:

```
SELECT WHERE id > 1 and age > 40 or active = true
draw 1. Table users
-----------------------------------------------------------
|   id   |   lastname   |   age   |   cost   |   active   |
-----------------------------------------------------------
|   3    |   fiodorow   |   40    |   null   |    true    |
-----------------------------------------------------------
```

* Значения, которые передаются на сравнение, не могут быть `null`. При записи значения колонки может быть `null`. 
Т.е. `‘age’ >= null` считаем, что такого не может быть. А `UPDATE VALUES ‘age’ = null` - может быть, в этом  случае значение из ячейки удаляется. Пример:

```
SELECT WHERE id > null, age > 40 or active = true

Error query. Param can not be null:id
Valid request example: SELECT id WHERE age >= 30 and lastName like %wordPart%
Possible problems: spelling, no blanks    ^  ^                    ^

UPDATE VALUES ‘active’ = null, ‘cost’ = 10.1 where ‘id’ = 3 or id = 1
draw 1. Table users
-----------------------------------------------------------
|   id   |   lastname   |   age   |   cost   |   active   |
-----------------------------------------------------------
|   3    |   fiodorow   |   40    |   10.1   |    null    |<-modified row
-----------------------------------------------------------
```

* Если значение в ячейке например `age = null`, а на вход передается условие типа `‘age’ != 0`. То запрос считается корректным, `0 != null`. Пример:

```
draw 1. Table users
-----------------------------------------------------------
|   id   |   lastname   |   age   |   cost   |   active   |
-----------------------------------------------------------
|   3    |   fiodorow   |   40    |   10.1   |    null    |
-----------------------------------------------------------
|   1    |   fiodorow   |  null   |   null   |    true    |
-----------------------------------------------------------
|   2    |   fiodorow   |    0    |   null   |    true    |
-----------------------------------------------------------

SELECT WHERE age != 0
draw 1. Table users
-----------------------------------------------------------
|   id   |   lastname   |   age   |   cost   |   active   |
-----------------------------------------------------------
|   3    |   fiodorow   |   40    |   10.1   |    null    |
-----------------------------------------------------------
|   1    |   fiodorow   |  null   |   null   |    true    |
-----------------------------------------------------------
```

## Структура проекта
Проект состоит из 1 модуля, который содержит:
* _Коллекцию_ - для хранения данных в виде таблицы
* _Сервис_  - для управления данными в коллекции

## Коллекция
Данные хранятся в виде таблицы, которую описывает класс _[Table](https://github.com/PavelNaymovets/interview_task_digDesJavaSchool/blob/master/src/main/java/com/digdes/school/entity/Table.java)_.
У таблицы есть: имя `String name`, структура `List<String> schema` и коллекция `List<Map<String, Object>> data` в которой расположены данные. Таблица реализована
с применением свойств паттерна `singleton`, т.к. в программе нужен 1 объект для хранения данных на время работы программы
с доступом к данным из любой точки программы.

## Сервис
Сервис состоит из пакетов:
* exception
* execution
* repository
* service
* utils

Далее представленно краткое описание содержания и назначения пакетов и их классов.
### exception
Пользовательские исключения. Классы:
* _[QueryValidationException](https://github.com/PavelNaymovets/interview_task_digDesJavaSchool/blob/master/src/main/java/com/digdes/school/exception/QueryValidationException.java)_ - ошибка в запросе
* _[TableNotCreatedException](https://github.com/PavelNaymovets/interview_task_digDesJavaSchool/blob/master/src/main/java/com/digdes/school/exception/TableNotCreatedException.java)_ - таблица не создана

### execution
Выполнения запроса к данным в таблице. Классы:
* _[QueryExecutorImpl](https://github.com/PavelNaymovets/interview_task_digDesJavaSchool/blob/master/src/main/java/com/digdes/school/execution/QueryExecutorImpl.java)_ - принимает запрос, разбивает на массив слов, убирает лишние символы, проводит первичную валидацию селекторов, ключевых слов, параметров вставки, условных параметры на корректность написания

### repository
Работа с коллекцией. Классы:
* _[TableRepositoryCRUDImpl](https://github.com/PavelNaymovets/interview_task_digDesJavaSchool/blob/master/src/main/java/com/digdes/school/repository/TableRepositoryCRUDImpl.java)_ - выполняет CRUD операции: SELECT, INSERT, UPDATE, DELETE
* _[Query](https://github.com/PavelNaymovets/interview_task_digDesJavaSchool/blob/master/src/main/java/com/digdes/school/repository/query/Query.java)_ - содержит поля и методы общие для CRUD операций. От этого класса наследуются классы, описывающие CRUD запросы к коллекции
* _[SelectQuery](https://github.com/PavelNaymovets/interview_task_digDesJavaSchool/blob/master/src/main/java/com/digdes/school/repository/query/SelectQuery.java)_ - наследник Query. Выборка данных(SELECT)
* _[InsertQuery](https://github.com/PavelNaymovets/interview_task_digDesJavaSchool/blob/master/src/main/java/com/digdes/school/repository/query/InsertQuery.java)_ - наследник Query. Вставка данных(INSERT)
* _[UpdateQuery](https://github.com/PavelNaymovets/interview_task_digDesJavaSchool/blob/master/src/main/java/com/digdes/school/repository/query/UpdateQuery.java)_ - наследник Query. Обновление данных(UPDATE)
* _[DeleteQuery](https://github.com/PavelNaymovets/interview_task_digDesJavaSchool/blob/master/src/main/java/com/digdes/school/repository/query/DeleteQuery.java)_ - наследник Query. Удаление данных(UPDATE)

### service
Подготовка запроса: валидация, работа с параметрами. Классы:
* _[TableServiceImpl](https://github.com/PavelNaymovets/interview_task_digDesJavaSchool/blob/master/src/main/java/com/digdes/school/service/TableServiceImpl.java)_ - валидирует запрос и в зависимости от селектора направляет на выполнение одной из CRUD операции: SELECT, INSERT, UPDATE, DELETE
* _[ParamAuthenticatorImpl](https://github.com/PavelNaymovets/interview_task_digDesJavaSchool/blob/master/src/main/java/com/digdes/school/service/authenticator/ParamAuthenticatorImpl.java)_ - с помощью вспомогательных классов разбивает запрос на массивы: параметры вставки(params), параметры условий(whereParams), подсчитывает количество условий(requestConditionsCount)
* _[ParamCollectorImpl](https://github.com/PavelNaymovets/interview_task_digDesJavaSchool/blob/master/src/main/java/com/digdes/school/service/collector/ParamCollectorImpl.java)_ - вспомогательный класс. Разбивает запрос на массивы: параметры вставки - params, параметры условий - whereParams
* _[ParamCounterImpl](https://github.com/PavelNaymovets/interview_task_digDesJavaSchool/blob/master/src/main/java/com/digdes/school/service/counter/ParamCounterImpl.java)_ - вспомогательный класс. Подсчитывает количество условий в запросе и складывает их в массив - requestConditionsCount. В дальнейшем, при выполнении запроса в классе _TableRepositoryCRUDImpl_, проверяются и подсчитываются параметры запроса. Если значения подсчитыванных параметров в запросе и после проверки выполнения условий совпадают - запрос выполняется

### utils
Дополнительный функционал. Классы:
* _[ConsoleReaderImpl](https://github.com/PavelNaymovets/interview_task_digDesJavaSchool/blob/master/src/main/java/com/digdes/school/utils/input/ConsoleReaderImpl.java)_ - читает запрос из консоли
* _[ConsoleWriterImpl](https://github.com/PavelNaymovets/interview_task_digDesJavaSchool/blob/master/src/main/java/com/digdes/school/utils/output/ConsoleWriterImpl.java)_ - пишет ответ в консоль в виде таблицы с данными из коллекции
* _[TableWriterImpl](https://github.com/PavelNaymovets/interview_task_digDesJavaSchool/blob/master/src/main/java/com/digdes/school/utils/output/table/TableWriterImpl.java)_ - рисует таблицу. Применен паттерн `decorator` для удобного разделения функций рисования между классами и дальнейшего расширения функционала
* _[PrinterImpl](https://github.com/PavelNaymovets/interview_task_digDesJavaSchool/blob/master/src/main/java/com/digdes/school/utils/output/table/decorator/PrinterImpl.java)_ - `decorator`. Вывод значений параметров в консоль
* _[LeftBorder](https://github.com/PavelNaymovets/interview_task_digDesJavaSchool/blob/master/src/main/java/com/digdes/school/utils/output/table/decorator/LeftBorder.java)_ - `decorator`. Рисование левой границы от параметра
* _[RightBorder](https://github.com/PavelNaymovets/interview_task_digDesJavaSchool/blob/master/src/main/java/com/digdes/school/utils/output/table/decorator/RightBorder.java)_ - `decorator` - Рисование правой границы от параметра
* _[HorizontalBorder](https://github.com/PavelNaymovets/interview_task_digDesJavaSchool/blob/master/src/main/java/com/digdes/school/utils/output/table/decorator/HorizontalBorder.java)_ - рисование горизонтальной линии
* _[TableCounterImpl](https://github.com/PavelNaymovets/interview_task_digDesJavaSchool/blob/master/src/main/java/com/digdes/school/utils/output/table/counter/TableCounterImpl.java)_ - подсчет общей ширины таблицы, подсчет максимальной ширины столбца для каждого из параметров таблицы. Эти значения нужны для отрисовки красивой таблицы и размещения параметров в середине ячейки
* _[QueryValidatorImpl](https://github.com/PavelNaymovets/interview_task_digDesJavaSchool/blob/master/src/main/java/com/digdes/school/utils/validator/QueryValidatorImpl.java)_ - валидация запроса. Проверка на корректность написания селекторов, ключевых слов, параметров и математических операторов

## Запуск приложения
* Приложение запускается из класса _[JavaSchoolStarter](https://github.com/PavelNaymovets/interview_task_digDesJavaSchool/blob/master/src/main/java/com/digdes/school/JavaSchoolStarter.java)_

## Логирование
Приложение логирует ответ на запрос в виде таблицы и возможные ошибки в запросе в консоль. Классы устаствующие в логировании действий:
* [ConsoleReaderImpl](https://github.com/PavelNaymovets/interview_task_digDesJavaSchool/blob/master/src/main/java/com/digdes/school/utils/input/ConsoleReaderImpl.java) - читает запрос из консоли
* [ConsoleWriterImpl](https://github.com/PavelNaymovets/interview_task_digDesJavaSchool/blob/master/src/main/java/com/digdes/school/utils/output/ConsoleWriterImpl.java) - пишет ответ в консоль в виде таблицы с данными из коллекции