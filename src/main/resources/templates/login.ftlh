<form action="/login" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>

    <div>
        <div>
            <input type="text" id="username" name="username" placeholder="Логин">
            <script>
                var url = window.location.href.toString().includes('/login?error');
                if (url) document.write("<span style= \"color:#dc3545; font-size:80%\" >Неверный логин или пароль</span>");
            </script>
        </div>
    </div>
    <div>
        <div>
            <input type="text" id="password" name="password" placeholder="Пароль">
        </div>
    </div>
    <button type="submit" class="btn-secondary" style="margin-top: 6vh;">Войти</button>
</form>
