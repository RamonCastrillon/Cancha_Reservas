<%-- 
    Document   : index
    Created on : 10/11/2024, 8:24:57 p. m.
    Author     : ramon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
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
        <section class="page-section clearfix">
            <div class="container">
                <div class="intro">
                    <img class="intro-img img-fluid mb-3 mb-lg-0 rounded" src="assets/img/indice.jpeg" alt="..." />
                    <div class="intro-text left-0 text-center bg-faded p-5 rounded">
                        <h2 class="section-heading mb-4">
                            <span class="section-heading-upper">Tu pasión, nuestro campo</span>
                            <span class="section-heading-lower">Vive el fútbol</span>
                        </h2>
                        <p class="mb-3">En Cancha Sintética El Diamante, cada jugada es un momento inolvidable. Desde emocionantes torneos hasta celebraciones únicas, este es el lugar perfecto para disfrutar, competir y compartir con amigos y familia. Ven y sé parte de una experiencia deportiva llena de calidad, diversión y comunidad. ¡Te esperamos en Guarne, Antioquia!</p>
                    </div>
                </div>
            </div>
        </section>
        <section class="page-section cta">
            <div class="container">
                <div class="row">
                    <div class="col-xl-9 mx-auto">
                        <div class="cta-inner bg-faded text-center rounded">
                            <h2 class="section-heading mb-4">
                                <span class="section-heading-upper">Nuestra promesa</span>
                                <span class="section-heading-lower">Para tí</span>
                            </h2>
                            <p class="mb-0">Cuando visitas Cancha Sintética El Diamante, te ofrecemos un ambiente acogedor, atención cercana y servicios diseñados para que disfrutes tu experiencia al máximo. Trabajamos con dedicación para que cada visita sea memorable. Si hay algo que podamos mejorar, no dudes en decírnoslo. ¡Estamos aquí para ti!</p>
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
