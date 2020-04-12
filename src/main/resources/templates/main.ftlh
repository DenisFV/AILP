<#import "parts/common.ftlh" as c>

<@c.page>
    <a href="/">
        <h1>AILP</h1>
    </a>
    <input id="_csrf" type="hidden" name="_csrf" value="${_csrf.token}"/>
    <input id="back" type="button" value="back" name="back">
    <input id="next" type="button" value="next" name="next">
    <div>
        <iframe width="560" height="315" src="https://www.youtube.com/embed/xzCEdSKMkdU" frameborder="0"
                allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen>
        </iframe>
    </div>

    <script>
        $('#back').on('click',function () {

            var event =  { eventType : this.type, eventName : this.name };

            $.ajax({
                url : '/ailp/event',
                type: 'POST',
                data: JSON.stringify(event),
                contentType: 'application/json; charset=utf-8',
                beforeSend: function(xhr) {
                    xhr.setRequestHeader('X-CSRF-TOKEN', $('#_csrf').val());
                },
                success: function (e) {
                    console.log(e);
                    var eventLogDto = { eventId : e.eventId, eventDate : new Date().toISOString().replace('Z','') };

                    $.ajax({
                        url : window.location.href,
                        type: 'POST',
                        data: JSON.stringify(eventLogDto),
                        contentType: 'application/json',
                        beforeSend: function(xhr) {
                            xhr.setRequestHeader('X-CSRF-TOKEN', $('#_csrf').val());
                        },
                        success: function (e) { console.log(e); },
                        error: function (msg) { console.log(msg); }
                    });
                },
                error: function (msg) { console.log(msg); }
            });
        });


        $('#next').on('click',function () {

        });

    </script>
</@c.page>