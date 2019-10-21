var loading = document.getElementById('loading');
var mensaje = document.getElementById('mensaje');

var boton = document.getElementById('json_post');
boton.addEventListener('click', function () {
    loading.style.display = 'block';
    //http://localhost:8080/queues
    //https://digital-queue-404.herokuapp.com/queues
    axios.post('https://digital-queue-404.herokuapp.com/queues', {

        name: document.getElementById('recipient-name').value

    })
        .then(function (res) {
            if (res.status == 201) {
                mensaje.innerHTML = 'El nuevo Post ha sido almacenado con id: ' + res.data.id;
            }
        })
        .catch(function (err) {
            console.log(err);
        })
        .then(function () {
            loading.style.display = 'none';
        });
});


axios.get('https://digital-queue-404.herokuapp.com/queues')
    .then(response => {
        
        mydata = response.data;
        
        mydata = mydata._embedded.queueList;
        // $('#table1').bootstrapTable({
        //     data: mydata
        // });
        console.log(mydata);
        mydata.forEach(service => {
            $('#servicesTable').append(`
                <tr>
                    <td>` + service.name + `</td>
                    <td>` + service.name[0] + `</td>
                    <td>
                        <button type="button" class="btn btn-danger">
                            <i class="far fa-trash-alt"></i>
                        </button>
                    </td>

                </tr>
            `)
        });
    })
    .catch(e => {
        // Capturamos los errores
    })



