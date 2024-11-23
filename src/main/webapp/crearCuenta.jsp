<%-- 
    Document   : crearCuenta
    Created on : 17/11/2024, 10:40:53 a. m.
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
        <title>Crear Cuenta</title>
        <link rel="icon" type="image/x-icon" href="assets/img/logo1.png" />
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Raleway:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
    </head>
    <body id="crearCuenta">
        
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

        <section class="page-section about-heading">
            <div class="container contenedor-crear-cuenta">
                <div class="about-heading-content" style="margin-top: 70px">
                    <div class="row">
                        <div class="col-xl-9 custom-col-xl-9 col-lg-10 mx-auto">
                            <div class="bg-faded rounded p-5">
                                <h2 class="section-heading mb-4 text-center">
                                    <span class="section-heading-lower">Crea tu cuenta</span>
                                </h2>
                                <div class="text-center">
                                    
                                    <form action="SvUsuario" method="POST">
                                        <p><label>Documento de Identidad: </label>
                                           <input type="text" name="dni" size="15" class="form-control">
                                        </p>
                                        <p><label>Contraseña:</label>
                                           <input type="password" name="contrasena" value="" size="15" class="form-control" placeholder="Ingresa tu contraseña (Máximo 15 caracteres)">
                                        </p>
                                        <p><label>Primer Nombre: </label>
                                            <input type="text" name="nombre" size="15" class="form-control">
                                        </p>
                                        <p><label>Primer Apellido: </label>
                                           <input type="text" name="primerApellido" size="15" class="form-control">
                                        </p>
                                        <p><label>Segundo Apellido: </label>
                                           <input type="text" name="segundoApellido" size="15" class="form-control">
                                        </p>
                                        
                                        <p>
                                            <label for="fechaNacimiento">Fecha de Nacimiento</label>
                                            <input type="date" id="fechaNacimiento" name="fechaNacimiento" class="form-control fecha">
                                        </p>
                                        
                                        <p><label>Número de celular: </label>
                                           <input type="text" name="celular" size="15" class="form-control">
                                        </p>
                                        <p><label>Correo electrónico: </label>
                                           <input type="text" name="correoElectronico" size="15" class="form-control">
                                        </p>
                                        <button type="submit" class="btn btn-primary btn-xl mx-auto">Crear Usuario</button>
                                    </form>
                                   <form action="store.jsp" style="margin-top: 15px">
                                        <button type="submit" class="btn btn-primary btn-xl mx-auto">Volver</button>
                                    </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
    </body>
</html>
