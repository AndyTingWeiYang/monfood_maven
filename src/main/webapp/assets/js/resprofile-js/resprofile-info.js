function checkInputResName() {
    $('#checkResNameSp').html('');
    let resName = $('#resName').val().trim();
    let symbol = ['!', '@', '#', ';', '$', '%', '^', '&'];
    let flag = false;

    if (resName == '') {
        $('#checkResNameSp')
            .append($('<i>').addClass('fa-solid fa-circle-exclamation').addClass('mr-2'))
            .append('輸入內容不得為空!');
        return;
    } else {
        // 不是空值，檢核有沒有特殊字元
        for (let j = 0; j < symbol.length; j++) {
            if (resName.indexOf(symbol[j]) != -1) { // 輸入值有特殊字元
                flag = true;
                break;
            }
        }
    }

    if (flag) {
        $('#checkResNameSp').append($('<i>').addClass('fa-solid fa-circle-exclamation').addClass('mr-2')).append('不得輸入特殊符號!');
    } else {
        $('#checkResNameSp').append($('<i>').addClass('fa-solid fa-circle-check').addClass('mr-2'))
            .append('輸入正確');
    }

    return flag;
}

function checkInputResPhone() {
    $('#checkResPhoneSp').html('');
    let resPhone = $('#resPhone').val().trim();
    let symbol = ['!', '@', '#', ';', '$', '%', '^', '&'];
    let flag = false;

    if (resPhone == '') {
        $('#checkResPhoneSp')
            .append($('<i>').addClass('fa-solid fa-circle-exclamation').addClass('mr-2'))
            .append(' 輸入內容不得為空!');
        return;
    } else {
        for (let j = 0; j < symbol.length; j++) {
            if (resPhone.indexOf(symbol[j]) != -1) { // 有特殊字元才進入if
                flag = true;
                break;
            }
        }
    }
    if (flag) {
        $('#checkResPhoneSp').append($('<i>').addClass('fa-solid fa-circle-exclamation').addClass('mr-2')).append('不得有特殊符號!');
    } else {
        $('#checkResPhoneSp').append($('<i>').addClass('fa-solid fa-circle-check').addClass('mr-2')).append('輸入正確');
    }

    return flag;
}

function checkInputBzAdd() {
    $('#checkBzAddSp').html('');
    let bzAdd = $('#bzAdd').val().trim();
    let symbol = ['!', '@', '#', ';', '$', '%', '^', '&'];
    let flag = false;

    if (bzAdd == '') {
        $('#checkBzAddSp').append($('<i>').addClass('fa-solid fa-circle-exclamation').addClass('mr-2')).append('輸入內容不得為空!');
        return;
    } else {
        for (let i = 0; i < bzAdd.length; i++) {
            if (bzAdd.indexOf(symbol[i]) != -1) { // 有特殊字元

                flag = true;
                break;
            }
        }
    }


    if (flag) {
        $('#checkBzAddSp').append($('<i>').addClass('fa-solid fa-circle-exclamation').addClass('mr-2')).append('不得有特殊符號!');
    } else {
        $('#checkBzAddSp').append($('<i>').addClass('fa-solid fa-circle-check').addClass('mr-2')).append('輸入正確');
    }


}

