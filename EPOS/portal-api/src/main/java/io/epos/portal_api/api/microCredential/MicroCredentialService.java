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
  public Future<String> issue(int educationModuleId, int userId) {
    JsonObject microCredential = readJsonObject("micro_credential.json");
    Future<User> userFuture = dbClient.getConnection().compose(connection -> userRepository.getUser(connection, userId).eventually(v -> connection.close()));
    Future<EducationModule> educationModuleFuture = dbClient.getConnection().compose(connection -> educationModuleRepository.getEducationModule(connection, educationModuleId));

    userFuture.onSuccess(success -> LOGGER.info(LogUtils.REGULAR_CALL_SUCCESS_MESSAGE.buildMessage("Read user", success)))
      .onFailure(throwable -> LOGGER.error(LogUtils.REGULAR_CALL_ERROR_MESSAGE.buildMessage("Read user", throwable.getMessage())));

    educationModuleFuture.onSuccess(success -> LOGGER.info(LogUtils.REGULAR_CALL_SUCCESS_MESSAGE.buildMessage("Read education module", success)))
      .onFailure(throwable -> LOGGER.error(LogUtils.REGULAR_CALL_ERROR_MESSAGE.buildMessage("Read education module", throwable.getMessage())));

    // Issue microcredential once user is fetched successfully or else fail

    return Future.all(userFuture, educationModuleFuture).compose(ar -> {
      User user = ar.resultAt(0);
      EducationModule educationModule = ar.resultAt(1);
      return issueMicroCredential(waltidClient, microCredential);
    });



  }
}
