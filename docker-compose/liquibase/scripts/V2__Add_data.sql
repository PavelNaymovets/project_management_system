insert into employee (personal_number, last_name, first_name, middle_name, "position", login, email, status, password)
values ('384f508c-3016-3bf1-9334-6ccc1ca21c36', 'Прохорова', 'Инна', 'Андреевна', 'инженер ui тестирования', 'vil53', 'vil534@example.com', 'активный', '$2a$12$Pq7p4UGq08oKp.xCBGddd.CLE41cvfFgFjdWrW0hvr5ZFex4.LkoW'),
       ('5190a23e-98ac-3e82-88af-9c5cdd7ca811', 'Селезнёва', 'Ярослава', 'Борисовна', 'бизнес аналитик', 'zlata.rybakov', 'zlata.rybakov69@example.com', 'активный', '$2a$12$R41MrhAZFaGQCXaK4PFPqOp.vkpISDQTTy..sOIm5sAAlmC.Lkaj6'),
       ('22a7edc2-d9a3-3bc5-9241-c6922a7a45c1', 'Ермаков', 'Назар', 'Максимович', 'java разработчик', 'popova.oleg', 'popova.oleg31@example.com', 'активный', '$2a$12$nC0mOhkf.VBNeoNL4icswOfQ0GFrKGqWAOizfKudunvPd/aOUe.cG'),
       ('d4a5e6ff-8df2-377a-bd45-a7a698f76afb', 'Виноградова', 'Маргарита', 'Сергеевна', 'тим лид', 'romanova.dina', 'romanova.dina56@example.com', 'активный', '$2a$12$qbKPpKlxZ8L0J0cOr3PpG.qQ/0wAEZRQqWBGXYtsWxszhfjB3ar46'),
       ('b62a18bf-53a0-3f30-9f28-30c7bf10f96d', 'Алексеев', 'Аркадий', 'Иванович', 'java разработчик', 'akov.efremov', 'akov.efremov25@example.com', 'активный', '$2a$12$37aLWLHgzu1dt2Pa.Z8zLO4MrKpig4kMRrXlJyrI5pVydqMZTbiaG'),
       ('b2sd18bf-5fa0-3430-9218-30cios5sf96d', 'Иванов', 'Иван', 'Иванович', 'java разработчик', 'Ivanov.ivan', 'ivan.ivanov41@example.org', 'удаленный', '$2a$12$9.0ouDrO6AdbVHF7NYZS0.1Kf/J34OMOSOAW71Iqc9FwC5MiavDx6');

insert into project (code, name, description, status)
values ('1220680', 'система управления проектами', 'Взаимодействие корпорации и клиента традиционно специфицирует ролевой анализ зарубежного опыта Баинг и селлинг, безусловно, экономит продвигаемый повторный контакт', 'в разработке'),
       ('1713085', 'система обмена сообщениями', 'Селекция бренда решительно изменяет институциональный медиавес Опрос отражает институциональный медиамикс', 'завершен'),
       ('1998955', 'плагин для jira', 'Медиаплан недостаточно переворачивает экспериментальный рейтинг, используя опыт предыдущих кампаний', 'черновик'),
       ('1684317', 'вычисление квадратных уравнений', 'Потребление отражает системный анализ, оптимизируя бюджеты Целевая аудитория пока плохо трансформирует рекламный блок', 'в тестировании');

insert into task (name, description, project_id, employee_id, labor_cost, dead_line, status, author_id)
values ('Подготовить Liquebase-скрипт', 'Переложить скрипт, созданный в п.2 на Liquebase-скрипт(xml/yaml что хотите). В этом ДЗ запускать не требуется', '1', '1', '3', '2029-02-09', 'новая', '1'),
       ('Создать физическую модель БД в виде диаграммы', 'На модели должны быть отражены таблицы(сущности) с необходимыми аттрибутами(в том числе типами) и связи между таблицами', '1', '1', '1', '2028-12-20', 'выполнена', '2'),
       ('Написать и отладить SQL-скрипты для создания схемы БД', ' Создать файлик schema.sql(в resources). В нем должны быть скрипты на создание схемы БД с "нуля"', '1', '2', '6', '2025-04-11', 'в работе', '2'),
       ('Создать документ с описанием таблиц, колонок и тп', 'Создать файлик shema.md(в resources либо в каталоге docs). В нем должно краткое описание(что такое, для чего) всех таблиц и аттрибутов', '3', '4', '6', '2028-11-27', 'новая', '3'),
       ('Реализовать базовые операции над сущностями с помощью JDBC', 'Выбрать какую-нибудь сущность(модель) и реализовать для нее CRUD операции + поиск', '3', '3', '5', '2026-03-14', 'закрыта', '1');

insert into team (project_id)
values (1),
       (2),
       (3);

insert into team_member (team_id, employee_id, role)
values (1, 1, 'тестировщик'),
       (1, 2, 'аналитик'),
       (1, 3, 'разработчик'),
       (1, 4, 'руководитель проекта'),
       (1, 5, 'разработчик'),
       (3, 1, 'аналитик'),
       (3, 2, 'тестировщик'),
       (3, 3, 'руководитель проекта'),
       (3, 4, 'разработчик');

insert into role (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN'),
       ('ROLE_MANAGER');

insert into employee_role (employee_id, role_id)
values (1, 1),
       (2, 1),
       (3, 1),
       (3, 3),
       (4, 1),
       (4, 2),
       (4, 3),
       (5, 1),
       (5, 2);