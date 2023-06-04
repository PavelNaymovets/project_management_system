# Система управления проектами
## Структура базы данных
#### СУБД: PostgreSQL
### Описание структуры
Данные хранятся в 7 таблицах:
* _employee_ - данные сотрудников
* _project_ - данные о проекте
* _task_ - задачи для проекта
* _team_ - команда проекта
* _team_member_ - участники команды проекта
* _role_ - права сотрудника в программе
* _employee_role_ - назначение сотрдунику прав в программе

#### employee

![Image alt](https://github.com/PavelNaymovets/project_management_system/blob/develop/doc/arch/database/png/employee%20table%20diagram.png)

Атрибуты:
* id - уникальный идентификатор в таблице;
* personal_number - уникальный идентификатор во всей программе;
* last_name - фамилия;
* first_name - имя;
* middle_name - отчество;
* position - должность;
* login - учетная запись;
* email - электронная почта;
* status - статус сотрудника (1 - активный, 0 - удаленный);
* created_at - дата создания записи;
* updated_at - дата обновления записи;

#### project

![Image alt](https://github.com/PavelNaymovets/project_management_system/blob/develop/doc/arch/database/png/project%20table%20diagram.png)

Атрибуты:
* id - уникальный идентификатор в таблице;
* code - уникальный идентификатор во всей программе;
* name - название;
* description - описание;
* status - статус (черновик, в разработке, в тестировании, завершен);
* created_at - дата создания записи;
* updated_at - дата обновления записи;

#### task

![Image alt](https://github.com/PavelNaymovets/project_management_system/blob/develop/doc/arch/database/png/task%20table%20diagram.png)

Атрибуты:
* id - уникальный идентификатор в таблице;
* name - название;
* description - описание;
* project_id - ссылка на проект в котором поставлена задача;
* employee_id - ссылка на исполнителя;
* labor_costs - трудозатраты;
* dead_line - крайний срок выполнения;
* status - статус (новая, в работе, выполнена, закрыта);
* author_id - ссылка на автора;
* created_at - дата создания записи;
* updated_at - дата обновления записи;

#### team

![Image alt](https://github.com/PavelNaymovets/project_management_system/blob/develop/doc/arch/database/png/team%20table%20diagram.png)

Атрибуты:
* id - уникальный идентификатор в таблице;
* project_id - ссылка на проект, который делает команда;
* created_at - дата создания записи;
* updated_at - дата обновления записи;

#### team_member

![Image alt](https://github.com/PavelNaymovets/project_management_system/blob/develop/doc/arch/database/png/team%20member%20table%20diagram.png)

Атрибуты:
* id - уникальный идентификатор в таблице;
* team_id - ссылка на команду проекта в которой состоит сотрудник;
* employee_id - ссылка на сотрудника;
* role - роль сотрудника в проекте (руководитель проекта, аналитик, разработчик, тестировщик);
* created_at - дата создания записи;
* updated_at - дата обновления записи;

#### role

![Image alt](https://github.com/PavelNaymovets/project_management_system/blob/develop/doc/arch/database/png/role%20table%20diagram.png)

Атрибуты:
* id - уникальный идентификатор в таблице;
* name - название;

#### employee_role

![Image alt](https://github.com/PavelNaymovets/project_management_system/blob/develop/doc/arch/database/png/employee%20role%20table%20diagram.png)

Атрибуты:
* id - уникальный идентификатор в таблице;
* employee_id - ссылка на сотрудника;
* role_id - ссылка на роль;