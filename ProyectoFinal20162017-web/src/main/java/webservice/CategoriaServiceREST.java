/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import com.fpmislata.domain.Categoria;
import com.fpmislata.service.CategoriaServiceLocal;
import java.util.List;
import javax.ejb.EJB;
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
import javax.ws.rs.core.Response.Status;

/**
 *
 * @author lodiade
 */
@Path("/CategoriasService")
@Produces("application/json;charset=UTF-8")
@Consumes("application/json;charset=UTF-8")
@Stateless
public class CategoriaServiceREST {

    @EJB
    private CategoriaServiceLocal categoriaService;

    @GET
    @Produces("application/json;charset=UTF-8")
    @Path("/Categorias")
    public List<Categoria> listCategorias() {
        return categoriaService.listCategorias();
    }

    @GET
    @Produces("application/json;charset=UTF-8")
    @Consumes("application/json;charset=UTF-8")
    @Path("/Categorias/findCategoriaById/{id}") 
    public Categoria findCategoriaById(@PathParam("id") int id) {
        Categoria c = new Categoria();
        c.setId(id);
        return categoriaService.findCategoriaById(c);
    }

    @POST
    @Produces("application/json;charset=UTF-8")
    @Consumes("application/json;charset=UTF-8")
    @Path("/Categorias/add")
    public Response addCategoria(Categoria categoria) {
        try{
            categoriaService.addCategoria(categoria);
            return Response.ok().entity(categoria).
               header(HttpHeaders.CONTENT_TYPE, 
                 MediaType.APPLICATION_JSON_TYPE.withCharset("UTF-8")).
               build();
        }catch(Exception e){
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).
               header(HttpHeaders.CONTENT_TYPE, 
                 MediaType.APPLICATION_JSON_TYPE.withCharset("UTF-8")).build();
        }   
    }

    @PUT
    @Produces({"application/json; charset=UTF-8"})
    @Consumes({"application/json; charset=UTF-8"})
    @Path("/Categorias/update/{id}")    
    public Response updateCategoria(@PathParam("id") int id, Categoria categoriaModificada) {
        try {
            Categoria p = new Categoria();
            p.setId(id);
            p = categoriaService.findCategoriaById(p);
            if (p != null) {
                // Es necesario copiar los datos de la categoria modificada a p en este caso, ya que 
                // no hemos modificado claves ajenas.
                p.setNombre(categoriaModificada.getNombre());
                categoriaService.updateCategoria(p);
                return Response.ok().entity(p).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_TYPE.withCharset("UTF-8")).build();
            } else {
                return Response.status(Status.NOT_FOUND).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_TYPE.withCharset("UTF-8")).build();
            }
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_TYPE.withCharset("UTF-8")).build();
        }
    }

    @DELETE
    @Produces({"application/json; charset=UTF-8"})
    @Consumes({"application/json; charset=UTF-8"})
    @Path("/Categorias/delete/{id}")    
    public Response deleteCategoria(@PathParam("id") int id) {
        try {
            Categoria p = new Categoria();
            p.setId(id);            
            categoriaService.deleteCategoria(p);
            return Response.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_TYPE.withCharset("UTF-8")).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(404).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_TYPE.withCharset("UTF-8")).build();
        }        
    }
    
    
    
    
    
    
    
    
    

    
   
    
}
