package br.com.powerballoon.resource;

import br.com.powerballoon.dao.EmpresasDAO;
import br.com.powerballoon.model.Empresas;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("/empresas")
public class EmpresasResource {

    private EmpresasDAO empresasDAO = new EmpresasDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmpresas() {
        try {
            List<Empresas> empresas = empresasDAO.buscarTodas();
            return Response.ok(empresas).build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar empresas: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/{cnpj}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmpresaPorCnpj(@PathParam("cnpj") long cnpj) {
        try {
            Empresas empresa = empresasDAO.buscarPorCnpj(cnpj);
            if (empresa == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Erro: CNPJ da empresa não encontrado.")
                        .build();
            }
            return Response.ok(empresa).build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro no servidor: " + e.getMessage())
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response criarEmpresa(Empresas empresa) {
        try {
            if (empresa == null || !empresa.validarCnpj()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Erro: Dados inválidos fornecidos para a empresa.")
                        .build();
            }
            empresasDAO.inserir(empresa);
            return Response.status(Response.Status.CREATED)
                    .entity("Empresa criada com sucesso.")
                    .build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro no servidor: " + e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/{cnpj}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateEmpresa(@PathParam("cnpj") long cnpj, Empresas updatedEmpresa) {
        try {
            Empresas existingEmpresa = empresasDAO.buscarPorCnpj(cnpj);
            if (existingEmpresa == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Erro: CNPJ da empresa não encontrado.")
                        .build();
            }

            if (updatedEmpresa.getCnpj() != cnpj) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Erro: Não é possível editar o CNPJ da empresa.")
                        .build();
            }

            existingEmpresa.setNomEmpre(updatedEmpresa.getNomEmpre());
            existingEmpresa.setCepEmpre(updatedEmpresa.getCepEmpre());
            existingEmpresa.setPlanoEmpre(updatedEmpresa.getPlanoEmpre());
            existingEmpresa.setQuantidadeBalao(updatedEmpresa.getQuantidadeBalao());

            empresasDAO.atualizar(cnpj, existingEmpresa);
            return Response.ok("Empresa atualizada com sucesso.").build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro no servidor: " + e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{cnpj}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deletarEmpresa(@PathParam("cnpj") long cnpj) {
        try {
            Empresas existingEmpresa = empresasDAO.buscarPorCnpj(cnpj);
            if (existingEmpresa == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Erro: CNPJ da empresa não encontrado.")
                        .build();
            }
            empresasDAO.deletar(cnpj);
            return Response.ok("Empresa deletada com sucesso.").build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro no servidor: " + e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deletarTodasEmpresas() {
        try {
            empresasDAO.deletarTodas();
            return Response.ok("Todas as empresas foram deletadas com sucesso.").build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro no servidor: " + e.getMessage())
                    .build();
        }
    }
}
