try {
	if (typeof (EventSource) !== "undefined") {
		var source = new EventSource("event/pubsub/slow");
		source.onmessage = function(event) {
			document.getElementById("resultDiv").innerHTML += "<br>onmessage<br>id = " + event.id + "<br>name = " + event.name + "<br>data = " + event.data + "<br>";
		};
		source.onopen = function(event) {
			document.getElementById("resultDiv").innerHTML += "<br>onopen<br>id = " + event.id + "<br>name = " + event.name + "<br>data = " + event.data + "<br>";
		};
		source.onerror = function(event) {
			if (source.readyState == 0) {
				document.getElementById("resultDiv").innerHTML += "<br>onerror state=CONNECTING";
			} else if (source.readyState == 1) {
				document.getElementById("resultDiv").innerHTML += "<br>onerror state=OPEN";
			} else {
				document.getElementById("resultDiv").innerHTML += "<br>onerror state=CLOSED";
			}
		};
		source.addEventListener('poll', displayPoll, false);
	} else {
		document.getElementById("resultDiv").innerHTML += "Sorry, your browser does not support server-sent events...";
	}
} catch (e) {
	document.getElementById("resultDiv").innerHTML = e;
}
function displayPoll(event) {
	var html = event.data;
	document.getElementById('resultDiv').innerHTML += '<br/>poll: ' + html + "<br>";
}
/*
 * curl -H "Accept: text/event-stream" --url http://localhost:8080/sse/event/pubsub/slow
 */