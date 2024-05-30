//package io.epos.portal_api.api.microCredential.dto;
//
//import io.epos.portal_api.w3ih.models.openbadges.AchievementCredential;
//import io.vertx.core.json.JsonObject;
//
//public class MicroCredentialMapper {
//  public AchievementCredential toAchievementCredential(BaseCredential baseCredential) {
//    //{"courseName":"Nederlands","studyYear":2021,"description":"Dutch language course","imageUrl":"url to image","requiredAchievements":["A2","B1"],"skills":["listening","speaking","reading","1"],"attributes":{"EC":1,"language":"Dutch","EQF":1}}
//
//    AchievementCredential achievementCredential = new AchievementCredential();
//    achievementCredential.setName(microCredential.getString("courseName"));
//    achievementCredential.setDescription(microCredential.getString("description"));
//
//      .setDescription(microCredential.getString("description"))
//      .setCriteria(microCredential.getString("criteria"))
//      .setIssuer(microCredential.getString("issuer"))
//      .setIssuedOn(microCredential.getString("issuedOn"))
//      .setExpires(microCredential.getString("expires"))
//      .setBadge(microCredential.getString("badge"))
//      .setRecipient(microCredential.getString("recipient"));
//    return new AchievementCredential()
//      .setId(microCredential.getId())
//      .setName(microCredential.getName())
//      .setDescription(microCredential.getDescription())
//      .setCriteria(microCredential.getCriteria())
//      .setIssuer(microCredential.getIssuer())
//      .setIssuedOn(microCredential.getIssuedOn())
//      .setExpires(microCredential.getExpires())
//      .setBadge(microCredential.getBadge())
//      .setRecipient(microCredential.getRecipient());
//  }
//}
