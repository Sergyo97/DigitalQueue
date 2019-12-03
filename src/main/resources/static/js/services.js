var loading = document.getElementById('loading');
var mensaje = document.getElementById('mensaje');

var boton = document.getElementById('json_post');
boton.addEventListener('click', function () {
    loading.style.display = 'block';
    axios.post('https://localhost:8443/services', {

        name: $('#recipient-name').val(),
        identifier: $('#recipient-letter').val(),
        status: true

    })
        .then(function (res) {
            if (res.status == 201) {
                mensaje.innerHTML = 'El nuevo Post ha sido almacenado con id: ' + res.data.id;
            }
            window.location.reload
        })
        .catch(function (err) {
            console.log(err);
        })
        .then(function () {
            loading.style.display = 'none';
        });
    setTimeout(() => { window.location.reload(); }, 500);
});


axios.get('https://localhost:8443/services')
    .then(response => {
        mydata = response.data._embedded.serviceList;
        mydata.forEach(service => {
            $('#servicesTable').append(`
                <tr>
                    <td>` + service.name + `</td>
                    <td>` + service.name[0] + `</td>
                    <td>
                        <input type="checkbox" checked data-toggle="toggle" data-onstyle="outline-success" data-offstyle="outline-danger">
                    </td>
                    <td>
                        <button type="button" onclick="window.location.href='/attentionPoints.html?serviceName=` + service.name + ` '" class="btn btn-info">
                            <i class="far fa-edit"></i>
                        </button>
                    </td>
                    <td>
                        <button type="button" onclick="deleteService(`+ service.id + `)" class="btn btn-danger">
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



function deleteService(id) {
    axios.delete("https://localhost:8443/services/" + id).then(function (response) {
        setTimeout(() => { window.location.reload(); }, 500);
    })

}
