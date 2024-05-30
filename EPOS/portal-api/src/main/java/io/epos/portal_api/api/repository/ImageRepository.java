package io.epos.portal_api.api.repository;

import io.epos.portal_api.domain.Image;
import io.smallrye.mutiny.Uni;
import org.hibernate.reactive.mutiny.Mutiny;

public class ImageRepository {
  public Uni<Image> create(Mutiny.Session session, Image image) {
    return session.persist(image).chain(session::flush)
      .replaceWith(image);
  }
}
