function submitForm()
{
    var searchVal = $("#searchTerm").val();
    if (searchVal.length  < 1) {
        alert("Please enter a valid search keyword");
        return;
    }

    var getUrl = "http://localhost:8080/restservice-1.0/rest/twitter/query?keyword=" + searchVal + "&algorithm=A";
    console.log("getting data from:", getUrl);

    $.get( getUrl, function( data ) {
//      $( ".result" ).html( data );
      console.log("received:" + JSON.stringify(data));
    });
}


$(document).ready(function(){
    console.log("jQuery activated");
});
