package br.com.powerballoon.resource;

import br.com.powerballoon.dao.BalaoDAO;
import br.com.powerballoon.model.Balao;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("/baloes")
public class BalaoResource {

    private BalaoDAO balaoDAO = new BalaoDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBaloes() {
        try {
            List<Balao> baloes = balaoDAO.buscarTodos();
            return Response.ok(baloes).build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar balões: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/{rastreadorBalao}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBalaoPorRastreador(@PathParam("rastreadorBalao") int rastreadorBalao) {
        try {
            Balao balao = balaoDAO.buscarPorRastreador(rastreadorBalao);
            if (balao == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Balão não encontrado.")
                        .build();
            }
            return Response.ok(balao).build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar balão: " + e.getMessage())
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response criarBalao(Balao balao) {
        try {
            balaoDAO.inserir(balao);
            return Response.status(Response.Status.CREATED)
                    .entity("Balão criado com sucesso.")
                    .build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao criar balão: " + e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/{rastreadorBalao}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response atualizarBalao(@PathParam("rastreadorBalao") int rastreadorBalao, Balao balao) {
        try {
            Balao existingBalao = balaoDAO.buscarPorRastreador(rastreadorBalao);
            if (existingBalao == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Balão não encontrado.")
                        .build();
            }

            existingBalao.setGas(balao.getGas());
            existingBalao.setMatBalao(balao.getMatBalao());
            existingBalao.setCaboBalao(balao.getCaboBalao());

            balaoDAO.atualizar(existingBalao);
            return Response.ok("Balão atualizado com sucesso.").build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar balão: " + e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{rastreadorBalao}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deletarBalao(@PathParam("rastreadorBalao") int rastreadorBalao) {
        try {
            Balao existingBalao = balaoDAO.buscarPorRastreador(rastreadorBalao);
            if (existingBalao == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Balão não encontrado.")
                        .build();
            }

            balaoDAO.deletar(rastreadorBalao);
            return Response.ok("Balão deletado com sucesso.").build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao deletar balão: " + e.getMessage())
                    .build();
        }
    }
}
