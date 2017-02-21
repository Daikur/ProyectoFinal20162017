<%-- 
    Document   : listarClientes
    Created on : 21-feb-2017, 22:04:44
    Author     : David
--%>

<%@page import="com.fpmislata.domain.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Listado de Clientes</h1>
        <h2>Versión con controlador</h2>
        
	
	<br/>
	<br/>

	<table border="1">
		<tr>
                    <th>Id</th>
                    <th>Nombre</th>
                    <th>Apellidos</th>
                    <th>NIF</th>
                    <th>Direccion</th>
                    <th>Poblacion</th>
                    <th>Provincia</th>
                    <th>Codigo Postal</th>
                    <th>Telefono</th>
 
		</tr>

                <%
                ArrayList<Cliente> lista = (ArrayList) session.getAttribute("listaClientes");
                for(Cliente cliente : lista){
                    
                    int id = cliente.getId_cliente();
                    String nombre = cliente.getNombre();
                    String apellidos = cliente.getApellidos();
                    String nif =cliente.getNif();
                    String direccion = cliente.getDireccion();                   
                    String poblacion = cliente.getPoblacion();
                    String provincia = cliente.getProvincia();
                    String codigoPostal = cliente.getCodigopostal();
                    String telefono = cliente.getTelefono();

                %>                
		<tr>
                    <td><%=id%></a></td>
                    <td><%=nombre%></td>
                    <td><%=apellidos%></td>
                    <td><%=nif%></td>
                    <td><%=direccion%></td>
                    <td><%=poblacion%></td>
                    <td><%=provincia%></td>
                    <td><%=codigoPostal%></td>
                    <td><%=telefono%></td>
                    <td><a href="ModificarCategoria?accion=editar&id=<%=id%>">Modificar</td>
                    <td><a href="EliminarCategoria?id=<%=id%>">Eliminar</a></td>
                </tr>
                <% } %>
	</table>
	<br>
	<a href="index.jsp">Regresar al Inicio</a>
    </body>
</html>
