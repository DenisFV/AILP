<#import "parts/common.ftlh" as c>

<@c.page>
    <a href="/">
        <h1>AILP</h1>
    </a>
    <h1>${lessonTest.testName}</h1>
    <input id="back" type="button" value="back" name="back">
    <input id="next" type="button" value="next" name="next">
    <form id="form">
        <input id="_csrf" type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input id="test_id" type="hidden" name="test_id" value="${lessonTest.id}"/>
        <div>
            <ul>
                <#list questions as question>
                    <li id="question_${question.id}">${question.question}</li>
                    <#switch question.answerOption>
                        <#case 'mult'>
                            <#list question.answerList as answer>
                                <p>
                                    <input type="checkbox" id="answer_${answer.id}" name="question_${question.id}" value="answer_${answer.id}">
                                    <label for="answer_${answer.id}">${answer.answer}</label>
                                </p>
                            </#list>
                            <#break>
                        <#case 'one'>
                            <#list question.answerList as answer>
                                <p>
                                    <input type="radio" id="answer_${answer.id}" name="question_${question.id}" value="answer_${answer.id}">
                                    <label for="answer_${answer.id}">${answer.answer}</label>
                                </p>
                            </#list>
                            <#break>
                        <#case 'text'>
                            <#list question.answerList as answer>
                                <p><input type="text" id="answer_${answer.id}" name="question_${question.id}"></p>
                            </#list>
                            <#break>
                    </#switch>
                </#list>
            </ul>
        </div>
        <input id="save" type="button" value="save" name="save">
    </form>

    <script>
        $('#save').on('click', function () {

            var form = $('#form').serializeArray();
            var testResultDtoList = createResultTest(form);

            $.ajax({
                url: '/test-result/1/2',
                type: 'POST',
                data: JSON.stringify(testResultDtoList),
                contentType: 'application/json',
                beforeSend: function (xhr) {
                    xhr.setRequestHeader('X-CSRF-TOKEN', $('#_csrf').val());
                },
                success: function (e) { console.log(e); },
                error: function (msg) { console.log(msg); }
            });
        });

        function createResultTest(form) {

            var testResultDtoList = [];

            jQuery.each(form, function (i, val) {
                if (/question_/i.test(val.name)) {
                    var testResultDto = {
                        testId: $('#test_id').val(),
                        questionId: val.name.replace('question_', ''),
                        answerId: /answer_/i.test(val.value) ? val.value.replace('answer_', '') : null,
                        result: /answer_/i.test(val.value) ? null : val.value
                    };

                    testResultDtoList.push(testResultDto);
                }
            });

            return testResultDtoList;
        }
    </script>
</@c.page>