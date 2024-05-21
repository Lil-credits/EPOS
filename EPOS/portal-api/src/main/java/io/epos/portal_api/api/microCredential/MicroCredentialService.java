package io.epos.portal_api.api.microCredential;

import io.epos.portal_api.api.educationModule.EducationModuleRepository;
import io.epos.portal_api.api.educationModule.EducationModuleService;
import io.epos.portal_api.api.educationModule.model.GetEducationModuleResponse;
import io.epos.portal_api.api.user.UserRepository;
import io.epos.portal_api.domain.EducationModule;
import io.epos.portal_api.domain.EducationModuleVersion;
import io.epos.portal_api.domain.IssuedMicroCredential;
import io.epos.portal_api.domain.User;
import io.epos.portal_api.integration.waltid.WaltidClient;
import io.epos.portal_api.util.LogUtils;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.JsonObject;
import io.vertx.json.schema.JsonSchema;
import io.vertx.sqlclient.Pool;

import static io.epos.portal_api.util.FileUtils.readJsonObject;
import static io.epos.portal_api.util.FileUtils.readJsonSchema;

public class MicroCredentialService {
  private static final Logger LOGGER = LoggerFactory.getLogger(MicroCredentialService.class);

  private final Pool dbClient;
  private final WaltidClient waltidClient;
  private final MicroCredentialRepository microCredentialRepository;

  private final EducationModuleRepository educationModuleRepository;

  private final UserRepository userRepository;

  public MicroCredentialService(Pool dbClient, MicroCredentialRepository microCredentialRepository, WaltidClient waltidClient, EducationModuleRepository educationModuleRepository, UserRepository userRepository) {
    this.dbClient = dbClient;
    this.microCredentialRepository = microCredentialRepository;
    this.waltidClient = waltidClient;
    this.educationModuleRepository = educationModuleRepository;
    this.userRepository = userRepository;

  }
  public Future<String> issueMicroCredential(WaltidClient waltidClient, JsonObject microCredential) {
    Promise<String> promise = Promise.promise();
    // ipv onComplete kan je ook eventueel .onSuccess en .onFailure gebruiken
    waltidClient.issue(microCredential).onComplete(ar -> {
      if (ar.succeeded()) {
        String qrCodeLink = ar.result();
        promise.complete(qrCodeLink);
      } else {
        Throwable cause = ar.cause();
        promise.fail(cause);
      }
    });
    return promise.future();
  }

  public JsonObject createMicroCredentialJson(User user, EducationModule educationModule, EducationModuleVersion educationModuleVersion) {
    JsonObject microCredential = readJsonObject("micro_credential.json");
    // weird workaround: type jwk does somehow not work only local with walt.id issuer api
    user.getIssuanceKey().put("type", "local");
    microCredential.put("issuanceKey", user.getIssuanceKey());
    JsonObject vc = microCredential.getJsonObject("vc");

    JsonObject issuer = vc.getJsonObject("issuer");
    issuer.put("id", user.getDid());
    vc.put("issuer", issuer);
    JsonObject credentialSubject = vc.getJsonObject("credentialSubject");
    credentialSubject.put("issuer", user.getName());
    credentialSubject.put("educationModule", educationModule.getName());
    credentialSubject.put("educationModuleVersion", educationModuleVersion.getVersion());
    credentialSubject.put("description", educationModuleVersion.getDescription());
    credentialSubject.put("attributes", educationModuleVersion.getAttributes());
    credentialSubject.put("requiredAchievements", educationModuleVersion.getRequiredAchievements());
    credentialSubject.put("skills", educationModuleVersion.getSkills());
    credentialSubject.put("issuerOrganization", educationModule.getTeamOrganization());
    credentialSubject.put("issuerTeamFaculty", educationModule.getTeamFaculty());
    credentialSubject.put("issuerTeam", educationModule.getTeam());
    vc.put("credentialSubject", credentialSubject);
    microCredential.put("vc", vc);
    return microCredential;
  }
  public Future<String> issue(int educationModuleId, int userId) {

    Promise<String> promise = Promise.promise();
    // we dont have a proper auth yet, so we use a temporary issuer id
    final int TEMP_ISSUER_ID = 2;
    Future<User> issuerFuture = dbClient.withConnection(connection -> userRepository.getUser(connection, TEMP_ISSUER_ID));
    Future<User> receiverFuture = dbClient.withConnection(connection -> userRepository.getUser(connection, userId));
    Future<EducationModule> educationModuleFuture = dbClient.withConnection(connection -> educationModuleRepository.getEducationModule(connection, educationModuleId));

    Future<String> qrCodeLinkFuture = Future.all(issuerFuture, receiverFuture, educationModuleFuture).compose(ar -> {
      User user = ar.resultAt(0);
      EducationModule educationModule = ar.resultAt(2);
      // get the first version of the education module since we don't have a versioning system yet
      EducationModuleVersion educationModuleVersion = educationModule.getEducationModuleVersions().get(0);
      JsonObject microCredentialJson = createMicroCredentialJson(user, educationModule, educationModuleVersion);
      return issueMicroCredential(waltidClient, microCredentialJson);
    });

//    // this fuction stores the issued micro credential in the database andis called when qrcodelinkfuture is succes
//    Future<IssuedMicroCredential> issuedMicroCredentialFuture = qrCodeLinkFuture.compose(qrCodeLink -> {
//      IssuedMicroCredential issuedMicroCredential = new IssuedMicroCredential(TEMP_ISSUER_ID, userId, educationModuleId, new JsonObject());
//    });
//
//    // now i want return the qrcodelink after it is succesfully retrieved and stored in the database
//    return Future.all(qrCodeLinkFuture, issuedMicroCredentialFuture).map(ar -> ar.resultAt(0));
    qrCodeLinkFuture.onSuccess(qrCodeLink -> {
      LOGGER.info("Micro credential issued successfully");
      promise.complete(qrCodeLink);
    }).onFailure(throwable -> {
      LOGGER.error("Failed to issue micro credential", throwable);
      promise.fail(new RuntimeException(throwable.getMessage()));
    });
    return promise.future();
  }
}
