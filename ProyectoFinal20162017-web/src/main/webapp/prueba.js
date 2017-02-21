function loadCats() {
    $.ajax({
        type: 'GET'
        , dataType: 'json'
        , url: 'http://localhost:8080/ProyectoFinal20162017-web/webservice/CategoriasService/Categorias'
        , success: function (cat) {
            i = 0;
            $.each(cat, function () {
                $('.categoria').eq(i).attr('id', this.cod);
                $('.categoria').find('span').eq(i).append((this.nombre).toUpperCase());
                i++;
            });
        },
        error: function(){
            alert("No se han podido cargar las categorías");
        }
    });
}

$(document).ready(function(){
    loadCats();
});
