var produs;

/**
 * Metoda utilizata pentru afisarea catalogului in functie de categoria ceruta.
 * @param {string} categorie categoria ceruta
 */
function afisareCatalog(categorie) {
    var datePost = '&categorie=' + categorie;
    
    $.ajax({
        url: "AfisareCatalog", 
        async: false, 
        contentType: 'application/x-www-form-urlencoded; charset=UTF-8', 
        data: datePost, 
        dataType: 'json',
        type: 'POST', 
        success: function (data, textStatus, jqXHR) {
            if (data.cod_operatie == 1) {
                alert('Probleme la incarcarea catalogului!');
                return;
            }
            
            var catalog = data.catalog;
            if (catalog.length == 0) {
                $('#catalog_produsele').html("Nu sunt produse de afisat!");
            } else {
                var count = 0;
                var html = "";
                $.each(catalog, function (index, produs) {
                    count++;
                    
                    if (count == 1) {
                        html += '<div class="row">';
                    }
                    
                    var linkDetalii = 'detaliiProdus.jsp?id=' + produs.id;
                    
                    html += '<div class="col-sm-3">';
                    html += '<div class="thumbnail">';
                    html += '<a href="' + linkDetalii + '">';
                    html += '<img src="' + produs.cale_poza + '" alt="Poza produs" style="width:100%">';
                    html += '<div class="caption">';
                    html += '<p>' + produs.denumire + '</p>';
                    html += '<p>' + produs.pret + ' RON</p>';
                    html += '</div>';
                    html += '</a>';
                    html += '</div>';
                    html += '</div>';
                    
                    if (count == 4) {
                        html += '</div>';
                        count = 0;
                    }
                    
                });
                
                $('#catalog_produsele').html(html);
            }
        }, 
        error: function (jqXHR, textStatus, errorThrown) {
            alert('Probleme la incarcarea catalogului!');
        }
    });
}

/**
 * Aducerea detaliilor produsului.
 * @param {int} id
 * @returns {undefined}
 */
function detaliiProdus(id) {
    var datePost = '&id=' + id;
    
    $.ajax({
        url: "DetaliiProdus", 
        async: false, 
        contentType: 'application/x-www-form-urlencoded; charset=UTF-8', 
        data: datePost, 
        dataType: 'json',
        type: 'POST', 
        success: function (data, textStatus, jqXHR) {
            if (data.cod_operatie == 1) {
                alert('Probleme la incarcarea catalogului!');
                return;
            }
            
            produs = data.produs;
            
            $('#denumire_produs').html(data.produs.denumire);
            $('#pret_produs').html(data.produs.pret);
            $('#descriere_produs').html(data.produs.descriere);
            $('#img_produs').attr('src', data.produs.cale_poza);
        }, 
        error: function (jqXHR, textStatus, errorThrown) {
            alert('Probleme la incarcarea catalogului!');
        }
    });
}

/**
 * Metoda utilizata pentru adaugarea produsului in cos.
 * @param {int} id id produs
 */
function adaugareCos(id) {
    var params = '?id=' + id;
    var cantitate = $('#cantitate_produs').val();
    params += '&cantitate=' + cantitate;
    $('#link_cumparare').attr('href', 'form_cumparare.jsp' + params);
    $('#link_cumparare').click();
}

function executaComanda() {
    var nume = $('#nume').val();
    var prenume = $('#prenume').val();
    var telefon = $('#telefon').val();
    var email = $('#email').val();
    var id = $('#id_produs').val();
    var cantitate = $('#cantitate').val();
    
    var datePost = '$nume=' + nume + '$prenume=' + prenume + '$telefon=' + telefon + 
            '&email=' + email + '&id=' + id + '&cantitate=' + cantitate;
    
    $.ajax({
        url: "Comanda", 
        async: false, 
        contentType: 'application/x-www-form-urlencoded; charset=UTF-8', 
        data: datePost, 
        dataType: 'json',
        type: 'POST', 
        success: function (data, textStatus, jqXHR) {
            if (data.cod_operatie == 1) {
                alert('Probleme la inregistrarea comenzii!');
            } else {
                alert('Comanda inregistrata!');
            }
        }, 
        error: function (jqXHR, textStatus, errorThrown) {
            alert('Probleme la inregistrarea comenzii!');
        }
    });
}