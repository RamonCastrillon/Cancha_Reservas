<%-- 
    Document   : crearCuenta
    Created on : 17/11/2024, 10:40:53 a. m.
    Author     : ramon
--%>

<%@page import="CRUDI.OperacionesInfoPagina"%>
<%@page import="CRUDI.Pagina"%>
<%@page import="CRUDI.Persona"%>
<%@page import="CRUDI.OperacionesBaseDatos"%>
<%@page import="CRUDI.OperacionesPersona"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Configurar Cuenta</title>
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
                                    <span class="section-heading-lower">Actualiza tus datos</span>
                                </h2>
                                <div class="text-center">
                                    <p>Ingrese solo los campos que desea actualizar y oprima el boton actualizar</p>
                                    <form action="SvInfoPagina" method="POST">
                                        <p><label>Contraseña actual </label>
                                           <input type="password" name="contrasena" size="15" class="form-control">
                                        </p>
                                        <p><label>Contraseña nueva</label>
                                           <input type="password" name="nuevaContrasena" value="" size="15" class="form-control" placeholder="Ingresa tu contraseña (Máximo 15 caracteres)">
                                        </p>
                                        <%
                                            OperacionesInfoPagina opPage = new OperacionesInfoPagina(OperacionesBaseDatos.conectar());

                                            Pagina pagina = opPage.buscarDatosPagina();
                                        %>
                                        <p><label>Correo (Actual: <%=pagina.getCorreo()%>) </label>
                                           <input type="text" name="correo" size="15" class="form-control">
                                        </p>
                                        <p><label>Celular(Actual: <%=pagina.getNumeroCelular()%>)</label>
                                           <input type="text" name="numeroCelular" value="" size="15" class="form-control" >
                                        </p>
                                        <p><label>Valor de la reserva (Actual: <%=pagina.getValorReserva()%>) </label>
                                            <input type="text" name="valorReserva" size="15" class="form-control">
                                        </p>
                                        
                                        <button type="submit" class="btn btn-primary btn-xl mx-auto">Actualizar Datos</button>
                                    </form>
                                    <form action="opcionesAdmin.jsp" style="margin-top: 15px">
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
