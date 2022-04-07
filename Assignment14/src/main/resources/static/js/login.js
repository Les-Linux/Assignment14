function isEmpty(usrId){
    return usrId === null || (/^ *$/).test(usrId);
}

document.addEventListener('DOMContentLoaded', () => {
    console.log("entered login script")
    let firstName = sessionStorage.getItem('firstName')
    let lastName = sessionStorage.getItem('lastName')
    if(!(isEmpty(firstName))){
        console.log("entered login script if condition")
        console.log("Session Id Is=" + sessionStorage.getItem('userId'))
        document.querySelector('#inp_firstName').value = firstName
        document.querySelector('#inp_lastName').value = lastName

        document.querySelector('#div_firstName').style.display='none'
        document.querySelector('#div_lastName').style.display='none'
    } else{
        document.querySelector('#div_firstName').style.display='block'
        document.querySelector('#div_lastName').style.display='block'
    }
})

