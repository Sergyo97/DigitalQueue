
var $table = $('#table');
var mydata = {};
axios.get('http://localhost:8080/employees')
    .then(response => {
        mydata = response.data;
        mydata = mydata._embedded.employeeList;
        console.log(mydata);
        myFunc();

    })
    .catch(e => {
        // Capturamos los errores
    })
console.log(mydata);
function myFunc() {
    $('#table').bootstrapTable({
        data: mydata
    });
};