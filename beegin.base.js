var POST = function(api, data, func){
    var request = new XMLHttpRequest();
    request.onreadystatechange = function(){
        if(request.readyState == 4){
            try {
                //console.log(request.response);
                var resp = JSON.parse(request.response);
            } catch (e){
                var resp = {
                    status: 'error',
                    data: 'Unknown error occurred: [' + request.responseText + ']'
                };
            }
            console.log(resp.status + ': ' + resp.data);
            if (resp.status == 'error') {
                //alert(resp.data);
                return;
            }
            func(resp);
        }
    };
    request.open('POST', api);
    request.send(data);
    return request;
}