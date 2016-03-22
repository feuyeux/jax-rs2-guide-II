jQuery.noConflict();
jQuery(document).ready(function() {
    jQuery.ajax({
      url: "http://localhost:8080/hi"
    }).then(function(data) {
      jQuery(data).each(function(i,val) {
        jQuery('.hi-content').append(i+" "+val+"<br/>");
      });
    });
});
