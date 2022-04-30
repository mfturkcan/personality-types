function test() {
    console.log("hello");
}

async function handleForm() {
    let answers = [];
    let formNode = document.getElementById("question-form");
    var form = new FormData(formNode);

    var values = [];

    for (var value of form.values()) {
        values.push(value);
    }

    formNode.reset();

    for (let i = 0; i < values.length; i++) {
        answers.push({
            question: values[i],
            questionAnswer: values[i + 1],
        });
        i++;
    }

    $.ajax({
        url: "http://localhost:8080/question/saveQuestions",
        type: "POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(answers),
        cache: false,
        success: function (msg) {
            console.log("sa");
            // location.reload();
        }
    });


    // answers.forEach(a => {
    //     formNode.append(`<input type="hidden" value="${JSON.stringify(a)}"/>`);
    // });

    // formNode.submit();
    // await fetch("/question/saveQuestions", {
    //     body: JSON.stringify(answers),
    //     method: "POST",
    //     headers: {
    //         'Content-Type': 'application/json'
    //         // 'Content-Type': 'application/x-www-form-urlencoded',
    //     },
    // });
}