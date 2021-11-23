package rest;

import com.google.gson.Gson;
import utils.HttpUtils;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Path("crypto")
public class CryptoResource {
    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    Gson gson = new Gson();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("user")
    public String getCrypto() throws IOException, ExecutionException, InterruptedException {
       String result = gson.toJson(HttpUtils.fetchData("https://api.coinstats.app/public/v1/tickers?exchange=yobit&pair=BTC-USD"));
        System.out.println(result);
       return result;
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("user")
    @Path("/all")
    public String getAll() throws IOException, ExecutionException, InterruptedException {
        return gson.toJson(HttpUtils.fetchDataParallel());
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)

    @Path("/tworemoteservers")
    public String getFromTwo() throws IOException, ExecutionException, InterruptedException {
        return gson.toJson(HttpUtils.combinedEnds());
    }


}
