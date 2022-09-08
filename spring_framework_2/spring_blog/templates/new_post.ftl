<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add new post</title>
</head>
<body>
    <form action="/posts/new" method="post">
        <p>Title: <input type="text" name="title"></p>
        <p>Content: <textarea name="content" cols="30" rows="10"></textarea></p>
        <p>Category: <input type="text" name="category"></p>
        <p><button type="submit">Save</button></p>
    </form>
</body>
</html>