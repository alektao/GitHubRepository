<!DOCTYPE html>
<html>
<head>
    <title>X Pluz Z</title>

    <link href="css/bootstrap.css" rel="stylesheet">

</head>

<body>
<g:each in="${contents}" var="content">
    <div class="has-feedback">
        <g:img uri="${content.userImg}"/>
        <table>
            <tr>Name : ${content.userScreenName}</tr>
            <tr>Friends : ${content.userFriendsCount}    Follower : ${content.userFollowerCount}     Statuses : ${content.userStatusesCount}</tr>
        </table>
        <div class="bg-info">
            WeiBo :${content.text}
        </div>
    </div>
</g:each>
<g:paginate total="${contents.size()}" max="1" params="${contents}"/>

</body>
</html>
