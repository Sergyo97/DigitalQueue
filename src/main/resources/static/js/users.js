
var loading = document.getElementById('loading');
var mensaje = document.getElementById('mensaje');
var boton = document.getElementById('json_post');
boton.addEventListener('click', function () {
    loading.style.display = 'block';
    //https://digital-queue-404.herokuapp.com/users
    axios.post('http://localhost:8080/users', {
        name: document.getElementById('recipient-name').value,
        email: document.getElementById('recipient-email').value

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

//http://localhost:8080/users
axios.get('http://localhost:8080/users')
    .then(response => {
        mydata = response.data;
        mydata = mydata._embedded.userList;
        // $('#table1').bootstrapTable({
        //     data: mydata
        // });
        console.log(mydata);
        mydata.forEach(employee => {
            $('#employeeTable').append(`
                <tr>    
                    <td>` + employee.name + `</td>
                    <td>` + employee.email + `</td>
                    <td>Admin</td>
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