$(document).ready(function(){
    console.log("jQuery activated", results[0]);
});

function twitterStream()
{
        $("#results").empty();
        $("streamResults").empty();
        var port = (document.location.host.indexOf('localhost') >= 0) ? undefined : ':8000';
        var url = (port !== undefined) ? 'ws://' + document.location.host + port + document.location.pathname + 'tweets' :
          'ws://' + document.location.host + document.location.pathname + 'tweets';
        var connection = new WebSocket(url);
          connection.onopen = function() {
            connection.send('brazil');
          };
          connection.onerror = function(error) {
            console.log('WebSocket Error ' + error);
          };
          connection.onmessage = function(e)
          {
            var parseToJSON = JSON.parse(e.data);
            if(parseToJSON["sentiment"].toLowerCase() == "positive")
            {
                $("#streamResults").prepend("<p class = \"bg-success\">" +JSON.stringify(parseToJSON.tweet)+ "</p>");
            }
            else if(parseToJSON["sentiment"].toLowerCase() == "negative")
            {
                $("#streamResults").prepend("<p class = \"bg-danger\">" +JSON.stringify(parseToJSON.tweet)+ "</p>");
            }else
            {
                $("#streamResults").prepend("<p class = \"bg-info\">" +JSON.stringify(parseToJSON.tweet)+ "</p>");
            }
            console.log(e);
          };
}

