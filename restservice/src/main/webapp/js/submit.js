
function clickAlchemy()
{
    var searchVal = $("#searchTerm").val();
        if (searchVal.length  < 1) {
            alert("Please enter a valid search keyword");
            return;
        }

        var getUrl = "http://localhost:8080/restservice-1.0/rest/twitter/query?keyword=" + searchVal + "&algorithm=A";
        console.log("getting data from:", getUrl);

        $.get( getUrl, function( results )
        {
          renderResults(results);
        });
}

function clickNLP()
{
    var searchVal = $("#searchTerm").val();
    if (searchVal.length  < 1) {
        alert("Please enter a valid search keyword");
        return;
    }

    var getUrl = "http://localhost:8080/restservice-1.0/rest/twitter/query?keyword=" + searchVal + "&algorithm=N";
    console.log("getting data from:", getUrl);

    $.get( getUrl, function( results )
    {
      renderResults(results);
    });
}


function clickIdol()
{
   var searchVal = $("#searchTerm").val();
       if (searchVal.length  < 1) {
           alert("Please enter a valid search keyword");
           return;
       }

       var getUrl = "http://localhost:8080/restservice-1.0/rest/twitter/query?keyword=" + searchVal + "&algorithm=I";
       console.log("getting data from:", getUrl);

       $.get( getUrl, function( results )
       {
         renderResults(results);
       });
}

function renderResults(results)
{
    console.log("results from renderResults:", results);
    $("#results").empty();
    for(i = 0; i < results.length; i++)
    {
        console.log(results[i].sentiment.toLowerCase());
        if(results[i].sentiment.toLowerCase() == "positive")
        {
            $("#results").append("<p class = \"bg-success\">" +JSON.stringify(results[i])+ "</p>");
        }
        else if(results[i].sentiment.toLowerCase() == "negative")
        {
           $("#results").append("<p class = \"bg-danger\">" +JSON.stringify(results[i])+ "</p>");
        }
        else
        {
           $("#results").append("<p class = \"bg-info\">" +JSON.stringify(results[i])+ "</p>");
        }
    }
}

$(document).ready(function(){
    console.log("jQuery activated", results[0]);
});
