<#import "parts/common.ftl" as c>

<@c.page>
    <a href="/">
        <h1>AILP</h1>
    </a>
    <input id="back" type="button" value="back" name="back">
    <input id="next" type="button" value="next" name="next">
    <div>
        <iframe width="560" height="315" src="https://www.youtube.com/embed/xzCEdSKMkdU" frameborder="0"
                allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen>
        </iframe>
    </div>

    <script>
        $('#back, #next').on('click',function (e) {
            // var userEventDto = { userEventId: 1, eventId: 1, userId: 1, eventDate: "1", pageId: 1};
            // var url = JSON.stringify({UserEventDto: userEventDto});

            var url = 'type='+e.valueOf().type+'&name='+e.valueOf().toElement.name+'&page=1&eventDate='+new Date().toISOString().replace('Z','');
            $.ajax({
                url : 'ailp/',
                type: 'POST',
                data: url,
                // contentType: "application/json; charset=utf-8",
                // dataType: "json",
                success: function () {
                    console.log('ok');
                },
                error: function (msg) {
                    console.log(msg);
                }
            });
        });
    </script>
</@c.page>