function checkInputOwnerName() {
    $('#checkOwnerNameSp').html('');
    let ownerName = $('#ownerName').val().trim();
    let symbol = ['!', '@', '#', ';', '$', '%', '^', '&'];
    let flag = false;

    if (ownerName == '') {
        $('#checkOwnerNameSp')
            .append($('<i>').addClass('fa-solid fa-circle-exclamation').addClass('mr-2'))
            .append('輸入內容不得為空!');
        return;
    } else {
        // 不是空值，檢核有沒有特殊字元
        for (let j = 0; j < symbol.length; j++) {
            if (ownerName.indexOf(symbol[j]) != -1) { // 輸入值有特殊字元
                flag = true;
                break;
            }
        }
    }

    if (flag) {
        $('#checkOwnerNameSp').append($('<i>').addClass('fa-solid fa-circle-exclamation').addClass('mr-2')).append('不得輸入特殊符號!');
    } else {
        $('#checkOwnerNameSp').append($('<i>').addClass('fa-solid fa-circle-check').addClass('mr-2'))
            .append('輸入正確');
    }
    return flag;
}

function checkInputResPhone() {
    $('#checkResPhoneSp').html('');
    let resPhone = $('#resPhone').val().trim();
    let symbol = ['!', '@', '#', ';', '$', '%', '^', '&'];
    let flag = false;

	// 比對手機號碼格式
const regex =/\d{10}/;

	
    if (resPhone == '' || !regex.test($('#resPhone').val()) ) {
        $('#checkResPhoneSp')
            .append($('<i>').addClass('fa-solid fa-circle-exclamation').addClass('mr-2'))
            .append(' 輸入內容不得為空，請輸入10位數電話號');
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
        $('#checkResPhoneSp').append($('<i>').addClass('fa-solid fa-circle-exclamation').addClass('mr-2')).append('不得有特殊符號');
    } else {
        $('#checkResPhoneSp').append($('<i>').addClass('fa-solid fa-circle-check').addClass('mr-2')).append('輸入格式正確');
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



