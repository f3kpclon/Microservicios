INSERT INTO user (user_name, password,enabled,name,last_name,email) VALUES ('FSotelo','$2a$10$jB.u.vsXZwvZt7SPU0953ucN6puJymFbgFEJqMMquHMsVOpAuRFO2',true ,'Felix','Sotelo','fsotelo@sotelo.com')
INSERT INTO user (user_name, password,enabled,name,last_name,email) VALUES ('CLoayza','$2a$10$P6IGz6m3v0MNFtUmChYg0O0MMEFfyDiCk39zR.sCez1pdtqJ0IgPq',true ,'Cindy','Loayza','floayza@sotelo.com')

INSERT INTO  rol(role) VALUES ('ROLE_USER')
INSERT INTO  rol(role) VALUES ('ROLE_ADMIN')

INSERT INTO user_rol(user_id,rol_id) VALUES (1,1)
INSERT INTO user_rol(user_id,rol_id) VALUES (2,2)
INSERT INTO user_rol(user_id,rol_id) VALUES (2,1)
--INSERT INTO `user_rol`(user_id,rol_id) VALUES (1,2)