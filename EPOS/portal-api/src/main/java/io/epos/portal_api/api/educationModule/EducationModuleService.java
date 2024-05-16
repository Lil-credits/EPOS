package io.epos.portal_api.api.educationModule;

import io.epos.portal_api.api.educationModule.model.CreateEducationModuleRequest;
import io.epos.portal_api.api.educationModule.model.GetAllEducationModuleResponse;
import io.epos.portal_api.api.educationModule.model.GetEducationModuleResponse;
import io.epos.portal_api.domain.EducationModule;
import io.epos.portal_api.domain.EducationModuleVersion;
import io.epos.portal_api.util.LogUtils;
import io.epos.portal_api.util.QueryUtils;
import io.vertx.core.Future;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.sqlclient.Pool;

import java.util.List;
import java.util.stream.Collectors;

public class EducationModuleService {
  private static final Logger LOGGER = LoggerFactory.getLogger(EducationModuleService.class);

  private final Pool dbClient;
  private final EducationModuleRepository educationModuleRepository;

  public EducationModuleService(Pool dbClient,
                                EducationModuleRepository educationModuleRepository) {
    this.dbClient = dbClient;
    this.educationModuleRepository = educationModuleRepository;
  }
  public Future<GetEducationModuleResponse> readOne(int id) {
    return dbClient.withTransaction(
        connection -> educationModuleRepository.getEducationModule(connection, id)
          .map(GetEducationModuleResponse::new)
      )
      .onSuccess(success -> LOGGER.info(LogUtils.REGULAR_CALL_SUCCESS_MESSAGE.buildMessage("Read one education module", success)))
      .onFailure(throwable -> LOGGER.error(LogUtils.REGULAR_CALL_ERROR_MESSAGE.buildMessage("Read one education module", throwable.getMessage())));
  }

  public Future<GetAllEducationModuleResponse> readAll(String p, String l){
    return dbClient.withTransaction(
        connection -> {
          final int page = QueryUtils.getPage(p);
          final int limit = QueryUtils.getLimit(l);
          final int offset = QueryUtils.getOffset(page, limit);

          return educationModuleRepository.count(connection)
            .flatMap(total ->
              educationModuleRepository.getAllEducationModules(connection, limit, offset)
                .map(result -> {
                  final List<GetEducationModuleResponse> educationModules = result.stream()
                    .map(GetEducationModuleResponse::new)
                    .collect(Collectors.toList());

                  return new GetAllEducationModuleResponse(total, page, limit, educationModules);
                })
            );
        })
      .onSuccess(success -> LOGGER.info(LogUtils.REGULAR_CALL_SUCCESS_MESSAGE.buildMessage("Read all books", success.getEducationModules())))
      .onFailure(throwable -> LOGGER.error(LogUtils.REGULAR_CALL_ERROR_MESSAGE.buildMessage("Read all books", throwable.getMessage())));
  }

  public Future<EducationModuleVersion> create(CreateEducationModuleRequest createEducationModuleRequest) {
    // create a EducationModule object from the request body
    EducationModule educationModule = new EducationModule();
    educationModule.setName(createEducationModuleRequest.getCourseName());
    educationModule.setImageUrl(createEducationModuleRequest.getImageUrl());
    // temporary team id
    educationModule.setTeamId(1);

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
          .map(compositeFuture -> {
            // combine results of both futures into a response object

            return createVersionFuture.result();});
      })
      .onSuccess(success -> LOGGER.info(LogUtils.REGULAR_CALL_SUCCESS_MESSAGE.buildMessage("Create one education module", success)))
      .onFailure(throwable -> LOGGER.error(LogUtils.REGULAR_CALL_ERROR_MESSAGE.buildMessage("Create one education module", throwable.getMessage())));
  }


}
