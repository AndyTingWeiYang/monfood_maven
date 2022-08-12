var i = 6 ;
function timesCount(){
    i--;
    // 在 Worker 中將資料傳給主執行緒。這裡的i=主執行緒的e.data
    self.postMessage(i);

    //在 Worker 中，每隔一秒就執行timesCount()
    setTimeout(function(){
        timesCount()
    },1000);
}
timesCount();