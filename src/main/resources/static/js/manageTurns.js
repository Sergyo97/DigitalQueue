
var botonNextTurn = document.getElementById('nextTurn');
var botonTurnCompleted = document.getElementById('turnCompleted');
var botonCancelTurn = document.getElementById('cancelTurn');


var attentionPoint;
var service;
var turn;
var attentionPointId = getParameterByName('id');
console.log(attentionPointId)

axios.get('https://localhost:8443/attentionPoints/' + attentionPointId)
    .then(response => {
        attp = response.data;
        //console.log(attp)
        localStorage.setItem('attentionPoint', JSON.stringify(attp))
    })
    .catch(e => {
        // Capturamos los errores
    }
    )

attentionPoint = JSON.parse(localStorage.getItem('attentionPoint'));
console.log(attentionPoint);
service = attentionPoint.service;
console.log(service);

botonNextTurn.addEventListener('click', function(){
    console.log("next Turn");
});

botonTurnCompleted.addEventListener('click', function(){
    console.log("turn completed");
});

botonCancelTurn.addEventListener('click', function(){
    console.log("cancel turn");
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