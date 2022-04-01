let intervalId
let channel = document.querySelector('#span_cid').innerHTML

function start() {
    intervalId= setInterval(getChatMessageHistory,500,channel)
}

function stop(){
    clearInterval(intervalId)
}

// let btnTest = document.querySelector('#btnTest')
// btnTest.addEventListener('click', () => {
//     let channelId = document.querySelector('#span_cid').innerHTML
//     console.log('Button Click Detected')
//     getChatMessageHistory(channelId)
// })



function getChatMessageHistory(channelId){
    fetch(`/channel/chat?channelId=${channelId}`,  {
    })
    .then(response => response.text())
    .then(data => {
        chatHistory.innerHTML = ''
        chatHistory.innerHTML = data
    })
}

start()

