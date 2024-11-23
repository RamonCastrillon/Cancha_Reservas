<%-- 
    Document   : about.jsp
    Created on : 21/11/2024, 10:47:13 p. m.
    Author     : ramon
--%>

<%@page import="CRUDI.OperacionesInfoPagina"%>
<%@page import="CRUDI.Pagina"%>
<%@page import="CRUDI.OperacionesBaseDatos"%>
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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"> <!--- Importamos la libreria pa los iconos donde van las cosas -->
    </head>
    <body>
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
        <section class="page-section about-heading">
            <div class="container">
                <img class="img-fluid rounded about-heading-img mb-3 mb-lg-0" src="assets/img/contactanos.jpg" alt="..." />
                <div class="about-heading-content">
                    <div class="row">
                        <div class="col-xl-9 col-lg-10 mx-auto">
                            <div class="bg-faded rounded p-5">
                                <h2 class="section-heading mb-4">
                                    <span class="section-heading-upper">Conéctate con Nosotros</span>
                                    <span class="section-heading-lower">Redes Sociales de El Diamante</span>
                                </h2>
                                <%
                                    OperacionesInfoPagina opPage = new OperacionesInfoPagina(OperacionesBaseDatos.conectar());
                                    Pagina pagina = opPage.buscarDatosPagina();
                                    
                                %>
                                <div class="text-center">
                                    <a href="https://www.instagram.com/diamantesintetica?igsh=Y284Mzd6NmhrNm05" target="_blank" class="social-icon">
                                        <!-- target blank hace que se abra una pestaña nueva donde se abre el link todo lo que va dentro del a es clickeable-->
                                        <i class="fab fa-instagram fa-2x"></i> <!-- icono de instagram con su multiplicador de tamaño -->
                                    </a>
                                    <a href="https://mail.google.com/mail/?view=cm&fs=1&to=<%=pagina.getCorreo()%>" target="_blank" class="social-icon" style="margin-left: 20px;">
                                        <i class="fas fa-envelope fa-2x"></i> 
                                    </a>
                                    
                                    <a href="https://wa.me/57<%=pagina.getNumeroCelular()%>" target="_blank" class="social-icon" style="margin-left: 20px;">
                                        <i class="fab fa-whatsapp fa-2x"></i> 
                                    </a>
                                   
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

