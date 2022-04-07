function isEmpty(usrId){
    return usrId === null || (/^ *$/).test(usrId);
}

function welcomeUser(){
    let uid = sessionStorage.getItem('userId')

    if(!(isEmpty(uid))){
        document.querySelector('#span_welcomeUser').innerText = uid
    } else {
        document.querySelector('#span_welcomeUser').innerText = 'Guest'
    }
}

welcomeUser()