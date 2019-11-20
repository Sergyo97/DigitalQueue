
var loading = document.getElementById('loading');
var mensaje = document.getElementById('mensaje');
var boton = document.getElementById('json_post');


axios.get('https://localhost:8443/queues/')
    .then(response => {
        var queues = response.data._embedded.queueList;

        queues.forEach(queue => {
            $('#services').append(`<option>` + queue.name + `</option>`);
        });
        localStorage.setItem('queues', JSON.stringify(queues));
    });


axios.get('https://localhost:8443/users/')
    .then(response => {
        var users = response.data._embedded.userList;

        users.forEach(user => {
            $('#users').append(`<option>` + user.email + `</option>`);
        });
        localStorage.setItem('users', JSON.stringify(users));
    });

axios.get('https://localhost:8443/attentionPoints')
    .then(response => {
        mydata = response.data;
        mydata = mydata._embedded.attentionPointList;
        mydata.forEach(attentionPoint => {
            $('#attentionPointsTable').append(`
                <tr>
                    <td>` + attentionPoint.code + `</td>
                    <td>` + (attentionPoint.enable ? 'True' : 'False') + `</td>
                    <td>` + attentionPoint.user.name + `</td>
                    <td>` + "1" + `</td>
                    <td>
                        <button type="button" onclick="deleteAttentionPoint(`+ attentionPoint.id + `)" class="btn btn-danger">
                            <i class="far fa-trash-alt"></i>
                        </button>
                    </td>

                </tr>
            `)
        });
    })
    .catch(e => {
        // Capturamos los errores
    }
    )

boton.addEventListener('click', function () {
    loading.style.display = 'block';

    var agent = JSON.parse(localStorage.getItem('users')).find(user => {
        return user.email == $('#users').val()
    });
    var queue = JSON.parse(localStorage.getItem('queues')).find(queue => {
        return queue.name == $('#services').val()
    })
    var attentionPoint = {
        code: $('#recipient-code').val(),
        user: agent,
        queue: queue,
        enable: true
    }
    console.log(attentionPoint);
    axios.post('https://localhost:8443/attentionPoints', attentionPoint)
        .then(res => {
            if (res.status == 201) {
                mensaje.innerHTML = 'El nuevo Post ha sido almacenado con id: ' + res.data.id;
            }
        })
        .catch(function (err) {
            console.log(err);
        })
});


function deleteAttentionPoint(id) {
    axios.delete("https://localhost:8443/attentionPoints/" + id)
        .then(function (response) {
            window.location.reload
        })

}
