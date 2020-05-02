<#import "parts/common.ftlh" as c>

<@c.page>
    <a href="/">
        <h1>AILP</h1>
    </a>
    <h1>${lessonTest.testName}</h1>
    <input id="_csrf" type="hidden" name="_csrf" value="${_csrf.token}"/>
    <input id="back" type="button" value="back" name="back">
    <input id="next" type="button" value="next" name="next">
    <div>
        <ul>
            <#list questions as question>
                <li id="${question.id}">${question.question}</li>
                <ul>
                    <#list question.answerList as answer>
                        <li id="${answer.id}">${answer.answer}</li>
                    </#list>
                </ul>
            </#list>
        </ul>
    </div>
</@c.page>