<%-- 
    Document   : hacerReserva.jsp
    Created on : 17/11/2024, 8:27:05 p. m.
    Author     : ramon
--%>

<%@page import="CRUDI.Reserva"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Mis Reservas - El Diamante</title>
    <link rel="icon" type="image/x-icon" href="assets/img/logo1.png" />
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Raleway:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i" rel="stylesheet" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="css/styles.css" rel="stylesheet" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="js/scripts.js?v=1.0"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
    <body id="misReservas">
        
        <% String mensajeError = (String) request.getAttribute("mensajeError"); %>
        <% if (mensajeError != null) { %>
            <div class="alert alert-danger" role="alert">
                <strong>Error: </strong> <%= mensajeError%>
            </div>   
        <% } %>
        
        <form action="SvCerrarSesion">
            <button type="submit" class="logout-btn">Cerrar Sesión</button>
        </form>
        
        <header>
            <h1 class="site-heading text-center text-faded d-none d-lg-block">
                <span class="site-heading-lower">Reservaciones</span>
            </h1>
        </header>
        
        <nav class="navbar navbar-expand-lg navbar-dark py-lg-4" id="mainNav">
            <div class="container">
                <a class="navbar-brand text-uppercase fw-bold d-lg-none" href="hacerReserva.jsp">El Diamante</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mx-auto">
                        <li class="nav-item px-lg-4"><a class="nav-link text-uppercase" href="hacerReserva.jsp">Reservar</a></li>
                        <li class="nav-item px-lg-4"><a class="nav-link text-uppercase active" href="misReservas.jsp">Mis Reservaciones</a></li>
                        <li class="nav-item px-lg-4"><a class="nav-link text-uppercase" href="configuracionesUsuario.jsp">Configuraciones Usuario</a></li>
                        <!-- <li class="nav-item px-lg-4"><a class="nav-link text-uppercase" href="#">Compártenos Tu Experiencia</a></li> -->
                    </ul>
                </div>
            </div>
        </nav>
        <section class="page-section about-heading">
            <div class="container">
                <div class="about-heading-content" style="margin-top: 30px">
                    <div class="row">
                        <div class="col-xl-9 col-lg-10 mx-auto">
                            <div class="bg-faded rounded p-5">
                                <h2 class="section-heading mb-4 text-center">
                                    <span class="section-heading-lower">Cancelar Reserva</span>
                                </h2>
                                <div class="text-center">
                                    <form id="formFecha" style="margin-bottom: 20px">
                                        <p><label for="fecha" style="margin-bottom: 20px">Selecciona una fecha</label>
                                            <input type="date" id="fechaReserva" name="fechaReserva" class="form-control">
                                        </p>
                                    </form>
                                </div>
                                <div id="listaHorarios">

                                </div>
                                <div id="contenedorBotones">

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <footer class="footer text-faded text-center py-5">
            <div class="container"><p class="m-0 small">Copyright &copy; Your Website 2023</p></div>
        </footer>
        <!-- Bootstrap core JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS -->
        <script src="js/scripts.js"></script>
        <script src="js/cancelReservation.js"></script>
    </body>
</html>
