USE todolist;

-- insert into todo(text, completed) value('Task 1', false);
-- insert into todo(text, completed) value('Task 2', true);
-- insert into todo(text) value('Task 3');

select id, text, completed from todo;
-- select count(*) as count from todo;

update todo set text='Task A', completed=true where id=1;
update todo set text='Task A' where id=1;
update todo set completed=true where id=1;

-- -- delete from todo;