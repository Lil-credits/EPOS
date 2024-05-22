package io.epos.portal_api.api.product;

import io.epos.portal_api.domain.Product;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.ext.web.RoutingContext;
import org.hibernate.reactive.mutiny.Mutiny;

import java.util.List;

public class ProductHandler {

  private final Mutiny.SessionFactory emf;
  public ProductHandler(Mutiny.SessionFactory emf) {
    this.emf = emf;

  }
  public Uni<Product> getProduct(RoutingContext ctx) {
    long id = Long.parseLong(ctx.pathParam("id"));
    return emf.withSession(session -> session
        .find(Product.class, id))
      .onItem().ifNull().continueWith(Product::new);
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
