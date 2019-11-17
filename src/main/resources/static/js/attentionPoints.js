
var loading = document.getElementById('loading');
var mensaje = document.getElementById('mensaje');
var boton = document.getElementById('json_post');

axios.get('https://localhost:8080/queues/')
    .then(response => {        
        var queues = response.data._embedded.queueList;

        queues.forEach(queue => {
            $('#services').append(`<option>` + queue.name + `</option>`);
        });
        localStorage.setItem('queues', JSON.stringify(queues));
    }
    );


axios.get('https://localhost:8080/users/')
    .then(response => {
        console.log(response)
        var users = response.data._embedded.userList;

        users.forEach(user => {
            $('#users').append(`<option>` + user.email+ `</option>`);
        });
        localStorage.setItem('users', JSON.stringify(users));
    }
    );

//http://localhost:8080/users
axios.get('https://localhost:8080/attentionPoints')
    .then(response => {
        mydata = response.data;
        mydata = mydata._embedded.attentionPointList;
        
        mydata.forEach(attentionPoint => {
            $('#attentionPointsTable').append(`
                <tr>
                    <td>` + attentionPoint.code + `</td>
                    <td>` + "True" + `</td>
                    <td>` + "Sergio" + `</td>
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
    //https://localhost:8080/users

    axios.post('https://localhost:8080/attentionPoints', {
        code: $('#recipient-code').val(),
        queue: JSON.parse(localStorage.getItem('queues')).find(queue => {
            return queue.name == $('#services').val()
        }),
        employee: JSON.parse(localStorage.getItem('users')).find(user => {
            return user.email == $('#services').val()
        })
    })
        .then(function (res) {
            if (res.status == 201) {
                mensaje.innerHTML = 'El nuevo Post ha sido almacenado con id: ' + res.data.id;
            }
        })
        .catch(function (err) {
            console.log(err);
        })
});


function deleteAttentionPoint(id) {
    axios.delete("https://localhost:8080/attentionPoints/" + id).then(function (response) {
    })

}




