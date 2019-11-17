axios.get('https://localhost:8080/queues/')
    .then(response => {
        var queues = response.data._embedded.queueList;
        queues.forEach(queue => {
            $('#services').append(`<option>` + queue.name + `</option>`);
        });
        localStorage.setItem('queues', JSON.stringify(queues));
    })

function saveTurn(turn) {
    axios.post("https://localhost:8080/turns", turn)
        .then(response => {
            alert('Turn successfully created.')
        });
}

function request() {
    var queue = JSON.parse(localStorage.getItem('queues')).find(queue => {
        return queue.name == $('#services').val();
    })
    axios.get('https://localhost:8080/turns/count?queue=' + queue.name)
        .then(response => {
            var code = queue.identifier + (response.data + 1);
            console.log('Code: ' + code);
            var turn = {
                code: code,
                clientName: $('#name').val(),
                requestedDateTime: new Date(),
                attendedDateTime: null,
                queue: queue,
                attended: false,
                cancelled: false,
                attentionPoint: null
            }
            saveTurn(turn);
        });
}