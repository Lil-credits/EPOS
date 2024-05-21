import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.api.tasks.testing.logging.TestLogEvent.*

plugins {
  java
  application
  id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "io.epos"
version = "1.0.0-SNAPSHOT"

repositories {
  mavenCentral()
}

val vertxVersion = "4.5.7"
val junitJupiterVersion = "5.9.1"

val mainVerticleName = "io.epos.portal_api.verticle.MainVerticle"
val launcherClassName = "io.vertx.core.Launcher"

val watchForChange = "src/**/*"
val doOnChange = "${projectDir}/gradlew classes"

application {
  mainClass.set(launcherClassName)
}

dependencies {
  implementation(platform("io.vertx:vertx-stack-depchain:$vertxVersion"))
  implementation("io.vertx:vertx-web-client")
  implementation("io.vertx:vertx-web-validation")
  implementation("io.vertx:vertx-sql-client-templates")
  implementation("io.vertx:vertx-web")
  implementation("io.vertx:vertx-pg-client")
  implementation("io.vertx:vertx-json-schema")
  // https://mvnrepository.com/artifact/io.vertx/vertx-health-check
  implementation("io.vertx:vertx-health-check:4.5.7")
  // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
  implementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")
// https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core
  implementation("com.fasterxml.jackson.core:jackson-core:2.17.0")
// https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-annotations
  implementation("com.fasterxml.jackson.core:jackson-annotations:2.17.0")
  implementation("com.ongres.scram:client:2.1")
  // https://mvnrepository.com/artifact/org.flywaydb/flyway-core
  implementation("org.flywaydb:flyway-core:10.11.0")
// https://mvnrepository.com/artifact/org.postgresql/postgresql
  implementation("org.postgresql:postgresql:42.7.3")
  // https://mvnrepository.com/artifact/org.hibernate.reactive/hibernate-reactive-core
  implementation("org.hibernate.reactive:hibernate-reactive-core:2.3.0.Final")
  // https://mvnrepository.com/artifact/org.hibernate.validator/hibernate-validator
  implementation("org.hibernate.validator:hibernate-validator:8.0.1.Final")

// https://mvnrepository.com/artifact/io.smallrye.reactive/smallrye-mutiny-vertx-core
  implementation("io.smallrye.reactive:smallrye-mutiny-vertx-core:3.12.0")
// https://mvnrepository.com/artifact/org.projectlombok/lombok
  compileOnly("org.projectlombok:lombok:1.18.32")


// https://mvnrepository.com/artifact/org.flywaydb/flyway-database-postgresql
  runtimeOnly("org.flywaydb:flyway-database-postgresql:10.11.0")
  testImplementation("io.vertx:vertx-junit5")
  testImplementation("org.junit.jupiter:junit-jupiter:$junitJupiterVersion")
}

java {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<ShadowJar> {
  archiveClassifier.set("fat")
  manifest {
    attributes(mapOf("Main-Verticle" to mainVerticleName))
  }
  mergeServiceFiles()
}

tasks.withType<Test> {
  useJUnitPlatform()
  testLogging {
    events = setOf(PASSED, SKIPPED, FAILED)
  }
}

tasks.withType<JavaExec> {
  args = listOf("run", mainVerticleName, "--redeploy=$watchForChange", "--launcher-class=$launcherClassName", "--on-redeploy=$doOnChange")
}
