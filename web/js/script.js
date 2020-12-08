/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


document.getElementById("claveId").addEventListener("keydown", checkinputClave);

function checkinputClave() {
    let x = document.getElementById("errorClave");
    let y = document.getElementById("claveId");
    if (event.keyCode == 32) {
        event.returnValue = false;
        y.style.border = "2px solid red";
        x.innerText = "No se acepta espacios";
    } else {
        y.style.border = "2px solid rgb(133, 133, 133)";
        x.innerText = "";
    }
}

function checkInputProd(char) {
    let x = document.getElementsByClassName("error")[0];
    let checkinp = /[@#$%^!&*()_+*]/g;

    if (event.keyCode == 32) {
        event.returnValue = false;
        x.innerText = "No se acepta espacios";
    } else if (checkinp.test(char.key) == true) {
        event.returnValue = false;
        x.innerText = "No se acepta Simbolos";
    } else {
        x.innerText = "";
    }
}

function checkInput(char) {
    let x = document.getElementsByClassName("error")[0];
    let y = document.getElementById("cantidadId").value;
    let ch = char.key;

    console.log(typeof y);

    if (ch == "-") {
        event.returnValue = false;
        x.innerHTML = "No se acepta valor negativo";
    } else if (y.length > 2) {
        event.returnValue = false;
        x.innerHTML = "Longitud maxima";
    } else {
        x.innerHTML = "";
    }
}

