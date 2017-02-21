package webservice;

import com.fpmislata.domain.Categoria;
import com.fpmislata.domain.Cliente;
import com.fpmislata.service.ClienteServiceLocal;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author David
 */
@Path("/ClienteService")
@Produces("application/json;charset=UTF-8")
@Consumes("application/json;charset=UTF-8")
@Stateless
public class ClienteService {

    @EJB
    private ClienteServiceLocal clienteService;

    @GET
    @Produces("application/json;charset=UTF-8")
    @Path("/Clientes")
    public List listClientes() {
        return clienteService.listClientes();
    }

    @GET
    @Produces("application/json;charset=UTF-8")
    @Consumes("application/json;charset=UTF-8")
    @Path("/Clientes/findClienteById/{id}")
    public Cliente findClienteById(@PathParam("id") int id) {
        Cliente c = new Cliente();
        c.setId_cliente(id);
        return clienteService.findClienteById(c);
    }

    @POST
    @Produces("application/json;charset=UTF-8")
    @Consumes("application/json;charset=UTF-8")
    @Path("/Clientes/add")
    public Response addCliente(Cliente cliente) {
        try {
            clienteService.addCliente(cliente);
            return Response.ok().entity(cliente).
                    header(HttpHeaders.CONTENT_TYPE,
                            MediaType.APPLICATION_JSON_TYPE.withCharset("UTF-8")).
                    build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    header(HttpHeaders.CONTENT_TYPE,
                            MediaType.APPLICATION_JSON_TYPE.withCharset("UTF-8")).build();
        }
    }

    @PUT
    @Produces({"application/json; charset=UTF-8"})
    @Consumes({"application/json; charset=UTF-8"})
    @Path("/Clientes/update/{id}")
    public Response updateCliente(@PathParam("id") int id, Cliente clienteModificado) {
        try {
            Cliente c = new Cliente();
            c.setId_cliente(id);
            c = clienteService.findClienteById(c);
            if (c != null) {
                c.setNombre(clienteModificado.getNombre());
                c.setApellidos(clienteModificado.getApellidos());
                c.setNif(clienteModificado.getNif());
                c.setDireccion(clienteModificado.getDireccion());
                c.setPoblacion(clienteModificado.getPoblacion());
                c.setProvincia(clienteModificado.getProvincia());
                c.setCodigopostal(clienteModificado.getCodigopostal());
                c.setTelefono(clienteModificado.getTelefono());
                clienteService.updateCliente(c);
                return Response.ok().entity(c).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_TYPE.withCharset("UTF-8")).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_TYPE.withCharset("UTF-8")).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_TYPE.withCharset("UTF-8")).build();
        }
    }

    @DELETE
    @Produces({"application/json; charset=UTF-8"})
    @Consumes({"application/json; charset=UTF-8"})
    @Path("/Clientes/delete/{id}")
    public Response deleteCliente(@PathParam("id") int id) {
        try {
            Cliente c = new Cliente();
            c.setId_cliente(id);
            clienteService.deleteCliente(c);
            return Response.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_TYPE.withCharset("UTF-8")).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(404).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_TYPE.withCharset("UTF-8")).build();
        }
    }

}
