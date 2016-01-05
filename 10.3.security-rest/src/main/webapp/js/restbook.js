var bookRestUrl = 'webapi/books';
function rest(restUrl, httpMethod, param, contenttype, datatype, callback) {
	jQuery('#resultDiv').html("Loading...");
	var request = jQuery.ajax({type : httpMethod, url : restUrl, data : param, contentType : contenttype, dataType : datatype});
	request.done(function(data) {
		try {
			if (data === null || data === undefined) {
				jQuery('#resultDiv').html("No result from server");
			} else {
				callback(data);
			}
		} catch (e) {
			jQuery('#resultDiv').html(e);
		}
	});
	request.fail(function(textStatus, errorThrown) {
		jQuery('#resultDiv').html(errorThrown + " status=" + textStatus.status);
	});
}
/*GET*/
function getAllBook() {
	rest(bookRestUrl, 'GET', null, null, 'json', renderGetAll);
}
function getBookByQuery() {
	var url = $("#queryUrl").val();
	rest(bookRestUrl + url, 'GET', null, null, 'json', renderQueryGet);
}
function getBookByPath() {
	var url = $("#pathUrl").val();
	rest(bookRestUrl + url, 'GET', null, null, 'json', renderPathGet);
}
/*PUT*/
function putBook() {
	var contentType = $("input[name='updateRadio']:checked").val();
	var putData;
	var idValue = $("#bookId0").val();
	var nameValue = $("#bookName0").val();
	var publisherValue = $("#publisher0").val();
	if (contentType === "application/xml") {
		putData = "<book";
		if (nameValue !== "") {
			putData += " bookName='" + nameValue + "'";
		}
		if (publisherValue !== "") {
			putData += " publisher='" + publisherValue + "'";
		}
		putData += "/>";
	} else {
		var book = new Object();
		if (nameValue !== "") {
			book.bookName = nameValue;
		}
		if (publisherValue !== "") {
			book.publisher = publisherValue;
		}
		putData = JSON.stringify(book);
	}
	rest(bookRestUrl + "/" + idValue, 'PUT', putData, contentType, 'json', renderPut);
}
/*POST*/
function postBook() {
	var contentType = $("input[name='saveRadio']:checked").val();
	var postData;
	var nameValue = $("#bookName").val();
	var publisherValue = $("#publisher").val();
	if (contentType === "application/xml") {
		postData = "<book bookName='" + nameValue + "'/>";
	} else {
		postData = JSON.stringify({bookName : nameValue, publisher : publisherValue});
	}
	rest(bookRestUrl, 'POST', postData, contentType, 'json', renderPut);
};
/*DELETE*/
function deleteBook() {
	var url = $("#delUrl").val();
	rest(bookRestUrl + url, 'DELETE', null, null, 'text', renderDelete);
}
/*Render DOM*/
function renderGetAll(data) {
	var books = data.bookList.book;
	var result = "";
	for ( var i = 0; i < books.length; i++) {
		result += books[i].bookId + "-" + books[i].bookName + "-" + books[i].publisher + "<br/>";
	}
	$('#resultDiv').html(result);
}
function renderQueryGet(data) {
	$('#resultDiv').html("query result: " + data.bookId + "-" + data.bookName + "-" + data.publisher);
}
function renderPathGet(data) {
	$('#resultDiv').html("path result: " + data.bookId + "-" + data.bookName + "-" + data.publisher);
}
function renderPut(data) {
	$('#resultDiv').html("DONE! id=" + data.bookId);
}
function renderDelete(data) {
	$('#resultDiv').html(data);
}