package io.epos.portal_api.api.product;

import io.epos.portal_api.api.common.RequestParameters;
import io.epos.portal_api.api.common.ResponseBuilder;
import io.epos.portal_api.domain.Product;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.ext.web.RoutingContext;
import org.hibernate.reactive.mutiny.Mutiny;

import java.util.List;
import java.util.NoSuchElementException;

public class ProductHandler {

  private final Mutiny.SessionFactory emf;
  public ProductHandler(Mutiny.SessionFactory emf) {
    this.emf = emf;

  }
  public void getProduct(RoutingContext ctx) {
    String id = ctx.pathParam(RequestParameters.ID_PARAMETER);

    getProductFromDatabase(Long.parseLong(id))
      .subscribe().with(
        product -> ResponseBuilder.buildOkResponse(ctx, product),
        error -> ResponseBuilder.buildErrorResponse(ctx, error)
      );
  }

  public Uni<Product> getProductFromDatabase(long id) {
    return emf.withSession(session ->
        session.find(Product.class, id))
      .onItem().ifNull().failWith(new NoSuchElementException("No product with id " + id));
  }


  public Uni<List<Product>> listProducts(RoutingContext ctx) {
    return emf.withSession(session -> session
      .createQuery("from Product", Product.class)
      .getResultList());
  }
  public Uni<Product> createProduct(RoutingContext ctx) {
    Product product = new Product(ctx.body().asJsonObject());
    return emf.withSession(session -> session.
      persist(product)
      .call(session::flush)
      .replaceWith(product));
  }
}
