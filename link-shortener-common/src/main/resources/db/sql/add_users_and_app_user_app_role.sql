INSERT into app_user (username, first_name, last_name, password, is_account_non_expired,
                      is_account_non_locked, is_credentials_non_expired, is_enabled)
VALUES ('johndoe', 'John', 'Doe', '$2a$10$fr2D4oZ7mvAvpQVcKePSI.yzzbmFKJA94d/bj7gr73boKxAOL0z2C', true, true, true, true),
       ('janedoe', 'Jane', 'Doe', '$2a$10$fZ16FbVa2lioJxuQsKZyV.rU19Pv0MNdQolmZGmLIqAvt9BINGjh6', true, true,true, true);

INSERT into app_user_app_role (username, role_id)
VALUES ('johndoe', 'ROLE_ADMIN'),
       ('janedoe', 'ROLE_GUEST');