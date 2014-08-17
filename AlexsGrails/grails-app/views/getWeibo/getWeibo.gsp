<!DOCTYPE html>
<html>
<head>
    <title>X Pluz Z</title>

    <link href="css/bootstrap.css" rel="stylesheet">

</head>

<body>
<g:each in="${contents}" var="content">
    <div class="has-feedback">
        <div class="table">
            <table>
                <tr>
                    <td><g:img uri="${content.userImg}"/></td>
                    <td>
                        <table>
                            <tr><td>Name : ${content.userScreenName}</td></tr>
                            <tr><td>Friends : ${content.userFriendsCount}    Follower : ${content.userFollowerCount}     Statuses : ${content.userStatusesCount}</td></tr>
                        </table>
                    </td>
                </tr>
            </table>
        </div>
        <div class="bg-info">
            WeiBo :${content.text}
        </div>
        <br/>
    </div>
</g:each>
<g:paginate total="${contents.size()}" max="1" params="${contents}"/>

</body>
</html>
