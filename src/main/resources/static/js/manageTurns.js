
var botonNextTurn = document.getElementById('nextTurn');
var botonTurnCompleted = document.getElementById('turnCompleted');
var botonCancelTurn = document.getElementById('cancelTurn');


var attentionPoint;
var service;
var turn;
localStorage.setItem('turn', "")
var attentionPointId = getParameterByName('id');

axios.get('https://digital-queue-404.herokuapp.com/attentionPoints/' + attentionPointId)
    .then(response => {
        attp = response.data;
        localStorage.setItem('attentionPoint', JSON.stringify(attp))
    })
    .catch(e => {
        //+console.log('Error en el Axios gonorrea');
        console.log(e);
    })

attentionPoint = JSON.parse(localStorage.getItem('attentionPoint'));
console.log(attentionPoint);
service = attentionPoint.service;
console.log(service);

getNumTurns();

botonNextTurn.addEventListener('click', function () {
    console.log("next turn")
    if (!localStorage.getItem("turn")) {
        axios.get('https://digital-queue-404.herokuapp.com/turns/next?service=' + service.name).then(function (result) {
            var res = result.data
            if (res) {
                localStorage.setItem('turn', JSON.stringify(res));
                confirm("Next turn " + res.code + " customer's name " + res.clientName) 
            } else {
                confirm("There are no more turns.")
            }
            getNumTurns();
        })
    } else {
        confirm("first cancel or complete your current turn");
    }
});

botonTurnCompleted.addEventListener('click', function () {
    console.log("turn completed");
    localStorage.setItem('turn', "")
    getNumTurns();
    confirm("Completed turn");

});

botonCancelTurn.addEventListener('click', function () {
    console.log("cancel turn");
    if (localStorage.getItem('turn') != "") {
        var turnToCancel = JSON.parse(localStorage.getItem("turn"));
        console.log(turnToCancel.code);
        axios.delete("https://digital-queue-404.herokuapp.com/turns/cancel/" + turnToCancel.code).then(function (response) {
            localStorage.setItem('turn', "")
            confirm("The turn " + turnToCancel.code + " has been cancelled")

        });
    } else {
        confirm("There's no turn to cancel.")
    }
    getNumTurns();

});

function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, '\\$&');
    var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, ' '));
}



function getNumTurns() {
    console.log(service.name);
    axios.get('https://digital-queue-404.herokuapp.com/turns/totalWaitingByQueue?service=' + service.name).then(function (result) {
        var res = result.data
        //console.log(res)
        var totalWaiting = document.getElementById("totalWaiting")
        totalWaiting.innerHTML = res
    });
}

