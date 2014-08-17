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

function nlpAlgorithm()
{
    var searchVal = $("#searchTerm").val();
    if (searchVal.length  < 1) {
        alert("Please enter a valid search keyword");
        return;
    }

//    var getUrl = "http://localhost:8080/restservice-1.0/rest/twitter/query?keyword=" + searchVal + "&algorithm=B";
//    console.log("getting data from:", getUrl);

//    $.get( getUrl, function( data ) {
//      console.log("received:" + JSON.stringify(data));
      renderResults();
//    });
}

var results = [{"line":"Police firing tear gas at protesters?\n\n@BarackObama, WHAT KIND OF SHIT HOLE ARE YOU RUNNING HERE?! #Ferguson","sentiment":"Negative"},{"line":"barackobama: sirlightbulb: dear god I hope that no one on this website ever tries to run for president... http://t.co/a5G8AfX8bA","sentiment":"Neutral"},{"line":"@BarackObama @GovJayNixon what's happening in #Ferguson tonight is disgraceful.  The world is watching this.  #PoliceState #Unconstitutional","sentiment":"Neutral"},{"line":"Damn you still hella quiet @BarackObama","sentiment":"Positive"},{"line":"You should catch an @Cardinals game @BarackObama. They have the best fans and the most abusive suburban cops.","sentiment":"Positive"},{"line":"@BarackObama is retweets not endorsements","sentiment":"Neutral"},{"line":"@SheilaBabsss @ANGLAESPNO @olazogilmay_ @JoshuaMacaspac_ @mathswifts @DaleSwifty @itsKimMcGraw @noynoyaquino @BarackObama or a dog","sentiment":"Neutral"},{"line":"\"@TimesofIsrael: Izzy Lemberg's Blog: @BarackObamaâ€™s ambush of #Israel http://t.co/bnKS2PwQpa via @timesofisrael\"","sentiment":"Positive"},{"line":"#Ferguson? \"@BarackObama: \"The death of Michael Brown is heartbreaking.\" â€”President Obama. Read the full statement: http://t.co/18DeLqMeVn\"","sentiment":"Neutral"},{"line":"Whats number one ? @BarackObama Answer Choice B: Public Safety. Good job. Let them clown on you, keep working. Yes sir.","sentiment":"Neutral"},{"line":"@SheilaBabsss @ANGLAESPNO @olazogilmay_ @JoshuaMacaspac_ @mathswifts @DaleSwifty @itsKimMcGraw @noynoyaquino @BarackObama or a cat","sentiment":"Neutral"},{"line":"Police firing tear gas at protesters?\n\n@BarackObama, WHAT KIND OF SHIT HOLE ARE YOU RUNNING HERE?! #Ferguson","sentiment":"Negative"},{"line":"barackobama: sirlightbulb: dear god I hope that no one on this website ever tries to run for president... http://t.co/a5G8AfX8bA","sentiment":"Neutral"},{"line":"@BarackObama @GovJayNixon what's happening in #Ferguson tonight is disgraceful.  The world is watching this.  #PoliceState #Unconstitutional","sentiment":"Neutral"},{"line":"Damn you still hella quiet @BarackObama","sentiment":"Positive"},{"line":"You should catch an @Cardinals game @BarackObama. They have the best fans and the most abusive suburban cops.","sentiment":"Positive"},{"line":"@BarackObama is retweets not endorsements","sentiment":"Neutral"}]
function renderResults(){
    console.log("results:", results);

    for(i = 0; i < results.length; i++) {
         $("#123").append("<p>" + JSON.stringify(results[i]) + "</p>");
    }
}






$(document).ready(function(){
    console.log("jQuery activated", results[0]);
});
