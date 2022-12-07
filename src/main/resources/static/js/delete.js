$(document).ready(function(){
    $(".deleteButton").on('click', function(e){
        let id = document.getElementsByName("data-id").values()
        window.location.replace(`/posts/delete/`);
    });
});