package io.epos.portal_api.api.microCredential;

import io.epos.portal_api.api.educationModule.EducationModuleService;
import io.epos.portal_api.api.educationModule.model.GetEducationModuleResponse;
import io.epos.portal_api.domain.EducationModule;
import io.epos.portal_api.domain.IssuedMicroCredential;
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
  private final MicroCredentialRepository microCredentialRepository;
  private final WaltidClient waltidClient;

  public MicroCredentialService(Pool dbClient, MicroCredentialRepository microCredentialRepository, WaltidClient waltidClient) {
    this.dbClient = dbClient;
    this.microCredentialRepository = microCredentialRepository;
    this.waltidClient = waltidClient;
  }
  public Future<String> issue(int educationModuleId, int userId) {
    JsonObject validMicroCredential = readJsonObject("micro_credential.json");

    // enrich the micro credential with
    Promise<String> promise = Promise.promise();
    waltidClient.issue(validMicroCredential).onComplete(ar -> {
      if (ar.succeeded()) {
        String qrCodeLink = ar.result();
        promise.complete(qrCodeLink);
      } else {
        Throwable cause = ar.cause();
        promise.fail(cause);
      }
    });

    // retrieve education module information with latest version
    EducationModule educationModule = new EducationModule();
    IssuedMicroCredential issuedMicroCredential = new IssuedMicroCredential(1, userId, educationModuleId, validMicroCredential);

    // store issued microcredential information in the database
    microCredentialRepository.insertIssuedMicroCredential(
      educationModuleId, userId, validMicroCredential).onComplete(ar -> {
      if (ar.succeeded()) {
        LOGGER.info(LogUtils.REGULAR_CALL_SUCCESS_MESSAGE.buildMessage("Issue micro credential", ar.result()));
      } else {
        LOGGER.error(LogUtils.REGULAR_CALL_ERROR_MESSAGE.buildMessage("Issue micro credential", ar.cause().getMessage()));
      }
    });

    return promise.future();
  }
}
