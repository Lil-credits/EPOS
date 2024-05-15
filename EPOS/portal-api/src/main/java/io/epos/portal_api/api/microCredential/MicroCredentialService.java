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
  public Future<String> issue(int educationModuleId, int userId) {
    JsonObject microCredential = readJsonObject("micro_credential.json");

    return dbClient.withTransaction(
      connection -> {
        Future<User> userFuture = userRepository.getUser(connection, userId);
        userFuture.compose(user -> {
          microCredential.put("user", new JsonObject()
            .put("id", user.getId())
            .put("name", user.getName())
            .put("did", user.getDid())
            .put("issuanceKey", user.getIssuanceKey())
          );
          return educationModuleRepository.getEducationModule(connection, educationModuleId);
        }).onSuccess(educationModule -> {
          microCredential.put("educationModule", new JsonObject()
            .put("id", educationModule.getId())
            .put("name", educationModule.getName())
            .put("teamId", educationModule.getTeamId())
            .put("imageUrl", educationModule.getImageUrl())
          );
        }).onFailure(throwable -> {
          LOGGER.error(LogUtils.REGULAR_CALL_ERROR_MESSAGE.buildMessage("Enrich micro credential", throwable.getMessage()));
        });

        Future.all(educationModuleFuture, userFuture).onComplete(ar -> {
          if (ar.succeeded()) {
            EducationModule educationModule = ar.;
            User user = ar.resultAt(1);

            microCredential.put("educationModule", new JsonObject()
              .put("id", educationModule.getId())
              .put("name", educationModule.getName())
              .put("teamId", educationModule.getTeamId())
              .put("imageUrl", educationModule.getImageUrl())
            );

            microCredential.put("user", new JsonObject()
              .put("id", user.getId())
              .put("name", user.getName())
              .put("did", user.getDid())
              .put("issuanceKey", user.getIssuanceKey())
            );

            LOGGER.info(LogUtils.REGULAR_CALL_SUCCESS_MESSAGE.buildMessage("Enrich micro credential", microCredential));
          } else {
            Throwable cause = ar.cause();
            LOGGER.error(LogUtils.REGULAR_CALL_ERROR_MESSAGE.buildMessage("Enrich micro credential", cause.getMessage()));
          }
        }

      }
    )


    //futures chainen:
    // concurrently read education module  & user information
    // if both are successful, enrich the micro credential with education module information and version
    // issue credential on waltid
    // store issued micro credential information in the database
    // if one of them fails, return an error message

    // enrich the micro credential with education module information and version



    // hardcoding issuer since we don't have a way to get it from the request
    int issuerId = 1;
    IssuedMicroCredential issuedMicroCredential = new IssuedMicroCredential(issuerId, userId, educationModuleId, microCredential);


//    // store issued micro credential information in the database
//    microCredentialRepository.insertIssuedMicroCredential(
//      issuedMicroCredential).onComplete(ar -> {
//      if (ar.succeeded()) {
//        LOGGER.info(LogUtils.REGULAR_CALL_SUCCESS_MESSAGE.buildMessage("Issue micro credential", ar.result()));
//      } else {
//        LOGGER.error(LogUtils.REGULAR_CALL_ERROR_MESSAGE.buildMessage("Issue micro credential", ar.cause().getMessage()));
//      }
//    });
    Promise<String> promise = Promise.promise();
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
}
