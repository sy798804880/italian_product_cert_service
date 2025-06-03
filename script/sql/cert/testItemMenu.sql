-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(1929789612025434113, '测试项目', '1927642988125741058', '1', 'testItem', 'masterData/testItem/index', 1, 0, 'C', '0', '0', 'masterData:testItem:list', '#', 103, 1, sysdate(), null, null, '测试项目菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(1929789612025434114, '测试项目查询', 1929789612025434113, '1',  '#', '', 1, 0, 'F', '0', '0', 'masterData:testItem:query',        '#', 103, 1, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(1929789612025434115, '测试项目新增', 1929789612025434113, '2',  '#', '', 1, 0, 'F', '0', '0', 'masterData:testItem:add',          '#', 103, 1, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(1929789612025434116, '测试项目修改', 1929789612025434113, '3',  '#', '', 1, 0, 'F', '0', '0', 'masterData:testItem:edit',         '#', 103, 1, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(1929789612025434117, '测试项目删除', 1929789612025434113, '4',  '#', '', 1, 0, 'F', '0', '0', 'masterData:testItem:remove',       '#', 103, 1, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(1929789612025434118, '测试项目导出', 1929789612025434113, '5',  '#', '', 1, 0, 'F', '0', '0', 'masterData:testItem:export',       '#', 103, 1, sysdate(), null, null, '');
