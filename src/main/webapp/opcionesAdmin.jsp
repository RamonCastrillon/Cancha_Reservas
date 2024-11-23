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
    <title>Administrador</title>
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
    <body id="consultarReservas">
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
        <form action="SvCerrarSesion">
            <button type="submit" class="logout-btn">Cerrar Sesión</button>
        </form>
    <header>
        <h1 class="site-heading text-center text-faded d-none d-lg-block">
            <span class="site-heading-lower">Consultar Reservas</span>
        </h1>
    </header>
    
    <nav class="navbar navbar-expand-lg navbar-dark py-lg-4" id="mainNav">
        <div class="container">
            <a class="navbar-brand text-uppercase fw-bold d-lg-none" href="hacerReserva.jsp">El Diamante</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mx-auto">
                    <li class="nav-item px-lg-4"><a class="nav-link text-uppercase" href="opcionesAdmin.jsp">Consultar Reservas</a></li>
                    <li class="nav-item px-lg-4"><a class="nav-link text-uppercase active" href="configuracionesPagina.jsp">Administrar Pagina</a></li>
                    <!-- <li class="nav-item px-lg-4"><a class="nav-link text-uppercase" href="#">Administrar Página</a></li>
                    <li class="nav-item px-lg-4"><a class="nav-link text-uppercase" href="#">Administrar Usuarios</a></li>
                    <li class="nav-item px-lg-4"><a class="nav-link text-uppercase" href="#">Consultar Retroalimentación</a></li> --> 
                </ul>
            </div>
        </div>
    </nav>
    
    <section class="page-section">
        <div class="container">
            <div class="about-heading-content" style="margin-top: 30px">
                <div class="row">
                    <div class="col-lg-12">
                            <div class="bg-faded rounded p-5">
                                <h2 class="section-heading mb-4">
                                    <span class="section-heading-lower">Búsqueda de Reservas</span>
                                </h2>

                            
                            <div class="row" style="margin-top: 30px">
                                    <div class="col-md-6">
                                        <h3>Búsqueda por Fecha</h3>
                                        <form id="busquedaFecha" class="mb-3">
                                            <div class="form-group">
                                                <label for="fechaReserva">Fecha:</label>
                                                <input type="date" id="fechaReserva" name="fechaReserva" class="form-control" required>
                                            </div>
                                            <div class="form-group" style="margin-bottom: 36px">
                                                <button type="button" onclick="buscarPorFecha('dia')" class="btn btn-primary btn-xl mt-2">Buscar por Día</button>
                                                <button type="button" onclick="buscarPorFecha('semana')" class="btn btn-primary btn-xl mt-2">Buscar por Semana</button>
                                                <button type="button" onclick="buscarPorFecha('mes')" class="btn btn-primary btn-xl mt-2" >Buscar por Mes</button>
                                            </div>
                                        </form>
                                        
                                        <h3 class="mt-3">Búsqueda por Apellido</h3>
                                        <form id="busquedaApellido" class="mb-3">
                                            <div class="form-group">
                                                <label for="primerApellido">Primer Apellido:</label>
                                                <input type="text" id="primerApellido" name="primerApellido" class="form-control" required>
                                            </div>
                                            <button type="button" onclick="buscarPorApellido()" class="btn btn-primary btn-xl mt-2" style="margin-bottom: 20px">Buscar por Apellido</button>
                                        </form>
                                        
                                        <h3 class="mt-3">Búsqueda por ID Reserva</h3>
                                        <form id="busquedaIdReserva" class="mb-3">
                                            <div class="form-group">
                                                <label for="idReserva">ID de Reserva:</label>
                                                <input type="number" id="idReserva" name="idReserva" class="form-control" required>
                                            </div>
                                            <button type="button" onclick="buscarPorIdReserva()" class="btn btn-primary btn-xl mt-2">Buscar por ID Reserva</button>
                                        </form>
                                    </div>
                                    <div class="col-md-6">
                                        <h3>Búsqueda por Nombre</h3>
                                        <form id="busquedaNombre" class="mb-3" >
                                            <div class="form-group">
                                                <label for="nombreUsuario">Nombre de Usuario:</label>
                                                <input type="text" id="nombreUsuario" name="nombreUsuario" class="form-control" required>
                                            </div>
                                            <button type="button" onclick="buscarPorNombre()" class="btn btn-primary btn-xl mt-2" style="margin-bottom: 20px">Buscar por Nombre</button>
                                        </form>

                                        <h3 class="mt-3">Búsqueda por ID</h3>
                                        <form id="busquedaId" class="mb-3">
                                            <div class="form-group">
                                                <label for="idUsuario">ID de Usuario:</label>
                                                <input type="number" id="idUsuario" name="idUsuario" class="form-control" required>
                                            </div>
                                            <button type="button" onclick="buscarPorIdUsuario()" class="btn btn-primary btn-xl mt-2">Buscar por ID Usuario</button>
                                        </form>
                                    </div>
                                </div>
                            

                            <div id="resultadoConsulta" class="mt-4">
                                <!-- Aquí se mostrarán los resultados de la búsqueda -->
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
    
    </body>
</html>
