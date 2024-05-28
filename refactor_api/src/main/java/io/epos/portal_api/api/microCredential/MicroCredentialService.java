package io.epos.portal_api.api.microCredential;

import io.epos.portal_api.domain.Account;
import io.epos.portal_api.domain.EducationModuleVersion;
import io.epos.portal_api.domain.IssuedCredential;
import io.epos.portal_api.domain.Membership;
import io.epos.portal_api.integration.waltid.WaltidClient;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import org.hibernate.reactive.mutiny.Mutiny;

import static io.epos.portal_api.util.FileUtils.readJsonObject;

public class MicroCredentialService {

  private final MicroCredentialRepository repository;
  private final Mutiny.SessionFactory emf;

  private final WaltidClient waltidClient;

  public MicroCredentialService(MicroCredentialRepository repository, Mutiny.SessionFactory emf, WaltidClient waltidClient) {
    this.repository = repository;
    this.emf = emf;
    this.waltidClient = waltidClient;
  }

  public JsonObject createMicroCredential(Membership issuer, Membership subject, EducationModuleVersion educationModuleVersion) {
    JsonObject microCredential = readJsonObject("micro_credential.json");
    return microCredential;
  }
  public Uni<String> issueMicroCredential(int issuerId, int subjectId, int educationModuleVersionId) {
    Uni<Membership> issuerUni = emf.withSession(session -> repository.getMembership(session, issuerId));
    Uni<Account> subjectUni = emf.withSession(session -> repository.getAccount(session, subjectId));
    Uni<EducationModuleVersion> educationModuleVersionUni = emf.withSession(session -> repository.getEducationModuleVersion(session, educationModuleVersionId));

    return Uni.combine().all().unis(issuerUni, subjectUni, educationModuleVersionUni).asTuple()
      .onItem().transformToUni(tuple -> {
        IssuedCredential issuedCredential = new IssuedCredential();
        issuedCredential.setIssuerMembership(tuple.getItem1());
        issuedCredential.setSubjectAccount(tuple.getItem2());
        issuedCredential.setEducationModuleVersion(tuple.getItem3());

        JsonObject microCredential = readJsonObject("micro_credential.json"); // Assume this method reads and constructs the JsonObject
        return waltidClient.issue(microCredential)
          .onItem().transformToUni(link ->
            emf.withTransaction(session -> repository.createIssuedCredential(session, issuedCredential)
              .replaceWith(link)
            )
          );
      });
  }
}

