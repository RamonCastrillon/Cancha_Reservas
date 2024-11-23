<%-- 
    Document   : store
    Created on : 17/11/2024, 12:50:27 p. m.
    Author     : ramon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>El Diamante - Cancha Sintética</title>
        <link rel="icon" type="image/x-icon" href="assets/img/logo1.png" />
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Raleway:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
    </head>
    <body>
        <%-- Verificar si hay un mensaje de éxito --%>
        <% String mensajeExito = (String) request.getAttribute("mensajeExito"); %>
        <% if (mensajeExito != null) { %>
            <div class="alert alert-success" role="alert">
                <strong>¡Éxito!</strong> <%= mensajeExito %>
            </div>
        <% } %>
        <% String mensajeError = (String) request.getAttribute("mensajeError"); %>
        <% if (mensajeError != null) { %>
            <div class="alert alert-danger" role="alert">
                <strong>Error: </strong> <%= mensajeError%>
            </div>   
        <% } %>
        <header>
            <h1 class="site-heading text-center text-faded d-none d-lg-block">
                <span class="site-heading-upper text-primary mb-3">Cancha Sintética</span>
                <span class="site-heading-lower">El Diamante</span>
            </h1>
        </header>
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-dark py-lg-4" id="mainNav">
            <div class="container">
                <a class="navbar-brand text-uppercase fw-bold d-lg-none" href="index.jsp">El diamante</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mx-auto">
                        <li class="nav-item px-lg-4"><a class="nav-link text-uppercase" href="index.jsp">Inicio</a></li>
                        <li class="nav-item px-lg-4"><a class="nav-link text-uppercase" href="products.html">Servicios</a></li>
                        <li class="nav-item px-lg-4"><a class="nav-link text-uppercase" href="store.jsp">Horarios y Reservas</a></li>
                        <li class="nav-item px-lg-4"><a class="nav-link text-uppercase" href="combos.html">Combos y ofertas</a></li>
                        <li class="nav-item px-lg-4"><a class="nav-link text-uppercase" href="about.jsp">Contáctanos</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <section class="page-section cta">
            <div class="container">
                <div class="row">
                    <div class="col-xl-9 mx-auto">
                        <div class="cta-inner bg-faded text-center rounded">
                            <h2 class="section-heading mb-5">
                                <span class="section-heading-lower">Horarios</span>
                            </h2>
                            <ul class="list-unstyled list-hours mb-5 text-left mx-auto">
                                <li class="list-unstyled-item list-hours-item d-flex">
                                    Domingo
                                    <span class="ms-auto">11:00 AM - 10:00 PM</span>
                                </li>
                                <li class="list-unstyled-item list-hours-item d-flex">
                                    Lunes
                                    <span class="ms-auto">8:00 AM - 11:59 PM</span>
                                </li>
                                <li class="list-unstyled-item list-hours-item d-flex">
                                    Martes
                                    <span class="ms-auto">8:00 AM - 11:59 PM</span>
                                </li>
                                <li class="list-unstyled-item list-hours-item d-flex">
                                    Miércoles
                                    <span class="ms-auto">8:00 AM - 11:59 PM</span>
                                </li>
                                <li class="list-unstyled-item list-hours-item d-flex">
                                    Jueves
                                    <span class="ms-auto">8:00 AM - 11:59 PM</span>
                                </li>
                                <li class="list-unstyled-item list-hours-item d-flex">
                                    Viernes
                                    <span class="ms-auto">8:00 AM - 11:59 PM</span>
                                </li>
                                <li class="list-unstyled-item list-hours-item d-flex">
                                    Sábado
                                    <span class="ms-auto">11:00 AM - 10:00 PM</span>
                                </li>
                            </ul>
                            <p class="address mb-5">
                                <em>
                                    <strong>Calle 41A #48-151(130)</strong>
                                    <br />
                                    Guarne, Antioquia (054050)
                                </em>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <section class="page-section about-heading">
            <div class="container">
                <img class="img-fluid rounded about-heading-img mb-3 mb-lg-0" src="assets/img/reserva.jpeg" alt="..." />
                <div class="about-heading-content">
                    <div class="row">
                        <div class="col-xl-9 col-lg-10 mx-auto">
                            <div class="bg-faded rounded p-5 text-center">
                                <h2 class="section-heading mb-4 text-center">
                                    <span class="section-heading-upper">Anímate</span>
                                    <span class="section-heading-lower">Reserva con nosotros</span>
                                </h2>
                                <form action="SvInicioSesion" method="Post">
                                    <p class="mb-0 text-center">Ingresa tu id:<input type="text" name="idUsuario" value="" size="15" style="margin-left: 100px;margin-bottom: 15px;"/></p>
                                    <p class="mb-0 text-center">Ingresa tu contraseña:<input type="password" name="contrasena" value="" size="15" style="margin-left: 33px;margin-bottom: 15px"/></p>
                                    <button type="submit" class="btn btn-primary btn-xl mx-auto">Ingresar</button>
                                </form>
                                    <p class="mb-0 text-center" style="margin-top: 10px">¿No tienes cuenta?
                                    </p>
                                    <p class="mb-0 text-center">
                                        <a href="crearCuenta.jsp">
                                            <span>Crea una</span>
                                        </a>
                                    </p>
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
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
    </body>
</html>
