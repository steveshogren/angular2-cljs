$(document).ready(function() {
    var times = 0;
    $("p").click(function() {
        times++;
        if (times===1) {
            $("body").append("<p>single click</p>");
        }
        if (times===2) {
            $("body").append("<p>double click</p>");
        }
    });
});
