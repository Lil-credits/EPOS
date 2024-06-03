package io.epos.portal_api.api.microCredential;

import io.epos.portal_api.domain.Account;
import io.epos.portal_api.domain.EducationModuleVersion;
import io.epos.portal_api.domain.IssuedCredential;
import io.epos.portal_api.domain.Membership;
import io.epos.portal_api.integration.waltid.WaltidClient;
import io.epos.portal_api.w3ih.models.openbadges.AchievementCredential;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import org.hibernate.reactive.mutiny.Mutiny;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.epos.portal_api.util.FileUtils.readJsonObject;

/**
 * Service class for handling micro-credential operations.
 */
public class MicroCredentialService {

  private static final Logger logger = LoggerFactory.getLogger(MicroCredentialService.class);

  private final MicroCredentialRepository repository;
  private final Mutiny.SessionFactory emf;
  private final WaltidClient waltidClient;

  /**
   * Constructor for MicroCredentialService.
   *
   * @param repository   The micro-credential repository.
   * @param emf          The Hibernate session factory.
   * @param waltidClient The Walt.id client for issuing credentials.
   */
  public MicroCredentialService(MicroCredentialRepository repository, Mutiny.SessionFactory emf, WaltidClient waltidClient) {
    this.repository = repository;
    this.emf = emf;
    this.waltidClient = waltidClient;
  }

  /**
   * Issues a micro-credential.
   *
   * @param issuerId               The ID of the issuer.
   * @param subjectId              The ID of the subject.
   * @param educationModuleVersionId The ID of the education module version.
   * @return A Uni that emits the invitation link to the issued credential.
   */
  public Uni<String> issueMicroCredential(int issuerId, int subjectId, int educationModuleVersionId) {
    logger.info("Issuing micro-credential for issuer ID: {}, subject ID: {}, module version ID: {}", issuerId, subjectId, educationModuleVersionId);

    Uni<Membership> issuerUni = emf.withSession(session -> repository.getMembership(session, issuerId));
    Uni<Account> subjectUni = emf.withSession(session -> repository.getAccount(session, subjectId));
    Uni<EducationModuleVersion> educationModuleVersionUni = emf.withSession(session -> repository.getEducationModuleVersion(session, educationModuleVersionId));

    return Uni.combine().all().unis(issuerUni, subjectUni, educationModuleVersionUni).asTuple()
      .onItem().transformToUni(tuple -> {
        JsonObject microCredential = createMicroCredential(tuple.getItem1(), tuple.getItem2(), tuple.getItem3());
        IssuedCredential issuedCredential = new IssuedCredential(microCredential, tuple.getItem3(), tuple.getItem1(), tuple.getItem2());

        return waltidClient.issue(microCredential)
          .onItem().transformToUni(link ->
            emf.withTransaction(session -> repository.createIssuedCredential(session, issuedCredential)
              .replaceWith(link)
            )
          );
      });
  }

  /**
   * Creates a micro-credential JSON object.
   *
   * @param issuer                The issuer's membership information.
   * @param subject               The subject's account information.
   * @param educationModuleVersion The education module version.
   * @return A JsonObject representing the micro-credential.
   */
  private JsonObject createMicroCredential(Membership issuer, Account subject, EducationModuleVersion educationModuleVersion) {
    JsonObject microCredential = readJsonObject("micro_credential.json");
    fillMicroCredentialWithIssuerData(microCredential, issuer);

    AchievementCredential achievementCredential = createAchievementCredential(issuer, subject, educationModuleVersion);
    JsonObject vc = convertAchievementCredentialToJsonObject(achievementCredential);

    microCredential.put("vc", vc);
    return microCredential;
  }

  /**
   * Fills the micro-credential JSON object with issuer data.
   *
   * @param microCredential The micro-credential JSON object.
   * @param issuer          The issuer's membership information.
   */
  private void fillMicroCredentialWithIssuerData(JsonObject microCredential, Membership issuer) {
    microCredential.put("issuerDid", issuer.getDid());
    microCredential.put("issuanceKey", issuer.getIssuanceKey());
  }

  /**
   * Creates an achievement credential from issuer, subject, and education module version data.
   *
   * @param issuer                The issuer's membership information.
   * @param subject               The subject's account information.
   * @param educationModuleVersion The education module version.
   * @return An AchievementCredential object.
   */
  private AchievementCredential createAchievementCredential(Membership issuer, Account subject, EducationModuleVersion educationModuleVersion) {
    AchievementCredential achievementCredential = new AchievementCredential();
    // Fill achievementCredential with data from subject and educationModuleVersion
    // For example:
    // achievementCredential.setSubject(subject);
    // achievementCredential.setEducationModuleVersion(educationModuleVersion);
    return achievementCredential;
  }

  /**
   * Converts an AchievementCredential object to a JsonObject.
   *
   * @param achievementCredential The AchievementCredential object.
   * @return A JsonObject representation of the achievement credential.
   */
  private JsonObject convertAchievementCredentialToJsonObject(AchievementCredential achievementCredential) {
//    String jsonString = Json.encode(achievementCredential);
//    return new JsonObject(jsonString);
    String mcTest = "{\n" +
      "    \"@context\": [\n" +
      "        \"https://www.w3.org/2018/credentials/v1\",\n" +
      "        \"https://purl.imsglobal.org/spec/ob/v3p0/context-3.0.3.json\",\n" +
      "        \"https://purl.imsglobal.org/spec/ob/v3p0/extensions.json\"\n" +
      "    ],\n" +
      "    \"id\": \"http://1edtech.edu/credentials/3732\",\n" +
      "    \"type\": [\n" +
      "        \"VerifiableCredential\",\n" +
      "        \"OpenBadgeCredential\"\n" +
      "    ],\n" +
      "    \"name\": \"1EdTech University Degree for Example Student\",\n" +
      "    \"description\": \"1EdTech University Degree Description\",\n" +
      "    \"image\": {\n" +
      "        \"id\": \"https://1edtech.edu/credentials/3732/image\",\n" +
      "        \"type\": \"Image\",\n" +
      "        \"caption\": \"1EdTech University Degree for Example Student\"\n" +
      "    },\n" +
      "    \"credentialSubject\": {\n" +
      "        \"id\": \"did:example:ebfeb1f712ebc6f1c276e12ec21\",\n" +
      "        \"type\": \"AchievementSubject\",\n" +
      "        \"activityEndDate\": \"2010-01-02T00:00:00Z\",\n" +
      "        \"activityStartDate\": \"2010-01-01T00:00:00Z\",\n" +
      "        \"creditsEarned\": 42.0,\n" +
      "        \"licenseNumber\": \"A-9320041\",\n" +
      "        \"role\": \"Major Domo\",\n" +
      "        \"source\": {\n" +
      "            \"id\": \"https://school.edu/issuers/201234\",\n" +
      "            \"type\": \"Profile\",\n" +
      "            \"name\": \"1EdTech College of Arts\"\n" +
      "        },\n" +
      "        \"term\": \"Fall\",\n" +
      "        \"identifier\": [\n" +
      "            {\n" +
      "                \"type\": \"IdentityObject\",\n" +
      "                \"identityHash\": \"student@1edtech.edu\",\n" +
      "                \"identityType\": \"emailAddress\",\n" +
      "                \"hashed\": false,\n" +
      "                \"salt\": \"not-used\"\n" +
      "            },\n" +
      "            {\n" +
      "                \"type\": \"IdentityObject\",\n" +
      "                \"identityHash\": \"somebody@gmail.com\",\n" +
      "                \"identityType\": \"emailAddress\",\n" +
      "                \"hashed\": false,\n" +
      "                \"salt\": \"not-used\"\n" +
      "            }\n" +
      "        ],\n" +
      "        \"achievement\": {\n" +
      "            \"id\": \"https://1edtech.edu/achievements/degree\",\n" +
      "            \"type\": \"Achievement\",\n" +
      "            \"alignment\": [\n" +
      "                {\n" +
      "                    \"type\": \"Alignment\",\n" +
      "                    \"targetCode\": \"degree\",\n" +
      "                    \"targetDescription\": \"1EdTech University Degree programs.\",\n" +
      "                    \"targetName\": \"1EdTech University Degree\",\n" +
      "                    \"targetFramework\": \"1EdTech University Program and Course Catalog\",\n" +
      "                    \"targetType\": \"CFItem\",\n" +
      "                    \"targetUrl\": \"https://1edtech.edu/catalog/degree\"\n" +
      "                },\n" +
      "                {\n" +
      "                    \"type\": \"Alignment\",\n" +
      "                    \"targetCode\": \"degree\",\n" +
      "                    \"targetDescription\": \"1EdTech University Degree programs.\",\n" +
      "                    \"targetName\": \"1EdTech University Degree\",\n" +
      "                    \"targetFramework\": \"1EdTech University Program and Course Catalog\",\n" +
      "                    \"targetType\": \"CTDL\",\n" +
      "                    \"targetUrl\": \"https://credentialengineregistry.org/resources/ce-98cb027b-95ef-4494-908d-6f7790ec6b6b\"\n" +
      "                }\n" +
      "            ],\n" +
      "            \"achievementType\": \"Degree\",\n" +
      "            \"creator\": {\n" +
      "                \"id\": \"https://1edtech.edu/issuers/565049\",\n" +
      "                \"type\": \"Profile\",\n" +
      "                \"name\": \"1EdTech University\",\n" +
      "                \"url\": \"https://1edtech.edu\",\n" +
      "                \"phone\": \"1-222-333-4444\",\n" +
      "                \"description\": \"1EdTech University provides online degree programs.\",\n" +
      "                \"endorsement\": [\n" +
      "                    {\n" +
      "                        \"@context\": [\n" +
      "                            \"https://www.w3.org/2018/credentials/v1\",\n" +
      "                            \"https://purl.imsglobal.org/spec/ob/v3p0/context-3.0.3.json\",\n" +
      "                            \"https://w3id.org/security/data-integrity/v1\"\n" +
      "                        ],\n" +
      "                        \"id\": \"http://1edtech.edu/endorsementcredential/3732\",\n" +
      "                        \"type\": [\n" +
      "                            \"VerifiableCredential\",\n" +
      "                            \"EndorsementCredential\"\n" +
      "                        ],\n" +
      "                        \"name\": \"SDE endorsement\",\n" +
      "                        \"issuer\": {\n" +
      "                            \"id\": \"https://accrediter.edu/issuers/565049\",\n" +
      "                            \"type\": \"Profile\",\n" +
      "                            \"name\": \"Example Accrediting Agency\"\n" +
      "                        },\n" +
      "                        \"issuanceDate\": \"2010-01-01T00:00:00Z\",\n" +
      "                        \"expirationDate\": \"2020-01-01T00:00:00Z\",\n" +
      "                        \"credentialSubject\": {\n" +
      "                            \"id\": \"https://1edtech.edu/issuers/565049\",\n" +
      "                            \"type\": \"EndorsementSubject\",\n" +
      "                            \"endorsementComment\": \"1EdTech University is in good standing\"\n" +
      "                        },\n" +
      "                        \"credentialSchema\": [\n" +
      "                            {\n" +
      "                                \"id\": \"https://purl.imsglobal.org/spec/ob/v3p0/schema/json/ob_v3p0_endorsementcredential_schema.json\",\n" +
      "                                \"type\": \"1EdTechJsonSchemaValidator2019\"\n" +
      "                            },\n" +
      "                            {\n" +
      "                                \"id\": \"https://accrediter.edu/schema/endorsementcredential.json\",\n" +
      "                                \"type\": \"1EdTechJsonSchemaValidator2019\"\n" +
      "                            }\n" +
      "                        ],\n" +
      "                        \"credentialStatus\": {\n" +
      "                            \"id\": \"https://1edtech.edu/credentials/3732/revocations\",\n" +
      "                            \"type\": \"1EdTechRevocationList\"\n" +
      "                        },\n" +
      "                        \"refreshService\": {\n" +
      "                            \"id\": \"http://1edtech.edu/credentials/3732\",\n" +
      "                            \"type\": \"1EdTechCredentialRefresh\"\n" +
      "                        },\n" +
      "                        \"proof\": [\n" +
      "                            {\n" +
      "                                \"type\": \"DataIntegrityProof\",\n" +
      "                                \"cryptosuite\": \"eddsa-rdf-2022\",\n" +
      "                                \"created\": \"2022-05-26T18:17:08Z\",\n" +
      "                                \"verificationMethod\": \"https://accrediter.edu/issuers/565049#zvPkQiUFfJrgnCRhyPkTSkgrGXbnLR15pHH5HZVYNdM4TCAwQHqG7fMeMPLtYNRnEgoV1aJdR5E61eWu5sWRYgtA\",\n" +
      "                                \"proofPurpose\": \"assertionMethod\",\n" +
      "                                \"proofValue\": \"zvPkQiUFfJrgnCRhyPkTSkgrGXbnLR15pHH5HZVYNdM4TCAwQHqG7fMeMPLtYNRnEgoV1aJdR5E61eWu5sWRYgtA\"\n" +
      "                            }\n" +
      "                        ]\n" +
      "                    },\n" +
      "                    {\n" +
      "                        \"@context\": [\n" +
      "                            \"https://www.w3.org/2018/credentials/v1\",\n" +
      "                            \"https://purl.imsglobal.org/spec/ob/v3p0/context-3.0.3.json\",\n" +
      "                            \"https://w3id.org/security/data-integrity/v1\"\n" +
      "                        ],\n" +
      "                        \"id\": \"http://1edtech.edu/endorsementcredential/3733\",\n" +
      "                        \"type\": [\n" +
      "                            \"VerifiableCredential\",\n" +
      "                            \"EndorsementCredential\"\n" +
      "                        ],\n" +
      "                        \"name\": \"SDE endorsement\",\n" +
      "                        \"issuer\": {\n" +
      "                            \"id\": \"https://state.gov/issuers/565049\",\n" +
      "                            \"type\": \"Profile\",\n" +
      "                            \"name\": \"State Department of Education\"\n" +
      "                        },\n" +
      "                        \"issuanceDate\": \"2010-01-01T00:00:00Z\",\n" +
      "                        \"expirationDate\": \"2020-01-01T00:00:00Z\",\n" +
      "                        \"credentialSubject\": {\n" +
      "                            \"id\": \"https://1edtech.edu/issuers/565049\",\n" +
      "                            \"type\": \"EndorsementSubject\",\n" +
      "                            \"endorsementComment\": \"1EdTech University is in good standing\"\n" +
      "                        },\n" +
      "                        \"credentialSchema\": [\n" +
      "                            {\n" +
      "                                \"id\": \"https://purl.imsglobal.org/spec/ob/v3p0/schema/json/ob_v3p0_endorsementcredential_schema.json\",\n" +
      "                                \"type\": \"1EdTechJsonSchemaValidator2019\"\n" +
      "                            },\n" +
      "                            {\n" +
      "                                \"id\": \"https://state.gov/schema/endorsementcredential.json\",\n" +
      "                                \"type\": \"1EdTechJsonSchemaValidator2019\"\n" +
      "                            }\n" +
      "                        ],\n" +
      "                        \"credentialStatus\": {\n" +
      "                            \"id\": \"https://state.gov/credentials/3732/revocations\",\n" +
      "                            \"type\": \"1EdTechRevocationList\"\n" +
      "                        },\n" +
      "                        \"refreshService\": {\n" +
      "                            \"id\": \"http://state.gov/credentials/3732\",\n" +
      "                            \"type\": \"1EdTechCredentialRefresh\"\n" +
      "                        },\n" +
      "                        \"proof\": [\n" +
      "                            {\n" +
      "                                \"type\": \"DataIntegrityProof\",\n" +
      "                                \"cryptosuite\": \"eddsa-rdf-2022\",\n" +
      "                                \"created\": \"2022-05-26T18:25:59Z\",\n" +
      "                                \"verificationMethod\": \"https://accrediter.edu/issuers/565049#z5bDnmSgDczXwZGya6ZjxKaxkdKxzsCMiVSsgEVWxnaWK7ZqbKnzcCd7mUKE9DQaAL2QMXP5AquPeW6W2CWrZ7jNC\",\n" +
      "                                \"proofPurpose\": \"assertionMethod\",\n" +
      "                                \"proofValue\": \"z5bDnmSgDczXwZGya6ZjxKaxkdKxzsCMiVSsgEVWxnaWK7ZqbKnzcCd7mUKE9DQaAL2QMXP5AquPeW6W2CWrZ7jNC\"\n" +
      "                            }\n" +
      "                        ]\n" +
      "                    }\n" +
      "                ],\n" +
      "                \"image\": {\n" +
      "                    \"id\": \"https://1edtech.edu/logo.png\",\n" +
      "                    \"type\": \"Image\",\n" +
      "                    \"caption\": \"1EdTech University logo\"\n" +
      "                },\n" +
      "                \"email\": \"registrar@1edtech.edu\",\n" +
      "                \"address\": {\n" +
      "                    \"type\": [\n" +
      "                        \"Address\"\n" +
      "                    ],\n" +
      "                    \"addressCountry\": \"USA\",\n" +
      "                    \"addressCountryCode\": \"US\",\n" +
      "                    \"addressRegion\": \"TX\",\n" +
      "                    \"addressLocality\": \"Austin\",\n" +
      "                    \"streetAddress\": \"123 First St\",\n" +
      "                    \"postOfficeBoxNumber\": \"1\",\n" +
      "                    \"postalCode\": \"12345\",\n" +
      "                    \"geo\": {\n" +
      "                        \"type\": \"GeoCoordinates\",\n" +
      "                        \"latitude\": 1.0,\n" +
      "                        \"longitude\": 1.0\n" +
      "                    }\n" +
      "                },\n" +
      "                \"otherIdentifier\": [\n" +
      "                    {\n" +
      "                        \"type\": \"IdentifierEntry\",\n" +
      "                        \"identifier\": \"12345\",\n" +
      "                        \"identifierType\": \"sourcedId\"\n" +
      "                    },\n" +
      "                    {\n" +
      "                        \"type\": \"IdentifierEntry\",\n" +
      "                        \"identifier\": \"67890\",\n" +
      "                        \"identifierType\": \"nationalIdentityNumber\"\n" +
      "                    }\n" +
      "                ],\n" +
      "                \"official\": \"Horace Mann\",\n" +
      "                \"parentOrg\": {\n" +
      "                    \"id\": \"did:example:123456789\",\n" +
      "                    \"type\": \"Profile\",\n" +
      "                    \"name\": \"Universal Universities\"\n" +
      "                }\n" +
      "            },\n" +
      "            \"creditsAvailable\": 36.0,\n" +
      "            \"criteria\": {\n" +
      "                \"id\": \"https://1edtech.edu/achievements/degree\",\n" +
      "                \"narrative\": \"# Degree Requirements\\nStudents must complete...\"\n" +
      "            },\n" +
      "            \"description\": \"1EdTech University Degree Description\",\n" +
      "            \"endorsement\": [\n" +
      "                {\n" +
      "                    \"@context\": [\n" +
      "                        \"https://www.w3.org/2018/credentials/v1\",\n" +
      "                        \"https://purl.imsglobal.org/spec/ob/v3p0/context-3.0.3.json\",\n" +
      "                        \"https://w3id.org/security/data-integrity/v1\"\n" +
      "                    ],\n" +
      "                    \"id\": \"http://1edtech.edu/endorsementcredential/3734\",\n" +
      "                    \"type\": [\n" +
      "                        \"VerifiableCredential\",\n" +
      "                        \"EndorsementCredential\"\n" +
      "                    ],\n" +
      "                    \"name\": \"EAA endorsement\",\n" +
      "                    \"issuer\": {\n" +
      "                        \"id\": \"https://accrediter.edu/issuers/565049\",\n" +
      "                        \"type\": \"Profile\",\n" +
      "                        \"name\": \"Example Accrediting Agency\"\n" +
      "                    },\n" +
      "                    \"issuanceDate\": \"2010-01-01T00:00:00Z\",\n" +
      "                    \"expirationDate\": \"2020-01-01T00:00:00Z\",\n" +
      "                    \"credentialSubject\": {\n" +
      "                        \"id\": \"https://1edtech.edu/issuers/565049\",\n" +
      "                        \"type\": \"EndorsementSubject\",\n" +
      "                        \"endorsementComment\": \"1EdTech University is in good standing\"\n" +
      "                    },\n" +
      "                    \"credentialSchema\": [\n" +
      "                        {\n" +
      "                            \"id\": \"https://purl.imsglobal.org/spec/ob/v3p0/schema/json/ob_v3p0_endorsementcredential_schema.json\",\n" +
      "                            \"type\": \"1EdTechJsonSchemaValidator2019\"\n" +
      "                        },\n" +
      "                        {\n" +
      "                            \"id\": \"https://accrediter.edu/schema/endorsementcredential.json\",\n" +
      "                            \"type\": \"1EdTechJsonSchemaValidator2019\"\n" +
      "                        }\n" +
      "                    ],\n" +
      "                    \"credentialStatus\": {\n" +
      "                        \"id\": \"https://1edtech.edu/credentials/3732/revocations\",\n" +
      "                        \"type\": \"1EdTechRevocationList\"\n" +
      "                    },\n" +
      "                    \"refreshService\": {\n" +
      "                        \"id\": \"http://1edtech.edu/credentials/3732\",\n" +
      "                        \"type\": \"1EdTechCredentialRefresh\"\n" +
      "                    },\n" +
      "                    \"proof\": [\n" +
      "                        {\n" +
      "                            \"type\": \"DataIntegrityProof\",\n" +
      "                            \"cryptosuite\": \"eddsa-rdf-2022\",\n" +
      "                            \"created\": \"2022-05-26T18:17:08Z\",\n" +
      "                            \"verificationMethod\": \"https://accrediter.edu/issuers/565049#zvPkQiUFfJrgnCRhyPkTSkgrGXbnLR15pHH5HZVYNdM4TCAwQHqG7fMeMPLtYNRnEgoV1aJdR5E61eWu5sWRYgtA\",\n" +
      "                            \"proofPurpose\": \"assertionMethod\",\n" +
      "                            \"proofValue\": \"zvPkQiUFfJrgnCRhyPkTSkgrGXbnLR15pHH5HZVYNdM4TCAwQHqG7fMeMPLtYNRnEgoV1aJdR5E61eWu5sWRYgtA\"\n" +
      "                        }\n" +
      "                    ]\n" +
      "                }\n" +
      "            ],\n" +
      "            \"fieldOfStudy\": \"Research\",\n" +
      "            \"humanCode\": \"R1\",\n" +
      "            \"image\": {\n" +
      "                \"id\": \"https://1edtech.edu/achievements/degree/image\",\n" +
      "                \"type\": \"Image\",\n" +
      "                \"caption\": \"1EdTech University Degree\"\n" +
      "            },\n" +
      "            \"name\": \"1EdTech University Degree\",\n" +
      "            \"otherIdentifier\": [\n" +
      "                {\n" +
      "                    \"type\": \"IdentifierEntry\",\n" +
      "                    \"identifier\": \"abde\",\n" +
      "                    \"identifierType\": \"identifier\"\n" +
      "                }\n" +
      "            ],\n" +
      "            \"resultDescription\": [\n" +
      "                {\n" +
      "                    \"id\": \"urn:uuid:f6ab24cd-86e8-4eaf-b8c6-ded74e8fd41c\",\n" +
      "                    \"type\": \"ResultDescription\",\n" +
      "                    \"alignment\": [\n" +
      "                        {\n" +
      "                            \"type\": \"Alignment\",\n" +
      "                            \"targetCode\": \"project\",\n" +
      "                            \"targetDescription\": \"Project description\",\n" +
      "                            \"targetName\": \"Final Project\",\n" +
      "                            \"targetFramework\": \"1EdTech University Program and Course Catalog\",\n" +
      "                            \"targetType\": \"CFItem\",\n" +
      "                            \"targetUrl\": \"https://1edtech.edu/catalog/degree/project\"\n" +
      "                        }\n" +
      "                    ],\n" +
      "                    \"allowedValue\": [\n" +
      "                        \"D\",\n" +
      "                        \"C\",\n" +
      "                        \"B\",\n" +
      "                        \"A\"\n" +
      "                    ],\n" +
      "                    \"name\": \"Final Project Grade\",\n" +
      "                    \"requiredValue\": \"C\",\n" +
      "                    \"resultType\": \"LetterGrade\"\n" +
      "                },\n" +
      "                {\n" +
      "                    \"id\": \"urn:uuid:a70ddc6a-4c4a-4bd8-8277-cb97c79f40c5\",\n" +
      "                    \"type\": \"ResultDescription\",\n" +
      "                    \"alignment\": [\n" +
      "                        {\n" +
      "                            \"type\": \"Alignment\",\n" +
      "                            \"targetCode\": \"project\",\n" +
      "                            \"targetDescription\": \"Project description\",\n" +
      "                            \"targetName\": \"Final Project\",\n" +
      "                            \"targetFramework\": \"1EdTech University Program and Course Catalog\",\n" +
      "                            \"targetType\": \"CFItem\",\n" +
      "                            \"targetUrl\": \"https://1edtech.edu/catalog/degree/project\"\n" +
      "                        }\n" +
      "                    ],\n" +
      "                    \"allowedValue\": [\n" +
      "                        \"D\",\n" +
      "                        \"C\",\n" +
      "                        \"B\",\n" +
      "                        \"A\"\n" +
      "                    ],\n" +
      "                    \"name\": \"Final Project Grade\",\n" +
      "                    \"requiredLevel\": \"urn:uuid:d05a0867-d0ad-4b03-bdb5-28fb5d2aab7a\",\n" +
      "                    \"resultType\": \"RubricCriterionLevel\",\n" +
      "                    \"rubricCriterionLevel\": [\n" +
      "                        {\n" +
      "                            \"id\": \"urn:uuid:d05a0867-d0ad-4b03-bdb5-28fb5d2aab7a\",\n" +
      "                            \"type\": \"RubricCriterionLevel\",\n" +
      "                            \"alignment\": [\n" +
      "                                {\n" +
      "                                    \"type\": \"Alignment\",\n" +
      "                                    \"targetCode\": \"project\",\n" +
      "                                    \"targetDescription\": \"Project description\",\n" +
      "                                    \"targetName\": \"Final Project\",\n" +
      "                                    \"targetFramework\": \"1EdTech University Program and Course Catalog\",\n" +
      "                                    \"targetType\": \"CFRubricCriterionLevel\",\n" +
      "                                    \"targetUrl\": \"https://1edtech.edu/catalog/degree/project/rubric/levels/mastered\"\n" +
      "                                }\n" +
      "                            ],\n" +
      "                            \"description\": \"The author demonstrated...\",\n" +
      "                            \"level\": \"Mastered\",\n" +
      "                            \"name\": \"Mastery\",\n" +
      "                            \"points\": \"4\"\n" +
      "                        },\n" +
      "                        {\n" +
      "                            \"id\": \"urn:uuid:6b84b429-31ee-4dac-9d20-e5c55881f80e\",\n" +
      "                            \"type\": \"RubricCriterionLevel\",\n" +
      "                            \"alignment\": [\n" +
      "                                {\n" +
      "                                    \"type\": \"Alignment\",\n" +
      "                                    \"targetCode\": \"project\",\n" +
      "                                    \"targetDescription\": \"Project description\",\n" +
      "                                    \"targetName\": \"Final Project\",\n" +
      "                                    \"targetFramework\": \"1EdTech University Program and Course Catalog\",\n" +
      "                                    \"targetType\": \"CFRubricCriterionLevel\",\n" +
      "                                    \"targetUrl\": \"https://1edtech.edu/catalog/degree/project/rubric/levels/basic\"\n" +
      "                                }\n" +
      "                            ],\n" +
      "                            \"description\": \"The author demonstrated...\",\n" +
      "                            \"level\": \"Basic\",\n" +
      "                            \"name\": \"Basic\",\n" +
      "                            \"points\": \"4\"\n" +
      "                        }\n" +
      "                    ]\n" +
      "                },\n" +
      "                {\n" +
      "                    \"id\": \"urn:uuid:b07c0387-f2d6-4b65-a3f4-f4e4302ea8f7\",\n" +
      "                    \"type\": \"ResultDescription\",\n" +
      "                    \"name\": \"Project Status\",\n" +
      "                    \"resultType\": \"Status\"\n" +
      "                }\n" +
      "            ],\n" +
      "            \"specialization\": \"Computer Science Research\",\n" +
      "            \"tag\": [\n" +
      "                \"research\",\n" +
      "                \"computer science\"\n" +
      "            ]\n" +
      "        },\n" +
      "        \"image\": {\n" +
      "            \"id\": \"https://1edtech.edu/credentials/3732/image\",\n" +
      "            \"type\": \"Image\",\n" +
      "            \"caption\": \"1EdTech University Degree for Example Student\"\n" +
      "        },\n" +
      "        \"narrative\": \"There is a final project report and source code evidence.\",\n" +
      "        \"result\": [\n" +
      "            {\n" +
      "                \"type\": [\n" +
      "                    \"Result\"\n" +
      "                ],\n" +
      "                \"alignment\": [\n" +
      "                    {\n" +
      "                        \"type\": \"Alignment\",\n" +
      "                        \"targetCode\": \"project\",\n" +
      "                        \"targetDescription\": \"Project description\",\n" +
      "                        \"targetName\": \"Final Project\",\n" +
      "                        \"targetFramework\": \"1EdTech University Program and Course Catalog\",\n" +
      "                        \"targetType\": \"CFItem\",\n" +
      "                        \"targetUrl\": \"https://1edtech.edu/catalog/degree/project/result/1\"\n" +
      "                    }\n" +
      "                ],\n" +
      "                \"resultDescription\": \"urn:uuid:f6ab24cd-86e8-4eaf-b8c6-ded74e8fd41c\",\n" +
      "                \"value\": \"A\"\n" +
      "            },\n" +
      "            {\n" +
      "                \"type\": [\n" +
      "                    \"Result\"\n" +
      "                ],\n" +
      "                \"achievedLevel\": \"urn:uuid:d05a0867-d0ad-4b03-bdb5-28fb5d2aab7a\",\n" +
      "                \"alignment\": [\n" +
      "                    {\n" +
      "                        \"type\": \"Alignment\",\n" +
      "                        \"targetCode\": \"project\",\n" +
      "                        \"targetDescription\": \"Project description\",\n" +
      "                        \"targetName\": \"Final Project\",\n" +
      "                        \"targetFramework\": \"1EdTech University Program and Course Catalog\",\n" +
      "                        \"targetType\": \"CFItem\",\n" +
      "                        \"targetUrl\": \"https://1edtech.edu/catalog/degree/project/result/1\"\n" +
      "                    }\n" +
      "                ],\n" +
      "                \"resultDescription\": \"urn:uuid:f6ab24cd-86e8-4eaf-b8c6-ded74e8fd41c\"\n" +
      "            },\n" +
      "            {\n" +
      "                \"type\": [\n" +
      "                    \"Result\"\n" +
      "                ],\n" +
      "                \"resultDescription\": \"urn:uuid:f6ab24cd-86e8-4eaf-b8c6-ded74e8fd41c\",\n" +
      "                \"status\": \"Completed\"\n" +
      "            }\n" +
      "        ]\n" +
      "    },\n" +
      "    \"endorsement\": [\n" +
      "        {\n" +
      "            \"@context\": [\n" +
      "                \"https://www.w3.org/2018/credentials/v1\",\n" +
      "                \"https://purl.imsglobal.org/spec/ob/v3p0/context-3.0.3.json\",\n" +
      "                \"https://w3id.org/security/data-integrity/v1\"\n" +
      "            ],\n" +
      "            \"id\": \"http://1edtech.edu/endorsementcredential/3735\",\n" +
      "            \"type\": [\n" +
      "                \"VerifiableCredential\",\n" +
      "                \"EndorsementCredential\"\n" +
      "            ],\n" +
      "            \"name\": \"EAA endorsement\",\n" +
      "            \"issuer\": {\n" +
      "                \"id\": \"https://accrediter.edu/issuers/565049\",\n" +
      "                \"type\": \"Profile\",\n" +
      "                \"name\": \"Example Accrediting Agency\"\n" +
      "            },\n" +
      "            \"issuanceDate\": \"2010-01-01T00:00:00Z\",\n" +
      "            \"expirationDate\": \"2020-01-01T00:00:00Z\",\n" +
      "            \"credentialSubject\": {\n" +
      "                \"id\": \"https://1edtech.edu/issuers/565049\",\n" +
      "                \"type\": \"EndorsementSubject\",\n" +
      "                \"endorsementComment\": \"1EdTech University is in good standing\"\n" +
      "            },\n" +
      "            \"credentialSchema\": [\n" +
      "                {\n" +
      "                    \"id\": \"https://purl.imsglobal.org/spec/ob/v3p0/schema/json/ob_v3p0_endorsementcredential_schema.json\",\n" +
      "                    \"type\": \"1EdTechJsonSchemaValidator2019\"\n" +
      "                },\n" +
      "                {\n" +
      "                    \"id\": \"https://accrediter.edu/schema/endorsementcredential.json\",\n" +
      "                    \"type\": \"1EdTechJsonSchemaValidator2019\"\n" +
      "                }\n" +
      "            ],\n" +
      "            \"credentialStatus\": {\n" +
      "                \"id\": \"https://1edtech.edu/credentials/3732/revocations\",\n" +
      "                \"type\": \"1EdTechRevocationList\"\n" +
      "            },\n" +
      "            \"refreshService\": {\n" +
      "                \"id\": \"http://1edtech.edu/credentials/3732\",\n" +
      "                \"type\": \"1EdTechCredentialRefresh\"\n" +
      "            },\n" +
      "            \"proof\": [\n" +
      "                {\n" +
      "                    \"type\": \"DataIntegrityProof\",\n" +
      "                    \"cryptosuite\": \"eddsa-rdf-2022\",\n" +
      "                    \"created\": \"2022-05-26T18:17:08Z\",\n" +
      "                    \"verificationMethod\": \"https://accrediter.edu/issuers/565049#zvPkQiUFfJrgnCRhyPkTSkgrGXbnLR15pHH5HZVYNdM4TCAwQHqG7fMeMPLtYNRnEgoV1aJdR5E61eWu5sWRYgtA\",\n" +
      "                    \"proofPurpose\": \"assertionMethod\",\n" +
      "                    \"proofValue\": \"zvPkQiUFfJrgnCRhyPkTSkgrGXbnLR15pHH5HZVYNdM4TCAwQHqG7fMeMPLtYNRnEgoV1aJdR5E61eWu5sWRYgtA\"\n" +
      "                }\n" +
      "            ]\n" +
      "        }\n" +
      "    ],\n" +
      "    \"evidence\": [\n" +
      "        {\n" +
      "            \"id\": \"https://1edtech.edu/credentials/3732/evidence/1\",\n" +
      "            \"type\": \"Evidence\",\n" +
      "            \"narrative\": \"# Final Project Report \\n This project was ...\",\n" +
      "            \"name\": \"Final Project Report\",\n" +
      "            \"description\": \"This is the final project report.\",\n" +
      "            \"genre\": \"Research\",\n" +
      "            \"audience\": \"Department\"\n" +
      "        },\n" +
      "        {\n" +
      "            \"id\": \"https://github.com/somebody/project\",\n" +
      "            \"type\": \"Evidence\",\n" +
      "            \"name\": \"Final Project Code\",\n" +
      "            \"description\": \"This is the source code for the final project app.\",\n" +
      "            \"genre\": \"Research\",\n" +
      "            \"audience\": \"Department\"\n" +
      "        }\n" +
      "    ],\n" +
      "    \"issuer\": {\n" +
      "        \"id\": \"https://1edtech.edu/issuers/565049\",\n" +
      "        \"type\": \"Profile\",\n" +
      "        \"name\": \"1EdTech University\",\n" +
      "        \"url\": \"https://1edtech.edu\",\n" +
      "        \"phone\": \"1-222-333-4444\",\n" +
      "        \"description\": \"1EdTech University provides online degree programs.\",\n" +
      "        \"endorsement\": [\n" +
      "            {\n" +
      "                \"@context\": [\n" +
      "                    \"https://www.w3.org/2018/credentials/v1\",\n" +
      "                    \"https://purl.imsglobal.org/spec/ob/v3p0/context-3.0.3.json\",\n" +
      "                    \"https://w3id.org/security/data-integrity/v1\"\n" +
      "                ],\n" +
      "                \"id\": \"http://1edtech.edu/endorsementcredential/3736\",\n" +
      "                \"type\": [\n" +
      "                    \"VerifiableCredential\",\n" +
      "                    \"EndorsementCredential\"\n" +
      "                ],\n" +
      "                \"name\": \"EAA endorsement\",\n" +
      "                \"issuer\": {\n" +
      "                    \"id\": \"https://accrediter.edu/issuers/565049\",\n" +
      "                    \"type\": \"Profile\",\n" +
      "                    \"name\": \"Example Accrediting Agency\"\n" +
      "                },\n" +
      "                \"issuanceDate\": \"2010-01-01T00:00:00Z\",\n" +
      "                \"expirationDate\": \"2020-01-01T00:00:00Z\",\n" +
      "                \"credentialSubject\": {\n" +
      "                    \"id\": \"https://1edtech.edu/issuers/565049\",\n" +
      "                    \"type\": \"EndorsementSubject\",\n" +
      "                    \"endorsementComment\": \"1EdTech University is in good standing\"\n" +
      "                },\n" +
      "                \"credentialSchema\": [\n" +
      "                    {\n" +
      "                        \"id\": \"https://purl.imsglobal.org/spec/ob/v3p0/schema/json/ob_v3p0_endorsementcredential_schema.json\",\n" +
      "                        \"type\": \"1EdTechJsonSchemaValidator2019\"\n" +
      "                    },\n" +
      "                    {\n" +
      "                        \"id\": \"https://accrediter.edu/schema/endorsementcredential.json\",\n" +
      "                        \"type\": \"1EdTechJsonSchemaValidator2019\"\n" +
      "                    }\n" +
      "                ],\n" +
      "                \"credentialStatus\": {\n" +
      "                    \"id\": \"https://1edtech.edu/credentials/3732/revocations\",\n" +
      "                    \"type\": \"1EdTechRevocationList\"\n" +
      "                },\n" +
      "                \"refreshService\": {\n" +
      "                    \"id\": \"http://1edtech.edu/credentials/3732\",\n" +
      "                    \"type\": \"1EdTechCredentialRefresh\"\n" +
      "                },\n" +
      "                \"proof\": [\n" +
      "                    {\n" +
      "                        \"type\": \"DataIntegrityProof\",\n" +
      "                        \"cryptosuite\": \"eddsa-rdf-2022\",\n" +
      "                        \"created\": \"2022-05-26T18:17:08Z\",\n" +
      "                        \"verificationMethod\": \"https://accrediter.edu/issuers/565049#zvPkQiUFfJrgnCRhyPkTSkgrGXbnLR15pHH5HZVYNdM4TCAwQHqG7fMeMPLtYNRnEgoV1aJdR5E61eWu5sWRYgtA\",\n" +
      "                        \"proofPurpose\": \"assertionMethod\",\n" +
      "                        \"proofValue\": \"zvPkQiUFfJrgnCRhyPkTSkgrGXbnLR15pHH5HZVYNdM4TCAwQHqG7fMeMPLtYNRnEgoV1aJdR5E61eWu5sWRYgtA\"\n" +
      "                    }\n" +
      "                ]\n" +
      "            }\n" +
      "        ],\n" +
      "        \"image\": {\n" +
      "            \"id\": \"https://1edtech.edu/logo.png\",\n" +
      "            \"type\": \"Image\",\n" +
      "            \"caption\": \"1EdTech University logo\"\n" +
      "        },\n" +
      "        \"email\": \"registrar@1edtech.edu\",\n" +
      "        \"address\": {\n" +
      "            \"type\": [\n" +
      "                \"Address\"\n" +
      "            ],\n" +
      "            \"addressCountry\": \"USA\",\n" +
      "            \"addressCountryCode\": \"US\",\n" +
      "            \"addressRegion\": \"TX\",\n" +
      "            \"addressLocality\": \"Austin\",\n" +
      "            \"streetAddress\": \"123 First St\",\n" +
      "            \"postOfficeBoxNumber\": \"1\",\n" +
      "            \"postalCode\": \"12345\",\n" +
      "            \"geo\": {\n" +
      "                \"type\": \"GeoCoordinates\",\n" +
      "                \"latitude\": 1.0,\n" +
      "                \"longitude\": 1.0\n" +
      "            }\n" +
      "        },\n" +
      "        \"otherIdentifier\": [\n" +
      "            {\n" +
      "                \"type\": \"IdentifierEntry\",\n" +
      "                \"identifier\": \"12345\",\n" +
      "                \"identifierType\": \"sourcedId\"\n" +
      "            },\n" +
      "            {\n" +
      "                \"type\": \"IdentifierEntry\",\n" +
      "                \"identifier\": \"67890\",\n" +
      "                \"identifierType\": \"nationalIdentityNumber\"\n" +
      "            }\n" +
      "        ],\n" +
      "        \"official\": \"Horace Mann\",\n" +
      "        \"parentOrg\": {\n" +
      "            \"id\": \"did:example:123456789\",\n" +
      "            \"type\": \"Profile\",\n" +
      "            \"name\": \"Universal Universities\"\n" +
      "        }\n" +
      "    },\n" +
      "    \"issuanceDate\": \"2010-01-01T00:00:00Z\",\n" +
      "    \"expirationDate\": \"2030-01-01T00:00:00Z\",\n" +
      "    \"credentialSchema\": [\n" +
      "        {\n" +
      "            \"id\": \"https://purl.imsglobal.org/spec/ob/v3p0/schema/json/ob_v3p0_achievementcredential_schema.json\",\n" +
      "            \"type\": \"1EdTechJsonSchemaValidator2019\"\n" +
      "        }\n" +
      "    ],\n" +
      "    \"credentialStatus\": {\n" +
      "        \"id\": \"https://1edtech.edu/credentials/3732/revocations\",\n" +
      "        \"type\": \"1EdTechRevocationList\"\n" +
      "    },\n" +
      "    \"refreshService\": {\n" +
      "        \"id\": \"http://1edtech.edu/credentials/3732\",\n" +
      "        \"type\": \"1EdTechCredentialRefresh\"\n" +
      "    }\n" +
      "}";;
    return new JsonObject(mcTest);
  }
}
