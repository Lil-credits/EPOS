package io.epos.portal_api.api.microCredential;

import io.smallrye.mutiny.Uni;
import org.hibernate.reactive.mutiny.Mutiny;

public class MicroCredentialService {

  private final MicroCredentialRepository repository;
  private final Mutiny.SessionFactory emf;

  public MicroCredentialService(MicroCredentialRepository repository, Mutiny.SessionFactory emf) {
    this.repository = repository;
    this.emf = emf;
  }

  public Uni<String> issueMicroCredential(int userId, int microCredentialVersionId) {
    return Uni.createFrom().item("MicroCredential issued");
  }
}
