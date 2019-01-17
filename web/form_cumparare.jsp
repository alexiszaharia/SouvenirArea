<%-- 
    Document   : form_cumparare
    Created on : Jan 17, 2019, 3:18:00 PM
    Author     : Alexis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checkout</title>
        <link rel="shortcut icon" href="img/enjoy-3.png" />
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/management_produse.js"></script>
    </head>
    <body>
        
        <%
            String strId = request.getParameter("id");
            String strCantitate = request.getParameter("cantitate");
            int id = Integer.parseInt(strId);
            int cantitate = Integer.parseInt(strCantitate);
        %>
        
        <div class="container">
            <h1 style="text-align: center; margin-top: 5%; margin-bottom: 5%;">SOUVENIR AREA</h1>
        </div>
        <div class="page-header"></div>
        <div class="container">
            <div class="row">
                <div class="col-sm-3">
                    <!--<form action="" method="POST">-->
                        <div class="form-group">
                            <label for="nume">Nume:</label>
                            <input type="text" name="nume" class="form-control" id="nume">
                        </div>
                        <div class="form-group">
                            <label for="prenume">Prenume:</label>
                            <input type="text" name="prenume" class="form-control" id="prenume">
                        </div>
                        <div class="form-group">
                            <label for="telefon">Telefon:</label>
                            <input type="text" name="telefon" class="form-control" id="telefon">
                        </div>
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="email" name="email" class="form-control" id="email">
                        </div>
                        <div class="form-group">
                            <input type="hidden" name="id" value="<%=id%>" id="id_produs">
                        </div>
                        <div class="form-group">
                            <input type="hidden" name="cantitate" value="<%=cantitate%>"id="cantitate">
                        </div>
                        <button type="button" class="btn" onclick="executaComanda()">Comanda</button>
                    <!--</form>-->
                </div>
                <div class="col-sm-9"></div>
            </div>
        </div>
    </body>
</html>
