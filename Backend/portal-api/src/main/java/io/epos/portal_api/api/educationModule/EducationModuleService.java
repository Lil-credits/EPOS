package io.epos.portal_api.api.educationModule;

import io.epos.portal_api.api.educationModule.model.CreateEducationModuleRequest;
import io.epos.portal_api.domain.EducationModule;
import io.epos.portal_api.domain.EducationModuleVersion;
import io.epos.portal_api.util.LogUtils;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.sqlclient.Pool;

public class EducationModuleService {
  private static final Logger LOGGER = LoggerFactory.getLogger(EducationModuleService.class);

  private final Pool dbClient;
  private final EducationModuleRepository educationModuleRepository;

  public EducationModuleService(Pool dbClient,
                                EducationModuleRepository educationModuleRepository) {
    this.dbClient = dbClient;
    this.educationModuleRepository = educationModuleRepository;
  }
  public Future<EducationModule> create(CreateEducationModuleRequest createEducationModuleRequest) {
    // create a EducationModule object from the request body
    EducationModule educationModule = new EducationModule();
    educationModule.setName(createEducationModuleRequest.getCourseName());
    educationModule.setImageUrl(createEducationModuleRequest.getImageUrl());
    // temporary team id
    educationModule.setTeamId(2);

    // create a EducationModuleVersion object from the request body
    EducationModuleVersion educationModuleVersion = new EducationModuleVersion();
    educationModuleVersion.setAttributes(createEducationModuleRequest.getAttributes());
    educationModuleVersion.setDescription(createEducationModuleRequest.getDescription());
    educationModuleVersion.setSkills(createEducationModuleRequest.getSkills());
    educationModuleVersion.setRequiredAchievements(createEducationModuleRequest.getRequiredAchievements());



    // use the db client to store the education module and the education module version in the database

    return dbClient.withTransaction(connection -> {
        // Insert EducationModule
        Future<EducationModule> insertModuleFuture = educationModuleRepository.insertEducationModule(connection, educationModule);
        // Create EducationModuleVersion
        Future<EducationModuleVersion> createVersionFuture = insertModuleFuture.compose(module -> {
          // hardcoding version for now
          educationModuleVersion.setVersion(2);
          educationModuleVersion.setEducationModuleID(module.getId());
          // Call insert function for EducationModuleVersion
          return educationModuleRepository.insertEducationModuleVersion(connection, educationModuleVersion);
        });

        // Combine both futures into one
        return Future.all(insertModuleFuture, createVersionFuture)
          .map(compositeFuture -> educationModule);
      })
      .onSuccess(success -> LOGGER.info(LogUtils.REGULAR_CALL_SUCCESS_MESSAGE.buildMessage("Create one education module", success)))
      .onFailure(throwable -> LOGGER.error(LogUtils.REGULAR_CALL_ERROR_MESSAGE.buildMessage("Create one education module", throwable.getMessage())));
  }
}
