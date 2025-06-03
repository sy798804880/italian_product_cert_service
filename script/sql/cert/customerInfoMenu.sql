-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(1929789611371122690, '客户信息', '1927642988125741058', '1', 'customerInfo', 'masterData/customerInfo/index', 1, 0, 'C', '0', '0', 'masterData:customerInfo:list', '#', 103, 1, sysdate(), null, null, '客户信息菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(1929789611371122691, '客户信息查询', 1929789611371122690, '1',  '#', '', 1, 0, 'F', '0', '0', 'masterData:customerInfo:query',        '#', 103, 1, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(1929789611371122692, '客户信息新增', 1929789611371122690, '2',  '#', '', 1, 0, 'F', '0', '0', 'masterData:customerInfo:add',          '#', 103, 1, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(1929789611371122693, '客户信息修改', 1929789611371122690, '3',  '#', '', 1, 0, 'F', '0', '0', 'masterData:customerInfo:edit',         '#', 103, 1, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(1929789611371122694, '客户信息删除', 1929789611371122690, '4',  '#', '', 1, 0, 'F', '0', '0', 'masterData:customerInfo:remove',       '#', 103, 1, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(1929789611371122695, '客户信息导出', 1929789611371122690, '5',  '#', '', 1, 0, 'F', '0', '0', 'masterData:customerInfo:export',       '#', 103, 1, sysdate(), null, null, '');
