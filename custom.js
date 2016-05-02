$(document).ready(function() {
    $("p").bind("click", function() {
        $("body").append("<p>single click</p>");
    });
    $("p").bind("dblclick", function() {
        $("body").append("<p>double click</p>");
    });
});
