insert into users (avatar_url, email, password, username) values
    ('/avatar/url/1', 'delopata1@gmail.com', 'test1234', 'Mikola 1'),
    ('/avatar/url/2', 'delopata2@gmail.com', 'test1234', 'Mikola 2'),
    ('/avatar/url/3', 'delopata3@gmail.com', 'test1234', 'Mikola 3');

insert into posts (created_at, description, title, user_id) values
    (now(), 'Some description in the post 1', 'Test post 1', 1),
    (now(), 'Some description in the post 2', 'Test post 2', 1),
    (now(), 'Some description in the post 3', 'Test post 3', 2),
    (now(), 'Some description in the post 4', 'Test post 4', 3);