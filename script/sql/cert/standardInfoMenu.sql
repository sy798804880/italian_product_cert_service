-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(1929789611698278401, '标准信息', '1927642988125741058', '1', 'standardInfo', 'masterData/standardInfo/index', 1, 0, 'C', '0', '0', 'masterData:standardInfo:list', '#', 103, 1, sysdate(), null, null, '标准信息菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(1929789611698278402, '标准信息查询', 1929789611698278401, '1',  '#', '', 1, 0, 'F', '0', '0', 'masterData:standardInfo:query',        '#', 103, 1, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(1929789611698278403, '标准信息新增', 1929789611698278401, '2',  '#', '', 1, 0, 'F', '0', '0', 'masterData:standardInfo:add',          '#', 103, 1, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(1929789611698278404, '标准信息修改', 1929789611698278401, '3',  '#', '', 1, 0, 'F', '0', '0', 'masterData:standardInfo:edit',         '#', 103, 1, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(1929789611698278405, '标准信息删除', 1929789611698278401, '4',  '#', '', 1, 0, 'F', '0', '0', 'masterData:standardInfo:remove',       '#', 103, 1, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(1929789611698278406, '标准信息导出', 1929789611698278401, '5',  '#', '', 1, 0, 'F', '0', '0', 'masterData:standardInfo:export',       '#', 103, 1, sysdate(), null, null, '');
