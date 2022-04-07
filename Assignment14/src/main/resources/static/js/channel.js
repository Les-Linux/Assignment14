let firstName = document.querySelector('#span_firstName').innerHTML
let lastName = document.querySelector('#span_lastName').innerHTML
let userId = document.querySelector('#span_uid').innerHTML
let channelId = document.querySelector('#span_cid').innerHTML
let chatHistory = document.querySelector('#chatHistory')
let chatMessageTextarea = document.querySelector('#chatMessage')
let channelName = document.querySelector('#span_channelName').innerHTML


function isEmpty(usrId){
    return usrId === null || (/^ *$/).test(usrId);
}
async function getChannelName(chnlId){
    let chnNme = await fetch(`/channel/name?channelId=${chnlId}`,{
    })
        .then(response => {
            return response.text()
        })
        .then(data => {
            console.log('channel data=' + data)
            return data
        })
    return chnNme
}
async function updateChannelName(chId){
    let chNamePromise = new Promise((resolve,reject) => {
        resolve(getChannelName(chId))
    })

    return await chNamePromise.then((message) => {
        return message
    }).catch(message =>{
        console.log('Error - ' + message)
    })
}
document.addEventListener('DOMContentLoaded', () => {
    if(isEmpty(userId) && isEmpty(sessionStorage.getItem('userId'))){
        console.log("UserId is Empty")
        window.location.replace("/welcome")
    } else{
        updateChannelName(channelId).then((message) => {
            if(!isEmpty(message)){
                console.log('setting sessionStorageWith=' + message)
                document.querySelector('#span_channelName').innerHTML = message
                sessionStorage.setItem('channelName',message.toString())
            }
        })

        console.log('DOMCLoaded userId=' + sessionStorage.getItem('userId'))
        console.log('DOMCLoaded channelName=' + sessionStorage.getItem('channelName'))


        if(isEmpty(sessionStorage.getItem('userId'))){
            sessionStorage.setItem('userId',userId)
            sessionStorage.setItem('channelName', channelName)
            sessionStorage.setItem('firstName',firstName)
            sessionStorage.setItem('lastName',lastName)
        } else if((sessionStorage.getItem('userId') !== userId) && (userId !== null) && (userId != '')){
            sessionStorage.setItem('userId',userId)
            sessionStorage.setItem('firstName',firstName)
            sessionStorage.setItem('lastName', lastName)
        }

        document.querySelector('#fullName').innerHTML = sessionStorage.getItem('userId')
        document.querySelector('#span_channelName').innerHTML = sessionStorage.getItem('channelName')
    }
})
chatMessageTextarea.addEventListener('keypress', (event) => {
    if(event.key === 'Enter'){

        console.log('Enter ws Pressed')
        console.log('ChannelId=' + channelId)

        if(chatMessageTextarea.value.trim().length != 0){

            let chatMessageDto = {
                'channelId': channelId,
                'message': sessionStorage.getItem('userId') + ': ' + chatMessageTextarea.value
            }

            fetch(`/channel/chat`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(chatMessageDto)
            })
            .then()
            chatHistory.innerHTML += '<strong>' + sessionStorage.getItem('userId') + ': ' + '</strong>'
            chatHistory.innerHTML += chatMessageTextarea.value.trim() + '<br/>'
            chatMessageTextarea.value = ''
        }
    }

})

