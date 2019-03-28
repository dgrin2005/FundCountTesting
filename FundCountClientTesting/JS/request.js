function httpGetAsync(theUrl, callback) {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() {
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            callback(xmlHttp.responseText);
        } else {
            if (xmlHttp.readyState === 4) {
                displayError(xmlHttp.response);
            }
        }
    };
    xmlHttp.open("GET", theUrl, true);
    xmlHttp.send(null);
}


function httpPostAsync(theUrl, data, callback) {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() {
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            callback(xmlHttp.responseText);
        } else {
            if (xmlHttp.readyState === 4) {
                displayError(xmlHttp.response);
            }
        }
    };
    xmlHttp.open("POST", theUrl, true);
    xmlHttp.setRequestHeader('Content-Type', 'application/json; charset=utf-8');
    xmlHttp.send(prepareRequestBody());
}