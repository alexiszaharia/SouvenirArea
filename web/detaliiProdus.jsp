<%-- 
    Document   : detaliiProdus
    Created on : Jan 17, 2019, 2:13:14 PM
    Author     : Alexis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detalii produs</title>
        <link rel="shortcut icon" href="img/enjoy-3.png" />
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/management_produse.js"></script>
    </head>
    <body>
        
        <%
            String strId = request.getParameter("id");
            int id = Integer.parseInt(strId);
        %>
        
<!--        <div class="container-fluid">
            <a href="#" class="btn btn-link" style="float: right;">CART<span id="nr_prod_cart" class="badge">0</span></a>
            <a href="#" class="btn btn-link" style="float: right;">SEARCH</a>
        </div>-->
        <div class="container">
            <h1 style="text-align: center; margin-top: 5%; margin-bottom: 5%;">SOUVENIR AREA</h1>
        </div>
        <div class="container page-header">
            <a href="index.html" class="btn btn-link active">TOATE PRODUSELE</a>
            <a href="obiecte_ceramica.html" class="btn btn-link">OBIECTE CERAMICA</a>
            <a href="tesaturi_traditionale.html" class="btn btn-link">TESATURI TRADITIONALE</a>
            <a href="obiecte_din_lemn.html" class="btn btn-link">OBIECTE DIN LEMN</a>
            <a href="dulciuri.html" class="btn btn-link">DULCIURI</a>
            <a href="oua_incondeiate.html" class="btn btn-link">OUA INCONDEIATE</a>
            <a href="cani.html" class="btn btn-link">CANI</a>
            <a href="globuri.html" class="btn btn-link">GLOBURI</a>
            <a href="magneti.html" class="btn btn-link">MAGNETI</a>
            <a href="tricouri.html" class="btn btn-link">TRICOURI</a>
            <a href="livrare_si_retur.html" class="btn btn-link">LIVRARE SI RETUR</a>
            <a href="contact.html" class="btn btn-link">CONTACT</a>
        </div>
        
        <div class="container">
            <div class="row">
                <div class="col-sm-6">
                    <img id="img_produs" src="img/cana_bloody.jpg" alt="Poza produs" style="width: 100%">
                </div>
                <div class="col-sm-6">
                    <h1 id="denumire_produs"></h1>
                    <h2 id="pret_produs"></h2>
                    <div class="page-header"></div>
                    <form action="form_cumparare.jsp" method="POST">
                        <div class="form-group row">
                            <div class="col-sm-3">
                                <label for="cantitate_produs">Cantitate:</label>
                                <input type="number" name="cantitate" class="form-control" id="cantitate_produs" min="1" max="100" value="1">
                            </div>
                        </div>
                        <div class="form-group">
                            <input type="hidden" name="id" value="<%=id%>">
                        </div>
                        <button type="submit" class="btn">Cumparare</button>
                    </form>
<!--                    <button type="button" id="btn_add_chart" class="btn" onclick="adaugareCos(<%=id%>)">Cumparare</button>
                    <a id="link_cumparare" href="" style="visibility: hidden;"></a>-->
                </div>
            </div>
        </div>
        <div class="page-header"></div>
        <div class="container">
            <h3>Descriere</h3>
            <p id="descriere_produs">O descriere</p>
        </div>
        
        <script>
            $(document).ready(function() {
                detaliiProdus(<%=id%>);
            });
        </script>
        
    </body>
</html>
